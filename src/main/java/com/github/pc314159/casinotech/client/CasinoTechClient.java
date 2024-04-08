package com.github.pc314159.casinotech.client;

import com.github.pc314159.casinotech.event.AutoDarts;
import com.github.pc314159.casinotech.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;

public class CasinoTechClient implements ClientModInitializer {
    private boolean modActive = false;
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register(this);
        AutoDarts.register(this);
    }

    public boolean isModActive() {
        return modActive;
    }

    public void setModActive(boolean modActive) {
        this.modActive = modActive;
    }
}
