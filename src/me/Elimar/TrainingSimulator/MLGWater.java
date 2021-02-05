package me.Elimar.TrainingSimulator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;


public class MLGWater implements CommandExecutor, Listener {
    private static class PlayerData{
        int height;
        int range;
        public PlayerData(int height, int range){
            this.height = height;
            this.range = range;
        }
    }

    HashMap<UUID, PlayerData> playerData = new HashMap<UUID, PlayerData>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("mlgwater")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("*console goes flying without water bucket*");
                return true;
            }
            Player player = (Player) sender;
            int height = 40;
            int range = 0;
            if (args.length >= 1)
                try {
                    height = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    return false;
                }
            if (args.length >= 2){
                try {
                    range = Integer.parseInt(args[1]);
                    if (range > height){
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Range cannot be bigger than height!!!");
                        return true;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }

            }
            if (args.length >= 3) {
                return false;
            }

            if (height == -1) {
                playerData.remove(player.getUniqueId());
                sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Training canceled");
                return true;
            }
            PlayerData data = new PlayerData(height, range);
            playerData.put(player.getUniqueId(), data);
            sender.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "MLG Water training started");
            teleportUp(player, data);
            return true;
        }
        return false;
    }

    public void teleportUp(Player player, PlayerData data) {
        ItemStack item = new ItemStack(Material.WATER_BUCKET, 1);
        if ( !player.getInventory().getItemInMainHand().isSimilar(item)) {
            player.getInventory().setItemInMainHand(item);
        }

        Random rand = new Random();
        int height = data.height + rand.nextInt(data.range*2 + 1) - data.range;

        player.teleport(player.getLocation().add(0.0, height, 0.0));

    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                PlayerData data = playerData.get(player.getUniqueId());
                if (data != null) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Try again!");
                    teleportUp(player, data);
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onSuccess(PlayerBucketEmptyEvent event){
        Player player = event.getPlayer();
        PlayerData data = playerData.get(player.getUniqueId());
        if (data != null) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Success!!!");
            teleportUp(player, data);
            event.setCancelled(true);
        }
    }
}
