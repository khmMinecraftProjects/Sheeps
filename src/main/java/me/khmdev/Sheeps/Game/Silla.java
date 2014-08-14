package me.khmdev.Sheeps.Game;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.khmdev.APIAuxiliar.Inventory.CustomInventorys.CItems;
import me.khmdev.APIAuxiliar.Inventory.CustomInventorys.CustomItem;
import me.khmdev.APIAuxiliar.Players.AuxPlayer;
import me.khmdev.APIGames.Auxiliar.Jugador;
import me.khmdev.APIGames.Auxiliar.Variables;

public class Silla extends CustomItem {
	private static HashMap<String, Boolean> order = new HashMap<>();
	private static final String libre = "Vacia", ocupada = "Ocupada";
	private ItemStack lib = AuxPlayer.getItem(Material.SADDLE,
			"Silla de montar", 0, libre), oc = AuxPlayer.getItem(
			Material.SADDLE, "Silla de montar", 0, ocupada);
	private static GameSheeps g;

	public Silla(GameSheeps gg) {
		super(AuxPlayer.getItem(Material.SADDLE, "Silla de montar", 0, libre));
		g = gg;
		CItems.addItem(this);
		CItems.addItem(new Silla(AuxPlayer.getItem(Material.SADDLE,
				"Silla de montar", 0, ocupada)));
	}

	private Silla(ItemStack it) {
		super(it);
	}

	public void execute(InventoryClickEvent event) {
		event.setCancelled(true);

	}

	//@SuppressWarnings("deprecation")
	public void execute(PlayerInteractEvent event) {

		event.setCancelled(true);
		/*
		 * boolean b=order.containsKey(event.getPlayer().getName())?
		 * order.get(event.getPlayer().getName()):false;
		 * order.remove(event.getPlayer().getName()); if(b){return;}
		 */
		/*
		 * if(event.getPlayer().getPassenger()!=null){
		 * event.getPlayer().getPassenger().leaveVehicle();
		 * event.getPlayer().sendMessage("sheep desmontada");
		 * event.getPlayer().setItemInHand(lib);
		 * event.getPlayer().updateInventory(); }
		 */
	}

	/*
	 * private boolean isEmpty(ItemStack item){ List<String> lore=( item!=null
	 * &&item.getItemMeta()!=null)? item.getItemMeta().getLore():null;
	 * if(lore==null||lore.contains(libre)){return true;} return false; }
	 */
	@SuppressWarnings("deprecation")
	public void execute(PlayerInteractEntityEvent event){
		
		event.setCancelled(true);
		
		Sheep sheep=(Sheep) (event.getRightClicked() instanceof Sheep?
				event.getRightClicked():null);
		if(sheep==null){return;}
		Jugador j=g.getJugador(event.getPlayer().getName());
		PartidaSheeps p=(PartidaSheeps) j.getPartida();
		order.put(event.getPlayer().getName(), true);
		
		
		if(event.getPlayer().getPassenger()==null){
			Sheep s=null;
			if((s=p.getSheep(sheep,j))!=null){
				event.getPlayer().sendMessage("Has cogido una oveja");
				s.setColor(Variables.get(j.getEquipo()).dye);
				
				s.removePotionEffect(PotionEffectType.SLOW);

				
				event.getPlayer().setPassenger(s);
				event.getPlayer().setItemInHand(oc);
				event.getPlayer().updateInventory();
			}else{
				event.getPlayer().sendMessage("No puedes coger esa oveja");
			}
		}else{
			Entity passenger=event.getPlayer().getPassenger();
			if(passenger instanceof Sheep){
				if(p.leaveSheep(j, sheep,(Sheep) passenger)){
					((Sheep) passenger).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000,
							100000, true));
					event.getPlayer().setItemInHand(lib);
					event.getPlayer().updateInventory();
				}
			}
		}
		
	};
}
