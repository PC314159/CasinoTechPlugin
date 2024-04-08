package com.github.pc314159.casinotech.event;

import com.github.pc314159.casinotech.client.CasinoTechClient;
import fi.dy.masa.malilib.util.GuiUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;

public class AutoDarts {
    public static int previous = -1;
    public static int placebetcd = 0;

    public static void register(CasinoTechClient ctc) {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (ctc.isModActive() && GuiUtils.getCurrentScreen() instanceof GenericContainerScreen) {
                GenericContainerScreen screen = (GenericContainerScreen) GuiUtils.getCurrentScreen();
                GenericContainerScreenHandler handler = screen.getScreenHandler();

                if (screen.getTitle().getString().equals(Text.of("            投镖游戏").getString())) {
//                    currently in darts game
                    if (handler.slots.get(34).getStack().getName().getString().equals(Text.of("下注以开始旋转!").getString())) {
//                        place bet in slot
                        if (handler.getCursorStack().getItem().equals(Items.DIAMOND)) {
                            client.interactionManager.clickSlot(handler.syncId, 25, 0, SlotActionType.PICKUP, client.player);
                            placebetcd = 10;
                        }
//                        get first slot of chips
                        else if (placebetcd == 0) {
                            for (int i = 72; i < 81; i++) {
                                if (handler.slots.get(i).getStack().getItem().equals(Items.DIAMOND)) {
                                    if (handler.slots.get(i).getStack().getName().getString().equals(Text.of("筹码").getString())) {
                                        client.interactionManager.clickSlot(handler.syncId, i, 0, SlotActionType.PICKUP, client.player);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else if (handler.slots.get(34).getStack().getName().getString().equals(Text.of("点击旋转").getString())) {
                        client.interactionManager.clickSlot(handler.syncId, 34, 0, SlotActionType.PICKUP_ALL, client.player);
                    }

                    Inventory inv = handler.getInventory();
                    for (int i = 0; i < inv.size(); i++) {
                        if (inv.getStack(i).getItem().equals(Items.PLAYER_HEAD)) {
                            if (i != AutoDarts.previous) {
                                AutoDarts.previous = i;
                                client.interactionManager.clickSlot(handler.syncId, handler.getSlot(i).id, 0, SlotActionType.PICKUP_ALL, client.player);
                                break;
                            }
                        }
                    }

                    if (placebetcd > 0) {
                        placebetcd -= 1;
                    }
                }
            }
        });
    }
}
