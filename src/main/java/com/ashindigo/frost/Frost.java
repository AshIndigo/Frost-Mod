package com.ashindigo.frost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.ashindigo.frost.api.FrostTier;
import com.ashindigo.frost.api.FrostTierManager;
import com.ashindigo.frost.entities.EntityHailSphere;
import com.ashindigo.frost.network.FrostMachineProgressPacket;
import com.ashindigo.frost.network.FrostMaxPacketManager;
import com.ashindigo.frost.network.FrostMaxPowerPacket;
import com.ashindigo.frost.network.FrostPowerPacket;
import com.ashindigo.frost.network.FrostPowerPacketManager;
import com.ashindigo.frost.network.FrostProgressUpdatePacketManager;
import com.ashindigo.frost.recipes.FrozenTableRecipes;
import com.ashindigo.frost.tileentities.TileEntityFrozenTable;
import com.ashindigo.frost.tileentities.TileEntityIceDischarger;
import com.ashindigo.frost.tileentities.TileEntityIceFreezer;
import com.ashindigo.frost.world.FrozenWastelandBiome;
import com.ashindigo.indigolib.modding.IndigoMod;
import com.ashindigo.indigolib.modding.UtilsCreativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

// TODO Terrain check for power +/-
// TODO Low level tools
// TODO Debug items - Creative Ice Block - For environmental testing
// TODO Entities - Yetis
// TODO Structures - Ice Castle in wasteland (Makes exploring the biome worth it in later stages)
// XXX Textures/Models
// XXX Change wasteland biome to have end game castle instead?
// TODO Finish ore chunks
// TODO Add special tier effects?
// TODO Write/Re-write journal entries
// TODO Add FE costs for frozen table recipes
// TODO Fix 3d model for the hail sphere launchers
// TODO Ice skates to remove sliding on ice?
// FE can not be used in its default state. It requires a foci to shape into something more usable
// Energy is drained from the player into the machines
// Encourages player to build up their terrain for power. So they can support the machines.
@Mod(modid = FrostConstants.MODID, name = FrostConstants.NAME, version = "0.0.1", dependencies = "required-after:indigoutils;required-after:baubles")
public class Frost extends IndigoMod {

	@Instance(FrostConstants.MODID)
	public static Frost instance;

	public static final CreativeTabs frostTab = new UtilsCreativeTab("frosttab", new ItemStack(FrostItems.journal), FrostConstants.NAME, FrostConstants.MODID);

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(FrostConstants.MODID);

	public static final DamageSource FROSTDMG = new DamageSource("frost").setMagicDamage();

	public static FrozenWastelandBiome fwbiome;

	public static HashMap<BlockPos, Integer> progressMap = new HashMap<BlockPos, Integer>();

	@SidedProxy(serverSide = "com.ashindigo.frost.FrostCommonProxy", clientSide = "com.ashindigo.frost.FrostClientProxy")
	public static FrostCommonProxy proxy;

	public static ToolMaterial frostToolmat = EnumHelper.addToolMaterial(FrostConstants.MODID, 3, 450, 7.0F, 7.0F, 15);

	public static ArrayList<String> oreList = new ArrayList<String>();

	@Override
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		logger.log(Level.INFO, "Frost Mod Pre-Init");
		FrostItems.preInit();
		FrostBlocks.preInit();
		fwbiome = new FrozenWastelandBiome(new Biome.BiomeProperties(FrostConstants.WASTELANDNAME).setBaseHeight(0.1f));
		ForgeRegistries.BIOMES.register(fwbiome);
		BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(fwbiome, 3));
		MinecraftForge.EVENT_BUS.register(new FrostEventHandler());
		GameRegistry.registerTileEntity(TileEntityFrozenTable.class, "frozentable");
		GameRegistry.registerTileEntity(TileEntityIceDischarger.class, "icedischarger");
		GameRegistry.registerTileEntity(TileEntityIceFreezer.class, "icefreezer");
		EntityRegistry.registerModEntity(new ResourceLocation(FrostConstants.MODID, "hailsphere"), EntityHailSphere.class, "hailsphere", 0, instance, 50, 50, true);
		OBJLoader.INSTANCE.addDomain(FrostConstants.MODID);
	}

	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.log(Level.INFO, "Frost Mod Init");
		BiomeDictionary.addTypes(fwbiome, new Type[] { Type.COLD, Type.MAGICAL, Type.RARE });
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new FrostGuiHandler());
		INSTANCE.registerMessage(FrostPowerPacketManager.class, FrostPowerPacket.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(FrostMaxPacketManager.class, FrostMaxPowerPacket.class, 1, Side.CLIENT);
		INSTANCE.registerMessage(FrostProgressUpdatePacketManager.class, FrostMachineProgressPacket.class, 3, Side.CLIENT);
		// Default tiers
		FrostTierManager.addTier("pureice", new FrostTier("pureice", "Pure Ice", 50, 3, 100)); // 50 Max Power, 3 Power per tick, 100 per operation
		FrostTierManager.addTier("pykrete", new FrostTier("pykrete", "Pykrete", 100, 2, 100)); // 100 Max Power, 2 Power per tick, 100 per operation
		FrostTierManager.addTier("nitrogen", new FrostTier("nitrogen", "Nitrogen", 150, 1, 50)); // 150 Max power, 1 power per tick, 50 per operation
		// Registering ice into the oredict
		OreDictionary.registerOre("blockIce", Blocks.ICE);
		OreDictionary.registerOre("ingotFrost", new ItemStack(FrostItems.frostingot));
		FrozenTableRecipes.initRecipes();
		// Disabling Research Packet
		// INSTANCE.registerMessage(FrostListPacketManager.class, xFrostListPowerPacket.class, 2, Side.CLIENT);
		proxy.init();
		logger.log(Level.INFO, "Preparing Ore List");
		List<String> list = Arrays.asList(OreDictionary.getOreNames());
		for (int i = 0; list.size() > i; i++) {
			if (list.get(i).startsWith("ore")) {
				oreList.add(list.get(i));
			}
		}
	}

	@Override
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}

	@Override
	public String getModID() {
		return FrostConstants.MODID;
	}

}
