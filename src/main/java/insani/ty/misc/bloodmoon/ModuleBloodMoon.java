package insani.ty.misc.bloodmoon;

import apple.configs.factory.AppleConfigLike;
import apple.lib.pmc.PluginModule;

import java.util.List;

public class ModuleBloodMoon extends PluginModule {
    private static ModuleBloodMoon instance;

    public ModuleBloodMoon() {
        instance = this;
    }

    public static ModuleBloodMoon get() {
        return instance;
    }

    @Override
    public void enable() {
        BloodmoonClock.load();
    }

    @Override
    public List<AppleConfigLike> getConfigs() {
        return List.of(configFolder("bloodmoon", configJson(BloodMoonConfig.class, "General")));
    }

    @Override
    public String getName() {
        return "BloodMoon";
    }
}
