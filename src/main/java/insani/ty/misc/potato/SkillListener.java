package insani.ty.misc.potato;

import insani.ty.misc.InsanityPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkillListener implements Listener {
    private final Map<UUID, PlayerSkillManager> clicks = new HashMap<>();

    public SkillListener() {
        InsanityPlugin.get().registerEvents(this);
    }

    @EventHandler
    public void onInteraction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerSkillManager playerSkillManager = clicks.computeIfAbsent(player.getUniqueId(), uuid -> new PlayerSkillManager(player));
        playerSkillManager.onInteraction(event);

    }
}
