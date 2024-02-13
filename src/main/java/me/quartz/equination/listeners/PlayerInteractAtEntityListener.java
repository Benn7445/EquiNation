package me.quartz.equination.listeners;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void playerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        ItemStack itemStack = event.getPlayer().getItemInHand();
        if(entity instanceof Horse && itemStack.getType() == Material.NAME_TAG && itemStack.getItemMeta() != null && !itemStack.getItemMeta().getDisplayName().equals("Name Tag")) {
            Horse horse = (Horse) entity;
            CHorse cHorse = EquiNation.getInstance().getHorseManager().getHorse(horse);
            if(cHorse != null) {
                cHorse.rename(itemStack.getItemMeta().getDisplayName());
            }
        }
    }
}
