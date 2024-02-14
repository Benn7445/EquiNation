package me.quartz.equination.listeners;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        if(damager instanceof Player player && entity instanceof Horse horse && ((Player) damager).getItemInHand().getType() == Material.LEAD) {

            CHorse cHorse = EquiNation.getInstance().getHorseManager().getHorse(horse);
            if(cHorse != null) {
                Inventory inventory = Bukkit.createInventory(null, 45, cHorse.getName() + "'s Info");

                for(int i = 0; i < inventory.getSize() - 1; i++)
                    inventory.setItem(0, createItemStack(Material.BLACK_STAINED_GLASS_PANE, ChatColor.AQUA + "", List.of()));

                inventory.setItem(10, createItemStack(Material.SKELETON_SKULL, "", List.of()));
                inventory.setItem(19, createItemStack(Material.RED_STAINED_GLASS_PANE, "", List.of()));
                inventory.setItem(20, createItemStack(Material.BREAD, "", List.of()));
                inventory.setItem(29, createItemStack(Material.WATER_BUCKET, "", List.of()));
                inventory.setItem(28, createItemStack(Material.RED_STAINED_GLASS_PANE, "", List.of()));
                inventory.setItem(22, createItemStack(player.getName(), "", List.of()));
                inventory.setItem(23, createItemStack(Material.TORCH, "", List.of()));
                inventory.setItem(24, createItemStack(Material.REDSTONE_TORCH, "", List.of()));
                inventory.setItem(32, createItemStack(Material.CLOCK, "", List.of()));
                inventory.setItem(33, createItemStack(Material.GOLDEN_APPLE, "", List.of()));

                player.openInventory(inventory);
                event.setCancelled(true);
            }
        }
    }

    private ItemStack createItemStack(Material material, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.setDisplayName(name);
            itemMeta.setLore(lore);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    private ItemStack createItemStack(String owner, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.setOwner(owner);;
            itemMeta.setDisplayName(name);
            itemMeta.setLore(lore);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }
}
