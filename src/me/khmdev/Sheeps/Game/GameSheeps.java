package me.khmdev.Sheeps.Game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.khmdev.API.API;
import me.khmdev.API.Games.GameE4;
import me.khmdev.API.Games.Partidas.IPartida;
import me.khmdev.API.Games.Ventajas.SelectorGame;


public class GameSheeps extends GameE4 {

	public GameSheeps(API ap) {
		super(ap, "SP", "Sheeps");
	}

	@Override
	public void iniListen() {
		listener = new ListenerSheeps(this);
		listen();
	}

	@Override
	public IPartida NPartida(String s) {
		return new PartidaSheeps(s, this, api);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length == 0) {
			sender.sendMessage(help());

			return false;
		}
		if (args[0].equalsIgnoreCase(name)) {

			if (args.length < 3) {
				sender.sendMessage("No se ha introducido comando");
				help(sender);

				return true;
			}
			PartidaSheeps partida = (PartidaSheeps) NPartida(args[1]);
			if (partidas.containsKey(args[1])) {
				partida = (PartidaSheeps) partidas.get(args[1]);
			}
			if (args[1].equalsIgnoreCase("addSpawn")) {
				if (sender.getName().equalsIgnoreCase("console")) {
					return true;
				}

				partida.addSpawn(Bukkit.getServer().getPlayer(sender.getName())
						.getLocation());
				sender.sendMessage("Spawn añadido");
			}

			return super.onCommand(sender, cmd, label, args);

		}

		return false;

	}

	public void help(CommandSender sender) {
		sender.sendMessage(help());
		sender.sendMessage("          MA\n");
		sender.sendMessage("          MB\n");
	}

	@Override
	protected void initVentajas() {
		gestorV = SelectorGame.getGV(this, 
				Material.WOOL);

	}

	protected void initKits() {
		gestorK = SelectorGame.getGK(this, Material.WOOL);


	}

}
