package net.orcinus.galosphere.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.orcinus.galosphere.Galosphere;

import java.util.function.Consumer;

public class BiomeReagentHandler {
    //2510115296214374384
    //3477 73 -3050
    //-1490930914106892748
    //156 -9 482
    //460628901
    //1248939201
    //53285197, coords: 5237 23 -5750
    public static final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
    public static final Climate.Parameter CAVE_BIOME_RANGE = Climate.Parameter.span(0.2F, 0.9F);

    public static final ResourceKey<Biome> CRYSTAL_CANYONS = registerResourceKey("crystal_canyons");

    //temperature
    //humidity
    //continentalness
    //erosion
    //depth
    //weirdness
    //offset
    public static final Climate.ParameterPoint CRYSTAL_CANYONS_PARAMETER = Climate.parameters(
            Climate.Parameter.span(-1.0F, -0.45F),
            Climate.Parameter.span(-1.0F, -0.35F),
            Climate.Parameter.span(0.03F, 0.3F),
            Climate.Parameter.span(-0.2225F,0.45F),
            CAVE_BIOME_RANGE,
            FULL_RANGE,
            0.0F);
//    public static final Climate.ParameterPoint CRYSTAL_CANYONS_PARAMETER2 = Climate.parameters(
//            Climate.Parameter.span(-1.0F, -0.8F),
//            FULL_RANGE,
//            Climate.Parameter.span(0.5F, 1.0F),
//            FULL_RANGE,
//            CAVE_BIOME_RANGE,
//            FULL_RANGE,
//            0.0F);

    public static void init(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        consumer.accept(Pair.of(CRYSTAL_CANYONS_PARAMETER, CRYSTAL_CANYONS));
    }

    private static ResourceKey<Biome> registerResourceKey(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Galosphere.MODID, name));
    }

}
