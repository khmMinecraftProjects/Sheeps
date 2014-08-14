package me.khmdev.Sheeps.lang;

import java.util.Locale;
import java.util.ResourceBundle;

public class Lang {
	public static ResourceBundle lang=ResourceBundle.getBundle(
			"me.khmdev.Sheeps.lang.txt",
			new Locale("es", "ES"));;

	public static String get(String s){
		return lang.getString(s);
	}

}
