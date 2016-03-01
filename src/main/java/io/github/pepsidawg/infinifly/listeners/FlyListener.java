package io.github.pepsidawg.infinifly.listeners;

import io.github.pepsidawg.infinifly.util.Mode;
import io.github.pepsidawg.infinifly.util.Velocity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class FlyListener implements Listener {
    Velocity velocity;

    public FlyListener() {
        velocity = Velocity.getInstance();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        ItemStack chestplate = event.getPlayer().getInventory().getChestplate();

        if(chestplate == null)
            return;

        if(chestplate.getType().equals(Material.ELYTRA) && !event.getPlayer().isOnGround()) {
            if(velocity.getVelocityMode().equals(Mode.AUTOMATIC)) {
                if (event.getPlayer().getLocation().getPitch() < 0)
                    velocity.applyVelocity(event.getPlayer());
            } else {
                if(event.getPlayer().isSneaking())
                    velocity.applyVelocity(event.getPlayer());
            }
        }
    }
}
