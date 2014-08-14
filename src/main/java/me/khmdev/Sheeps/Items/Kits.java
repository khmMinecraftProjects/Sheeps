package me.khmdev.Sheeps.Items;

import me.khmdev.APIAuxiliar.Inventory.InventoryBase;
import me.khmdev.APIAuxiliar.Inventory.InventoryConfig;
import me.khmdev.APIAuxiliar.Inventory.StandarInventorys;
import me.khmdev.APIAuxiliar.Inventory.CustomInventorys.CItems;
import me.khmdev.APIAuxiliar.Players.AuxPlayer;
import me.khmdev.APIGames.Books.Kits.GestorKit;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Kits {
	private static final String
				standar="Kit estandar SP",
				arquero="Arquero",
					guerrero="Guerrero",
					guerreroU="Guerrero Ultra",
					ovejero="Ovejero",
					vip="vipkit.apig";
			
	public static void init(GestorKit gestorK) {
		if (gestorK == null) {
			return;
		}
		
		if (!StandarInventorys.containInventory(standar)) {
			standarKit();
		}
		InventoryBase inv=StandarInventorys.getInventory(standar);
		if(inv!=null){
			gestorK.addKit(Material.WOOD_SWORD,inv);
		}

		if (!StandarInventorys.containInventory(arquero)) {
			ArqueroKit();
		}
		inv=StandarInventorys.getInventory(arquero);
		if(inv!=null){
			gestorK.addKit(Material.BOW,inv,10);
		}

		if (!StandarInventorys.containInventory(guerrero)) {
			guerreroKit();
		}
		inv=StandarInventorys.getInventory(guerrero);
		if(inv!=null){
			gestorK.addKit(Material.IRON_SWORD,inv,10);
		}
		
		if (!StandarInventorys.containInventory(guerreroU)) {
			guerreroUltraKit();
		}
		inv=StandarInventorys.getInventory(guerreroU);
		if(inv!=null){
			gestorK.addKit(Material.IRON_HOE,inv,5,vip);
		}

		if (!StandarInventorys.containInventory(ovejero)) {
			obejeroKit();
		}
		inv=StandarInventorys.getInventory(ovejero);
		if(inv!=null){
			gestorK.addKit(Material.MONSTER_EGG,inv,0,vip);
		}

	}

	public static void standarKit() {
		InventoryBase inv = new InventoryBase(standar);
		inv.setInventory(new ItemStack[] { new ItemStack(Material.WOOD_SWORD) });
		inv.setArmor(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS),
				new ItemStack(Material.LEATHER_LEGGINGS),
				new ItemStack(Material.LEATHER_CHESTPLATE),
				new ItemStack(Material.LEATHER_HELMET) });

		StandarInventorys.addInventory(standar, new InventoryConfig(
				standar, inv.getArmor(), inv.getInventory()));
	}

	public static void ArqueroKit() {
		InventoryBase inv = new InventoryBase(arquero);
		ItemStack bow = new ItemStack(Material.BOW);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		inv.setInventory(new ItemStack[] { bow,
				new ItemStack(Material.ARROW, 1) });
		inv.setArmor(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS),
				new ItemStack(Material.LEATHER_LEGGINGS),
				new ItemStack(Material.LEATHER_CHESTPLATE),
				new ItemStack(Material.LEATHER_HELMET) });

		StandarInventorys.addInventory(arquero, new InventoryConfig(
				arquero, inv.getArmor(), inv.getInventory()));
	}

	public static void guerreroKit() {
		InventoryBase inv = new InventoryBase(guerrero);
		inv.setInventory(new ItemStack[] { new ItemStack(Material.IRON_SWORD) });

		inv.setArmor(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS),
				new ItemStack(Material.LEATHER_LEGGINGS),
				new ItemStack(Material.LEATHER_CHESTPLATE),
				new ItemStack(Material.LEATHER_HELMET) });

		StandarInventorys.addInventory(guerrero, new InventoryConfig(
				guerrero, inv.getArmor(), inv.getInventory()));
	}

	public static void guerreroUltraKit() {
		InventoryBase inv = new InventoryBase(guerreroU);
		Azada az = new Azada();
		CItems.addItem(az);
		inv.setInventory(new ItemStack[] { new ItemStack(Material.IRON_SWORD),
				az.getItem() });

		inv.setArmor(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS),
				new ItemStack(Material.LEATHER_LEGGINGS),
				new ItemStack(Material.IRON_CHESTPLATE),
				new ItemStack(Material.LEATHER_HELMET) });

		StandarInventorys.addInventory(guerreroU, new InventoryConfig(
				guerreroU, inv.getArmor(), inv.getInventory()));
	}

	private static void obejeroKit() {
		InventoryBase inv = new InventoryBase(ovejero);
		SpeedHacha sp = new SpeedHacha();
		CItems.addItem(sp);
		inv.setInventory(new ItemStack[] {
				new ItemStack(Material.IRON_SWORD),
				AuxPlayer.getItem(Material.MONSTER_EGG, "Huevo de oveja", 91,
						"Solo se puede usar a cierta distancia del spawn"),
				sp.getItem() });

		inv.setArmor(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS),
				new ItemStack(Material.LEATHER_LEGGINGS),
				new ItemStack(Material.LEATHER_CHESTPLATE),
				new ItemStack(Material.LEATHER_HELMET) });

		StandarInventorys.addInventory(ovejero, new InventoryConfig(
				ovejero, inv.getArmor(), inv.getInventory()));
	}
}
