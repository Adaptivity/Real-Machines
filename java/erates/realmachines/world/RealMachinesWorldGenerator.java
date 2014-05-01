package erates.realmachines.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import erates.realmachines.blocks.ModBlocks;

public class RealMachinesWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
			case 0:
				generateSurface(random, chunkX * 16, chunkZ * 16, world);
				break;
			case 1:
				generateEnd(random, chunkX * 16, chunkZ * 16, world);
				break;
			case -1:
				generateNether(random, chunkX * 16, chunkZ * 16, world);
				break;
			default:
				;
		}
	}

	public void generateSurface(Random random, int chunkX, int chunkZ, World world) {
		addOreSpawn(ModBlocks.oreChromium, world, random, chunkX, chunkZ, 2, 9, 10, 0, 12);
	}

	public void generateEnd(Random random, int chunkX, int chunkZ, World world) {
		// Add End Generation
	}

	public void generateNether(Random random, int chunkX, int chunkZ, World world) {
		// Add Nether Generation
	}

	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVainSize, int maxVainSize, int chancesToSpawn, int minY, int maxY) {
		for (int i = 0; i < chancesToSpawn; i++) {
			int posX = blockXPos + random.nextInt(16);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(16);
			new WorldGenMinable(block, (minVainSize + random.nextInt(maxVainSize - minVainSize)), Blocks.stone).generate(world, random, posX, posY, posZ);
		}
	}
}
