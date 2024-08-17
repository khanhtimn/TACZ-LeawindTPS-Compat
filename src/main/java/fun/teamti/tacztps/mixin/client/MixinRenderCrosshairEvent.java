package fun.teamti.tacztps.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
import com.tacz.guns.client.event.RenderCrosshairEvent;
import net.leawind.mc.thirdperson.ThirdPersonStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderCrosshairEvent.class, remap = false)
public class MixinRenderCrosshairEvent {

    @ModifyExpressionValue(
            method = "renderCrosshair",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/CameraType;isFirstPerson()Z")
    )
    private static boolean modifyCrosshairVisibilityCheck(boolean original) {
        return original || ThirdPersonStatus.shouldRenderThirdPersonCrosshair();
    }

    @ModifyExpressionValue(
            method = { "onRenderOverlay", "lambda$onRenderOverlay$0" },
            at = @At(value = "INVOKE", target = "Lcom/tacz/guns/api/client/gameplay/IClientPlayerGunOperator;getClientAimingProgress(F)F")
    )
    private static float forceShowCrosshairWhenAim(float oldValue) {
        return ThirdPersonStatus.shouldRenderThirdPersonCrosshair() ? 0 : oldValue;
    }

    @Mixin(value = GunAnimationStateMachine.class, remap = false)
    public static class ShowCrosshairThirdPerson {
        @Inject(method = "shouldHideCrossHair", at = @At("HEAD"), cancellable = true)
        private void forceShowCrosshairWhenAim(CallbackInfoReturnable<Boolean> cir) {
            if (ThirdPersonStatus.shouldRenderThirdPersonCrosshair()) {
                cir.setReturnValue(false);
            }
        }
    }

}
