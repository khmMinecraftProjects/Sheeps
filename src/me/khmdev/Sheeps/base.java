package me.khmdev.Sheeps;

import me.khmdev.API.API;
import me.khmdev.API.Games.APIG;


public class base {
	APIG apig;
	API api;

	public base(init plug){

		Kits.init();
		api = API.getInstance();

		apig = APIG.getInstance();
		//apig.newGame(g);

	}

}
