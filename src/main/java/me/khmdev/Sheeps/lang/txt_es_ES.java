package me.khmdev.Sheeps.lang;
import java.util.*;

public class txt_es_ES extends ListResourceBundle 
{ 
   public Object[][] getContents() 
   {
      return contenido;
   }
   private Object[][] contenido = { 
		   {"leave_sheep","El  jugador %Player%(%Team%) ha dejado una oveja"},
		   {"player_roba_team","%Player% ha robado una oveja al equipo %Team%"},
		   {"player_roba_player","%Player1% (%Team1%) ha robado una oveja a %Player2%(%Team2%)"}
		   
   };
}