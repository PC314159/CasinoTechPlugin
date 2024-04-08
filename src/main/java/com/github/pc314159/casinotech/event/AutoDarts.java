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

    public static void register(CasinoTechClient ctc) {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (ctc.isModActive() && GuiUtils.getCurrentScreen() instanceof GenericContainerScreen) {
                GenericContainerScreen screen = (GenericContainerScreen) GuiUtils.getCurrentScreen();
                GenericContainerScreenHandler handler = screen.getScreenHandler();
                Inventory inv = handler.getInventory();

                if (screen.getTitle().getString().equals(Text.of("            投镖游戏").getString())) {
//                    client.player.sendMessage(Text.of("in darts"));
                    if (handler.slots.get(34).getStack().getName().getString().equals(Text.of("下注以开始旋转!").getString())) {
//                        client.player.sendMessage(Text.of("awaiting"));
                        if (handler.getCursorStack().getItem().equals(Items.DIAMOND)) {
                            if (handler.slots.get(72).getStack().getItem().equals(Items.DIAMOND)) {
                                System.out.println(handler.slots.get(72).getStack());
//                                client.player.sendMessage(Text.of("toss"));
                                client.interactionManager.clickSlot(handler.syncId, handler.EMPTY_SPACE_SLOT_INDEX, 0, SlotActionType.PICKUP, client.player);
                            }
                            else{
                                System.out.println(handler.slots.get(72).getStack());
//                                client.player.sendMessage(Text.of("place"));
                                client.interactionManager.clickSlot(handler.syncId, 25, 0, SlotActionType.PICKUP, client.player);
                            }
                        }
                        else{
                            if (handler.slots.get(73).getStack().getItem().equals(Items.DIAMOND)) {
                                System.out.println(handler.slots.get(73).getStack());
//                                client.player.sendMessage(Text.of("pickup extra"));
                                client.interactionManager.clickSlot(handler.syncId, 73, 0, SlotActionType.PICKUP, client.player);
                            }
                            else if (handler.slots.get(72).getStack().getItem().equals(Items.DIAMOND)) {
                                System.out.println(handler.slots.get(72).getStack());
//                                client.player.sendMessage(Text.of("pickup"));
                                client.interactionManager.clickSlot(handler.syncId, 72, 0, SlotActionType.PICKUP, client.player);
                            }
                        }
                    }
                    else if (handler.slots.get(34).getStack().getName().getString().equals(Text.of("点击旋转").getString())) {
                        client.interactionManager.clickSlot(handler.syncId, 34, 0, SlotActionType.PICKUP_ALL, client.player);
                    }
                }


                for (int i = 0; i < inv.size(); i++) {
                    if (inv.getStack(i).getItem().equals(Items.PLAYER_HEAD)) {
//                        client.player.sendMessage(Text.of("---------" + i));
                        if (i != AutoDarts.previous) {
                            AutoDarts.previous = i;
//                            client.player.sendMessage(Text.of("clicked"));
                            client.interactionManager.clickSlot(handler.syncId, handler.getSlot(i).id, 0, SlotActionType.PICKUP, client.player);
                            break;
                        }
                    }
                }
            }
        });
    }
}
