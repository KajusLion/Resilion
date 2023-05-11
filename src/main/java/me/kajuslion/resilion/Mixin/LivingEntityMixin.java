package me.kajuslion.resilion.Mixin;


import me.kajuslion.resilion.item.ModItems;
import me.kajuslion.resilion.Resilion;



import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    private DamageSource onApplyDamage_damageSource;

    @Inject(method = "tryUseTotem", at = @At("RETURN"), cancellable = true)
    private void onTryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        LivingEntity entity = (LivingEntity)(Object)this;


        Resilion.LOGGER.info("Start (Chapter 1)");
        //LivingEntity entity = (LivingEntity)(Object)this;

        if (info.getReturnValue()) {
            return;
        }

        ItemStack itemStack = null;
        Hand[] handValues = Hand.values();

        for (Hand hand : handValues) {
            Resilion.LOGGER.info("for 1");
            ItemStack stackInHand = entity.getStackInHand(hand);
            final Random r = new Random();
            Resilion.LOGGER.info("for 2");
            if (stackInHand.isOf(ModItems.KAJUS_TOTEM.asItem())){
                Resilion.LOGGER.info("IT IS!");
            //if (itemStack(ModItems.KAJUS_TOTEM.asItem())) {
                itemStack = stackInHand.copy();
                stackInHand.decrement(1);
                break;
            }

        }

        Resilion.LOGGER.info("Chapter 2");

        if (itemStack == null) {
            return;
        }


        if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));
            Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
        }


        entity.setHealth(1.0F);
        entity.clearStatusEffects();

        if (itemStack.isOf(ModItems.KAJUS_TOTEM.asItem())) {

            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));



        entity.world.sendEntityStatus(entity, (byte)35);
        info.setReturnValue(true);
        }
    }
}