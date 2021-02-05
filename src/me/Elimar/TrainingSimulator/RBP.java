package me.Elimar.TrainingSimulator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class RBP implements CommandExecutor, Listener {
    private static class PlayerData{
        enum State{
            JUMP,
            LAND
        }
        int height;
        List<Location> blocksPlaced = new ArrayList<>();
        int score = 0;
        public PlayerData(int height){
            this.height = height;
        }
    }

    HashMap<UUID, PlayerData> playerData = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("rbp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("*console has tripped immediately*");
                return true;
            }
            Player player = (Player) sender;
            ItemStack cactus = new ItemStack(Material.CACTUS, 1);
            player.getInventory().setItemInMainHand(cactus);

            playerData.put(player.getUniqueId(), new PlayerData(player.getLocation().getBlockY()));
        }

        return false;
    }

    @EventHandler
    public void onLand(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onJump(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if (event.getFrom().getY() < event.getTo().getY() &&
                player.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR) {

        }
    }
}
