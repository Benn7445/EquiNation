package me.quartz.equination.horse;

import me.quartz.equination.EquiNation;

import java.util.*;

public class CHorse {

    // General
    private final UUID entity;
    private String name;
    private final List<UUID> owners;
    private final List<UUID> trusts;
    private final CGender gender;
    private String breed;

    // Stats
    private int ran;
    private int jumped;

    // Potential
    private final double potentialSpeed;
    private final double potentialJump;

    // Care
    private Date shod;
    private Date fed;
    private Date watered;

    public CHorse(UUID entity) {
        this.entity = entity;
        this.name = "Unnamed";
        this.owners = new ArrayList<>();
        this.trusts = new ArrayList<>();
        this.gender = new Random().nextBoolean() ? CGender.MARE : CGender.STALLION;
        this.breed = "";
        this.ran = 0;
        this.jumped = 0;
        this.potentialSpeed = EquiNation.getInstance().getHorseManager().getPotentialSpeed();
        this.potentialJump = EquiNation.getInstance().getHorseManager().getPotentialJump();
        this.shod = new Date();
        this.fed = new Date();
        this.watered = new Date();
    }

    public UUID getEntity() {
        return entity;
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }

    public List<UUID> getOwner() {
        return owners;
    }

    public void addOwner(UUID uuid) {
        owners.add(uuid);
    }

    public void removeOwner(UUID uuid) {
        owners.remove(uuid);
    }

    public List<UUID> getTrusts() {
        return trusts;
    }

    public void addTrust(UUID uuid) {
        trusts.add(uuid);
    }

    public void removeTrust(UUID uuid) {
        trusts.remove(uuid);
    }

    public CGender getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getRan() {
        return ran;
    }

    public void addRan() {
        this.ran++;
    }

    public int getJumped() {
        return jumped;
    }

    public void addJump() {
        this.jumped++;
    }

    public double getPotentialSpeed() {
        return potentialSpeed;
    }

    public double getPotentialJump() {
        return potentialJump;
    }

    public boolean isShod() {
        return (new Date().getTime() - shod.getTime()) < (EquiNation.getInstance().getConfig().getInt("duration.shoeing") * 60000L);
    }

    public void shoe() {
        this.shod = new Date();
    }

    public boolean isFed() {
        return (new Date().getTime() - fed.getTime()) < (EquiNation.getInstance().getConfig().getInt("duration.feeding") * 60000L);
    }

    public void feed() {
        this.fed = new Date();
    }

    public boolean isWatered() {
        return (new Date().getTime() - watered.getTime()) < (EquiNation.getInstance().getConfig().getInt("duration.watering") * 60000L);
    }

    public void water() {
        this.watered = new Date();
    }
}
