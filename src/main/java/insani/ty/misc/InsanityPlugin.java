package insani.ty.misc;

import insani.ty.misc.player.tweak.ModuleTweaks;
import insani.ty.misc.potato.ModulePotato;
import apple.lib.pmc.ApplePlugin;
import apple.lib.pmc.PluginModule;

import java.util.Collection;
import java.util.List;

public class InsanityPlugin extends ApplePlugin {
    private static InsanityPlugin instance;

    public InsanityPlugin() {
        instance = this;
    }

    public static InsanityPlugin get() {
        return instance;
    }

    @Override
    public Collection<PluginModule> getModules() {
        return List.of(new ModulePotato(), new ModuleTweaks());
    }
}
