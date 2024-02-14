package me.quartz.equination.listeners;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

public class EntityTameListener implements Listener {

    @EventHandler
    public void EntityTameEvent(EntityTameEvent event) {
        if(event.getEntity() instanceof Horse horse) {
            CHorse cHorse = EquiNation.getInstance().getHorseManager().getHorse(horse);
            if(cHorse == null) EquiNation.getInstance().getHorseManager().attachHorse(horse);
            else if(cHorse.getOwners().isEmpty()) cHorse.addOwner(event.getOwner().getUniqueId());
            else event.setCancelled(true);
        }
    }
}
