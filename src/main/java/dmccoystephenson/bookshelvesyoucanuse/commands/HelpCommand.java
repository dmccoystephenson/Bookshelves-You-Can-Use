package dmccoystephenson.bookshelvesyoucanuse.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel McCoy Stephenson
 * @since August 28th, 2022
 */
public class HelpCommand extends AbstractPluginCommand {

    public HelpCommand() {
        super(new ArrayList<>(List.of("help")), new ArrayList<>(List.of("epp.help")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.AQUA + "/epp help");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}
