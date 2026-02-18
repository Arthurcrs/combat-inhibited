package com.mahghuuuls.examplemod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExampleMod.MOD_ID, name = ExampleMod.NAME, version = ExampleMod.VERSION, dependencies = ExampleMod.DEPENDENCIES)
public class ExampleMod {
	public static final String MOD_ID = "examplemod";
	public static final String NAME = "Example Mod";
	public static final String VERSION = "1.0.0";
	public static final String DEPENDENCIES = "";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}