package insani.ty.misc.bloodmoon;

import insani.ty.misc.InsanityPlugin;
import apple.lib.pmc.FileIOServiceNow;
import apple.utilities.database.SaveFileable;
import apple.utilities.database.ajd.AppleAJDTypedImpl;
import apple.utilities.threading.service.queue.AsyncTaskQueue;
import io.papermc.paper.world.MoonPhase;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BloodmoonClock implements SaveFileable {
    private static final long TICK_INTERVAL = 500;
    private static final Map<UUID, BloodmoonClock> databases = new HashMap<>();
    private static final AppleAJDTypedImpl<BloodmoonClock, AsyncTaskQueue> databaseManager = new AppleAJDTypedImpl<>(
            BloodmoonClock.class,
            ModuleBloodMoon.get().getFile("bloodmoon", "worlds"),
            FileIOServiceNow.get().taskCreator()
    );
    private int skipBloodmoonCount = 0;

    public static void load() {
        databaseManager.loadFolderNow();
        for (World world : Bukkit.getWorlds()) {
            databases.computeIfAbsent(world.getUID(), BloodmoonClock::new);
        }
        databases.values().stream().filter(BloodmoonClock::initWorld).forEach(BloodmoonClock::tick);
    }

    private boolean initWorld() {
        this.world = Bukkit.getWorld(this.worldUUID);
        return this.world != null;
    }

    private UUID worldUUID;
    private transient World world;
    private BloodmoonPhase phase = BloodmoonPhase.EASY;

    public BloodmoonClock() {
    }

    public BloodmoonClock(UUID uuid) {
        this.worldUUID = uuid;
        databaseManager.saveInFolder(this);
    }

    private void scheduleNextTick() {
        long time = this.world.getTime();
        long timeToNextTick = TICK_INTERVAL - time % TICK_INTERVAL;
        InsanityPlugin.get().scheduleSyncDelayedTask(this::tick, timeToNextTick);
    }

    private void tick() {
        this.scheduleNextTick();
        this.verifyBloodmoon();
    }

    private void verifyBloodmoon() {
        BloodmoonPhase oldPhase = this.phase;
        BloodmoonPhase newPhase = BloodmoonPhase.phaseFrom(getMoonPhase());
        if (oldPhase == newPhase) return;
        BloodmoonContext context = new BloodmoonContext(this.world, oldPhase, newPhase);
        if (context.shouldSwitch()) {
            context.doSwitch();
            this.phase = newPhase;
        }
    }

    @NotNull
    private MoonPhase getMoonPhase() {
        return this.world.getMoonPhase();
    }

    private void startBloodmoon() {
        this.phase = BloodmoonPhase.BLOODMOON;
        if (this.skipBloodmoonCount == 0) {

        } else {
            this.skipBloodmoonCount--;
        }

    }

    @Override
    public String getSaveFileName() {
        return this.world.getUID().toString();
    }
}
