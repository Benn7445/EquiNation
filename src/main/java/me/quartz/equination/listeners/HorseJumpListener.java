package me.quartz.equination.listeners;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.HorseJumpEvent;

import java.util.Objects;

public class HorseJumpListener implements Listener {

    @EventHandler
    public void HorseJumpEvent(HorseJumpEvent event) {
        CHorse cHorse = EquiNation.getInstance().getHorseManager().getHorse(event.getEntity());
        cHorse.addJump();
    }
}

