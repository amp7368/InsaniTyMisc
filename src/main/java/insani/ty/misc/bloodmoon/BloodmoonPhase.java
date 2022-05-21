package insani.ty.misc.bloodmoon;

import io.papermc.paper.world.MoonPhase;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.papermc.paper.world.MoonPhase.*;

public enum BloodmoonPhase {
    BLOODMOON(List.of(FULL_MOON)),
    EASING(List.of(WANING_GIBBOUS, WAXING_GIBBOUS)),
    EASY(List.of(LAST_QUARTER, WANING_CRESCENT, NEW_MOON, WAXING_CRESCENT, FIRST_QUARTER));

    private static final Map<MoonPhase, BloodmoonPhase> moonToBlood = new HashMap<>();
    private final List<MoonPhase> moonPhases;

    static {
        for (BloodmoonPhase bloodPhase : values()) {
            for (MoonPhase moonPhase : bloodPhase.moonPhases) {
                moonToBlood.put(moonPhase, bloodPhase);
            }
        }
    }

    BloodmoonPhase(List<MoonPhase> moonPhases) {
        this.moonPhases = moonPhases;
    }

    @NotNull
    public static BloodmoonPhase phaseFrom(MoonPhase moonPhase) {
        return moonToBlood.get(moonPhase);
    }
}
