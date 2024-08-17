package fun.teamti.tacztps.mixin.client;

import com.tacz.guns.client.event.CameraSetupEvent;
import net.leawind.mc.thirdperson.ThirdPerson;
import net.leawind.mc.thirdperson.ThirdPersonStatus;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CameraSetupEvent.class, remap = false)
public class MixinCameraSetupEvent {

    @Inject(method = "applyCameraRecoil", at = @At("HEAD"), cancellable = true)
    private static void fixNoRecoilOnSsBug(ViewportEvent.ComputeCameraAngles event, CallbackInfo ci) {
        if (event.getPhase() == EventPriority.NORMAL && ThirdPerson.isAvailable() && ThirdPersonStatus.isRenderingInThirdPerson()) {
            ci.cancel();
        }
    }

}


