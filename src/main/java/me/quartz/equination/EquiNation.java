package me.quartz.equination;

import me.quartz.equination.commands.HBreedCommand;
import me.quartz.equination.commands.HSpawnCommand;
import me.quartz.equination.horse.HorseManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EquiNation extends JavaPlugin {

    private static EquiNation instance;
    private HorseManager horseManager;

    @Override
    public void onEnable() {
        registerManagers();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    private void registerManagers() {
        horseManager = new HorseManager();
    }

    private void registerCommands() {
        getCommand("hspawn").setExecutor(new HSpawnCommand());
        getCommand("hbreed").setExecutor(new HBreedCommand());
    }

    public static EquiNation getInstance() {
        return instance;
    }

    public HorseManager getHorseManager() {
        return horseManager;
    }
}
