package me.quartz.equination.horse;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

public class CAid {

    private final String name;
    private final PotionEffectType effect;
    private final Material curing;
    private final PotionEffectType curingEffect;
    private final String when;
    private final int chance;

    public CAid(String name, PotionEffectType effect, Material curing, PotionEffectType curingEffect, String when, int chance) {
        this.name = name;
        this.effect = effect;
        this.curing = curing;
        this.curingEffect = curingEffect;
        this.when = when;
        this.chance = chance;
    }

    public String getName() {
        return name;
    }

    public PotionEffectType getEffect() {
        return effect;
    }

    public Material getCuring() {
        return curing;
    }

    public PotionEffectType getCuringEffect() {
        return curingEffect;
    }

    public String getWhen() {
        return when;
    }

    public int getChance() {
        return chance;
    }
}
