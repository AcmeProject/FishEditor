package net.poweredbyhate.fisheditor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

public final class FishEditor extends JavaPlugin implements Listener {

    Inventory fishBucket;
    Random r = new Random();
    public static FishEditor instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        fishBucket = Bukkit.createInventory(new FishBucket(this),54, ChatColor.AQUA+"FishBucket");
        getCommand("fishedit").setExecutor(new FishEditorCmd());
        Bukkit.getPluginManager().registerEvents(this, this);
        instance = this;
        setInventory();
    }

    public void onDisable() {
        saveInventory();
    }

    public void setInventory() {
        if (getConfig().getString("FishBucket") == null) {
            return;
        }
        try {
            fishBucket = Serialization.fromBase64(getConfig().getString("FishBucket"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInventory() {
        getConfig().set("FishBucket", Serialization.toBase64(fishBucket));
        saveConfig();
    }

    @EventHandler
    public void onClose(InventoryCloseEvent ev) {
        if (ev.getInventory().getHolder() instanceof FishBucket) {
            fishBucket = ev.getInventory();
            saveInventory();
            ev.getPlayer().sendMessage("Fishes saved!");
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent ev) {
        if (ev.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            ItemStack is = fishBucket.getItem(r.nextInt(fishBucket.getSize()));
            if (is == null) {
                return;
            }
            Item it = (Item) ev.getCaught();
            it.setItemStack(is);
        }
    }

}
