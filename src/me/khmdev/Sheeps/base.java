package me.khmdev.Sheeps;

import me.khmdev.API.API;
import me.khmdev.API.Games.APIG;
import me.khmdev.Sheeps.Game.GameSheeps;


public class base {
	APIG apig;
	API api;
	private GameSheeps game;

	public base(init plug){

		Kits.init();
		api = API.getInstance();

		apig = APIG.getInstance();
		game=new GameSheeps(api);
		apig.newGame(game);

	}

}
