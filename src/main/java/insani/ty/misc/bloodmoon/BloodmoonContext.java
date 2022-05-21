package insani.ty.misc.bloodmoon;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static insani.ty.misc.bloodmoon.BloodmoonPhase.BLOODMOON;
import static insani.ty.misc.bloodmoon.BloodmoonPhase.EASY;

public record BloodmoonContext(World world, BloodmoonPhase oldPhase, BloodmoonPhase newPhase) {
    private static final Component BLOODMOON_SET_TEXT = Component.text("The bloodmoon has set",
                                                                       TextColor.color(0, 166, 255)
    );
    private static final Title BLOODMOON_SET = Title.title(BLOODMOON_SET_TEXT,
                                                           Component.empty(),
                                                           Title.Times.times(Duration.ofMillis(500),
                                                                             Duration.ofMillis(2500),
                                                                             Duration.ofMillis(500)
                                                           )
    );

    private static final @NotNull TextComponent BLOODMOON_RISING_TEXT = Component.text("The bloodmoon is rising!",
                                                                                       TextColor.color(112, 11, 11)
    );
    private static final Title BLOODMOON_RISING = Title.title(BLOODMOON_RISING_TEXT,
                                                              Component.empty(),
                                                              Title.Times.times(Duration.ofMillis(500),
                                                                                Duration.ofMillis(2500),
                                                                                Duration.ofMillis(500)
                                                              )
    );

    public boolean shouldSwitch() {
        return switch (this.newPhase) {
            case BLOODMOON -> !this.isDayTime();
            case EASING, EASY -> this.isDayTime();
        };
    }

    private boolean isDayTime() {
        return this.world().isDayTime();
    }

    public void doSwitch() {
        if (oldPhase == BLOODMOON) {
            endBloodmoon();
        } else if (newPhase == BLOODMOON) {
            startBloodmoon();
        } else if (newPhase == EASY) {
            easyMode();
        } else {
            normalMode();
        }
    }

    private void normalMode() {
        world.setDifficulty(Difficulty.NORMAL);
    }

    private void easyMode() {
        world.setDifficulty(Difficulty.EASY);
    }

    private void endBloodmoon() {
        this.world.setDifficulty(Difficulty.NORMAL);
        for (Player player : this.world.getPlayers()) {
            player.showTitle(BLOODMOON_SET);
        }
        this.world.sendMessage(BLOODMOON_SET_TEXT);
    }

    private void startBloodmoon() {
        world.setDifficulty(Difficulty.HARD);
        for (Player player : this.world.getPlayers()) {
            player.showTitle(BLOODMOON_RISING);
        }
        this.world.sendMessage(BLOODMOON_RISING_TEXT);
    }
}
