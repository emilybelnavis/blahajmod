/*
    BlahajMain.java
    blahaj
    
    Copyright (C) 2022 Emily Belnavis

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
  */
package com.friendsofblahaj.blahajmod;

import com.friendsofblahaj.blahajmod.init.*;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(BlahajMain.MOD_ID)
public class BlahajMain {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "blahajmod";

    public BlahajMain() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);
        BlahajInit.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Blahaj is here!");
    }

    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab(BlahajMain.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
        // TODO implement creative tab

            return BlahajInit.ORIGINAL_BLAHAJ.get().asItem().getDefaultInstance();
        }
    };
}
