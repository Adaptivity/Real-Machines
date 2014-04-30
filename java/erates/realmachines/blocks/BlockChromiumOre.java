package erates.realmachines.blocks;

import java.util.Random;

import erates.realmachines.RealMachines;
import erates.realmachines.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockChromiumOre extends Block {

	protected BlockChromiumOre() {
		super(Material.rock);
		setBlockName("rmOreChromium");
		setBlockTextureName("erates_realmachines:blockChromiumOre");
		setCreativeTab(RealMachines.tabRealMachines);
		setHardness(3.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return ModItems.itemChromiumDust;
	}

	public int quantityDroppedWithBonus(int bonus, Random rand) {
		return this.quantityDropped(rand) + rand.nextInt(bonus + 1);
	}

	public int quantityDropped(Random rand) {
		return 4 + rand.nextInt(2);
	}

}
