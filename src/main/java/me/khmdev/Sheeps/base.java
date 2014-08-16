package me.khmdev.Sheeps;

import org.bukkit.plugin.Plugin;

import me.khmdev.APIBase.API;
import me.khmdev.APIGames.APIG;
import me.khmdev.Sheeps.Game.GameSheeps;


public class base {
	APIG apig;
	API api;
	private GameSheeps game;
	private static init instance;
	public base(init plug){
		instance=plug;
		api = API.getInstance();

		apig = APIG.getInstance();
		game=new GameSheeps(api);
		apig.newGame(game);

	}

	public static Plugin getInstance() {
		return instance;
	}

}
