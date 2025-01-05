package me.theclashfruit.rhenium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class Rhenium implements ModInitializer {
    public static Logger logger = LoggerFactory.getLogger("Rhenium");

    public static final Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();

    @Override
    public void onInitialize() {
        logger.info("Rhenium is initializing!");
    }
}
