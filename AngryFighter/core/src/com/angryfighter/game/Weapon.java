package com.angryfighter.game;

import java.util.ArrayList;

import com.angryfighter.game.manager.GameManager;
import com.angryfighter.game.manager.SoundManager;
import com.angryfighter.game.visual.WeaponVisual;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa broni z pociskami.
*/

public class Weapon extends Vob {
	
	private int ammo = 0;
	private WeaponType weaponType;
	private boolean equipped = false;
	private long timeNoAmmo = 0;
	private long timeShoot = 0;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public static final long NOAMMO_DELAY		  = 200;
	public static final long AK47_DELAY 		  = 100;
	public static final long M4_DELAY 		  	  = 100;
	public static final long WUNDERWAFFE_DELAY	  = 100;
	public static final long MINIGUN_DELAY 	  	  = 50;
	
	
	enum WeaponType {
		FIST,
		AK47,
		M4,
		WUNDERWAFFE,
		MINIGUN
	}
	
	/**
	  * Konstruktor tworzacy bron
	  * @param weaponVisual wyglad broni
	*/
	
	public Weapon(WeaponVisual weaponVisual) {
		this.visual = weaponVisual;
		this.makeDimensions();
		
		this.determineWeaponType(this.visual.getVisualID());
	}
	
	/**
	  * Konstruktor tworzacy bron
	  * @param visualName wyglad broni
	*/
	
	public Weapon(String visualName) {
		this.visual = new WeaponVisual(visualName);
		this.makeDimensions();
		
		this.determineWeaponType(this.visual.getVisualID());
	}
	
	/**
	  * Konstruktor tworzacy bron
	  * @param visualID wyglad broni
	*/
	
	public Weapon(int visualID) {
		this.visual = new WeaponVisual(visualID);
		this.makeDimensions();
		
		this.determineWeaponType(visualID);
	}
	
	/**
	  * Renderuje bron
	  * @param sb powierzchnia do renderowania
	*/
	
	@Override
	public void render(SpriteBatch sb) {
		
		if(GameManager.getGameManager().isPaused() == false) {
			if(this.isEquipped() == false) {
				sb.draw(this.getVisual().getTextureRegion(), this.x, this.y);
				/*float width = this.getVisual().getTextureRegion().getTexture().getWidth();
				float height = this.getVisual().getTextureRegion().getTexture().getHeight();
				sb.draw(this.getVisual().getTextureRegion(), this.x, this.y, width, height, width, height, 1, 1, this.getRotation());*/
			}
			
			for(int i = 0; i < this.bullets.size(); i++) {
				
				Bullet bullet = this.bullets.get(i);
				if(bullet.isFlying() == true) {
					float rotDirection = bullet.getDirection();
					bullet.x += (float)Math.sin(rotDirection * 3.14f / 180) * 10;
					bullet.y += (float)Math.cos(rotDirection * 3.14f / 180) * 10;
					
					sb.draw(bullet.getVisual().getTextureRegion(), bullet.x, bullet.y);
					
					//Is out of area
					if(bullet.IsOutOfArea() == true) {
						bullet.setFlying(false);
					}
					
					//Hit in vob
					if(bullet.isFlying() == true) {
						for(int j = 0; j < World.getWorld().getVobList().size(); j++) {
							Vob vob = World.getWorld().getVobList().get(j);
							if(bullet.overlaps(vob) == true) {
								bullet.setFlying(false);
							}
						}
					}
					
					//Hit in npc
					if(bullet.isFlying() == true) {
						for(int j= 0; j < World.getWorld().getNpcList().size(); j++) {
							Npc npc = World.getWorld().getNpcList().get(j);
							if(bullet.overlaps(npc) == true) {
								bullet.setFlying(false);
								npc.setHealth(npc.getHealth() - 1);
							}
						}
					}
					
					if(bullet.isFlying() == false) { //remove bullet 
						this.bullets.remove(bullet);
					}
				}
			}
		}
	}
	
	/**
	  * Okresla typ broni
	  * @param visualID wyglad broni
	*/
	
	private void determineWeaponType(int visualID) {
		
		switch(visualID) {
			case 0: {
				this.weaponType = WeaponType.AK47;
				this.setName("AK-47");
				break;
			}
			case 1: {
				this.weaponType = WeaponType.M4;
				this.setName("M4");
				break;
			}
			case 2: {
				this.weaponType = WeaponType.WUNDERWAFFE;
				this.setName("Wunderwaffe");
				break;
			}
			case 3: {
				this.weaponType = WeaponType.MINIGUN;
				this.setName("Minigun");
				break;
			}
		}
	}
	
	/**
	  * Pobiera typ broni
	  * @return typ broni
	*/
	
	public WeaponType getWeaponType() {
		return this.weaponType;
	}
	
	/**
	  * Sprawdza czy znajduje sie w ekwipunku
	  * @return true lub false
	*/
	
	public boolean isEquipped() {
		return this.equipped;
	}
	
	/**
	  * Ustawia status znajdowania sie w ekwipunku
	  * @param equipped true lub false
	*/
	
	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}
	
	/**
	  * Ustawia ilosc amunicji
	  * @param ammo ilosc amunicji
	*/
	
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	/**
	  * Pobiera ilosc amunicji
	  * @return ilosc amunicji
	*/
	
	public int getAmmo() {
		return this.ammo;
	}
	
	/**
	  * Dokonuje strzalu
	  * @return true jesli sie powiodlo, false jezeli nie
	*/
	
	public boolean doShoot() {
		if(this.isEquipped() == true && this.getAmmo() > 0 && System.currentTimeMillis() > this.timeShoot) {
			
			World world = World.getWorld();
			SoundManager soundManager = SoundManager.getSoundManager();
			
			float px;
			float py;
			float rot;
			
			float offsetX = 0;
			float offsetY = 0;
			float bulletOffset = 0;
			
			if(this.getFlipRight() == true) {
				
				rot = 360 - (this.getRotation() - 90);
				
				if(this.getWeaponType() == WeaponType.AK47) 			{ offsetX = 0; offsetY = 10; bulletOffset = 70;  }
				else if(this.getWeaponType() == WeaponType.M4) 			{ offsetX = -5; offsetY = 9; bulletOffset = 70;  }
				else if(this.getWeaponType() == WeaponType.WUNDERWAFFE) { offsetX = -5; offsetY = 11; bulletOffset = 70; }
				else if(this.getWeaponType() == WeaponType.MINIGUN) 	{ offsetX = 0; offsetY = 5; bulletOffset = 70;   }
			}else{
				rot = 360 - (this.getRotation() + 90);
				
				if(this.getWeaponType() == WeaponType.AK47) 			{ offsetX = 40; offsetY = 10; bulletOffset = 70; }
				else if(this.getWeaponType() == WeaponType.M4) 			{ offsetX = 40; offsetY = 10; bulletOffset = 70; }
				else if(this.getWeaponType() == WeaponType.WUNDERWAFFE) { offsetX = 40; offsetY = 9; bulletOffset = 70;  }
				else if(this.getWeaponType() == WeaponType.MINIGUN) 	{ offsetX = 40; offsetY = 5; bulletOffset = 70;  }
			}
			
			px = this.x + offsetX;
			py = this.y + offsetY;
			
			px += (float)Math.sin(rot * 3.14f / 180) * bulletOffset;
			py += (float)Math.cos(rot * 3.14f / 180) * bulletOffset;
			
			//create bullet
			Bullet bullet = new Bullet(px, py, rot);
			this.bullets.add(bullet);
			
			this.setAmmo(this.getAmmo() - 1);
			
			//Sound
			switch(this.getWeaponType()) {
				case AK47: {
					soundManager.playSound("Sound//ak47_shoot.wav");
					this.timeShoot = System.currentTimeMillis() + Weapon.AK47_DELAY;
					break;
				}
				case M4: {
					soundManager.playSound("Sound//m4_shoot.wav");
					this.timeShoot = System.currentTimeMillis() + Weapon.M4_DELAY;
					break;
				}
				case WUNDERWAFFE: {
					soundManager.playSound("Sound//wunderwaffe_shoot.wav");
					this.timeShoot = System.currentTimeMillis() + Weapon.WUNDERWAFFE_DELAY;
					break;
				}
				case MINIGUN: {
					soundManager.playSound("Sound//minigun_shoot.wav");
					this.timeShoot = System.currentTimeMillis() + Weapon.MINIGUN_DELAY;
					break;
				}
				default: break;
			}
			
			return true;
		}
		else if(this.isEquipped() == true && this.getAmmo() == 0 && System.currentTimeMillis() > this.timeNoAmmo) {
			SoundManager.getSoundManager().playSound("Sound//noammo.wav");
			this.timeNoAmmo = System.currentTimeMillis() + Weapon.NOAMMO_DELAY;
		}
		return false;
	}
}
