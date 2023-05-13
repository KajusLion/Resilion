package me.kajuslion.resilion.utility;

import me.kajuslion.resilion.entity.InvisEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import net.minecraft.world.World;

public class ModUtil {

    public static float getYawFromDirection(Direction direction) {
        if (direction == Direction.NORTH) {
            return 180;
        } else if (direction == Direction.WEST) {
            return 90;
        } else if (direction == Direction.SOUTH) {
            return 0;
        } else {
            return -90;

        }
    }




}
