package me.quartz.equination.commands;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("equination.hspawn")) {
            if(commandSender instanceof Player) {
                Player player = (Player) commandSender;
                Horse horse = player.getWorld().spawn(player.getLocation(), Horse.class);
                EquiNation.getInstance().getHorseManager().attachHorse(horse);
                horse.setJumpStrength(EquiNation.getInstance().getHorseManager().getStartingJump());
                AttributeInstance ai = horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                if(ai != null) ai.setBaseValue(EquiNation.getInstance().getHorseManager().getStartingSpeed());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(EquiNation.getInstance().getConfig().getString("messages.spawned"))));
            } else commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(EquiNation.getInstance().getConfig().getString("messages.only-players"))));
        } else commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                Objects.requireNonNull(EquiNation.getInstance().getConfig().getString("messages.no-permissions"))));
        return true;
    }
}
