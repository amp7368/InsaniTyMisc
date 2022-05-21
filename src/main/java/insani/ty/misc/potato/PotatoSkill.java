package insani.ty.misc.potato;

import apple.mc.utilities.inventory.item.InventoryUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PotatoSkill {
    public static void potato(Player player) {
        Location location = player.getEyeLocation();
        Location source = location.add(location.getDirection().multiply(1.5));
        source.getWorld().playSound(source, Sound.ENTITY_LINGERING_POTION_THROW, SoundCategory.PLAYERS, 1f, 1f);
        player
                .getWorld()
                .spawnEntity(source,
                             EntityType.ENDER_PEARL,
                             CreatureSpawnEvent.SpawnReason.COMMAND,
                             potato -> handlePotato(player, (EnderPearl) potato)
                );
    }

    private static void handlePotato(Player player, EnderPearl potato) {
        potato.setItem(InventoryUtils.get().makeItem(Material.POTATO));
        potato.setVelocity(player.getLocation().getDirection());
        potato.setSilent(true);
    }
}
