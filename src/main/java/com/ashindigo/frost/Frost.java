 package com.ashindigo.frost;

import org.apache.logging.log4j.Level;

import com.ashindigo.frost.entities.EntityHailSphere;
import com.ashindigo.frost.network.FrostMaxPacketManager;
import com.ashindigo.frost.network.FrostMaxPowerPacket;
import com.ashindigo.frost.network.FrostPowerPacket;
import com.ashindigo.frost.network.FrostPowerPacketManager;
import com.ashindigo.frost.recipes.FrozenTableRecipes;
import com.ashindigo.frost.tileentities.TileEntityFrozenTable;
import com.ashindigo.frost.world.FrozenWastelandBiome;
import com.ashindigo.indigolib.modding.IndigoMod;
import com.ashindigo.indigolib.modding.UtilsCreativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;


// TODO Do math magic for centering
// TODO Weapons/Tools - Frostwave, Hail Sphere Launcher
// TODO Terrain check for power +/-
// TODO Machines/Blocks - Frost Discharger, Ice freezer
// TODO Debug items - Creative Gem
// TODO Entities - Yetis
// TODO Structures - Ice Castle in wasteland (Makes exploring the biome worth it in later stages)
// XXX Textures
// FE can not be used in its default state. It requires a foci to shape into something more usable
@Mod(modid = FrostConstants.MODID, name = "Frost", version = "0.0.1", dependencies = "required-after:indigoutils")
public class Frost extends IndigoMod {
	
	@Instance(FrostConstants.MODID)
	public static Frost instance;
	
	public static final CreativeTabs frostTab = new UtilsCreativeTab("frosttab", new ItemStack(FrostItems.journal), "Frost", FrostConstants.MODID);
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(FrostConstants.MODID);
	
	public static final DamageSource FROSTDMG = new DamageSource("frost").setMagicDamage();
	
	public static FrozenWastelandBiome fwbiome;

	@SidedProxy(serverSide = "com.ashindigo.frost.FrostCommonProxy", clientSide = "com.ashindigo.frost.FrostClientProxy")
	public static FrostCommonProxy proxy;

	public static ToolMaterial frostToolmat = EnumHelper.addToolMaterial(FrostConstants.MODID, 3, 450, 7.0F, 7.0F, 15);
	
	@Override
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		logger.log(Level.INFO, "Frost Mod Pre-Init");
		FrostItems.preInit();
		FrostBlocks.preInit();
	 	fwbiome = new FrozenWastelandBiome(new Biome.BiomeProperties(FrostConstants.WASTELANDNAME).setBaseHeight(0.1f));
		GameRegistry.register(fwbiome);
		BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(fwbiome, 3));
		MinecraftForge.EVENT_BUS.register(new FrostEventHandler());
		GameRegistry.registerTileEntity(TileEntityFrozenTable.class, "frozentable");
		EntityRegistry.registerModEntity(new ResourceLocation(FrostConstants.MODID, "hailsphere"), EntityHailSphere.class, "hailsphere", 0, instance, 50, 50, true);
		FrozenTableRecipes.initRecipes();
		//proxy.preInit();
	}

	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.log(Level.INFO, "Frost Mod Init");
		BiomeDictionary.addTypes(fwbiome, new Type[]{Type.COLD, Type.MAGICAL, Type.RARE});
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new FrostGuiHandler());
		INSTANCE.registerMessage(FrostPowerPacketManager.class, FrostPowerPacket.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(FrostMaxPacketManager.class, FrostMaxPowerPacket.class, 1, Side.CLIENT);
		// Disabling Research Packet
		//INSTANCE.registerMessage(FrostListPacketManager.class, FrostListPowerPacket.class, 2, Side.CLIENT);
		proxy.preInit();
		proxy.init();
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
