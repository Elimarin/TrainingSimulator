package me.Elimar.TrainingSimulator;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    @Override
    public void onEnable() {
        super.onEnable();
        MLGWater mlgWater = new MLGWater();
        RBP rbp = new RBP();
        this.getCommand("mlgwater").setExecutor(mlgWater);
        this.getCommand("rbp").setExecutor(rbp);
        this.getServer().getPluginManager().registerEvents(mlgWater, this);
        this.getServer().getPluginManager().registerEvents(rbp, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
