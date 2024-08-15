package fun.teamti.tacztps.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
import com.tacz.guns.client.event.RenderCrosshairEvent;
import fun.teamti.tacztps.client.LeawindCompat;
import net.leawind.mc.thirdperson.ThirdPerson;
import net.leawind.mc.thirdperson.ThirdPersonStatus;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderCrosshairEvent.class, remap = false)
public class MixinTaczRenderCrosshairEvent {
    //    @Redirect(
//            method = "renderCrosshair",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/CameraType;isFirstPerson()Z", remap = true),
//            remap = false, require = 0
//    )
//    private static boolean redirectIsFirstPerson(CameraType cameraType) {
//        boolean leawindThirdPersonForceShow = LeawindCompat.showCrosshair();
//
//        return cameraType.isFirstPerson() || leawindThirdPersonForceShow;
//    }
    @ModifyVariable(
            method = "renderCrosshair",
            at = @At("STORE"),
            ordinal = 0,
            remap = false, require = 0
    )
    private static boolean modifyCrosshairVisibilityCheck(boolean original, @Local(ordinal = 0) boolean shoulderSurfingForceShow) {
        return original || LeawindCompat.showCrosshair();
    }

//    @ModifyExpressionValue(
//            method = { "onRenderOverlay", "lambda$onRenderOverlay$0" },
//            at = @At(value = "INVOKE", target = "Lcom/tacz/guns/api/client/gameplay/IClientPlayerGunOperator;getClientAimingProgress(F)F")
//    )
//    private static float neverHideCrosshairWhenSs(float oldValue) {
//        return ShoulderSurfing.getInstance().isShoulderSurfing() ? 0 : oldValue;
//    }

    @Definition(
            id = "shouldHideCrossHair",
            method = "Lcom/tacz/guns/client/animation/statemachine/GunAnimationStateMachine;shouldHideCrossHair()Z",
            remap = false
    )
    @Expression("?.shouldHideCrossHair() == 0")
    @Inject(
            method = "onRenderOverlay",
            at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.BEFORE)
    )
    private static void switchToFirstPersonWhenZoom(RenderGuiOverlayEvent.Pre event, CallbackInfo ci) {
        System.out.println("Injected after animationStateMachine initialization");
    }

    @Mixin(value = GunAnimationStateMachine.class, remap = false)
    public static class showCrosshairThirdPerson {
        @Inject(method = "shouldHideCrossHair", at = @At("HEAD"), cancellable = true)
        private void neverHideCrosshairWhenSs(CallbackInfoReturnable<Boolean> cir) {
            if (ThirdPerson.isAvailable() && ThirdPersonStatus.isRenderingInThirdPerson()) {
                cir.setReturnValue(false);
            }
        }
    }

}
