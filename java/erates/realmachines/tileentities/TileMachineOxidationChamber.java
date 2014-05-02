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

public class TileMachineOxidationChamber extends TileEntityMachine {
	
	private final SimpleInventory _inventory = new SimpleInventory(4, "OxidationChamber", 1);

	public TileMachineOxidationChamber() {
		super();
		items = new ItemStack[4];
	}

	@Override
	public int getSizeInventory() {
		return _inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return _inventory.getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		return _inventory.decrStackSize(i, count);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return _inventory.getStackInSlotOnClosing(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		_inventory.setInventorySlotContents(slotId, itemstack);
	}

	@Override
	public String getInventoryName() {
		return _inventory.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return _inventory.hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return _inventory.getInventoryStackLimit();
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

		NBTTagCompound inventoryTag = new NBTTagCompound();
		_inventory.writeToNBT(inventoryTag);
		compound.setTag("inventory", inventoryTag);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		NBTTagCompound p = (NBTTagCompound) compound.getTag("inventory");
		_inventory.readFromNBT(p);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (worldObj.isRemote) return;

		ItemStack recipeOutputStack = RecipeHelper.getRecipeOutput(Recipe.OXIDATION_CHAMBER, new ItemStack[] { getStackInSlot(0), getStackInSlot(1), getStackInSlot(2) });
		if (recipeOutputStack == null) {
			currentWorkTime = 0;
			return;
		}

		if (RecipeHelper.getValidRecipe(Recipe.OXIDATION_CHAMBER, new ItemStack[] { getStackInSlot(0), getStackInSlot(1), getStackInSlot(2) }) != null) {
			if (getStackInSlot(3) != null) {
				if (getStackInSlot(3).getMaxStackSize() < getStackInSlot(3).stackSize + recipeOutputStack.stackSize) return;
				if (!getStackInSlot(3).getUnlocalizedName().equals(recipeOutputStack.getUnlocalizedName())) return;
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
