package me.quartz.equination;

import me.quartz.equination.commands.*;
import me.quartz.equination.files.FileManager;
import me.quartz.equination.horse.HorseManager;
import me.quartz.equination.listeners.*;
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
        getCommand("addowner").setExecutor(new AddOwnerCommand());
        getCommand("addtrust").setExecutor(new AddTrustCommand());
        getCommand("delowner").setExecutor(new DelOwnerCommand());
        getCommand("deltrust").setExecutor(new DelTrustCommand());
        getCommand("hage").setExecutor(new HAgeCommand());
        getCommand("hbreed").setExecutor(new HBreedCommand());
        getCommand("hhealth").setExecutor(new HHealthCommand());
        getCommand("hheight").setExecutor(new HHeightCommand());
        getCommand("hid").setExecutor(new HIdCommand());
        getCommand("hspawn").setExecutor(new HSpawnCommand());
        getCommand("htame").setExecutor(new HTameCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new EntityBreedListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityMountListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityTameListener(), this);
        Bukkit.getPluginManager().registerEvents(new HorseJumpListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractAtEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
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
