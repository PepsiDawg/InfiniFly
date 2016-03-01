package io.github.pepsidawg.infinifly.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandNumberFormatException;
import com.sk89q.minecraft.util.commands.NestedCommand;
import io.github.pepsidawg.infinifly.util.Mode;
import io.github.pepsidawg.infinifly.util.Velocity;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

public class ElytraCommands {

    public static class ElytraBaseCommand {
        @Command(
                aliases = "elytra",
                desc = "Base elytra command"
        )
        @NestedCommand(ElytraCommands.class)
        public static void elytra(CommandContext args, CommandSender sender) {
        }
    }

    @Command(
            aliases = {"velocity"},
            desc = "Change the fly speed modifier for elytra",
            usage = "<Modifier>",
            min = 1, max = 1
    )
    public static void modifier(CommandContext args, CommandSender sender) throws CommandNumberFormatException {
        double modifier = args.getDouble(0);

        Velocity.getInstance().setVelocityModifier(modifier);
        sender.sendMessage(ChatColor.DARK_AQUA + "Modifier set to " + ChatColor.GREEN + modifier);
    }

    @Command(
            aliases = {"mode"},
            desc = "Change the way elytra flying works",
            usage = "<mode>",
            min = 1, max = 1
    )
    public static void mode(CommandContext args, CommandSender sender) throws Exception{
        Mode mode;
        try { mode = Mode.valueOf(args.getString(0).toUpperCase()); }
        catch (RuntimeException e) { throw new Exception("Invalid Elytra mode! Expected \"Automatic\" or \"Shift\""); }

        Velocity.getInstance().setVelocityMode(mode);
        sender.sendMessage(ChatColor.DARK_AQUA + "Mode set to " + ChatColor.GREEN + mode.name());
    }
}
