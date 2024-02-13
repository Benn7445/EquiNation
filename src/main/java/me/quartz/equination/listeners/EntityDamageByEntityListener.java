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
                Inventory inventory = Bukkit.createInventory(null, 9, cHorse.getName() + "'s Info");

                inventory.setItem(0, createItemStack(Material.NAME_TAG, ChatColor.AQUA + "Name", List.of(ChatColor.GRAY + cHorse.getName())));
                inventory.setItem(1, createItemStack(Material.GOLDEN_HELMET, ChatColor.AQUA + "Owners", cHorse.getOwners().stream().map(uuid -> ChatColor.GRAY + Bukkit.getOfflinePlayer(uuid).getName()).collect(Collectors.toList())));
                inventory.setItem(2, createItemStack(Material.SUNFLOWER, ChatColor.AQUA + "Gender", List.of(ChatColor.GRAY + cHorse.getGender().getName())));
                inventory.setItem(3, createItemStack(Material.TORCH, ChatColor.AQUA + "Stats", List.of(ChatColor.GRAY + "Speed: " + (horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue() * 10), ChatColor.GRAY + "Jump Strength: " + (horse.getJumpStrength() * 10))));
                inventory.setItem(4, createItemStack(Material.REDSTONE_TORCH, ChatColor.AQUA + "Potential Stats", List.of(ChatColor.GRAY + "Speed: " + (cHorse.getPotentialSpeed() * 10), ChatColor.GRAY + "Jump Strength: " + (cHorse.getPotentialJump() * 10))));
                inventory.setItem(6, createItemStack(Material.IRON_ORE, ChatColor.AQUA + "Shoeing Done", List.of(ChatColor.GRAY + (cHorse.isShod() ? "Yes" : "No"))));
                inventory.setItem(7, createItemStack(Material.BREAD, ChatColor.AQUA + "Fed", List.of(ChatColor.GRAY + (cHorse.isFed() ? "Yes" : "No"))));
                inventory.setItem(8, createItemStack(Material.WATER_BUCKET, ChatColor.AQUA + "Watered", List.of(ChatColor.GRAY + (cHorse.isWatered() ? "Yes" : "No"))));


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
}
