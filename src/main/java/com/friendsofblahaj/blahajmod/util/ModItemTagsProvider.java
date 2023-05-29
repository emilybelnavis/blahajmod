/*
    ModItemTagsProvider.java
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

package com.friendsofblahaj.blahajmod.util;

import com.friendsofblahaj.blahajmod.BlahajMain;
import com.friendsofblahaj.blahajmod.init.BlahajInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {
    public static TagKey<Item> BLAHAJ = ItemTags.create(new ResourceLocation(BlahajMain.MOD_ID, "BLAHAJ"));

    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, BlahajMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        BlahajInit.ITEMS.getEntries().forEach(item -> {
            this.tag(BLAHAJ).add(item.get());
        });
    }
}
