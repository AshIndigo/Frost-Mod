package com.ashindigo.frost.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FrostTierManager {
	
	//PURE("pureice"), // Made from just ice and packed ice weakest tier of the mod
	//PYKRETE("pykrete"), // Made from sticks + ice or ice and sawdust (if oredict entry exists)
	//NITROGEN("nitrogen"), // Liquid nitrogen is embedded inside the machines as a form of coolant, reducing the energy needed to keep the machine from melting 
	// Cryotheum tier? - Too techy? - TE integration	
	
	private static HashMap<String, FrostTier> tiers = new HashMap<String, FrostTier>();
	
	public static void addTier(String modid, FrostTier tierClass) {
		tiers.put(tierClass.name, tierClass);
	}

	public static Map<String, FrostTier> getUnmodifiableTiersMap() {
		return Collections.unmodifiableMap(tiers);
	}
}
