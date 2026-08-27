package net.fedoo0.mysteries.beyonder.pathway.main.death.abilities;

import net.fedoo0.mysteries.MysteriesMod;
import net.fedoo0.mysteries.beyonder.Beyonder;
import net.fedoo0.mysteries.beyonder.BeyonderData;
import net.fedoo0.mysteries.beyonder.abilities.AbilityContext;
import net.fedoo0.mysteries.beyonder.abilities.PassiveAbility;
import net.fedoo0.mysteries.beyonder.pathway.Pathway;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = MysteriesMod.MOD_ID)
public class PhysicalEnhancement extends PassiveAbility {
    // Sequence 9, Physical enhancement. (Probably should replace full damage negation from effects to a partial damage reduction. To be decided later)
    // Also make it not work on withers and other powerful undeads, balancing and stuff
    public PhysicalEnhancement() {
        super(Pathway.death,9);
    }


    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.getType().is(EntityTypeTags.UNDEAD)) return;
        LivingEntity target = event.getNewAboutToBeSetTarget();
        if (!(target instanceof Player player) || player.level().isClientSide()) return;
        ServerLevel overworld = ((ServerLevel) player.level().getServer().overworld()) ;

        BeyonderData beyonderData = Beyonder.get(overworld).getBeyonder(player.getUUID());
        if (beyonderData == null) return;

        if (beyonderData.getPathway().equals(Pathway.death) && beyonderData.getSequence() <= 9) {
            event.setCanceled(true);
        }

    }
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player player) || player.level().isClientSide()) return;
        if (entity.getTicksFrozen() == 0) return;

        ServerLevel overworld = ((ServerLevel) player.level().getServer().overworld()) ;
        BeyonderData beyonderData = Beyonder.get(overworld).getBeyonder(player.getUUID());
        if (beyonderData.getPathway().equals(Pathway.death) && beyonderData.getSequence() <= 9) {
            entity.setTicksFrozen(0);
        }

    }

    @SubscribeEvent
    public static void onEffectApply(MobEffectEvent.Applicable event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player player) || player.level().isClientSide()) return;
        ServerLevel overworld = ((ServerLevel) player.level().getServer().overworld()) ;
        BeyonderData beyonderData = Beyonder.get(overworld).getBeyonder(player.getUUID());
        if (event.getEffectInstance().getEffect().is(MobEffects.WITHER) && beyonderData.getPathway().equals(Pathway.death) && beyonderData.getSequence() <= 9) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }


    @Override
    protected void onActivate(AbilityContext context) {

    }

    @Override
    protected void onDeactivate(AbilityContext context) {

    }

    @Override
    public void tick(AbilityContext context) {
        context.caster().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20,0, false, false, false));
    }
}

