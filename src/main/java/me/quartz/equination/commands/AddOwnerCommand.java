package me.quartz.equination.commands;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import java.util.Objects;

public class AddOwnerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Entity entity = player.getVehicle();
            if (entity instanceof Horse) {
                if (strings.length > 0) {
                    CHorse cHorse = EquiNation.getInstance().getHorseManager().getHorse(entity);
                    if (cHorse != null && cHorse.getOwners().contains(player.getUniqueId())) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(strings[0]);
                        cHorse.addOwner(target.getUniqueId());
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                Objects.requireNonNull(EquiNation.getInstance().getConfig().getString("messages.added-owner"))
                                        .replace("%target%", target.getName() != null ? target.getName() : strings[0])));
                    } else player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Objects.requireNonNull(EquiNation.getInstance().getConfig().getString("messages.not-owner"))));
                } else player.sendMessage(ChatColor.RED + "Usage: /addtrust <player>");
            } else commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    Objects.requireNonNull(EquiNation.getInstance().getConfig().getString("messages.not-attached"))));
        } else commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                Objects.requireNonNull(EquiNation.getInstance().getConfig().getString("messages.only-players"))));
        return true;
    }
}