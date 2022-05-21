package insani.ty.misc.bloodmoon;

public class BloodMoonConfig {
    private int skipPhases = 0;
    private static BloodMoonConfig instance;

    public BloodMoonConfig() {
        instance = this;
    }

    public static BloodMoonConfig get() {
        return instance;
    }
}
