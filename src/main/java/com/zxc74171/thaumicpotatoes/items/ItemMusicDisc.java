package com.zxc74171.thaumicpotatoes.items;

import java.util.Map;

import com.google.common.collect.Maps;
import com.zxc74171.thaumicpotatoes.Reference;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ItemMusicDisc extends ItemRecord{
	
	private static final Map<SoundEvent, ItemRecord> RECORDS = Maps.<SoundEvent, ItemRecord>newHashMap();
    private final SoundEvent sound;
    private final String displayName;


	public ItemMusicDisc(String p_i46742_1_, SoundEvent soundIn) {
		super(p_i46742_1_, soundIn);
		this.displayName = "farewell";
        this.sound = soundIn;
        this.maxStackSize = 1;
		// TODO Auto-generated constructor stub
	}
	
}