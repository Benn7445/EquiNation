package me.quartz.equination.listeners;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.getVehicle() instanceof Horse horse) {
            CHorse cHorse = EquiNation.getInstance().getHorseManager().getHorse(horse);
            if(event.getFrom().distance(Objects.requireNonNull(event.getTo())) > 0.9) cHorse.addRan();
        }
    }
}
