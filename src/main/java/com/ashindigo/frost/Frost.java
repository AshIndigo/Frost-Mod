 package com.ashindigo.frost;

import org.apache.logging.log4j.Level;

import com.ashindigo.frost.network.FrostMaxPacketManager;
import com.ashindigo.frost.network.FrostMaxPowerPacket;
import com.ashindigo.frost.network.FrostPowerPacket;
import com.ashindigo.frost.network.FrostPowerPacketManager;
import com.ashindigo.frost.recipes.FrozenTableRecipes;
import com.ashindigo.frost.tileentities.TileEntityFrozenTable;
import com.ashindigo.frost.world.FrozenWastelandBiome;
import com.ashindigo.indigolib.modding.IndigoMod;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;


// TODO Do math magic for centering
@Mod(modid = FrostConstants.MODID, name = "Frost", version = "0.0.1", dependencies = "required-before:indigoutils")
public class Frost extends IndigoMod {
	
	@Instance
	public static Frost instance;
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(FrostConstants.MODID);
	
	public static FrozenWastelandBiome fwbiome = new FrozenWastelandBiome(new Biome.BiomeProperties(FrostConstants.WASTELANDNAME).setBaseHeight(0.1f));

	@Override
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		logger.log(Level.INFO, "Frost Mod Pre-Init");
		FrostItems.preInit();
		FrostBlocks.preInit();
		GameRegistry.register(fwbiome);
		BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(fwbiome, 3));
		MinecraftForge.EVENT_BUS.register(new FrostEventHandler());
		GameRegistry.registerTileEntity(TileEntityFrozenTable.class, "frozentable");
		FrozenTableRecipes.initRecipes();
	}

	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(fwbiome, new Type[]{Type.COLD, Type.MAGICAL, Type.RARE});
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new FrostGuiHandler());
		INSTANCE.registerMessage(FrostPowerPacketManager.class, FrostPowerPacket.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(FrostMaxPacketManager.class, FrostMaxPowerPacket.class, 1, Side.CLIENT);
		FrostJournalEntries.initTitles();
		FrostJournalEntries.initDesc();
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
