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
        if (world.isClient) {
            Resilion.LOGGER.info("1");
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            Resilion.LOGGER.info("else");
            if (blockEntity instanceof BrewingStandBlockEntity) {
                Resilion.LOGGER.info("BS");
            }

            Resilion.LOGGER.info("return");
            return ActionResult.CONSUME;
        }
    }
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(-0.25, 9, -0.25, 16.25, 11, 16.25),
            Block.createCuboidShape(0, 11, 14, 16, 26, 16),
            Block.createCuboidShape(1, 11, 1, 15, 11.5, 14),
            Block.createCuboidShape(0, 0, 0, 2, 9, 2),
            Block.createCuboidShape(0.25, 8, 2, 1.75, 10, 14),
            Block.createCuboidShape(0, 0, 14, 2, 9, 16),
            Block.createCuboidShape(2, 8, 14.25, 14, 10, 15.75),
            Block.createCuboidShape(14, 0, 14, 16, 9, 16),
            Block.createCuboidShape(14.25, 8, 2, 15.75, 10, 14),
            Block.createCuboidShape(14, 0, 0, 16, 9, 2),
            Block.createCuboidShape(2, 8, 0.25, 14, 10, 1.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(-0.25, 9, -0.25, 16.25, 11, 16.25),
            Block.createCuboidShape(14, 11, 0, 16, 26, 16),
            Block.createCuboidShape(1, 11, 1, 14, 11.5, 15),
            Block.createCuboidShape(0, 0, 14, 2, 9, 16),
            Block.createCuboidShape(2, 8, 14.25, 14, 10, 15.75),
            Block.createCuboidShape(14, 0, 14, 16, 9, 16),
            Block.createCuboidShape(14.25, 8, 2, 15.75, 10, 14),
            Block.createCuboidShape(14, 0, 0, 16, 9, 2),
            Block.createCuboidShape(2, 8, 0.25, 14, 10, 1.75),
            Block.createCuboidShape(0, 0, 0, 2, 9, 2),
            Block.createCuboidShape(0.25, 8, 2, 1.75, 10, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(-0.25, 9, -0.25, 16.25, 11, 16.25),
            Block.createCuboidShape(0, 11, 0, 16, 26, 2),
            Block.createCuboidShape(1, 11, 2, 15, 11.5, 15),
            Block.createCuboidShape(14, 0, 14, 16, 9, 16),
            Block.createCuboidShape(14.25, 8, 2, 15.75, 10, 14),
            Block.createCuboidShape(14, 0, 0, 16, 9, 2),
            Block.createCuboidShape(2, 8, 0.25, 14, 10, 1.75),
            Block.createCuboidShape(0, 0, 0, 2, 9, 2),
            Block.createCuboidShape(0.25, 8, 2, 1.75, 10, 14),
            Block.createCuboidShape(0, 0, 14, 2, 9, 16),
            Block.createCuboidShape(2, 8, 14.25, 14, 10, 15.75)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(-0.25, 9, -0.25, 16.25, 11, 16.25),
            Block.createCuboidShape(0, 11, 0, 2, 26, 16),
            Block.createCuboidShape(2, 11, 1, 15, 11.5, 15),
            Block.createCuboidShape(14, 0, 0, 16, 9, 2),
            Block.createCuboidShape(2, 8, 0.25, 14, 10, 1.75),
            Block.createCuboidShape(0, 0, 0, 2, 9, 2),
            Block.createCuboidShape(0.25, 8, 2, 1.75, 10, 14),
            Block.createCuboidShape(0, 0, 14, 2, 9, 16),
            Block.createCuboidShape(2, 8, 14.25, 14, 10, 15.75),
            Block.createCuboidShape(14, 0, 14, 16, 9, 16),
            Block.createCuboidShape(14.25, 8, 2, 15.75, 10, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
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

