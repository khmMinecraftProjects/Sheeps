package me.khmdev.Sheeps.Game;

import org.bukkit.entity.Player;

import me.khmdev.APIAuxiliar.ScoreBoard.FunctorString;
import me.khmdev.APIAuxiliar.ScoreBoard.ObjetiveData;
import me.khmdev.APIAuxiliar.ScoreBoard.getConstant;
import me.khmdev.APIAuxiliar.ScoreBoard.getStringConst;
import me.khmdev.APIGames.Scores.BoardE4;

public class BoardSheeps extends BoardE4{
	protected PartidaSheeps sheeps;
	public BoardSheeps(PartidaSheeps p) {
		super(p);
		sheeps=p;
		objs.add(new ObjetiveData(null,new getConstant(-1)));
		objs.add(new ObjetiveData(
				new getStringConst("&6Siguiente:"),new getConstant(-2)));
		
		objs.add(new ObjetiveData(
				new FunctorString() {
					
					@Override
					public String getString(Player p) {
						return ((int)sheeps.getTimeNextSheep()/1000)+"";
					}
				},new getConstant(-3)));
	}
	

}
