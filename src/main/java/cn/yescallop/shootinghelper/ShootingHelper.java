package cn.yescallop.shootinghelper;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.TaskHandler;

import java.util.HashMap;
import java.util.Map;

public class ShootingHelper extends PluginBase {
    
    private Map<Player, TaskHandler> tasks = new HashMap<>();
    
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }
    
    public void processDrawBow(Player player) {
        this.tasks.put(player, this.getServer().getScheduler().scheduleRepeatingTask(new DrawBowTask(this, player, this.getServer().getTick()), 1));
    }
    
    public void processShootBow(Player player) {
        if (this.tasks.containsKey(player)) {
            this.tasks.remove(player).cancel();
        }
    }
}