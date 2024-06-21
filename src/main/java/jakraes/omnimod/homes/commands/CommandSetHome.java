package jakraes.omnimod.homes.commands;

import jakraes.omnimod.Omnimod;
import jakraes.omnimod.homes.Homes;
import jakraes.omnimod.utils.Position;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import net.minecraft.core.net.command.PlayerCommandSender;

public class CommandSetHome extends Command {
	public CommandSetHome() {
		super("sethome");
	}

	@Override
	public boolean execute(CommandHandler commandHandler, CommandSender commandSender, String[] strings) {
		String homeName = "default";

		if (strings.length > 0) {
			homeName = strings[0];
		}

		String username = commandSender.getName();
		Position position = new Position((int) commandSender.getPlayer().x, (int) commandSender.getPlayer().y, (int) commandSender.getPlayer().z, commandSender.getPlayer().dimension);

		Homes.addHome(username, homeName, position);

		commandSender.sendMessage("Home set successfully");

		return true;
	}

	@Override
	public boolean opRequired(String[] strings) {
		return false;
	}

	@Override
	public void sendCommandSyntax(CommandHandler commandHandler, CommandSender commandSender) {
		if (commandSender instanceof PlayerCommandSender) {
			commandSender.sendMessage("/sethome <home>");
		}
	}
}
