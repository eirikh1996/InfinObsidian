package io.github.eirikh1996.infinobsidian;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InfinObsidian extends JavaPlugin implements Listener{
	World w;
	Block b;
	Player p;
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this,this);
	}
	public void onDisable() {
		
	}

	@EventHandler
	public void onFromTo(BlockFromToEvent event){
		final Block from = event.getBlock();
		if (!from.getType().name().endsWith("LAVA")){
			return;
		}
		final Block to = event.getToBlock();
		final Material toType = to.getType();
		if (!adjacentToWater(to)){
			return;
		}
		if (toType.equals(Material.REDSTONE_WIRE) || toType.equals(Material.TRIPWIRE)){
			new BukkitRunnable() {
				@Override
				public void run() {
					to.setType(Material.OBSIDIAN);
				}
			}.runTask(this);
		}


	}

	private boolean adjacentToWater(Block b){
		return b.getRelative(1,0,0).getType().name().endsWith("WATER") ||
				b.getRelative(-1,0,0).getType().name().endsWith("WATER") ||
				b.getRelative(0,0,1).getType().name().endsWith("WATER") ||
				b.getRelative(1,0,0).getType().name().endsWith("WATER");
	}
}
