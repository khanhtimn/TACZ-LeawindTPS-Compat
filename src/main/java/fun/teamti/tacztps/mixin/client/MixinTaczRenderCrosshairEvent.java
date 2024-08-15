package fun.teamti.tacztps.mixin.client;

import com.tacz.guns.client.event.RenderCrosshairEvent;
import fun.teamti.tacztps.client.LeawindCompat;
import net.minecraft.client.CameraType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = RenderCrosshairEvent.class, remap = false)
public class MixinTaczRenderCrosshairEvent {

    @Redirect(
            method = "renderCrosshair",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/CameraType;isFirstPerson()Z", remap = true),
            remap = false, require = 0
    )
    private static boolean redirectIsFirstPerson(CameraType cameraType) {
        boolean leawindThirdPersonForceShow = LeawindCompat.showCrosshair();

        return cameraType.isFirstPerson() || leawindThirdPersonForceShow;
    }

}
