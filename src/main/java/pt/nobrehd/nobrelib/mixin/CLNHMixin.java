package pt.nobrehd.nobrelib.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pt.nobrehd.nobrelib.NobreLib;

@Mixin(ClientLoginNetworkHandler.class)
public class CLNHMixin {
    @Final
    @Shadow
    private ServerInfo serverInfo;

    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "onSuccess")
    private void onSuccess(CallbackInfo ci) {
        //if (serverInfo == null) ConfigManager.load("singleplayer");
        //else ConfigManager.load(serverInfo.address);
    }

    @Inject(at = @At("RETURN"), method = "onSuccess")
    private void onSuccessReturn(CallbackInfo ci) {
        ChatHud chatHud = client.inGameHud.getChatHud();
        String server = "Config loaded for server: " + (serverInfo == null ? "singleplayer" : serverInfo.address);
        String loadedMods = "Loaded mods: " + NobreLib.getListOfMods();
        int length = Math.max(server.length(), loadedMods.length()) + 2;
        chatHud.addMessage(Text.literal("-".repeat(length)).styled(style -> style.withColor(0x4343B5)));
        chatHud.addMessage(Text.literal("NobreLib").styled(style -> style.withColor(0x2E7AE6)));
        chatHud.addMessage(Text.literal(loadedMods));
        chatHud.addMessage(Text.literal(server));
        chatHud.addMessage(Text.literal("-".repeat(length)).styled(style -> style.withColor(0x4343B5)));
        chatHud.addMessage(Text.literal(""));
    }
}
