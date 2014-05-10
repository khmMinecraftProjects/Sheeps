package me.khmdev.Sheeps.Game;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Sheep;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.khmdev.API.CustomInventorys.CItems;
import me.khmdev.API.CustomInventorys.CustomItem;
import me.khmdev.API.Players.AuxPlayer;

public class Silla extends CustomItem {
	private static HashMap<String, Boolean> order=
			new HashMap<>();
	private static final String libre="Vacia",
			ocupada="Ocupada";
	private ItemStack lib=AuxPlayer.getItem(Material.SADDLE,
			"Silla de montar", 0, libre),
			oc=AuxPlayer.getItem(Material.SADDLE,
					"Silla de montar", 0, ocupada);
	public Silla(){
		super(AuxPlayer.getItem(Material.SADDLE,
				"Silla de montar", 0, libre));

		CItems.addItem(new Silla(AuxPlayer.getItem(Material.SADDLE,
				"Silla de montar", 0, ocupada)));
	}
	private Silla(ItemStack it){
		super(it);
	}
	public void execute(InventoryClickEvent event) {
		event.setCancelled(true);
		
	}

	@SuppressWarnings("deprecation")
	public void execute(PlayerInteractEvent event) {
		
		event.setCancelled(true);
		boolean b=order.containsKey(event.getPlayer().getName())?
				order.get(event.getPlayer().getName()):false;
				order.remove(event.getPlayer().getName());
		if(b){return;}

		if(event.getPlayer().getPassenger()!=null){
			event.getPlayer().getPassenger().leaveVehicle();
			event.getPlayer().sendMessage("sheep desmontada");
			event.getPlayer().setItemInHand(lib);
			event.getPlayer().updateInventory();
		}
	}
	/*
	private boolean isEmpty(ItemStack item){
		List<String> lore=(
				item!=null
				&&item.getItemMeta()!=null)?
						item.getItemMeta().getLore():null;
		if(lore==null||lore.contains(libre)){return true;}
		return false;
	}
	*/
	@SuppressWarnings("deprecation")
	public void execute(PlayerInteractEntityEvent event){
		event.getPlayer().sendMessage("action");
		event.setCancelled(true);
		Sheep sheep=(Sheep) (event.getRightClicked() instanceof Sheep?
				event.getRightClicked():null);
		if(sheep==null){return;}
		
		order.put(event.getPlayer().getName(), true);
		if(event.getPlayer().getPassenger()==null){
			event.getPlayer().sendMessage("Libre sheep montada");
			event.getPlayer().setPassenger(sheep);
			event.getPlayer().setItemInHand(oc);
			event.getPlayer().updateInventory();
		}else{
			event.getPlayer().sendMessage("Ocupada sheep cambiada");
			event.getPlayer().getPassenger().leaveVehicle();
			event.getPlayer().setPassenger(sheep);
			event.getPlayer().setItemInHand(oc);
			event.getPlayer().updateInventory();
		}
		
	};
}
