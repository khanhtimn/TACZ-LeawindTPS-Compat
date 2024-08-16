package fun.teamti.tacztps.client;

import net.leawind.mc.api.base.GameStatus;
import net.leawind.mc.thirdperson.ThirdPersonStatus;
import net.minecraftforge.fml.ModList;

public class LeawindCompat {

    private static boolean INSTALLED = false;

    public static void init() {
        INSTALLED = ModList.get().isLoaded("leawind_third_person");
    }

    public static boolean showCrosshair() {
        if(INSTALLED) {
            return ThirdPersonStatus.shouldRenderThirdPersonCrosshair();
        }
        return false;
    }

    public static void switchFirstPerson() {
        if(INSTALLED) {
            GameStatus.isPerspectiveInverted = true;;
        }
    }
}
