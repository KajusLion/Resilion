package me.kajuslion.resilion.entity;

import me.kajuslion.resilion.Resilion;
import static me.kajuslion.resilion.utility.ModUtil.getYawFromDirection;

import net.minecraft.block.Block;
import net.minecraft.entity.Dismounting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class InvisEntity extends Entity {

    public boolean isRidden = false;

    public InvisEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
        this.noClip = true;
        this.setNoGravity(true);
    }

    public void setProperPosition(PlayerEntity player, BlockPos source, double offsetY,  Direction direction){
        Resilion.LOGGER.info("Direction " + direction);

        this.setPos(source.getX() + 0.5, source.getY() + offsetY, source.getZ() + 0.5);
        this.setRotation(direction.getOpposite().getOffsetY(), direction.getOffsetX());
        //player.setRotation(direction.get(), 0F);

    }

    @Override
    public void initDataTracker() {

    }

    //When world is opened, isRidden is set. Players may close the world while sitting.
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.isRidden = nbt.getBoolean("isSomeoneSitting");
    }


    //Saves the entity on world close while the player is sitting to avoid de-sitting when player comes back.
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("isSomeoneSitting", this.isRidden);
        nbt.putIntArray("blockpos", new int[]{blockpos.getX(),blockpos.getY(),blockpos.getZ()});
    }



    //Making sure it only takes damage when removed or void related.
    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return this.isRemoved() || this.isInvulnerable() && damageSource != DamageSource.OUT_OF_WORLD;
    }

    //Making it not attackable
    public boolean isAttackable() {
        return false;
    }
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public void tick(){
        super.tick();
        if(isRidden && this.getPassengerList().isEmpty()){
            this.kill();
        }
    }

    BlockPos blockpos;
    public void setBlockPos(BlockPos pos){
        blockpos = pos;
    }

    public static InvisEntity getInvisEntity(World world, BlockPos bp){
        if (!world.isClient) {
            InvisEntity invisentity;



            invisentity = ;

            return invisentity;
        }
        return null;
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Direction original = this.getHorizontalFacing();
        Direction[] offsets = {original, original.rotateYClockwise(), original.rotateYCounterclockwise(), original.getOpposite()};
        for(Direction dir : offsets)
        {
            Vec3d safeVec = Dismounting.findRespawnPos(passenger.getType(), passenger.getWorld(), passenger.getBlockPos().offset(dir), false);
            if(safeVec != null)
            {
                return safeVec.add(0, 0.25, 0);
            }
        }
        return passenger.getPos().add(0,0.5,0);
    }

    public void unsitPlayer(PlayerEntity player){
        if (!this.world.isClient) {
            this.isRidden = false;
            this.kill();
            //player.stopRiding();
        }
    }

    //Make the player ride the chair
    public void sitPlayer(PlayerEntity player, Direction direction) {
        if (!this.world.isClient) {
            this.isRidden = true;

            float yaw = getYawFromDirection(direction);

            player.setYaw(yaw);
            player.setPitch(this.getPitch());
            player.startRiding(this);
        }
    }

    //This tells minecraft that an entity has spawned.
    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}
