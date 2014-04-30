package cofh.api.item;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Implement this interface on TileEntities which should connect to item transportation blocks. This is intended for blocks which generate energy but do not
 * accept it.
 * 
 * Note that {@link IInventoryHandler} is an extension of this.
 * 
 * @author King Lemming
 * 
 */
public interface IItemConnection {

	boolean canConnectItem(ForgeDirection from);

}
