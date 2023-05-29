/*
    BlahajInit.java
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
package com.friendsofblahaj.blahajmod.init;

import com.friendsofblahaj.blahajmod.BlahajMain;
import com.friendsofblahaj.blahajmod.blocks.*;
//import com.friendsofblahaj.blahajmod.*;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BlahajInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BlahajMain.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BlahajMain.MOD_ID);

    private static <T extends Block>RegistryObject<T> registerShonkBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerShonkItem(name, toReturn, tab);
        return toReturn;
    };

    private static <T extends Block>RegistryObject<Item> registerShonkItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ITEMS.register(name, () -> new ShonkItem(block.get(), new Item.Properties().tab(tab), "block.blahajmod.blahaj.tooltip"));
    };

    public static final RegistryObject<Block> ORIGINAL_BLAHAJ = registerShonkBlock("original_blahaj",
            () -> new ShonkBlock(BlockBehaviour.Properties.of(Material.WOOL).strength(0.8f)),
            BlahajMain.CREATIVE_MODE_TAB);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}
