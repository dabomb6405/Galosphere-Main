package net.orcinus.galosphere.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.galosphere.init.GBlocks;
import net.orcinus.galosphere.init.GItems;

import java.util.stream.Collectors;

public class GBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        this.add(GBlocks.SILVER_ORE.get(), (block) -> createOreDrop(block, GItems.RAW_SILVER.get()));
        this.add(GBlocks.DEEPSLATE_SILVER_ORE.get(), (block) -> createOreDrop(block, GItems.RAW_SILVER.get()));
        dropSelf(GBlocks.CHARGED_LUMIERE_BLOCK.get());
        dropSelf(GBlocks.LUMIERE_BLOCK.get());
        dropSelf(GBlocks.ALLURITE_BLOCK.get());
        dropSelf(GBlocks.RAW_SILVER_BLOCK.get());
        dropSelf(GBlocks.SILVER_BLOCK.get());
        dropSelf(GBlocks.AMETHYST_STAIRS.get());
        dropSlab(GBlocks.AMETHYST_SLAB);
        dropSelf(GBlocks.ALLURITE_STAIRS.get());
        dropSlab(GBlocks.ALLURITE_SLAB);
        dropSelf(GBlocks.LUMIERE_STAIRS.get());
        dropSlab(GBlocks.LUMIERE_SLAB);
        dropSelf(GBlocks.SMOOTH_AMETHYST.get());
        dropSelf(GBlocks.SMOOTH_AMETHYST_STAIRS.get());
        dropSlab(GBlocks.SMOOTH_AMETHYST_SLAB);
        dropSelf(GBlocks.AMETHYST_BRICKS.get());
        dropSelf(GBlocks.AMETHYST_BRICK_STAIRS.get());
        dropSlab(GBlocks.AMETHYST_BRICK_SLAB);
        dropSelf(GBlocks.CHISELED_AMETHYST.get());
        this.add(GBlocks.ALLURITE_CLUSTER.get(), (block) -> createSilkTouchDispatchTable(block, LootItem.lootTableItem(GItems.ALLURITE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(block, LootItem.lootTableItem(GItems.ALLURITE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));
        this.add(GBlocks.LUMIERE_CLUSTER.get(), (block) -> createSilkTouchDispatchTable(block, LootItem.lootTableItem(GItems.LUMIERE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(applyExplosionDecay(block, LootItem.lootTableItem(GItems.LUMIERE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));
        dropSelf(GBlocks.AURA_RINGER.get());
        dropSelf(GBlocks.LUMIERE_LAMP.get());
        dropSelf(GBlocks.ALLURITE_LAMP.get());
        dropSelf(GBlocks.AMETHYST_LAMP.get());
        dropSelf(GBlocks.WARPED_ANCHOR.get());
        this.add(GBlocks.LUMIERE_COMPOSTER.get(), LootTable.lootTable().withPool(applyExplosionCondition(Blocks.COMPOSTER, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Blocks.COMPOSTER)))));
        dropSelf(GBlocks.COMBUSTION_TABLE.get());
        dropSelf(GBlocks.SMOOTH_ALLURITE.get());
        dropSlab(GBlocks.SMOOTH_ALLURITE_SLAB);
        dropSelf(GBlocks.SMOOTH_ALLURITE_STAIRS.get());
        dropSelf(GBlocks.ALLURITE_BRICKS.get());
        dropSelf(GBlocks.CHISELED_ALLURITE.get());
        dropSelf(GBlocks.SMOOTH_LUMIERE.get());
        dropSlab(GBlocks.SMOOTH_LUMIERE_SLAB);
        dropSelf(GBlocks.SMOOTH_LUMIERE_STAIRS.get());
        dropSelf(GBlocks.LUMIERE_BRICKS.get());
        dropSelf(GBlocks.CHISELED_LUMIERE.get());
        dropSelf(GBlocks.ALLURITE_BRICK_STAIRS.get());
        dropSlab(GBlocks.ALLURITE_BRICK_SLAB);
        dropSelf(GBlocks.LUMIERE_BRICK_STAIRS.get());
        dropSlab(GBlocks.LUMIERE_BRICK_SLAB);
    }

    private void dropSlab(RegistryObject<Block> slab) {
        this.add(slab.get(), BlockLoot::createSlabItemTable);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return GBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }
}
