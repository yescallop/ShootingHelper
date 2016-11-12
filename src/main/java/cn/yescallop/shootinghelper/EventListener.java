package cn.yescallop.shootinghelper;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityShootBowEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;

public class EventListener implements Listener {
    
    private ShootingHelper plugin;
    
    public EventListener(ShootingHelper plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem().getId() == Item.BOW && event.getAction() == PlayerInteractEvent.RIGHT_CLICK_AIR) {
            plugin.processDrawBow(event.getPlayer());
        }
    }
    
    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            plugin.processShootBow((Player) entity);
        }
    }
}