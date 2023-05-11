package me.kajuslion.resilion.mixin;

import me.kajuslion.resilion.item.ModItems;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Inject(method = "getActiveTotemOfUndying", at = @At("RETURN"), cancellable = true)
    private static void onGetActiveTotemOfUndying(PlayerEntity player, CallbackInfoReturnable<ItemStack> info) {
        Hand[] handValues = Hand.values();
        for(Hand hand : handValues) {
            ItemStack itemStack = player.getStackInHand(hand);

            if (!itemStack.isOf(ModItems.KAJUS_TOTEM.asItem())) continue;


            info.setReturnValue(itemStack);

        }
    }
}
