package pt.nobrehd.nobrelib;

import net.fabricmc.api.ModInitializer;

import java.util.HashMap;

public class NobreLib implements ModInitializer {
    private static final HashMap<String, String> REGISTERED_MODS = new HashMap<>();

    @Override
    public void onInitialize() {

    }

    public static void registerMod(String modid, String name) {
        REGISTERED_MODS.put(modid, name);
    }

    public static String getModName(String modid) {
        return REGISTERED_MODS.get(modid);
    }

    public static String getListOfMods() {
        return String.join(", ", REGISTERED_MODS.values());
    }
}
