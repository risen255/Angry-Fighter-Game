package com.angryfighter.game;

import java.util.ArrayList;

import com.angryfighter.game.feature.Feature;
import com.angryfighter.game.manager.GameManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Klasa swiata obslugujaca i wyswietlajaca wszystkie obiekty.
*/

public class World {
	
	public final String[] mapName =
	{	
			"VobVisual//bullet.png",
			"VobVisual//cegla01.png",
			"VobVisual//ziemia01.png",
			"VobVisual//kamien01.png",
			"VobVisual//kamien02.png",
			"VobVisual//kamien03.png",
			"VobVisual//drzewo01.png"
	};
	
	static private World worldGame = new World();
	
	private String currentMap = null;
	
	private ArrayList<Vob> vobList = new ArrayList<Vob>();
	private ArrayList<Npc> npcList = new ArrayList<Npc>();
	private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
	private Bomb bomb;
	private Pliers pliers;
	
	/**
	 * Konstruktor utworzenia swiata
	*/
	
	public World() {
	}
	
	/**
	 * Renderowanie swiata
	 * @param sb powierzchnia do renderowania
	*/
	
	public void render(SpriteBatch sb) {
		if(GameManager.getGameManager().isPaused() == false) {
			for(int i = 0; i < this.vobList.size(); i++)
				this.vobList.get(i).render(sb);
			
			for(int i = 0; i < this.npcList.size(); i++)
				this.npcList.get(i).render(sb);
			
			for(int i = 0; i < this.weaponList.size(); i++) {
				this.weaponList.get(i).render(sb);
			}
			
			if(this.getBomb() != null) {
				GameManager gameManager = GameManager.getGameManager();
				Npc hero = Npc.getHero();
				
				//Bomb ticking
				if(this.getBomb().bombCurrentTime > 0) {
					if(System.currentTimeMillis() > this.getBomb().bombTickingTime) {
						this.getBomb().bombCurrentTime--;					
						this.getBomb().bombTickingTime = System.currentTimeMillis() + 1000;
					}
				}else{
					if(this.getBomb().IsExploded() == false) {
						this.getBomb().doExplode();
					}
				}
				
				if(this.getBomb().IsExploded() == false) {
					int minute = this.getBomb().bombCurrentTime / 60;
					int second = this.getBomb().bombCurrentTime % 60;
					
					String strTime = String.format("%02d:%02d", minute, second);
					gameManager.getFont().setColor(Color.WHITE);;
					gameManager.getFont().draw(sb, strTime, this.getBomb().x - 30, this.getBomb().y + 60);
				}
				
				if(this.getBomb().IsExploded() == false) {
					Vector2 v1 = new Vector2();
					Vector2 v2 = new Vector2();
					hero.getPosition(v1);
					this.getBomb().getPosition(v2);
					
					gameManager.getFont().setColor(Color.RED);
					gameManager.getFont().draw(sb, "Rozbrojenie: " + this.getBomb().getDisarmedProgress() + "%", this.getBomb().x - 20, this.getBomb().y + 100);
					
					if(Feature.getDistance2D(v1.x, v1.y, v2.x, v2.y) <= Bomb.DISARM_DISTANCE && this.getBomb().getDisarmedProgress() < 100) {
						
						if(Gdx.input.isKeyPressed(Keys.E) == false) {
								gameManager.getFont().setColor(Color.WHITE);
								gameManager.getFont().draw(sb, "Aby rozrobic bombe przytrzymaj E", this.getBomb().x - 50, this.getBomb().y - 20);
						}else{
							if(hero.HasPliers() == false) {
								gameManager.getFont().setColor(Color.RED);
								gameManager.getFont().draw(sb, "Potrzebujesz kombinerek.", this.getBomb().x - 50, this.getBomb().y - 20);
							}
						}
					}
				}else{
					if(System.currentTimeMillis() > this.getBomb().explodeTime) {
						gameManager.doGameOver();
					}
				}
			}
			
			if(this.getPliers() != null) {
				this.getPliers().render(sb);
			}
		}
	}
	
	/**
	 * Pobiera liste obiektow wizualnych
	 * @return liste obiektow wizualnych
	*/
	
	public ArrayList<Vob> getVobList() {
		return this.vobList;
	}
	
	/**
	 * Pobiera liste bohaterow
	 * @return liste bohaterow
	*/
	
	public ArrayList<Npc> getNpcList() {
		return this.npcList;
	}
	
	/**
	 * Pobiera liste broni
	 * @return liste broni
	*/
	
	public ArrayList<Weapon> getWeaponList() {
		return this.weaponList;
	}
	
	/**
	 * Dodaje do swiata nowy obiekt wizualny
	 * @param visualName wyglad obiektu
	 * @param x pozycja x
	 * @param y pozycja y
	 * @return nowoutworzony obiekt wizualny
	*/
	
	public Vob createVob(String visualName, float x, float y) {
		Vob vob = new Vob(visualName);
		if(vob != null) {
			vob.setPosition(x, y);
			vobList.add(vob);
			return vob;
		}
		return null;
	}
	
	/**
	 * Dodaje do swiata nowy obiekt wizualny
	 * @param visualID wyglad obiektu
	 * @param x pozycja x
	 * @param y pozycja y
	 * @return nowoutworzony obiekt wizualny
	*/
	
	public Vob createVob(int visualID, float x, float y) {
		Vob vob = new Vob(visualID);
		if(vob != null) {
			vob.setPosition(x, y);
			vobList.add(vob);
			return vob;
		}
		return null;
	}
	
	/**
	 * Dodaje do swiata nowego bohatera
	 * @param visualName wyglad bohatera
	 * @param x pozycja x
	 * @param y pozycja y
	 * @return nowoutworzony bohater
	*/
	
	public Npc createNpc(String visualName, float x, float y) {
		Npc npc = new Npc(visualName);
		if(npc != null) {
			npc.setPosition(x, y);
			npcList.add(npc);
			return npc;
		}
		return null;
	}
	
	/**
	 * Dodaje do swiata nowego bohatera
	 * @param visualID wyglad bohatera
	 * @param x pozycja x
	 * @param y pozycja y
	 * @return nowoutworzony bohater
	*/
	
	public Npc createNpc(int visualID, float x, float y) {
		Npc npc = new Npc(visualID);
		if(npc != null) {
			npc.setPosition(x, y);
			npcList.add(npc);
			return npc;
		}
		return null;
	}
	
	/**
	 * Dodaje do swiata nowa bron
	 * @param visualName wyglad broni
	 * @param x pozycja x
	 * @param y pozycja y
	 * @return nowoutworzona bron
	*/
	
	public Weapon createWeapon(String visualName, float x, float y) {
		Weapon weapon = new Weapon(visualName);
		if(weapon != null) {
			weapon.setPosition(x, y);
			weaponList.add(weapon);
			return weapon;
		}
		return null;
	}
	
	/**
	 * Dodaje do swiata nowa bron
	 * @param visualID wyglad broni
	 * @param x pozycja x
	 * @param y pozycja y
	 * @return nowoutworzona bron
	*/
	
	public Weapon createWeapon(int visualID, float x, float y) {
		Weapon weapon = new Weapon(visualID);
		if(weapon != null) {
			weapon.setPosition(x, y);
			weaponList.add(weapon);
			return weapon;
		}
		return null;
	}
	
	/**
	 * Dodaje do swiata bombe
	 * @param x pozycja x
	 * @param y pozycja y
	 * @param bombTime czas w sekundach do wybuchniecia bomby
	 * @return referencje na obiekt bomby
	*/
	
	public Bomb createBomb(float x, float y, int bombTime) {
		this.vobList.remove(this.bomb);
		this.bomb = new Bomb(x, y, bombTime);
		this.vobList.add(this.bomb);
		return this.bomb;
	}
	
	/**
	 * Pobiera obiekt bomby
	 * @return referencje na obiekt bomby
	*/
	
	public Bomb getBomb() {
		return this.bomb;
	}
	
	/**
	 * Dodaje do swiata kombinerki
	 * @param x pozycja x
	 * @param y pozycja y
	 * @return referencje na obiekt kombinerek
	*/
	
	public Pliers createPliers(float x, float y) {
		this.pliers = new Pliers(x, y);
		return this.pliers;
	}
	
	/**
	 * Ustala obiekt aktualnych kombinerek
	 * @param pliers referencja na obiekt kombinerek
	*/
	
	public void setPliers(Pliers pliers) {
		this.pliers = pliers;
	}
	
	/**
	 * Pobiera obiekt kombinerek
	 * @return referencje na obiekt kombinerek
	*/
	
	public Pliers getPliers() {
		return this.pliers;
	}
	
	/**
	 * Pobiera nazwe aktualnej mapy
	 * @return nazwe aktualnej mapy
	*/
	
	public String getCurrentMap() {
		return this.currentMap;
	}
	
	/**
	 * Laduje mape o okreslonej nazwie
	 * @param mapName nazwa mapy
	 * @return true jesli sie powiodlo, false jezeli nie
	*/
	
	public boolean loadMap(String mapName) {
		
		if(mapName.equals("DesertMap") == true) {
			this.loadDesertMap();
			return true;
		}
		else if(mapName.equals("StoneMap") == true) {
			this.loadStoneMap();
			return true;
		}
		else if(mapName.equals("GroundMap") == true) {
			this.loadGroundMap();
			return true;
		}
		return false;
	}
	
	/**
	 * Laduje mape
	*/
	
	public void loadDesertMap() {
		this.currentMap = "DesertMap";
		
		Npc hero = this.createNpc("NpcVisual//NpcVisual01.png", 20, 20);
		Npc.setHero(hero);
		
		this.createWeapon("WeaponVisual//AK-47.png", 20, 250).setAmmo(500);
		this.createWeapon("WeaponVisual//Minigun.png", 150, 60).setAmmo(700);
		
		Weapon w = this.createWeapon("WeaponVisual//M4.png", 300, 20);
		w.setAmmo(99999);
		Npc npc = this.createNpc("NpcVisual//NpcVisual02.png", 300, 20);
		npc.equipWeapon(w);
		
		w = this.createWeapon("WeaponVisual//Wunderwaffe.png", 300, 20);
		w.setAmmo(99999);
		npc = this.createNpc("NpcVisual//NpcVisual02.png", 600, 210);
		npc.setFlipLeft();
		npc.equipWeapon(w);
		
		w = this.createWeapon("WeaponVisual//Minigun.png", 300, 20);
		w.setAmmo(99999);
		npc = this.createNpc("NpcVisual//NpcVisual03.png", 800, 390);
		npc.equipWeapon(w);
		npc.setFlipLeft();
		
		w = this.createWeapon("WeaponVisual//Minigun.png", 300, 20);
		w.setAmmo(99999);
		npc = this.createNpc("NpcVisual//NpcVisual04.png", 500, 600);
		npc.equipWeapon(w);
		
		this.createVob("VobVisual//sandwall01.png", -50, 0);
		this.createVob("VobVisual//sandwall01.png", 1010, 0);
		this.createVob("VobVisual//sandwall02.png", 0, -50);
		this.createVob("VobVisual//sandwall02.png", 700, -50);
		this.createVob("VobVisual//sandwall02.png", 0, 760);
		this.createVob("VobVisual//sandwall02.png", 700, 760);
		
		this.createVob("VobVisual//sand01.png", 65, 0);
		this.createVob("VobVisual//sand01.png", 65, 63);
		this.createVob("VobVisual//sand01.png", 65, 120);
	
		this.createVob("VobVisual//sand02.png", 65, 330);
		this.createVob("VobVisual//sand02.png", 120, 330);
		this.createVob("VobVisual//sand02.png", 183, 330);
		this.createVob("VobVisual//sand02.png", 246, 330);
		this.createVob("VobVisual//sand02.png", 309, 330);
		this.createVob("VobVisual//sand02.png", 372, 330);
		this.createVob("VobVisual//sand02.png", 435, 330);
		this.createVob("VobVisual//sand02.png", 498, 330);
		this.createVob("VobVisual//sand02.png", 561, 330);
		this.createVob("VobVisual//sand02.png", 624, 330);
		this.createVob("VobVisual//sand02.png", 687, 330);
		this.createVob("VobVisual//sand02.png", 750, 330);
		this.createVob("VobVisual//sand02.png", 813, 330);
		this.createVob("VobVisual//sand02.png", 876, 330);
		this.createVob("VobVisual//sand02.png", 939, 330);
		this.createVob("VobVisual//sand02.png", 1002, 330);
		
		this.createVob("VobVisual//sand01.png", 65, 140);
		this.createVob("VobVisual//sand01.png", 120, 140);
		this.createVob("VobVisual//sand01.png", 183, 140);
		this.createVob("VobVisual//sand01.png", 246, 140);
		this.createVob("VobVisual//sand01.png", 309, 140);
		this.createVob("VobVisual//sand01.png", 372, 140);
		this.createVob("VobVisual//sand01.png", 435, 140);
		this.createVob("VobVisual//sand01.png", 498, 140);
		this.createVob("VobVisual//sand01.png", 561, 140);
		this.createVob("VobVisual//sand01.png", 624, 140);
		this.createVob("VobVisual//sand01.png", 687, 140);
		this.createVob("VobVisual//sand01.png", 750, 140);
		this.createVob("VobVisual//sand01.png", 813, 140);
		this.createVob("VobVisual//sand01.png", 876, 140);
		
		this.createVob("VobVisual//sand02.png", 65, 393);
		this.createVob("VobVisual//sand02.png", 65, 456);
		this.createVob("VobVisual//sand02.png", 65, 519);
		this.createVob("VobVisual//sand02.png", 65, 575);
		
		this.createVob("VobVisual//sand03.png", 200, 700);
		this.createVob("VobVisual//sand03.png", 200, 637);
		this.createVob("VobVisual//sand03.png", 200, 574);
		this.createVob("VobVisual//sand03.png", 200, 511);
		this.createVob("VobVisual//sand03.png", 263, 511);
		this.createVob("VobVisual//sand03.png", 326, 511);
		this.createVob("VobVisual//sand03.png", 389, 511);
		this.createVob("VobVisual//sand03.png", 452, 511);
		this.createVob("VobVisual//sand03.png", 515, 511);
		this.createVob("VobVisual//sand03.png", 578, 511);
		this.createVob("VobVisual//sand03.png", 641, 511);
		this.createVob("VobVisual//sand03.png", 704, 511);
		this.createVob("VobVisual//sand03.png", 767, 511);
		this.createVob("VobVisual//sand03.png", 830, 511);
		this.createVob("VobVisual//sand03.png", 893, 511);
		
		this.createBomb(300, 600, 140);
		this.createPliers(230, 50);
	}
	
	/**
	 * Laduje mape
	*/
	
	public void loadStoneMap() {
		this.currentMap = "StoneMap";
		
		Npc hero = this.createNpc("NpcVisual//NpcVisual01.png", 20, 600);
		Npc.setHero(hero);
		
		this.createWeapon("WeaponVisual//M4.png", 200, 600).setAmmo(500);
		this.createWeapon("WeaponVisual//Wunderwaffe.png", 800, 100).setAmmo(500);
		
		Weapon w = this.createWeapon("WeaponVisual//Minigun.png", 300, 20);
		w.setAmmo(99999);
		Npc npc = this.createNpc("NpcVisual//NpcVisual03.png", 100, 380);
		npc.equipWeapon(w);
		
	    w = this.createWeapon("WeaponVisual//Minigun.png", 700, 20);
		w.setAmmo(99999);
		npc = this.createNpc("NpcVisual//NpcVisual04.png", 900, 380);
		npc.setFlipLeft();
		npc.equipWeapon(w);
		
		 w = this.createWeapon("WeaponVisual//Minigun.png", 700, 20);
		w.setAmmo(99999);
		npc = this.createNpc("NpcVisual//NpcVisual02.png", 200, 180);
		npc.equipWeapon(w);
		
		this.createVob("VobVisual//stonewall01.png", -70, 0);
		this.createVob("VobVisual//stonewall01.png", 1010, 0);
		this.createVob("VobVisual//stonewall02.png", 0, -70);
		this.createVob("VobVisual//stonewall02.png", 700, -70);
		this.createVob("VobVisual//stonewall02.png", 0, 760);
		this.createVob("VobVisual//stonewall02.png", 700, 760);
		this.createVob("VobVisual//stonewall02.png", -400, 500);	
		this.createVob("VobVisual//stonewall02.png", 600, 500);
		this.createVob("VobVisual//stonewall02.png", -400, 300);	
		this.createVob("VobVisual//stonewall02.png", 600, 300);
		this.createVob("VobVisual//stonewall02.png", -400, 100);
		
		this.createBomb(50, 200, 120);
		this.createPliers(20, 400);
	}
	
	/**
	 * Laduje mape
	*/
	
	public void loadGroundMap() {
		this.currentMap = "GroundMap";
		
		Npc hero = this.createNpc("NpcVisual//NpcVisual01.png", 950, 630);
		hero.setFlipLeft();
		Npc.setHero(hero);
		
		this.createWeapon("WeaponVisual//Wunderwaffe.png", 700, 650).setAmmo(500);
		this.createWeapon("WeaponVisual//M4.png", 800, 450).setAmmo(300);
		
		Weapon w = this.createWeapon("WeaponVisual//Minigun.png", 600, 400);
		w.setAmmo(99999);
		Npc npc = this.createNpc("NpcVisual//NpcVisual03.png", 700, 430);
		npc.setFlipLeft();
		npc.equipWeapon(w);
		
		w = this.createWeapon("WeaponVisual//AK-47.png", 600, 400);
		w.setAmmo(99999);
		npc = this.createNpc("NpcVisual//NpcVisual04.png", 300, 230);
		npc.equipWeapon(w);
		
		w = this.createWeapon("WeaponVisual//M4.png", 600, 400);
		w.setAmmo(99999);
		npc = this.createNpc("NpcVisual//NpcVisual02.png", 400, 20);
		npc.setFlipLeft();
		npc.equipWeapon(w);
		
		this.createVob("VobVisual//groundwall01.png", -70, 0);
		this.createVob("VobVisual//groundwall01.png", 1010, 0);
		this.createVob("VobVisual//groundwall02.png", 0, -70);
		this.createVob("VobVisual//groundwall02.png", 600, -70);
		this.createVob("VobVisual//groundwall02.png", 0, 760);
		this.createVob("VobVisual//groundwall02.png", 600, 760);
		this.createVob("VobVisual//groundwall02.png", 100, 550);
		this.createVob("VobVisual//groundwall02.png", 0, 350);
		this.createVob("VobVisual//groundwall02.png", 100, 150);
		
		this.createBomb(800, 50, 120);
		this.createPliers(550, 450);
	}
	
	/**
	 * Czysci swiat ze wszystkich obiektow
	*/
	
	public void clearWorld() {
		this.vobList.clear();
		this.npcList.clear();
		this.weaponList.clear();
		
		this.currentMap = null;
		this.bomb = null;
		this.pliers = null;
		System.out.println("Clear world");
	}
	
	/**
	 * Pobiera referencje na obiekt swiata
	 * @return referencje na obiekt swiata
	*/
	
	public static World getWorld() {
		return World.worldGame;
	}
}
