package com.pieterdebot.biomemapping.version;

import com.mojang.serialization.Lifecycle;
import com.pieterdebot.biomemapping.Biome;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryCustom;
import net.minecraft.core.RegistryMaterials;
import net.minecraft.data.RegistryGeneration;
import net.minecraft.data.worldgen.biome.EndBiomes;
import net.minecraft.data.worldgen.biome.NetherBiomes;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.biome.Biomes;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.OptionalInt;

public class Wrapper_1_18_R2 implements VersionWrapper {

    @Override
    public boolean biomeSupported(Biome biome) {
        return getResourceKey(biome) != null;
    }


    @Override
    public void replaceBiomes(Biome oldBiome, Biome newBiome) {

        ResourceKey<BiomeBase> oldBiomeResourceKey = getResourceKey(oldBiome);

        try {

            IRegistryCustom.b dimension = (IRegistryCustom.b) ((CraftServer) Bukkit.getServer()).getServer().Q;

            Field registryMap = dimension.getClass().getDeclaredField("e");
            registryMap.setAccessible(true);

            RegistryMaterials<BiomeBase> materials = (RegistryMaterials<BiomeBase>)
                    ((Map<? extends ResourceKey<? extends IRegistry<?>>, ? extends IRegistry<?>>)registryMap.get(dimension))
                            .get(IRegistry.aP);

            Field isFrozen = materials.getClass().getDeclaredField("bL");
            isFrozen.setAccessible(true);
            isFrozen.set(materials, false);

            materials.a(OptionalInt.empty(), oldBiomeResourceKey, getBiomeBase(newBiome), Lifecycle.stable());

        } catch (IllegalAccessException | NoSuchFieldException  exception) {
            exception.printStackTrace();
        }
    }

    private ResourceKey<BiomeBase> getResourceKey(Biome biome) {
        return switch (biome) {
            case THE_VOID -> Biomes.a;
            case PLAINS -> Biomes.b;
            case SUNFLOWER_PLAINS -> Biomes.c;
            case SNOWY_TUNDRA -> Biomes.d;
            case ICE_SPIKES -> Biomes.e;
            case DESERT -> Biomes.f;
            case SWAMP -> Biomes.g;
            case FOREST -> Biomes.h;
            case FLOWER_FOREST -> Biomes.i;
            case BIRCH_FOREST -> Biomes.j;
            case DARK_FOREST -> Biomes.k;
            case TALL_BIRCH_FOREST -> Biomes.l;
            case GIANT_TREE_TAIGA -> Biomes.m;
            case GIANT_SPRUCE_TAIGA -> Biomes.n;
            case TAIGA -> Biomes.o;
            case SNOWY_TAIGA -> Biomes.p;
            case SAVANNA -> Biomes.q;
            case SAVANNA_PLATEAU -> Biomes.r;
            case MOUNTAINS -> Biomes.s;
            case GRAVELLY_MOUNTAINS -> Biomes.t;
            case WOODED_MOUNTAINS -> Biomes.u;
            case SHATTERED_SAVANNA -> Biomes.v;
            case JUNGLE -> Biomes.w;
            case JUNGLE_EDGE -> Biomes.x;
            case BAMBOO_JUNGLE -> Biomes.y;
            case BADLANDS -> Biomes.z;
            case ERODED_BADLANDS -> Biomes.A;
            case WOODED_BADLANDS_PLATEAU -> Biomes.B;
            case MEADOW -> Biomes.C;
            case GROVE -> Biomes.D;
            case SNOWY_SLOPES -> Biomes.E;
            case FROZEN_PEAKS -> Biomes.F;
            case JAGGED_PEAKS -> Biomes.G;
            case STORY_PEAKS -> Biomes.H;
            case RIVER -> Biomes.I;
            case FROZEN_RIVER -> Biomes.J;
            case BEACH -> Biomes.K;
            case SNOWY_BEACH -> Biomes.L;
            case STONE_SHORE -> Biomes.M;
            case WARM_OCEAN -> Biomes.N;
            case LUKEWARM_OCEAN -> Biomes.O;
            case DEEP_LUKEWARM_OCEAN -> Biomes.P;
            case OCEAN -> Biomes.Q;
            case DEEP_OCEAN -> Biomes.R;
            case COLD_OCEAN -> Biomes.S;
            case DEEP_COLD_OCEAN -> Biomes.T;
            case FROZEN_OCEAN -> Biomes.U;
            case DEEP_FROZEN_OCEAN -> Biomes.V;
            case MUSHROOM_FIELDS -> Biomes.W;
            case DRIPSTONE_CAVES -> Biomes.X;
            case LUSH_CAVES -> Biomes.Y;
            case NETHER_WASTES -> Biomes.Z;
            case WARPED_FOREST -> Biomes.aa;
            case CRIMSON_FOREST -> Biomes.ab;
            case SOUL_SAND_VALLEY -> Biomes.ac;
            case BASALT_DELTAS -> Biomes.ad;
            case THE_END -> Biomes.ae;
            case END_HIGHLANDS -> Biomes.af;
            case END_MIDLANDS -> Biomes.ag;
            case SMALL_END_ISLANDS -> Biomes.ah;
            case END_BARRENS -> Biomes.ai;
            default -> null;
        };

    }

    private BiomeBase getBiomeBase(Biome biome) {
        return switch (biome) {
            case THE_VOID -> OverworldBiomes.i();
            case PLAINS -> OverworldBiomes.a(false, false, false);
            case SUNFLOWER_PLAINS -> OverworldBiomes.a(true, false, false);
            case SNOWY_TUNDRA -> OverworldBiomes.a(false, true, false);
            case ICE_SPIKES -> OverworldBiomes.a(false, true, true);
            case DESERT -> OverworldBiomes.d();
            case SWAMP -> OverworldBiomes.h();
            case FOREST -> OverworldBiomes.b(false, false, false);
            case FLOWER_FOREST -> OverworldBiomes.b(false, false, true);
            case BIRCH_FOREST -> OverworldBiomes.b(true, false, false);
            case DARK_FOREST -> OverworldBiomes.g();
            case TALL_BIRCH_FOREST -> OverworldBiomes.b(true, true, false);
            case GIANT_TREE_TAIGA -> OverworldBiomes.a(false);
            case GIANT_SPRUCE_TAIGA -> OverworldBiomes.a(true);
            case TAIGA -> OverworldBiomes.h(false);
            case SNOWY_TAIGA -> OverworldBiomes.h(true);
            case SAVANNA -> OverworldBiomes.a(false, false);
            case SAVANNA_PLATEAU -> OverworldBiomes.a(false, true);
            case MOUNTAINS -> OverworldBiomes.b(false);
            case GRAVELLY_MOUNTAINS -> OverworldBiomes.b(false);
            case WOODED_MOUNTAINS -> OverworldBiomes.b(true);
            case SHATTERED_SAVANNA -> OverworldBiomes.a(true, false);
            case JUNGLE -> OverworldBiomes.b();
            case JUNGLE_EDGE -> OverworldBiomes.a();
            case BAMBOO_JUNGLE -> OverworldBiomes.c();
            case BADLANDS -> OverworldBiomes.c(false);
            case ERODED_BADLANDS -> OverworldBiomes.c(false);
            case WOODED_BADLANDS_PLATEAU -> OverworldBiomes.c(true);
            case MEADOW -> OverworldBiomes.j();
            case GROVE -> OverworldBiomes.o();
            case SNOWY_SLOPES -> OverworldBiomes.n();
            case FROZEN_PEAKS -> OverworldBiomes.k();
            case JAGGED_PEAKS -> OverworldBiomes.l();
            case STORY_PEAKS -> OverworldBiomes.m();
            case RIVER -> OverworldBiomes.i(false);
            case FROZEN_RIVER -> OverworldBiomes.i(true);
            case BEACH -> OverworldBiomes.b(false, false);
            case SNOWY_BEACH -> OverworldBiomes.b(true, false);
            case STONE_SHORE -> OverworldBiomes.b(false, true);
            case WARM_OCEAN -> OverworldBiomes.f();
            case LUKEWARM_OCEAN -> OverworldBiomes.f(false);
            case DEEP_LUKEWARM_OCEAN -> OverworldBiomes.f(true);
            case OCEAN -> OverworldBiomes.e(false);
            case DEEP_OCEAN -> OverworldBiomes.e(true);
            case COLD_OCEAN -> OverworldBiomes.d(false);
            case DEEP_COLD_OCEAN -> OverworldBiomes.g(false);
            case FROZEN_OCEAN -> OverworldBiomes.d(true);
            case DEEP_FROZEN_OCEAN -> OverworldBiomes.g(true);
            case MUSHROOM_FIELDS -> OverworldBiomes.e();
            case DRIPSTONE_CAVES -> OverworldBiomes.q();
            case LUSH_CAVES -> OverworldBiomes.p();
            case NETHER_WASTES -> NetherBiomes.a();
            case WARPED_FOREST -> NetherBiomes.e();
            case CRIMSON_FOREST -> NetherBiomes.d();
            case SOUL_SAND_VALLEY -> NetherBiomes.b();
            case BASALT_DELTAS -> NetherBiomes.c();
            case THE_END -> EndBiomes.b();
            case END_HIGHLANDS -> EndBiomes.d();
            case END_MIDLANDS -> EndBiomes.c();
            case SMALL_END_ISLANDS -> EndBiomes.e();
            case END_BARRENS -> EndBiomes.a();
            default -> getBiomeBase(Biome.PLAINS);
        };
    }

}
