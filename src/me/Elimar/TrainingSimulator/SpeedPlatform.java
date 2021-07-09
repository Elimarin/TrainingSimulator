package me.Elimar.TrainingSimulator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SpeedPlatform implements Listener{
    PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 80, 4, false, false);
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location position = event.getTo();
        Block block = world.getBlockAt(position.getBlockX(), position.getBlockY() - 1, position.getBlockZ());
        if (block.getType() == Material.BLACKSTONE){
            effect.apply(player);
        }
    }
}
