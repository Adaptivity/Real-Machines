package erates.realmachines.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {

	public static Item itemGreenSand;
	public static Item itemChromiumDust;
	public static Item itemChromiteDust;

	public static void registerItems() {
		itemChromiumDust = new ItemChromiumDust();
		itemChromiteDust = new ItemChromiteDust();
		itemGreenSand = new ItemGreenSand();

		register(itemChromiumDust);
		register(itemChromiteDust);
		register(itemGreenSand);

		OreDictionary.registerOre("dustChromium", itemChromiumDust);
		OreDictionary.registerOre("dustChromite", itemChromiteDust);
		OreDictionary.registerOre("dustGreenSand", itemGreenSand);
	}

	private static void register(Item item) {
		GameRegistry.registerItem(item, "erates_realmachines_" + item.getUnlocalizedName().substring(5));
	}
}