package insani.ty.misc.potato;

import apple.lib.pmc.PluginModule;

public class ModulePotato extends PluginModule {
    @Override
    public void enable() {
        new CommandPotato();
        new SkillListener();
    }

    @Override
    public String getName() {
        return "Potato";
    }
}
