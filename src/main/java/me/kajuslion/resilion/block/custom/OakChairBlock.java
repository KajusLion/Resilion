package me.kajuslion.resilion.block.custom;

import me.kajuslion.resilion.Resilion;
import net.minecraft.block.Block;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
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

import java.util.stream.Stream;

public class OakChairBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


    public OakChairBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Resilion.LOGGER.info("1");
        return ActionResult.SUCCESS;


    }
    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(2, 6, 0.25, 14, 8, 1.75),
            Block.createCuboidShape(0.25, 6, 2, 1.75, 8, 14),
            Block.createCuboidShape(2, 6, 14.25, 14, 8, 15.75),
            Block.createCuboidShape(14.25, 6, 2, 15.75, 8, 14),
            Block.createCuboidShape(14, 0, 14, 16, 7, 16),
            Block.createCuboidShape(14, 0, 0, 16, 7, 2),
            Block.createCuboidShape(0, 0, 0, 2, 7, 2),
            Block.createCuboidShape(0, 0, 14, 2, 7, 16),
            Block.createCuboidShape(-0.25, 7, -0.25, 16.25, 9, 16.25),
            Block.createCuboidShape(1, 9, 1, 15, 9.5, 15)
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

