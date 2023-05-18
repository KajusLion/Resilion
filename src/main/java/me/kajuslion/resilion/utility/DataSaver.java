package me.kajuslion.resilion.utility;
import net.minecraft.nbt.NbtCompound;

public class DataSaver {

    public interface EntityDataSaver {
        NbtCompound getPersistentData();
    }
}
