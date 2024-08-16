package fun.teamti.tacztps.client.event;

import com.tacz.guns.api.DefaultAssets;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.api.item.attachment.AttachmentType;
import com.tacz.guns.client.animation.statemachine.GunAnimationStateMachine;
import fun.teamti.tacztps.ThirdPersonTacz;
import net.leawind.mc.api.base.GameStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        value = {Dist.CLIENT},
        modid = ThirdPersonTacz.MOD_ID
)
public class TickAnimationEvent {

//    @SubscribeEvent()
//    public static void switchToFirstPerson(TickEvent.ClientTickEvent event) {
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
