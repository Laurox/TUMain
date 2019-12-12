package ga.tumgaming.tumine.tuminetemplate.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
	public BlockBreakListener() {
	}

	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
	}
}
