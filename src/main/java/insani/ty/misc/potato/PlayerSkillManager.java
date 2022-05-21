package insani.ty.misc.potato;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerSkillManager {
    private Player player;

    public PlayerSkillManager(Player player) {
        this.player = player;
    }

    public void onInteraction(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null && item.getType() == Material.POTATO) {
            if (event.getAction().isRightClick()) {
                PotatoSkill.potato(player);
            }
        }
    }
}
