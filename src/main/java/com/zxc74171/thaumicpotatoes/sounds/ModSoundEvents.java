package com.zxc74171.thaumicpotatoes.sounds;

import com.zxc74171.thaumicpotatoes.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModSoundEvents {
	
	// ==== The sounds ====
	public static SoundEvent FAREWELL = new SoundEvent(new ResourceLocation(Reference.MODID, "farewell")).setRegistryName(new ResourceLocation(Reference.MODID, "farewell"));
	public static SoundEvent ENTITY_ZIEMNIAK_AMBIENT = new SoundEvent(new ResourceLocation(Reference.MODID, "ziemniak_ambient")).setRegistryName(new ResourceLocation(Reference.MODID, "ziemniak_ambient"));
	public static SoundEvent ENTITY_ZIEMNIAK_HURT = new SoundEvent(new ResourceLocation(Reference.MODID, "ziemniak_hurt")).setRegistryName(new ResourceLocation(Reference.MODID, "ziemniak_hurt"));
	public static SoundEvent ENTITY_ZIEMNIAK_DEATH = new SoundEvent(new ResourceLocation(Reference.MODID, "ziemniak_death")).setRegistryName(new ResourceLocation(Reference.MODID, "ziemniak_death"));
	public static SoundEvent POE = new SoundEvent(new ResourceLocation(Reference.MODID, "poe")).setRegistryName(new ResourceLocation(Reference.MODID, "poe"));
	public static SoundEvent POETAETOE = new SoundEvent(new ResourceLocation(Reference.MODID, "poetaetoe")).setRegistryName(new ResourceLocation(Reference.MODID, "poetaetoe"));
	
	public static SoundEvent MCDONALD = new SoundEvent(new ResourceLocation(Reference.MODID, "mcdonald")).setRegistryName(new ResourceLocation(Reference.MODID, "mcdonald"));
	public static SoundEvent LANLANLU = new SoundEvent(new ResourceLocation(Reference.MODID, "lanlanlu")).setRegistryName(new ResourceLocation(Reference.MODID, "lanlanlu"));
	public static SoundEvent DONARUTO = new SoundEvent(new ResourceLocation(Reference.MODID, "donaruto")).setRegistryName(new ResourceLocation(Reference.MODID, "donaruto"));
	public static SoundEvent ENTITY_FRYCOOK_AMBIENT = new SoundEvent(new ResourceLocation(Reference.MODID, "frycook_ambient")).setRegistryName(new ResourceLocation(Reference.MODID, "frycook_ambient"));
	public static SoundEvent ENTITY_FRYCOOK_HURT = new SoundEvent(new ResourceLocation(Reference.MODID, "frycook_hurt")).setRegistryName(new ResourceLocation(Reference.MODID, "frycook_hurt"));
	public static SoundEvent ENTITY_FRYCOOK_DEATH = new SoundEvent(new ResourceLocation(Reference.MODID, "frycook_death")).setRegistryName(new ResourceLocation(Reference.MODID, "frycook_death"));
	public static SoundEvent ENTITY_FRIES_AMBIENT = new SoundEvent(new ResourceLocation(Reference.MODID, "fries_ambient")).setRegistryName(new ResourceLocation(Reference.MODID, "fries_ambient"));
	public static SoundEvent ENTITY_FRIES_HURT = new SoundEvent(new ResourceLocation(Reference.MODID, "fries_hurt")).setRegistryName(new ResourceLocation(Reference.MODID, "fries_hurt"));
	public static SoundEvent ENTITY_FRIES_DEATH = new SoundEvent(new ResourceLocation(Reference.MODID, "fries_death")).setRegistryName(new ResourceLocation(Reference.MODID, "fries_death"));

	// ==== End of sounds ====
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event){
		event.getRegistry().register(ModSoundEvents.FAREWELL);
		event.getRegistry().register(ModSoundEvents.ENTITY_ZIEMNIAK_AMBIENT);
		event.getRegistry().register(ModSoundEvents.ENTITY_ZIEMNIAK_HURT);
		event.getRegistry().register(ModSoundEvents.ENTITY_ZIEMNIAK_DEATH);
		event.getRegistry().register(ModSoundEvents.POE);
		event.getRegistry().register(ModSoundEvents.POETAETOE);
		event.getRegistry().register(ModSoundEvents.ENTITY_FRYCOOK_AMBIENT);
		event.getRegistry().register(ModSoundEvents.ENTITY_FRYCOOK_HURT);
		event.getRegistry().register(ModSoundEvents.ENTITY_FRYCOOK_DEATH);
		event.getRegistry().register(ModSoundEvents.ENTITY_FRIES_AMBIENT);
		event.getRegistry().register(ModSoundEvents.ENTITY_FRIES_HURT);
		event.getRegistry().register(ModSoundEvents.ENTITY_FRIES_DEATH);
		event.getRegistry().register(ModSoundEvents.MCDONALD);
		event.getRegistry().register(ModSoundEvents.LANLANLU);
		event.getRegistry().register(ModSoundEvents.DONARUTO);
	}
	
	
}
