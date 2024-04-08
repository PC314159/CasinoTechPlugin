package com.github.pc314159.casinotech.event;

import com.github.pc314159.casinotech.client.CasinoTechClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_CASINOTECH = "Casino Tech";
    public static final String KEY_DARTS = "Auto Darts";

    public static KeyBinding dartsKey;

    public static void registerKeyInputs(CasinoTechClient ctc) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(dartsKey.wasPressed()) {
                // This happens when our custom key is pressed
                ctc.setModActive(!ctc.isModActive());
                if (ctc.isModActive()) {
                    client.player.sendMessage(Text.of("Casino Tech is enabled"));
                }
                else{
                    client.player.sendMessage(Text.of("Casino Tech is disabled"));
                }
            }
        });
    }

    public static void register(CasinoTechClient ctc) {
        dartsKey= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DARTS,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_CASINOTECH
        ));

        registerKeyInputs(ctc);
    }
}
