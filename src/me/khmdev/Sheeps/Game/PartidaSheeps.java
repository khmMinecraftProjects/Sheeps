package me.khmdev.Sheeps.Game;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;

import me.khmdev.API.API;

import me.khmdev.API.Games.Control;
import me.khmdev.API.Games.IGame;
import me.khmdev.API.Games.Jugador;
import me.khmdev.API.Games.Partidas.PartidaEquipos4;
import me.khmdev.API.Inventory.StandarInventorys;

public class PartidaSheeps extends PartidaEquipos4 {

	private List<Location> spawns=new LinkedList<>(); 
	public PartidaSheeps(String s, IGame game2, API ap) {
		super(s, game2, ap);
		pMax=1;
		Max = 2 ;
		actualizarSign();
		inventory=StandarInventorys.getInventory("Armor");
	}

	@Override
	public void iniControl() {
		control = new Control(this, game);
	}

	
	@Override
	public Jugador newIJ() {
		return new Jugador();
	}
	public boolean Funcional() {
		return super.Funcional()&&spawns.size()!=0;
	}

	public void addSpawn(Location location) {
		spawns.add(location);
	}

}
