package io.github.pepsidawg.infinifly.util;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class Velocity {
    private static Velocity self;
    private Mode velocityMode;
    private double velocityModifier;
    private double percentageUse;
    private double percentageRegen;

    private Velocity() {
        velocityMode = Mode.AUTOMATIC;
        velocityModifier = 1.25;
        percentageUse = .01;
        percentageRegen = .01;
    }

    public static Velocity getInstance() {
        if(self == null)
            self = new Velocity();
        return self;
    }

    public void setVelocityMode(Mode velocityMode) {
        this.velocityMode = velocityMode;
    }

    public Mode getVelocityMode() {
        return this.velocityMode;
    }

    public void setVelocityModifier(double velocityModifier) {
        this.velocityModifier = velocityModifier;
    }

    public double getVelocityModifier() {
        return velocityModifier;
    }

    public void applyVelocity(Player player) {
        Vector threshold = player.getLocation().getDirection().clone().multiply(getVelocityModifier());
        Vector velocity = player.getVelocity();
        Double x, y, z;

        x = Math.abs(threshold.getX()) < Math.abs(velocity.getX()) ? velocity.getX() : threshold.getX();
        y = Math.abs(threshold.getY()) < Math.abs(velocity.getY()) ? velocity.getY() : threshold.getY();
        z = Math.abs(threshold.getZ()) < Math.abs(velocity.getZ()) ? velocity.getZ() : threshold.getZ();

        player.setVelocity(new Vector(x, y, z));
    }

    public void applyBoost(Player player) {
        Vector velocity = player.getLocation().getDirection().add(player.getVelocity().multiply(getVelocityModifier())).multiply(.5).multiply(getVelocityModifier());
        float boostLeft = (float)(player.getExp() - getPercentageUse());

        boostLeft = boostLeft < 0 ? 0 : boostLeft;

        player.setVelocity(velocity);
        player.setExp(boostLeft);
    }

    public double getPercentageRegen() {
        return percentageRegen;
    }

    public void setPercentageRegen(double percentageRegen) {
        this.percentageRegen = percentageRegen;
    }

    public double getPercentageUse() {
        return percentageUse;
    }

    public void setPercentageUse(double percentageUse) {
        this.percentageUse = percentageUse;
    }
}
