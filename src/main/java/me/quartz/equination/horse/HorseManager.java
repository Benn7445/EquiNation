package me.quartz.equination.horse;

import me.quartz.equination.EquiNation;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class HorseManager {

    private final List<CHorse> horses;

    public HorseManager() {
        this.horses = new ArrayList<>();
    }

    public void attachHorse(Entity entity) {
        CHorse cHorse = new CHorse(entity.getUniqueId());
        horses.add(cHorse);
    }

    public CHorse getHorse(Entity entity) {
        return horses.stream().filter(cHorse -> cHorse.getEntity().toString().equals(entity.getUniqueId().toString())).findAny().orElse(null);
    }

    public double getPotentialSpeed() {
        double min = EquiNation.getInstance().getConfig().getDouble("stats.speed.potential.min");
        double max = EquiNation.getInstance().getConfig().getDouble("stats.speed.potential.min");
        return getRandom(min, max);
    }

    public double getStartingSpeed() {
        double min = EquiNation.getInstance().getConfig().getDouble("stats.speed.starting.min");
        double max = EquiNation.getInstance().getConfig().getDouble("stats.speed.starting.min");
        return getRandom(min, max);
    }

    public double getPotentialJump() {
        double min = EquiNation.getInstance().getConfig().getDouble("stats.jump.potential.min");
        double max = EquiNation.getInstance().getConfig().getDouble("stats.jump.potential.min");
        return getRandom(min, max);
    }

    public double getStartingJump() {
        double min = EquiNation.getInstance().getConfig().getDouble("stats.jump.starting.min");
        double max = EquiNation.getInstance().getConfig().getDouble("stats.jump.starting.min");
        return getRandom(min, max);
    }

    private double getRandom(double min, double max) {
        return ((Math.random() * (max - min)) + min);
    }

}
