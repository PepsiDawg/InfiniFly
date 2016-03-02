package io.github.pepsidawg.infinifly.util;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class BoostRegen  extends BukkitRunnable {
    private Velocity velocity;

    public BoostRegen() {
        velocity = Velocity.getInstance();
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().stream()
                .forEach(p -> {
                    if (velocity.getVelocityMode().equals(Mode.BOOST)) {
                        if(!p.isSneaking()) {
                            if(p.getExp() < 1 && p.getExp() >= 0) {
                                float modifier = (float) (p.getExp()  + velocity.getPercentageRegen());
                                modifier = modifier > 1 ? .99f : modifier;
                                p.setExp(modifier);
                            }
                        }
                    }
                });
    }
}
