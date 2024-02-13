package me.quartz.equination.horse;

import me.quartz.equination.EquiNation;

import java.util.*;

public class CHorse {

    // General
    private final UUID entity;
    private final int id;
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
        this.id = new Random().nextInt(900000);
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

    public CHorse(UUID entity, int id, String name, List<UUID> owners, List<UUID> trusts, CGender gender, String breed, int ran, int jumped, double potentialSpeed, double potentialJump, Date shod, Date fed, Date watered) {
        this.entity = entity;
        this.id = id;
        this.name = name;
        this.owners = owners;
        this.trusts = trusts;
        this.gender = gender;
        this.breed = breed;
        this.ran = ran;
        this.jumped = jumped;
        this.potentialSpeed = potentialSpeed;
        this.potentialJump = potentialJump;
        this.shod = shod;
        this.fed = fed;
        this.watered = watered;
    }

    public UUID getEntity() {
        return entity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public List<UUID> getOwners() {
        return owners;
    }

    public void addOwner(UUID uuid) {
        owners.add(uuid);
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public void removeOwner(UUID uuid) {
        owners.remove(uuid);
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public List<UUID> getTrusts() {
        return trusts;
    }

    public void addTrust(UUID uuid) {
        trusts.add(uuid);
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public void removeTrust(UUID uuid) {
        trusts.remove(uuid);
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public CGender getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public int getRan() {
        return ran;
    }

    public void addRan() {
        this.ran++;
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public int getJumped() {
        return jumped;
    }

    public void addJump() {
        this.jumped++;
        EquiNation.getInstance().getHorseManager().serialize(this);
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

    public Date getShod() {
        return shod;
    }

    public void shoe() {
        this.shod = new Date();
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public boolean isFed() {
        return (new Date().getTime() - fed.getTime()) < (EquiNation.getInstance().getConfig().getInt("duration.feeding") * 60000L);
    }

    public Date getFed() {
        return fed;
    }

    public void feed() {
        this.fed = new Date();
        EquiNation.getInstance().getHorseManager().serialize(this);
    }

    public boolean isWatered() {
        return (new Date().getTime() - watered.getTime()) < (EquiNation.getInstance().getConfig().getInt("duration.watering") * 60000L);
    }

    public Date getWatered() {
        return watered;
    }

    public void water() {
        this.watered = new Date();
        EquiNation.getInstance().getHorseManager().serialize(this);
    }
}
