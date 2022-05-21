package insani.ty.misc.bloodmoon;

import insani.ty.misc.InsanityPlugin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BloodmoonBossbar {
    private static final NamespacedKey BOSSBAR_KEY = InsanityPlugin.get().namespacedKey("bloodmoon");
    private static final BossBar BOSSBAR = Objects.requireNonNullElseGet(Bukkit.getBossBar(BOSSBAR_KEY),
                                                                         BloodmoonBossbar::createBossbar
    );

    static {
        BOSSBAR.setVisible(true);
    }

    @NotNull
    private static KeyedBossBar createBossbar() {
        return Bukkit.createBossBar(BOSSBAR_KEY, null, BarColor.RED, BarStyle.SEGMENTED_12);
    }

    public BloodmoonBossbar() {
    }

    public void bossbar() {
    }
}
