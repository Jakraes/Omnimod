package goldenage.omnimod.homes;

import com.google.gson.JsonObject;
import goldenage.omnimod.utils.Position;

import java.util.HashMap;
import java.util.Map;

public class PlayerHome {
	public String player;
	public Map<String, Position> homesLocation;

	public PlayerHome(String player) {
		this.player = player;
		homesLocation = new HashMap<>();
	}

	public Position get(String homeName) {
		return homesLocation.get(homeName);
	}

	public void set(String homeName, Position position) {
		homesLocation.put(homeName, position);
	}

	public JsonObject serialize() {
		JsonObject result = new JsonObject();
		result.addProperty("username", player);

		for (String homeName : homesLocation.keySet()) {
			Position position = homesLocation.get(homeName);

			result.addProperty(homeName, position.toString());
		}

		return result;
	}
}
