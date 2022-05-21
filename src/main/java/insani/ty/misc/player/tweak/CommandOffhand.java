package insani.ty.misc.player.tweak;

import insani.ty.misc.InsanityPlugin;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

@CommandAlias("offhand")
public class CommandOffhand extends BaseCommand {
    public CommandOffhand() {
        InsanityPlugin.get().registerCommand(this);
    }

    @Default()
    public void offhand(Player player) {
        PlayerInventory inventory = player.getInventory();
        @NotNull ItemStack mainHand = inventory.getItemInMainHand();
        @NotNull ItemStack offHand = inventory.getItemInOffHand();
        inventory.setItemInMainHand(offHand);
        inventory.setItemInOffHand(mainHand);
    }
}
