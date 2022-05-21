package insani.ty.misc.player.tweak;

import apple.lib.pmc.PluginModule;

public class ModuleTweaks extends PluginModule {
    @Override
    public void enable() {
        new CommandOffhand();
    }

    @Override
    public String getName() {
        return "Tweaks";
    }
}
