package me.khmdev.Sheeps.Game;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.khmdev.APIAuxiliar.Inventory.StandarInventorys;
import me.khmdev.APIBase.API;

import me.khmdev.APIBase.Almacenes.Almacen;
import me.khmdev.APIBase.Almacenes.local.LocAlmacen;
import me.khmdev.APIGames.Auxiliar.Control;
import me.khmdev.APIGames.Auxiliar.EquipoVar;
import me.khmdev.APIGames.Auxiliar.Jugador;
import me.khmdev.APIGames.Auxiliar.Variables;
import me.khmdev.APIGames.Auxiliar.ConstantesGames.Equipo;
import me.khmdev.APIGames.Games.IGame;
import me.khmdev.APIGames.Partidas.PartidaEquipos4;
import me.khmdev.APIGames.Scores.BoardGames;
import me.khmdev.Sheeps.lang.Lang;

public class PartidaSheeps extends PartidaEquipos4 {

	private List<Location> spawns = new LinkedList<>();

	public PartidaSheeps(String s, IGame game2, API ap) {
		super(s, game2, ap);
		pMax = 1;
		Max = 2;
		actualizarSign();
		inventory = StandarInventorys.getInventory("Kit estandar SP");
	}

	public void finalizar() {
		super.finalizar();
		num = 1;
	}
	private long randomNum(long num2) {
		num2=(int) (Math.random()*num2);
		if(num2==0){num2++;num2=1;}
		return num2<1?1:num2;
	}

	@SuppressWarnings("deprecation")
	public void Equipar(Jugador j) {
		super.Equipar(j);
		j.getPlayer().getInventory().addItem(((GameSheeps) game).getSilla());
		j.getPlayer().updateInventory();
	}

	private long last = 0, respawn = 15000, num = 1;

	@Override
	public BoardGames getScore() {
		return new BoardSheeps(this);
	}

	public long getTimeNextSheep() {
		long b = (System.currentTimeMillis() - last);
		return respawn < b ? 0 : (respawn - b);
	}

	@Override
	public void comprobaciones() {
		super.comprobaciones();

		if (getTimeNextSheep() == 0) {
			last = System.currentTimeMillis();
			// num++;
			for (int i = 0; i < randomNum(num); i++) {
				Location l = getRandomSpawn();
				spawnSheep(DyeColor.WHITE, l, "",false);
			}
		}
	}

	public String Datos() {
		String s = "";
		String okList = spawns != null && spawns.size() != 0 ? "[X]" : "[ ]";

		s += okList + "Spanws: " + (spawns != null ? spawns.size() : "") + "\n";
		s += ok(SA)
				+ "Sheep equipo A: "
				+ (SA != null ? SA.getX() + "-" + SA.getY() + "-" + SA.getZ()
						: "") + "\n";
		s += ok(SB)
				+ "Sheep equipo B: "
				+ (SB != null ? SB.getX() + "-" + SB.getY() + "-" + SB.getZ()
						: "") + "\n";
		s += ok(SC)
				+ "Sheep equipo C: "
				+ (SC != null ? SC.getX() + "-" + SC.getY() + "-" + SC.getZ()
						: "") + "\n";
		s += ok(SD)
				+ "Sheep equipo D: "
				+ (SD != null ? SD.getX() + "-" + SD.getY() + "-" + SD.getZ()
						: "") + "\n";

		s += super.Datos();
		return s;
	}

	private Sheep ShA, ShB, ShC, ShD;

	@Override
	public void iniciada() {
		super.iniciada();
		ShA = spawnSheep(Variables.A.dye, SA, Variables.A.chat+ Variables.A.name,false);
		ShB = spawnSheep(Variables.B.dye, SB, Variables.B.chat+ Variables.B.name,false);
		ShC = spawnSheep(Variables.C.dye, SC, Variables.C.chat+ Variables.C.name,false);
		ShD = spawnSheep(Variables.D.dye, SD, Variables.D.chat+ Variables.D.name,false);
	}

	public Sheep spawnSheep(DyeColor d, Location l, String st, boolean canmove) {
		Sheep s = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
		s.setCustomName(st);
		s.setAdult();
		s.setColor(d);
		if (!canmove) {
			s.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000,
					100000, true));
		}
		return s;
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
		return super.Funcional() && spawns.size() != 0 && SA != null
				&& SB != null && SC != null && SD != null;
	}

	public void addSpawn(Location location) {
		spawns.add(location);
	}

	public Location getRandomSpawn() {
		return spawns.get((int) (spawns.size() * Math.random()));
	}

	Location SA, SB, SC, SD;

	public void setSheepA(Location l) {
		SA = l;
	}

	public void setSheepB(Location l) {
		SB = l;
	}

	public void setSheepC(Location l) {
		SC = l;
	}

	public void setSheepD(Location l) {
		SD = l;
	}

	@Override
	public void guardar(Almacen nbt) {

		super.guardar(nbt);

		if (SA != null) {
			LocAlmacen.guardar(nbt, SA, "SA");
		}
		if (SB != null) {
			LocAlmacen.guardar(nbt, SB, "SB");
		}
		if (SC != null) {
			LocAlmacen.guardar(nbt, SC, "SC");
		}
		if (SD != null) {
			LocAlmacen.guardar(nbt, SD, "SD");
		}
		LocAlmacen.guardarList(nbt, spawns, "SheepSpawns");
	}

	public void cargar(Almacen nbt) {
		super.cargar(nbt);
		SA = LocAlmacen.cargar(nbt, "SA");
		SB = LocAlmacen.cargar(nbt, "SB");
		SC = LocAlmacen.cargar(nbt, "SC");
		SD = LocAlmacen.cargar(nbt, "SD");

		spawns = LocAlmacen.cargarList(nbt, "SheepSpawns");

	}

	public boolean leaveSheep(Jugador j, Sheep s, Sheep o) {

		switch (j.getEquipo()) {
		case A:
			if (s == (ShA)) {
				j.getPlayer().setPassenger(null);
				s.leaveVehicle();
				Acumularsheep(ShA, o);
				sendAll(Lang.get("leave_sheep")
						.replace("%Player%", j.toString())
						.replace("%Team%", Variables.A.name));
				j.setPuntuacion(j.getPuntuacion() + 1);
				pA++;
				return true;
			}
			break;
		case B:
			if (s.equals(ShB)) {
				j.getPlayer().setPassenger(null);
				s.leaveVehicle();
				Acumularsheep(ShB, o);
				sendAll(Lang.get("leave_sheep")
						.replace("%Player%", j.toString())
						.replace("%Team%", Variables.B.name));
				j.setPuntuacion(j.getPuntuacion() + 1);
				pB++;
				return true;
			}
			break;
		case C:
			if (s.equals(ShC)) {
				j.getPlayer().setPassenger(null);
				s.leaveVehicle();
				Acumularsheep(ShC, o);
				sendAll(Lang.get("leave_sheep")
						.replace("%Player%", j.toString())
						.replace("%Team%", Variables.C.name));
				j.setPuntuacion(j.getPuntuacion() + 1);
				pC++;
				return true;
			}
			break;
		case D:
			if (s.equals(ShD)) {
				j.getPlayer().setPassenger(null);
				s.leaveVehicle();
				Acumularsheep(ShD, o);
				sendAll(Lang.get("leave_sheep")
						.replace("%Player%", j.toString())
						.replace("%Team%", Variables.D.name));
				j.setPuntuacion(j.getPuntuacion() + 1);
				pD++;
				return true;
			}
			break;
		default:
			break;
		}
		return false;

	}

	private void Acumularsheep(Sheep vehicle, Sheep o) {
		/*
		while (vehicle.getPassenger() != null
				&& vehicle.getPassenger() instanceof Sheep) {
			vehicle = (Sheep) vehicle.getPassenger();
		}
		vehicle.setPassenger(o);
		*/
		o.leaveVehicle();
	}

	public Sheep getSheep(Sheep s, Jugador j) {
		Sheep p = s;
		while (p.getPassenger() != null && p.getPassenger() instanceof Sheep) {
			p = (Sheep) p.getPassenger();
		}
		while (s.getVehicle() != null && s.getVehicle() instanceof Sheep) {
			s = (Sheep) s.getVehicle();
		}

		if (p == ShA || p == ShB || p == ShC || p == ShD) {
			return null;
		}
		EquipoVar rob = null, lad = Variables.get(j.getEquipo());
		/*if (s.equals(ShA)) {
			if (j.getEquipo() != Equipo.A) {
				rob = Variables.A;
				pA--;
			} else {
				return null;
			}
		} else if (s.equals(ShB)) {
			if (j.getEquipo() != Equipo.B) {
				rob = Variables.B;
				pB--;
			} else {
				return null;
			}
		} else if (s.equals(ShC)) {
			if (j.getEquipo() != Equipo.C) {
				rob = Variables.C;
				pC--;
			} else {
				return null;
			}
		} else if (s.equals(ShD)) {
			if (j.getEquipo() != Equipo.D) {
				rob = Variables.D;
				pD--;
			} else {
				return null;
			}
		} else {*/
			if (s.getVehicle() instanceof Player) {
				Player pl = (Player) s.getVehicle();
				Jugador jene = getJugador(pl.getName());
				p.setColor(lad.dye);
				if (jene != null && jene.getEquipo() != j.getEquipo()) {
					sendAll(Lang
							.get("player_roba_player")
							.replace("%Player1%", j.getPlayer().getName())
							.replace("%Team1%", lad.name)
							.replace("%Player2%", pl.getName())
							.replace("%Team2%",
									Variables.get(jene.getEquipo()).name));
				}
			}
			DyeColor clr=p.getColor();
			if(clr!=DyeColor.WHITE){
				
				if(clr==Variables.A.dye&&j.getEquipo()!=Equipo.A){
					rob=Variables.A;pA--;
				}else if(clr==Variables.B.dye&&j.getEquipo()!=Equipo.B){
					rob=Variables.B;pB--;
				}else if(clr==Variables.C.dye&&j.getEquipo()!=Equipo.C){
					rob=Variables.C;pC--;
				}else if(clr==Variables.D.dye&&j.getEquipo()!=Equipo.D){
					rob=Variables.D;pD--;
				}else{
					return null;
				}
				
				sendAll(Lang.get("player_roba_team")
						.replace("%Player%", j.getPlayer().getName())
						.replace("%Team%", rob.name));
			}
			
			return p;
		/*}

		sendAll(Lang.get("player_roba_team")
				.replace("%Player%", j.getPlayer().getName())
				.replace("%Team%", rob.name));
		p.setColor(lad.dye);
		return p;*/
	}
	@Override
	public void cargaConf(ConfigurationSection section) {
		if(section.isInt("tiempo_spawneo_sheeps")){
			respawn=section.getInt("tiempo_spawneo_sheeps")*1000;
		}else{
			section.set("tiempo_spawneo_sheeps", respawn/1000);
		}
		if(section.isInt("numero_sheeps")){
			num=section.getInt("numero_sheeps");
		}else{
			section.set("numero_sheeps", num);
		}
		super.cargaConf(section);
	}

}
