/*
    ShonkItem.java
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

package com.friendsofblahaj.blahajmod.blocks;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.List;
import javax.annotation.Nullable;

public class ShonkItem extends BlockItem {
    public static final String OWNER_KEY = "Owner";

    // translatable subtitle
    public final Component subtitle;

    public ShonkItem(Block block, Properties properties, String subtitle) {
        super(block, properties);
        this.subtitle = subtitle == null ? null : new TranslatableComponent(subtitle);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        if(this.subtitle != null) {
            tooltip.add(this.subtitle);
        }

        CompoundTag nbt = stack.getTag();

        if (nbt != null) {
            String owner = nbt.getString(OWNER_KEY);
            if (owner.isEmpty()) {
                return;
            }

            if(stack.hasCustomHoverName()) {
                tooltip.add(new TranslatableComponent("tooltip.blahajmod.owner.rename", stack.getHoverName(), new TextComponent(owner)));
            } else {
                tooltip.add(new TranslatableComponent("tooltip.blahajmod.owner.craft", new TextComponent(owner)));
            }
        }
    }

    // on craft override
    @Override
    public void onCraftedBy(ItemStack stack, Level world, net.minecraft.world.entity.player.Player player) {
        if (player != null) {
            CompoundTag nbt = stack.getOrCreateTag();
            nbt.putString(OWNER_KEY, player.getName().getString());
        }
    }

    // mining speed multiplier
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 0.25F;
    }

}
