package net.poweredbyhate.fisheditor;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by Lax on 6/5/2017.
 */
public class FishBucket implements InventoryHolder {

    private FishEditor plugin;

    public FishBucket(FishEditor plugin) {
        this.plugin = plugin;
    }

    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(null, 54);
    }
}
