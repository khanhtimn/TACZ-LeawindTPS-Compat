package fun.teamti.tacztps;

import com.tacz.guns.client.event.CameraSetupEvent;
import net.leawind.mc.thirdperson.ThirdPerson;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(
        value = Dist.CLIENT,
        modid = ThirdPersonTacz.MOD_ID
)
public class ModEventSubscriber {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onCameraRotateThirdPerson(ViewportEvent.ComputeCameraAngles event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            float xr = player.getXRot();
            float yr = player.getYRot();
            CameraSetupEvent.applyCameraRecoil(event);
            float drx = player.getXRot() - xr;
            float dry = player.getYRot() - yr;
            player.setXRot(xr);
            player.setYRot(yr);
            ThirdPerson.CAMERA_AGENT.turnCamera(dry, drx);
        }
    }

    //    @SubscribeEvent()
//    public static void onScopeSwitchFirstPerson(TickEvent.ClientTickEvent event) {
//        LocalPlayer player = Minecraft.getInstance().player;
//        if (player != null) {
//            IClientPlayerGunOperator clientGunOperator = IClientPlayerGunOperator.fromLocalPlayer(player);
//            ItemStack mainhandItem = player.getMainHandItem();
//            Item var5 = mainhandItem.getItem();
//            if (var5 instanceof IGun iGun) {
//                ResourceLocation var6 = iGun.getGunId(mainhandItem);
//                ResourceLocation scopeId = iGun.getAttachmentId(mainhandItem, AttachmentType.SCOPE);
//                TimelessAPI.getClientGunIndex(var6).ifPresent((gunIndex) -> {
//                    GunAnimationStateMachine animationStateMachine = gunIndex.getAnimationStateMachine();
//                    if (animationStateMachine != null) {
//                        if(clientGunOperator.isAim()) {
//                            if (!DefaultAssets.isEmptyAttachmentId(scopeId)) {
//                                GameStatus.isPerspectiveInverted = true;
//                            }
//                        }
//                    }
//                });
//            }
//        }
//    }
}
