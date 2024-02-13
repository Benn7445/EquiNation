package me.quartz.equination.horse;

import me.quartz.equination.EquiNation;
import me.quartz.equination.files.CustomFile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HorseManager {

    private final List<CHorse> horses;

    public HorseManager() {
        this.horses = new ArrayList<>();
        deserialize();
    }

    public void serialize(CHorse cHorse) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            CustomFile horseFile = EquiNation.getInstance().getFileManager().getHorsesFile();
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".id", cHorse.getId());
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".name", cHorse.getName());
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".owners", cHorse.getOwners().stream().map(UUID::toString).collect(Collectors.toList()));
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".trusts", cHorse.getTrusts().stream().map(UUID::toString).collect(Collectors.toList()));
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".gender", cHorse.getGender().toString());
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".breed", cHorse.getBreed());
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".ran", cHorse.getRan());
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".jumped", cHorse.getJumped());
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".potential_speed", cHorse.getPotentialSpeed());
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".potential_jump", cHorse.getPotentialJump());
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".shod", formatter.format(cHorse.getShod()));
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".fed", formatter.format(cHorse.getFed()));
            horseFile.getCustomConfig().set(cHorse.getEntity() + ".watered", formatter.format(cHorse.getWatered()));
            horseFile.getCustomConfig().save(horseFile.getCustomConfigFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deserialize() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            CustomFile horseFile = EquiNation.getInstance().getFileManager().getHorsesFile();
            for (String id : horseFile.getCustomConfig().getKeys(false)) {
                ConfigurationSection cs = horseFile.getCustomConfig().getConfigurationSection(id);
                if (cs != null) {
                    CHorse cHorse = new CHorse(UUID.fromString(id), cs.getInt("id"), cs.getString("name"),
                            cs.getStringList("owners").stream().map(UUID::fromString).toList(),
                            cs.getStringList("trusts").stream().map(UUID::fromString).toList(),
                            CGender.valueOf(cs.getString("gender")), cs.getString("breed"), cs.getInt("ran"), cs.getInt("jumped"),
                            cs.getDouble("potential_speed"), cs.getDouble("potential_jump"), formatter.parse(cs.getString("shod")),
                            formatter.parse(cs.getString("fed")), formatter.parse(cs.getString("watered")));
                    horses.add(cHorse);
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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
        return getRandom(min, max) / 10;
    }

    public double getStartingSpeed() {
        double min = EquiNation.getInstance().getConfig().getDouble("stats.speed.starting.min");
        double max = EquiNation.getInstance().getConfig().getDouble("stats.speed.starting.min");
        return getRandom(min, max) / 10;
    }

    public double getPotentialJump() {
        double min = EquiNation.getInstance().getConfig().getDouble("stats.jump.potential.min");
        double max = EquiNation.getInstance().getConfig().getDouble("stats.jump.potential.min");
        return getRandom(min, max) / 10;
    }

    public double getStartingJump() {
        double min = EquiNation.getInstance().getConfig().getDouble("stats.jump.starting.min");
        double max = EquiNation.getInstance().getConfig().getDouble("stats.jump.starting.min");
        return getRandom(min, max) / 10;
    }

    private double getRandom(double min, double max) {
        return ((Math.random() * (max - min)) + min);
    }

}
