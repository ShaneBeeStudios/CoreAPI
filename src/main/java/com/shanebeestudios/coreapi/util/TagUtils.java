package com.shanebeestudios.coreapi.util;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.tag.Tag;
import io.papermc.paper.registry.tag.TagKey;
import org.bukkit.Keyed;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * General utility class for {@link Tag Tags}
 */
@ApiStatus.AvailableSince("1.1.0")
@SuppressWarnings({"UnstableApiUsage", "unused"})
public class TagUtils {

    private static final RegistryAccess REGISTRY_ACCESS = RegistryAccess.registryAccess();

    private TagUtils() {
    }

    /**
     * Get a {@link Tag} for a {@link TagKey Key}
     *
     * @param tagKey TagKey to get tag from
     * @param <T>    Registry class for tag
     * @return Tag from key
     */
    public static <T extends Keyed> Tag<@NotNull T> getTag(TagKey<T> tagKey) {
        return REGISTRY_ACCESS.getRegistry(tagKey.registryKey()).getTag(tagKey);
    }

    /**
     * Get all {@link TagKey TagKeys} for a specific {@link RegistryKey Registry}
     *
     * @param registryKey Key of registry to get tags from
     * @param <T>         Registry class
     * @return List of all TagKeys from Registry
     */
    public static <T extends Keyed> List<TagKey<T>> getTagKeys(RegistryKey<T> registryKey) {
        return REGISTRY_ACCESS.getRegistry(registryKey).getTags().stream().map(Tag::tagKey).toList();
    }

    /**
     * Get a {@link TypedKey} from a {@link Block}
     *
     * @param block Block to get key from
     * @return TypedKey from Block
     */
    public static TypedKey<BlockType> getBlockTypedKey(Block block) {
        return TypedKey.create(RegistryKey.BLOCK, block.getType().key());
    }

    /**
     * Get a {@link TypedKey} from an {@link ItemStack}
     *
     * @param stack ItemStack to get key from
     * @return TypedKey from ItemStack
     */
    public static TypedKey<ItemType> getItemTypedKey(ItemStack stack) {
        return TypedKey.create(RegistryKey.ITEM, stack.getType().key());
    }

}
