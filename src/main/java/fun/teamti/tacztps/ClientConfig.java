package fun.teamti.tacztps;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue SWITCH_FIRST_PERSON_AIMING;
    public static final ForgeConfigSpec.BooleanValue SWITCH_FIRST_PERSON_SCOPING;

    static {
        BUILDER.push("Configure how perspective is handled while using TACZ guns");
        SWITCH_FIRST_PERSON_AIMING = BUILDER
                .comment("""
                        If enabled, holding the aim button will put you in/out of first person view
                        This option overrides "switch_first_person_scoping"
                        This only works while TACZ's Config: tacz-client.key.holdToAim is set to true""")
                .define("switch_first_person_aiming", false);
        SWITCH_FIRST_PERSON_SCOPING = BUILDER
                .comment("""
                        If enabled, only when holding the aim button on a gun with a scope attachment will put you in/out of first person view
                        This only works while TACZ's Config: tacz-client.key.holdToAim is set to true""")
                .define("switch_first_person_scoping", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
