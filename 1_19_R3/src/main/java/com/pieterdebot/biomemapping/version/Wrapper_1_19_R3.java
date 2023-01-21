package com.pieterdebot.biomemapping.version;

import com.pieterdebot.biomemapping.Biome;
import net.minecraft.core.RegistryMaterials;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.biome.Biomes;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Wrapper_1_19_R3 implements VersionWrapper {

    @Override
    public boolean biomeSupported(Biome biome) {
        return getResourceKey(biome) != null;
    }

    public static void changeRegistryLock(boolean isLocked) {

        try {
            Server server = Bukkit.getServer();
            CraftServer craftServer = (CraftServer) server;
            DedicatedServer dedicatedServer = craftServer.getServer();
            RegistryMaterials<BiomeBase> materials = (RegistryMaterials<BiomeBase>) dedicatedServer.aW().d(Registries.al);

            Field isFrozen = materials.getClass().getDeclaredField("l");
            Field unregisteredIntrusiveHolders = materials.getClass().getDeclaredField("m");

            unregisteredIntrusiveHolders.setAccessible(true);
            unregisteredIntrusiveHolders.set(materials, new HashMap<>());
            isFrozen.setAccessible(true);
            isFrozen.set(materials, isLocked);

        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void replaceBiomes(Biome oldBiome, Biome newBiome) {

        changeRegistryLock(false);
        ResourceKey<BiomeBase> newBiomeResourceKey = getResourceKey(newBiome);



        changeRegistryLock(true);
    }

    private ResourceKey<BiomeBase> getResourceKey(Biome biome) {
        switch (biome) {
            case THE_VOID:
                return Biomes.a;
            case PLAINS:
                return Biomes.b;
            case SUNFLOWER_PLAINS:
                return Biomes.c;
            case SNOWY_TUNDRA:
                return Biomes.d;
            case ICE_SPIKES:
                return Biomes.e;
            case DESERT:
                return Biomes.f;
            case SWAMP:
                return Biomes.g;
            case MANGROVE_SWAMP:
                return Biomes.h;
            case FOREST:
                return Biomes.i;
            case FLOWER_FOREST:
                return Biomes.j;
            case BIRCH_FOREST:
                return Biomes.k;
            case DARK_FOREST:
                return Biomes.l;
            case TALL_BIRCH_FOREST:
                return Biomes.m;
            case GIANT_TREE_TAIGA:
                return Biomes.n;
            case GIANT_SPRUCE_TAIGA:
                return Biomes.o;
            case TAIGA:
                return Biomes.p;
            case SNOWY_TAIGA:
                return Biomes.q;
            case SAVANNA:
                return Biomes.r;
            case SAVANNA_PLATEAU:
                return Biomes.s;
            case MOUNTAINS:
                return Biomes.t;
            case GRAVELLY_MOUNTAINS:
                return Biomes.u;
            case WOODED_MOUNTAINS:
                return Biomes.v;
            case SHATTERED_SAVANNA:
                return Biomes.w;
            case JUNGLE:
                return Biomes.x;
            case JUNGLE_EDGE:
                return Biomes.y;
            case BAMBOO_JUNGLE:
                return Biomes.z;
            case BADLANDS:
                return Biomes.A;
            case ERODED_BADLANDS:
                return Biomes.B;
            case WOODED_BADLANDS_PLATEAU:
                return Biomes.C;
            case MEADOW:
                return Biomes.D;
            case GROVE:
                return Biomes.E;
            case SNOWY_SLOPES:
                return Biomes.F;
            case FROZEN_PEAKS:
                return Biomes.G;
            case JAGGED_PEAKS:
                return Biomes.H;
            case STORY_PEAKS:
                return Biomes.I;
            case RIVER:
                return Biomes.J;
            case FROZEN_RIVER:
                return Biomes.K;
            case BEACH:
                return Biomes.L;
            case SNOWY_BEACH:
                return Biomes.M;
            case STONE_SHORE:
                return Biomes.N;
            case WARM_OCEAN:
                return Biomes.O;
            case LUKEWARM_OCEAN:
                return Biomes.P;
            case DEEP_LUKEWARM_OCEAN:
                return Biomes.Q;
            case OCEAN:
                return Biomes.R;
            case DEEP_OCEAN:
                return Biomes.S;
            case COLD_OCEAN:
                return Biomes.T;
            case DEEP_COLD_OCEAN:
                return Biomes.U;
            case FROZEN_OCEAN:
                return Biomes.V;
            case DEEP_FROZEN_OCEAN:
                return Biomes.W;
            case MUSHROOM_FIELDS:
                return Biomes.X;
            case DRIPSTONE_CAVES:
                return Biomes.Y;
            case LUSH_CAVES:
                return Biomes.Z;
            case ANCIENT_CITIES:
                return Biomes.aa;
            case NETHER_WASTES:
                return Biomes.ab;
            case WARPED_FOREST:
                return Biomes.ac;
            case CRIMSON_FOREST:
                return Biomes.ad;
            case SOUL_SAND_VALLEY:
                return Biomes.ae;
            case BASALT_DELTAS:
                return Biomes.af;
            case THE_END:
                return Biomes.ag;
            case END_HIGHLANDS:
                return Biomes.ah;
            case END_MIDLANDS:
                return Biomes.ai;
            case SMALL_END_ISLANDS:
                return Biomes.aj;
            case END_BARRENS:
                return Biomes.ak;
        }

        return null;
    }

    private BiomeBase getBiomeBase(Biome biome) {
        return getBiomeBase(Biome.PLAINS);
    }

}
