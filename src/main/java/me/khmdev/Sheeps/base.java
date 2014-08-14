package me.khmdev.Sheeps;

import me.khmdev.APIBase.API;
import me.khmdev.APIGames.APIG;
import me.khmdev.Sheeps.Game.GameSheeps;


public class base {
	APIG apig;
	API api;
	private GameSheeps game;

	public base(init plug){

		api = API.getInstance();

		apig = APIG.getInstance();
		game=new GameSheeps(api);
		apig.newGame(game);

	}

}
