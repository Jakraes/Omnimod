package jakraes.omnimod;

import jakraes.omnimod.homes.Homes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.options.components.KeyBindingComponent;
import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.math3.analysis.function.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Omnimod implements ModInitializer, GameStartEntrypoint, ClientStartEntrypoint {
	public static final String MOD_ID = "omnimod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

	}

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {
		// If it's not the server running
		if (MinecraftServer.getInstance() == null) {
			LOGGER.info("Omnimod client initialized.");
			return;
		}

		// Data folder stuff
		Path dataFolder = Paths.get("./omnimod_data");

		if (Files.notExists(dataFolder)) {
			LOGGER.info("Data folder not found - creating...");
			try {
				Files.createDirectory(dataFolder);
				LOGGER.info("Created data folder");
			} catch (IOException e) {
				LOGGER.error("Failed to create data folder");
				throw new RuntimeException(e);
			}
		}

		// Home json file stuff
		Homes.setup();

		LOGGER.info("Omnimod server initialized.");
	}

	@Override
	public void beforeClientStart() {

	}

	@Override
	public void afterClientStart() {

	}


}
