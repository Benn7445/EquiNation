package me.quartz.equination.listeners;

import me.quartz.equination.EquiNation;
import me.quartz.equination.horse.CHorse;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void playerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        ItemStack itemStack = event.getPlayer().getItemInHand();
        if(entity instanceof Horse horse) {
            CHorse cHorse = EquiNation.getInstance().getHorseManager().getHorse(horse);
            if (cHorse != null && (cHorse.getOwners().contains(event.getPlayer().getUniqueId()) || cHorse.getTrusts().contains(event.getPlayer().getUniqueId()))) {
                if (itemStack.getType() == Material.NAME_TAG && itemStack.getItemMeta() != null && !itemStack.getItemMeta().getDisplayName().equals("Name Tag")) {
                    cHorse.rename(itemStack.getItemMeta().getDisplayName());
                } else if (itemStack.getType() == Material.POTION) {
                    PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
                    horse.addPotionEffect(itemMeta.getCustomEffects().get(0));
                    itemStack.setAmount(itemStack.getAmount() - 1);
                }

                for (String s : EquiNation.getInstance().getConfig().getConfigurationSection("foods").getKeys(false)) {
                    Material material = Material.valueOf(s);
                    if (itemStack.getType() == material) {
                        ConfigurationSection cs = EquiNation.getInstance().getConfig().getConfigurationSection("foods." + s);

                        cHorse.feed();

                        PotionEffect potionEffect = new PotionEffect(PotionEffectType.getByName(cs.getString("effect")), cs.getInt("duration"), cs.getInt("amplifier"));
                        horse.addPotionEffect(potionEffect);

                        itemStack.setAmount(itemStack.getAmount() - 1);
                        event.setCancelled(true);
                    }
                }
            }
        }

    }
}
