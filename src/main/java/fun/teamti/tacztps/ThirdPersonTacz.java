package fun.teamti.tacztps;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(ThirdPersonTacz.MOD_ID)
public class ThirdPersonTacz
{
    public static final String MOD_ID = "tacz_leawindtps_compat";

    public ThirdPersonTacz()
    {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC, "tac-leawindtps.toml");
    }
}
