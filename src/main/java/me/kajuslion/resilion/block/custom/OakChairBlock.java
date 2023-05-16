package me.kajuslion.resilion.block.custom;

import me.kajuslion.resilion.Resilion;
import me.kajuslion.resilion.entity.InvisEntity;
import me.kajuslion.resilion.entity.InvisEntity;

import net.minecraft.block.Block;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;



import java.util.List;
import java.util.stream.Stream;


public class OakChairBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


    public OakChairBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient()){
            List<InvisEntity> seats = world.getEntitiesByClass(InvisEntity.class, new Box(pos.getX(), pos.getY(), pos.getZ(),
                    pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0), i -> true);
            //This checks if chair is occupied.
            if(seats.isEmpty())
            {
                //Create an entity
                InvisEntity invis = Resilion.INVIS.create(world);
                //Set its position
                invis.setProperPosition(player, pos, 0.5, state.get(FACING));

                invis.setBlockPos(pos);

                //Spawn the entity on the world.
                world.spawnEntity(invis);
                //Make the player sit
                invis.sitPlayer(player, state.get(FACING));

            }



        }

        return ActionResult.SUCCESS;


    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        //mycode
        List<InvisEntity> seats = world.getEntitiesByClass(InvisEntity.class, new Box(pos.getX(), pos.getY(), pos.getZ(),
                pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0), i -> true);
        if(!seats.isEmpty())
        {
            for (InvisEntity seat : seats) {

                if (seat.getBlockPos().equals(pos)) {
                    seat.kill();
                    seat.isRidden = false;
                }
            }

        }

    }



    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(-0.25, 9, -0.25, 16.25, 11, 16.25),
            Block.createCuboidShape(1, 11, 1, 15, 11.5, 15),
            Block.createCuboidShape(14, 0, 14, 16, 9, 16),
            Block.createCuboidShape(14.25, 8, 2, 15.75, 10, 14),
            Block.createCuboidShape(14, 0, 0, 16, 9, 2),
            Block.createCuboidShape(2, 8, 0.25, 14, 10, 1.75),
            Block.createCuboidShape(0, 0, 0, 2, 9, 2),
            Block.createCuboidShape(0.25, 8, 2, 1.75, 10, 14),
            Block.createCuboidShape(0, 0, 14, 2, 9, 16),
            Block.createCuboidShape(2, 8, 14.25, 14, 10, 15.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}

