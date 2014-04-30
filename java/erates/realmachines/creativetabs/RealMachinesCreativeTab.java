package erates.realmachines.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class RealMachinesCreativeTab extends CreativeTabs{

	public RealMachinesCreativeTab() {
		super("tabRealMachines");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(Blocks.piston);
	}

}
