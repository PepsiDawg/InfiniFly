package io.github.pepsidawg.infinifly.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandNumberFormatException;
import com.sk89q.minecraft.util.commands.NestedCommand;
import io.github.pepsidawg.infinifly.util.Mode;
import io.github.pepsidawg.infinifly.util.Velocity;
import org.bukkit.ChatColor;
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
        catch (RuntimeException e) { throw new Exception("Invalid Elytra mode! Expected \"Automatic\" or \"Boost\""); }

        Velocity.getInstance().setVelocityMode(mode);
        sender.sendMessage(ChatColor.DARK_AQUA + "Mode set to " + ChatColor.GREEN + mode.name());
    }

    @Command(
            aliases = {"boost"},
            desc = "Change how boosting works",
            usage = "<use> <regen>",
            min = 2, max = 2
    )
    public static void boost(CommandContext args, CommandSender sender) throws Exception {
        double use, regen;

        use = args.getDouble(0);
        regen = args.getDouble(1);

        if( !(use > 0 && use <= 1) || !(regen >= 0 && regen <= 1)) {
            throw new Exception("Invalid value! Expected a number between 0 and 1");
        }

        Velocity.getInstance().setPercentageUse(use);
        Velocity.getInstance().setPercentageRegen(regen);

        sender.sendMessage(ChatColor.DARK_AQUA + "Boost values set to  Use(" + ChatColor.GREEN + use  + ChatColor.DARK_AQUA + ") Regen(" + ChatColor.GREEN + regen + ChatColor.DARK_AQUA + ")");

    }
}
