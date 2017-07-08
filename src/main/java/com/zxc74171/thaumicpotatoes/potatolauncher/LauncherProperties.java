package com.zxc74171.thaumicpotatoes.potatolauncher;

public enum LauncherProperties {
	STANDARD_LAUNCHER(0, 3.75f, 10.0f, Ammos.NORMAL, 3),
	NULL(0, 0, 0, Ammos.NULL, 0);
	
	public int durability;
	public float strength;
	public float drawbackSpeed;
	public Ammos ammo;
	public int tier;
	
	private LauncherProperties(int durability, float strength, float drawBackSpeed, Ammos ammo, int tier) {
		this.durability = durability;
		this.strength = strength;
		this.drawbackSpeed = drawBackSpeed;
		this.ammo = ammo;
		this.tier = tier;
	}
}
