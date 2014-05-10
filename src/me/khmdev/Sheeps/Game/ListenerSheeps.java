package me.khmdev.Sheeps.Game;

import me.khmdev.API.Games.Jugador;
import me.khmdev.API.Games.ListenerGames;
import me.khmdev.API.Games.Partidas.Partida;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ListenerSheeps extends ListenerGames implements Listener {
	GameSheeps game;


	public ListenerSheeps(GameSheeps plug) {
		super(plug);
		game = plug;
	}


	@Override
	protected void spawn(Jugador j, PlayerRespawnEvent event) {
		Partida p =  (Partida) j.getPartida();
		event.setRespawnLocation(p.spawnZone(j));
		p.Equipar(j);
	}


	@Override
	protected void death(Jugador j, EntityDeathEvent event) {
		
	}

	
}
