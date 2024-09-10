package elias.echat;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Echat implements ModInitializer {
	public static final String MOD_ID = "echat";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register the /chathistory command during mod initialization
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			CommandsClass.registerCommand(dispatcher);  // Register custom command from EchatCommand.java
		});

		LOGGER.info("Chat History Initialized!");
	}
}