package fun.teamti.tacztps.mixin.client;

import com.tacz.guns.api.DefaultAssets;
import com.tacz.guns.api.client.gameplay.IClientPlayerGunOperator;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.api.item.attachment.AttachmentType;
import com.tacz.guns.client.event.TickAnimationEvent;
import com.tacz.guns.client.resource.index.ClientGunIndex;
import fun.teamti.tacztps.ClientConfig;
import com.github.leawind.api.base.GameStatus;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TickAnimationEvent.class, remap = false)
public class MixinTickAnimationEvent {

    @Inject(method = "lambda$tickAnimation$0", at = @At(value = "INVOKE", target = "Lcom/tacz/guns/resource/pojo/data/gun/GunData;getBolt()Lcom/tacz/guns/resource/pojo/data/gun/Bolt;"))
    private static void onScopeSwitchFirstPerson(IClientPlayerGunOperator clientGunOperator, LocalPlayer player, IGun iGun, ItemStack mainhandItem, ClientGunIndex gunIndex, CallbackInfo ci) {
        ResourceLocation scopeId = iGun.getAttachmentId(mainhandItem, AttachmentType.SCOPE);
        if (clientGunOperator.isAim()) {
            if (ClientConfig.SWITCH_FIRST_PERSON_AIMING.get()) {
                GameStatus.isPerspectiveInverted = true;
            } else if (!DefaultAssets.isEmptyAttachmentId(scopeId) && ClientConfig.SWITCH_FIRST_PERSON_SCOPING.get()) {
                GameStatus.isPerspectiveInverted = true;
            }
        }
    }
}
