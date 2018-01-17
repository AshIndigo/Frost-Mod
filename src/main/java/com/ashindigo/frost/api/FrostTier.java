package com.ashindigo.frost.api;

/**
 * Contains generic data for a tier
 * - Name
 * - Translated Title
 * - Max power
 * - Power usage
 * - Speed
 * @author Ash
 */
public class FrostTier {
	
	public String name;
	public String title;
	public int maxPower;
	public int powerUsage;
	public int speed;
	
	public FrostTier(String name, String title ,int maxPower, int powerUsage, int speed) {
		this.name = name;
		this.title = title;
		this.maxPower = maxPower;
		this.powerUsage = powerUsage;
		this.speed = speed;
	}
}
