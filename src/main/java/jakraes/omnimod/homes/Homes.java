package jakraes.omnimod.homes;

import jakraes.omnimod.Omnimod;
import jakraes.omnimod.homes.commands.CommandHome;
import jakraes.omnimod.homes.commands.CommandSetHome;
import jakraes.omnimod.utils.Position;
import turniplabs.halplibe.helper.CommandHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Homes {
	// Key: Player username
	// Value: Json object with this format {"username": "foo", "(home name)": "30 64 -102"}
	public static Map<String, PlayerHome> homes;

	public static void setup() {
		Path homesFile = Paths.get("./omnimod_data/homes.json");

		if (Files.notExists(homesFile)) {
			Omnimod.LOGGER.info("Homes file not found - creating...");
			try {
				Files.createDirectory(homesFile);
				Omnimod.LOGGER.info("Created homes file");
			} catch (IOException e) {
				Omnimod.LOGGER.error("Failed to create homes file");
				throw new RuntimeException(e);
			}
		}

		homes = new HashMap<>();

		CommandHelper.Server.createCommand(new CommandHome());
		CommandHelper.Server.createCommand(new CommandSetHome());
	}

	public static void load() {
		File homesFile = new File("./omnimod_data/homes.json");
	}

	public static void save() throws FileNotFoundException {
		File homesFile = new File("./omnimod_data/homes.json");

		PrintWriter writer = new PrintWriter(homesFile);

		for (PlayerHome home : homes.values()) {
			writer.write(home.serialize().toString());
		}

		writer.close();
	}

	public static PlayerHome getHome(String username) {
		return homes.get(username);
	}

	public static Position getHomeLocation(String username, String homeName) {
		if (!homes.containsKey(username)) {
			return null;
		}

		if (!homes.get(username).homesLocation.containsKey(homeName)) {
			return null;
		}

		return homes.get(username).homesLocation.get(homeName);
	}

	public static void addHome(String username, String homeName, Position position) {
		if (!homes.containsKey(username)) {
			homes.put(username, new PlayerHome(username));
		}

		PlayerHome playerHome = homes.get(username);

		playerHome.homesLocation.put(homeName, position);
	}

	public static void removeHome(String username, String homeName) {
		if (!homes.containsKey(username)) {
			return;
		}

		if (!homes.get(username).homesLocation.containsKey(homeName)) {
			return;
		}

		homes.get(username).homesLocation.remove(homeName);
	}
}
