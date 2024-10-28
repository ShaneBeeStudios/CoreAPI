package com.shanebeestudios.coreapi.util;

import com.google.common.base.Preconditions;
import net.minecraft.core.component.DataComponentType;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Build an ItemStack
 */
@SuppressWarnings({"unused", "deprecation", "UnstableApiUsage", "DataFlowIssue"})
public class ItemBuilder {

    // STATIC

    /**
     * Create a new builder
     *
     * @param material Material to create
     * @return New builder
     */
    public static ItemBuilder builder(@NotNull Material material) {
        return builder(material, 1);
    }

    /**
     * Create a new builder
     *
     * @param material Material to create
     * @param amount   Amount of ItemStack
     * @return New builder
     */
    public static ItemBuilder builder(@NotNull Material material, int amount) {
        Preconditions.checkNotNull(material, "Material must not be null");
        Preconditions.checkArgument(material != Material.AIR, "Material must not be air");
        Preconditions.checkArgument(material.isItem(), "Material must be an item");
        return new ItemBuilder(material, amount);
    }

    /**
     * Create a new builder
     *
     * @param type ItemType to create
     * @return New builder
     */
    public static ItemBuilder builder(@NotNull ItemType type) {
        return builder(type, 1);
    }

    /**
     * Create a new builder
     *
     * @param type   ItemType to create
     * @param amount Amount of ItemStack
     * @return New builder
     */
    public static ItemBuilder builder(@NotNull ItemType type, int amount) {
        Preconditions.checkNotNull(type, "ItemType must not be null");
        Preconditions.checkArgument(type != ItemType.AIR, "ItemType must not be air");
        return builder(type.asMaterial(), amount);
    }

    // CLASS
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    private ItemBuilder(@NotNull Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = itemStack.getItemMeta();
        Preconditions.checkArgument(this.itemMeta != null, "ItemMeta was null.");
    }

    /**
     * Modify the amount of this item
     *
     * @param amount Amount of item
     * @return Builder
     */
    public ItemBuilder amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * Set the name of this item
     *
     * @param name Name of item
     * @return Builder
     */
    public ItemBuilder name(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    /**
     * Set the lore of this item
     *
     * @param lore Lore to set
     * @return Builder
     */
    public ItemBuilder lore(List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Add a line of lore to the lore of this item
     *
     * @param lore Lore to add
     * @return Builder
     */
    public ItemBuilder addLore(String lore) {
        List<String> list = new ArrayList<>();
        if (this.itemMeta.hasLore()) list = this.itemMeta.getLore();
        assert list != null;
        list.add(lore);
        this.itemMeta.setLore(list);
        return this;
    }

    /**
     * Add an enchantment to this item
     *
     * @param enchantment Enchantment to add
     * @param level       Level of enchantment to add
     * @return Builder
     */
    public ItemBuilder addEnchant(@NotNull Enchantment enchantment, int level) {
        Preconditions.checkNotNull(enchantment, "Enchantment must not be null");
        this.itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Modify the food component of this item
     *
     * @param food Food component to modify
     * @return Builder
     */
    public ItemBuilder food(@NotNull Consumer<FoodComponent> food) {
        FoodComponent foodComponent = this.itemMeta.getFood();
        food.accept(foodComponent);
        this.itemMeta.setFood(foodComponent);
        return this;
    }

    /**
     * Modify the tool component of this item
     *
     * @param tool Tool component to modify
     * @return Builder
     */
    public ItemBuilder tool(@NotNull Consumer<ToolComponent> tool) {
        ToolComponent toolComponent = this.itemMeta.getTool();
        tool.accept(toolComponent);
        this.itemMeta.setTool(toolComponent);
        return this;
    }

    /**
     * Modify the ItemMeta of this item
     *
     * @param meta ItemMeta to modify
     * @return Builder
     */
    public ItemBuilder meta(@NotNull Consumer<ItemMeta> meta) {
        meta.accept(this.itemMeta);
        return this;
    }

    /**
     * Add a vanilla component to this item
     *
     * @param type  Type of component to add
     * @param value Value to add
     * @param <T>   Class type of component
     * @return Builder
     */
    public <T> ItemBuilder vanillaComponent(@NotNull DataComponentType<T> type, @Nullable T value) {
        net.minecraft.world.item.ItemStack nmsItemStack = ((CraftItemStack) this.itemStack).handle;
        nmsItemStack.set(type, value);
        return this;
    }

    /**
     * Modify the NMS ItemStack of this item
     *
     * @param vanilla NMS ItemStack to modify
     * @return Builder
     */
    public ItemBuilder vanilla(@NotNull Consumer<net.minecraft.world.item.ItemStack> vanilla) {
        net.minecraft.world.item.ItemStack nms = ((CraftItemStack) this.itemStack).handle;
        vanilla.accept(nms);
        return this;
    }

    /**
     * Build this builder into an ItemStack
     *
     * @return ItemStack from builder
     */
    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

}
