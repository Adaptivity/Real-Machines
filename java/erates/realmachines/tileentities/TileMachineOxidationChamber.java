package erates.realmachines.tileentities;

import buildcraft.BuildCraftFactory;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import erates.realmachines.recipes.Recipe;
import erates.realmachines.recipes.RecipeHelper;
import erates.realmachines.recipes.RecipeOxidationChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

public class TileMachineOxidationChamber extends TileEntityMachine implements IInventory {

	private ItemStack[] items;

	public TileMachineOxidationChamber() {
		super();
		items = new ItemStack[4];
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemStack = getStackInSlot(i);

		if (itemStack != null) {
			if (itemStack.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				itemStack = itemStack.splitStack(count);
			}
		}

		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);

		setInventorySlotContents(i, null);

		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		items[i] = itemStack;

		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "InventoryOxidationChamber";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return RecipeHelper.isStackValidForSlot(Recipe.OXIDATION_CHAMBER, itemStack, i);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		NBTTagList items = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);

			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}

		compound.setTag("Items", items);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		NBTTagList items = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
			int slot = item.getByte("Slot");

			if (slot >= 0 && slot <= getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (worldObj.isRemote) return;

		ItemStack recipeOutputStack = RecipeHelper.getRecipeOutput(Recipe.OXIDATION_CHAMBER, new ItemStack[] { getStackInSlot(0), getStackInSlot(1), getStackInSlot(2) });
		if (recipeOutputStack == null) return;

		if (RecipeHelper.getValidRecipe(Recipe.OXIDATION_CHAMBER, new ItemStack[] { getStackInSlot(0), getStackInSlot(1), getStackInSlot(2) }) != null) {
			if (getStackInSlot(3) != null) {
				if (getStackInSlot(3).getMaxStackSize() < getStackInSlot(3).stackSize + recipeOutputStack.stackSize) return;
			}

			if (mjStored > POWER_USAGE) {
				mjStored -= POWER_USAGE;
			} else {
				return;
			}

			isWorking = true;
			currentWorkTime++;
			if (currentWorkTime == MAX_WORK_TICKS) {

				Recipe recipe = RecipeHelper.getValidRecipe(Recipe.OXIDATION_CHAMBER, new ItemStack[] { getStackInSlot(0), getStackInSlot(1), getStackInSlot(2) });

				decrStackSize(0, recipe.getInputItemStack(0).stackSize);
				decrStackSize(1, recipe.getInputItemStack(1).stackSize);
				decrStackSize(2, recipe.getInputItemStack(2).stackSize);

				ItemStack outputStack = getStackInSlot(3);
				if (outputStack == null) {
					setInventorySlotContents(3, recipeOutputStack);
				} else {
					outputStack.stackSize += recipeOutputStack.stackSize;
					setInventorySlotContents(3, outputStack);
				}
				currentWorkTime = 0;
				isWorking = false;
			}
		} else {
			currentWorkTime = 0;
			isWorking = false;
		}
	}
}
