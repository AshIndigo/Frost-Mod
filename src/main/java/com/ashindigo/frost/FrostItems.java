package com.ashindigo.frost;

import com.ashindigo.frost.integrate.baubles.ItemIceRing;
import com.ashindigo.frost.items.ItemJournal;
import com.ashindigo.indigolib.modding.UtilsItem;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;

public class FrostItems {
	
	public static Item frostingot;
	public static Item journal;
	public static Item icering;

	public static void preInit() {
		frostingot = new UtilsItem(FrostConstants.MODID, "frostingot", "Frost Ingot");
		journal = new ItemJournal(FrostConstants.MODID, "journal", "Frozen Journal");
		if (Loader.isModLoaded("baubles")) {
			icering = new ItemIceRing(FrostConstants.MODID, "icering", "Ice Ring");
		}
	}
}
