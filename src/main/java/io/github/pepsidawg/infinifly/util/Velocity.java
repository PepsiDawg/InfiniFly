package io.github.pepsidawg.infinifly.util;

import org.bukkit.entity.Player;

public class Velocity {
    private static Velocity self;
    private Mode velocityMode;
    private double velocityModifier;

    private Velocity() {
        velocityMode = Mode.AUTOMATIC;
        velocityModifier = 1.25;
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
        player.setVelocity(player.getLocation().getDirection().clone().multiply(getVelocityModifier()));
    }
}
