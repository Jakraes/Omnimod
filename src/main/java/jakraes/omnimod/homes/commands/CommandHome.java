package jakraes.omnimod.homes.commands;

import jakraes.omnimod.homes.Homes;
import jakraes.omnimod.homes.PlayerHome;
import jakraes.omnimod.utils.Position;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import net.minecraft.core.net.command.PlayerCommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.entity.player.EntityPlayerMP;

public class CommandHome extends Command {
	public CommandHome() {
		super("home");
	}

	@Override
	public boolean execute(CommandHandler commandHandler, CommandSender commandSender, String[] strings) {
		String homeName = "default";

		if (strings.length > 0) {
			homeName = strings[0];
		}

		String username = commandSender.getName();

		Position position = Homes.getHomeLocation(username, homeName);

		if (position == null) {
			commandSender.sendMessage("That home doesn't exist!");
			return true;
		}

		EntityPlayerMP playerMP = (EntityPlayerMP) commandSender.getPlayer();

		if (playerMP.dimension != position.dimension) {
			playerMP.mcServer.playerList.sendPlayerToOtherDimension(playerMP, position.dimension);
		}
		playerMP.playerNetServerHandler.teleport(position.x, position.y, position.z);

		return true;
	}

	@Override
	public boolean opRequired(String[] strings) {
		return false;
	}

	@Override
	public void sendCommandSyntax(CommandHandler commandHandler, CommandSender commandSender) {
		if (commandSender instanceof PlayerCommandSender) {
			commandSender.sendMessage("/home <home>");
		}
	}
}
