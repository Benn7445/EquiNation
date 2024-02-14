package me.quartz.equination.listeners;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

import java.util.Objects;

public class EntityBreedListener implements Listener {

    @EventHandler
    public void entityBreedEvent(EntityBreedEvent event) {
        if(event.getEntity() instanceof Horse horse) {
            CHorse mother = EquiNation.getInstance().getHorseManager().getHorse(event.getMother());
            CHorse father = EquiNation.getInstance().getHorseManager().getHorse(event.getFather());
            double speedCombined =
                    ((mother != null ? mother.getSpeed() : EquiNation.getInstance().getHorseManager().getStartingSpeed()) +
                            (father != null ? father.getSpeed() : EquiNation.getInstance().getHorseManager().getStartingSpeed())) / 2;
            double jumpCombined =
            ((mother != null ? mother.getJump() : EquiNation.getInstance().getHorseManager().getStartingJump()) +
                    (father != null ? father.getJump() : EquiNation.getInstance().getHorseManager().getStartingJump())) / 2;
            CHorse cHorse = EquiNation.getInstance().getHorseManager().breedHorse(horse, speedCombined, jumpCombined);
            cHorse.addOwner(Objects.requireNonNull(event.getBreeder()).getUniqueId());
        }
    }
}
