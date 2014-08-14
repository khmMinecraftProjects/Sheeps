package me.khmdev.Sheeps.Game;

import me.khmdev.APIGames.Auxiliar.Jugador;
import me.khmdev.APIGames.Auxiliar.ListenerGames;
import me.khmdev.APIGames.Partidas.Partida;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ListenerSheeps extends ListenerGames implements Listener {
	GameSheeps game;


	public ListenerSheeps(GameSheeps plug) {
		super(plug);
		game = plug;
	}
	@EventHandler
	public void Asegurar(EntityDamageByEntityEvent event) {
		super.Asegurar(event);
		if(event.getEntity() instanceof Sheep &&
				event.getDamager() instanceof Player &&
				game.containsJugador(((Player)event.getDamager()).getName())){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void Asegurar(EntityDeathEvent event) {
		System.out.println(event.getEntity());
		if(event.getEntityType()==EntityType.SHEEP){
			System.out.println("DEATH SHEEP "
		+event.getEntity().getCustomName());
		}
	}
	@Override
	protected void spawn(Jugador j, PlayerRespawnEvent event) {
		Partida p =  (Partida) j.getPartida();
		event.setRespawnLocation(p.spawnZone(j));
		p.Equipar(j);
	}


	@Override
	protected void death(Jugador j, EntityDeathEvent event) {
		Entity ent=j.getPlayer().getPassenger();
		if(ent instanceof Sheep){
			Sheep s=(Sheep) ent;
			s.setColor(DyeColor.WHITE);
		}
	}

	
}
