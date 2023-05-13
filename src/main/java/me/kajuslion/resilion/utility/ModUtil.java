package me.kajuslion.resilion.utility;

import net.minecraft.util.math.Direction;

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
