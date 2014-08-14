package me.khmdev.Sheeps.Game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import me.khmdev.APIAuxiliar.Inventory.CustomInventorys.CItems;
import me.khmdev.APIBase.API;
import me.khmdev.APIGames.Books.SelectorGame;
import me.khmdev.APIGames.Games.GameE4;
import me.khmdev.APIGames.Partidas.IPartida;
import me.khmdev.Sheeps.Items.Kits;
import me.khmdev.Sheeps.Items.eggSheep;

public class GameSheeps extends GameE4 {
	Silla silla;
	eggSheep egg;

	public GameSheeps(API ap) {
		super(ap, "SP", "Sheeps");
		silla = new Silla(this);
		egg = new eggSheep(this);
		CItems.addItem(egg);
	}

	public String help() {
		String s = "";
		s += super.help();
		s += ("          setSA/setSB/setSC/setSD\n");
		s += ("          addSpawn\n");
		return s;

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

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (args.length >= 3) {

			PartidaSheeps partida = (PartidaSheeps) NPartida(args[1]);
			if (partidas.containsKey(args[1])) {
				partida = (PartidaSheeps) partidas.get(args[1]);
			}
			if (args[2].equalsIgnoreCase("addSpawn")) {
				if (sender.getName().equalsIgnoreCase("console")) {
					return true;
				}

				partida.addSpawn(Bukkit.getServer().getPlayer(sender.getName())
						.getLocation());
				sender.sendMessage("Spawn añadido");
				return true;
			}
			if (args[2].equalsIgnoreCase("setSA")) {
				if (sender.getName().equalsIgnoreCase("console")) {
					return true;
				}

				partida.setSheepA(Bukkit.getServer()
						.getPlayer(sender.getName()).getLocation());
				sender.sendMessage("Spawn añadido");
				return true;

			}

			if (args[2].equalsIgnoreCase("setSB")) {
				if (sender.getName().equalsIgnoreCase("console")) {
					return true;
				}

				partida.setSheepB(Bukkit.getServer()
						.getPlayer(sender.getName()).getLocation());
				sender.sendMessage("Spawn añadido");
				return true;

			}

			if (args[2].equalsIgnoreCase("setSC")) {
				if (sender.getName().equalsIgnoreCase("console")) {
					return true;
				}

				partida.setSheepC(Bukkit.getServer()
						.getPlayer(sender.getName()).getLocation());
				sender.sendMessage("Spawn añadido");
				return true;

			}

			if (args[2].equalsIgnoreCase("setSD")) {
				if (sender.getName().equalsIgnoreCase("console")) {
					return true;
				}

				partida.setSheepD(Bukkit.getServer()
						.getPlayer(sender.getName()).getLocation());
				sender.sendMessage("Spawn añadido");
				return true;
			}
		}
		return super.onCommand(sender, cmd, label, args);

	}

	public void help(CommandSender sender) {
		sender.sendMessage(help());
		sender.sendMessage("          MA\n");
		sender.sendMessage("          MB\n");
	}

	@Override
	protected void initVentajas() {
		// gestorV = SelectorGame.getGV(this, Material.WOOL);

	}

	protected void initKits() {
		gestorK = SelectorGame.getGK(this, Material.DIAMOND_SWORD);
		Kits.init(gestorK);

	}

	public ItemStack getSilla() {
		return silla.getItem();
	}

}
