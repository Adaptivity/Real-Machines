package cofh.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Implement this interface on TileEntities which should connect to energy transportation blocks. This is intended for blocks which generate energy but do not
 * accept it.
 * 
 * Note that {@link IEnergyHandler} is an extension of this.
 * 
 * @author King Lemming
 * 
 */
public interface IEnergyConnection {

	boolean canConnectEnergy(ForgeDirection from);

}
