package me.Elimar.TrainingSimulator;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    @Override
    public void onEnable() {
        super.onEnable();
        MLGWater mlgWater = new MLGWater();
        RBP rbp = new RBP();
        SpeedPlatform speed = new SpeedPlatform();
        this.getCommand("mlgwater").setExecutor(mlgWater);
        this.getCommand("rbp").setExecutor(rbp);
        this.getServer().getPluginManager().registerEvents(mlgWater, this);
        this.getServer().getPluginManager().registerEvents(rbp, this);
        this.getServer().getPluginManager().registerEvents(speed, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
