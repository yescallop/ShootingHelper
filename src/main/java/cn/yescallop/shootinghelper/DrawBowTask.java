package cn.yescallop.shootinghelper;

import cn.nukkit.Player;
import cn.nukkit.level.particle.SmokeParticle;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;

public class DrawBowTask extends Task {
    
    private ShootingHelper plugin;
    private Player player;
    private int startAction;
    
    public DrawBowTask(ShootingHelper plugin, Player player, int startAction) {
        this.plugin = plugin;
        this.player = player;
        this.startAction = startAction;
    }
    
    @Override
    public void onRun(int currentTick) {
        int diff = currentTick - startAction;
        double p = (double) diff / 20;
        double f = Math.min((p * p + p * 2) / 3, 1) * 2;
        Vector3 motion = new Vector3(
                -Math.sin(player.yaw / 180d * Math.PI) * Math.cos(player.pitch / 180d * Math.PI),
                -Math.sin(player.pitch / 180d * Math.PI),
                Math.cos(player.yaw / 180d * Math.PI) * Math.cos(player.pitch / 180d * Math.PI)
        ).multiply(f);
        Vector3 point = new Vector3(player.x, player.y + player.getEyeHeight(), player.z);
        while (!player.level.getBlock(point).isSolid()) {
            player.level.addParticle(new SmokeParticle(point), player);
            // TODO: Find a new way that visible from long distance
            motion.y -= 0.05f;
            point.x += motion.x;
            point.y += motion.y;
            point.z += motion.z;
        }
    }
}