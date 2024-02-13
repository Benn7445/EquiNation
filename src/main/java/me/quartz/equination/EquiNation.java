package me.quartz.equination;

import me.quartz.equination.commands.HBreedCommand;
import me.quartz.equination.commands.HSpawnCommand;
import me.quartz.equination.commands.TameCommand;
import me.quartz.equination.files.FileManager;
import me.quartz.equination.horse.HorseManager;
import me.quartz.equination.listeners.EntityDamageByEntityListener;
import me.quartz.equination.listeners.PlayerInteractAtEntityListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EquiNation extends JavaPlugin {

    private static EquiNation instance;
    private FileManager fileManager;
    private HorseManager horseManager;

    @Override
    public void onEnable() {
        instance = this;
        registerFiles();
        registerManagers();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
    }

    private void registerFiles() {
        saveDefaultConfig();
    }

    private void registerManagers() {
        fileManager = new FileManager();
        horseManager = new HorseManager();
    }

    private void registerCommands() {
        getCommand("hspawn").setExecutor(new HSpawnCommand());
        getCommand("hbreed").setExecutor(new HBreedCommand());
        getCommand("tame").setExecutor(new TameCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractAtEntityListener(), this);
    }

    public static EquiNation getInstance() {
        return instance;
    }

    public HorseManager getHorseManager() {
        return horseManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
