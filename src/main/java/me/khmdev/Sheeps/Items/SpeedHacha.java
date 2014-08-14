package me.khmdev.Sheeps.Items;

import me.khmdev.APIAuxiliar.Inventory.CustomInventorys.CItems;
import me.khmdev.APIAuxiliar.Inventory.CustomInventorys.CustomItem;
import me.khmdev.APIAuxiliar.Players.AuxPlayer;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedHacha extends CustomItem{
	private long timeout=10000;

	public SpeedHacha(){
		super(AuxPlayer.getItem(Material.IRON_AXE, "Hacha de velocidad"));
	}
	@Override
	public void execute(InventoryClickEvent event) {

	}

	@Override
	public void execute(PlayerInteractEvent event) {
		if(event.getItem().getDurability()!=0){
			event.getPlayer().sendMessage("Aun no se ha recargado el item");
			return;
		}
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
				15*20, 2));
		
		ItemStack out=event.getPlayer().getItemInHand();
		CItems.addTimer(out, timeout,event.getPlayer());
	}


}
