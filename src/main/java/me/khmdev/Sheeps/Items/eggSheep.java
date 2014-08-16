package me.khmdev.Sheeps.Items;

import me.khmdev.APIAuxiliar.Effects.ListenerFreeze;
import me.khmdev.APIAuxiliar.Inventory.CustomInventorys.CustomItem;
import me.khmdev.APIAuxiliar.Players.AuxPlayer;
import me.khmdev.APIGames.Auxiliar.Jugador;
import me.khmdev.Sheeps.Game.GameSheeps;
import me.khmdev.Sheeps.Game.PartidaSheeps;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class eggSheep extends CustomItem {
	GameSheeps g;

	public eggSheep(GameSheeps game) {
		super(AuxPlayer.getItem(Material.MONSTER_EGG, "Huevo de oveja", 91,
				"Solo se puede usar a cierta distancia del spawn"));
		g = game;
	}

	@Override
	public void execute(InventoryClickEvent event) {

	}

	@Override
	public void execute(PlayerInteractEvent e) {
		if (ListenerFreeze.conteinPlayer(e.getPlayer().getName())) {
			return;
		}
		Jugador j = g.getJugador(e.getPlayer().getName());
		PartidaSheeps p = (PartidaSheeps) j.getPartida();

		switch (j.getEquipo()) {
		case A:
			if (p.getRA().getPosicion().distance(j.getPlayer().getLocation()) > 30) {
				e.setCancelled(false);
				return;
			}
			break;
		case B:
			if (p.getRA().getPosicion().distance(j.getPlayer().getLocation()) > 30) {
				e.setCancelled(false);
				return;
			}
			break;
		case C:
			if (p.getRA().getPosicion().distance(j.getPlayer().getLocation()) > 30) {
				e.setCancelled(false);
				return;
			}
			break;
		case D:
			if (p.getRA().getPosicion().distance(j.getPlayer().getLocation()) > 30) {
				e.setCancelled(false);
				return;
			}
			break;
		default:
			break;
		}
		j.getPlayer().sendMessage("No puedes usar eso aqui");
		e.setCancelled(true);
	}

}
