package com.angryfighter.game;

import java.util.ArrayList;
import java.util.Random;

import com.angryfighter.game.Weapon.WeaponType;
import com.angryfighter.game.feature.Feature;
import com.angryfighter.game.manager.GameManager;
import com.angryfighter.game.manager.SoundManager;
import com.angryfighter.game.manager.TextureManager;
import com.angryfighter.game.visual.NpcVisual;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa bohatera poruszajacego sie po planszy.
*/

public class Npc extends Vob {
	
	static private Npc hero = null;
	
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	private ArrayList<Bullet> invisibleBullets = new ArrayList<Bullet>();
	private long invTime = 0;
	
	private boolean pliers = false;
	private Weapon currentWeapon = null;
	private int currentSlot = 0;
	
	private static final long TIME_CHANGE_WEAPON = 150;
	private long timeChangeWeapon = 0;
	
	private int health = 100;
	
	 /**
	  * Konstruktor tworzacy bohatera
	  * @param npcVisual wyglad bohatera
	*/
	
	public Npc(NpcVisual npcVisual) {
		this.visual = npcVisual;
		this.makeDimensions();
	}
	
	/**
	  * Konstruktor tworzacy bohatera
	  * @param visualName wyglad bohatera
	*/
	
	public Npc(String visualName) {
		this.visual = new NpcVisual(visualName);
		this.makeDimensions();
	}
	
	/**
	  * Konstruktor tworzacy bohatera
	  * @param visualID wyglad bohatera
	*/
	
	public Npc(int visualID) {
		this.visual = new NpcVisual(visualID);
		this.makeDimensions();
	}
	
	/**
	  * Renderuje wyglad bohatera
	  * @param sb powierzchnia do renderowania
	*/
	
	@Override
	public void render(SpriteBatch sb) {
		
		if(GameManager.getGameManager().isPaused() == false) {
			Npc hero = Npc.getHero();
			//Invisible bullet
			if(this != hero && hero.isDead() == false && this.isDead() == false && this.isWeaponActive() == true) {
					
				World world = World.getWorld();
	
				if(System.currentTimeMillis() > this.invTime) {
					/*float ang = Feature.getRightVectorAngle(this.x, this.y + 50, Npc.getHero().x, Npc.getHero().y);
					ang = 360 - (ang - 90);
					Bullet invBullet = new Bullet(this.x, this.y + 50, ang, true);
					this.invisibleBullets.add(invBullet);*/
					
					float ang = Feature.getRightVectorAngle(this.x, this.y + 50, Npc.getHero().x, Npc.getHero().y + 47);
					ang = 360 - (ang - 90);
					Bullet invBullet = new Bullet(this.x, this.y + 50, ang, true);
					this.invisibleBullets.add(invBullet);
					
					/*ang = Feature.getRightVectorAngle(this.x, this.y + 50, Npc.getHero().x, Npc.getHero().y + 100);
					ang = 360 - (ang - 90);
					invBullet = new Bullet(this.x, this.y + 50, ang, true);
					this.invisibleBullets.add(invBullet);*/
					
					this.invTime = System.currentTimeMillis() + 50;
				}
				
				for(int i = 0; i < this.invisibleBullets.size(); i++) {
					
					Bullet bullet = this.invisibleBullets.get(i);
					if(bullet.isFlying() == true) {
						bullet.x += (float)Math.sin(bullet.getDirection() * 3.14f / 180) * 50;
						bullet.y += (float)Math.cos(bullet.getDirection() * 3.14f / 180) * 50;
						
						sb.draw(bullet.getVisual().getTextureRegion(), bullet.x, bullet.y);
						
						if(bullet.isFlying() == true) {
							for(Vob vob : world.getVobList()) {
								if(vob.overlaps(bullet) == true) {
									//System.out.println("ZA£APA£ ŒCIANE");
									bullet.setFlying(false);
								}
							}
						}
						
						if(bullet.isFlying() == true) {
							for(Npc npc : world.getNpcList()) {
								if(npc != this) {
									if(npc.overlaps(bullet) == true) {
										this.moveWeapon(npc.x, npc.y + 47);
										this.doShoot();
									}
								}
							}
						}
						
						if(bullet.isFlying() == false) { //remove invisible bullet
							this.invisibleBullets.remove(bullet);
						}
					}
				}
			}
			
			if(this.isDead() == false) {
				sb.draw(this.getVisual().getTextureRegion(), this.x, this.y);
				
				//Nickname and everything above head
				GameManager gameManager = GameManager.getGameManager();
		
				if(this == hero && this.isWeaponActive() == true) {
					Weapon currWeapon = this.getCurrentWeapon();
					if(currWeapon.getAmmo() > 0)
						gameManager.getFont().setColor(Color.GREEN);
					else
						gameManager.getFont().setColor(Color.RED);
					
					gameManager.getFont().draw(sb, "" + currWeapon.getAmmo(), this.x + 10, this.y + 160);
				}
				
				gameManager.getFont().setColor(Color.WHITE);
				gameManager.getFont().draw(sb, this.getHealth() + "%", this.x + 10, this.y + 140);
				
				if(this.HasPliers() == true) {
					sb.draw(TextureManager.getTextureManager().getVobTexture("VobVisual//pliers_small.png"), this.x - 25, this.y + 120);
				}
				
				//draw weapon also
				if(this.isWeaponActive() == true) {
					Weapon weapon = this.getCurrentWeapon();
					WeaponType weaponType = weapon.getWeaponType();
					
					float currX = 0;
					float currY = 0;
					
					if(this.getFlipRight() == true) { //Right
						if(weaponType == WeaponType.AK47 || weaponType == WeaponType.M4) { currX = this.x;		 currY = this.y + 30; }
						else if(weaponType == WeaponType.WUNDERWAFFE)					 { currX = this.x - 15;  currY = this.y + 25; }
						else															 { currX = this.x;       currY = this.y + 30; }
					}else{ //Left
						if(weaponType == WeaponType.AK47 || weaponType == WeaponType.M4) { currX = this.x - 22;	 currY = this.y + 30; }
						else if(weaponType == WeaponType.WUNDERWAFFE)					 { currX = this.x - 10;  currY = this.y + 25; }
						else if(weaponType == WeaponType.MINIGUN)						 { currX = this.x - 20;  currY = this.y + 30; }
						else															 { currX = this.x;       currY = this.y + 30; }
					}
					
					//Render weapon
					float width = weapon.getVisual().getTextureRegion().getTexture().getWidth();
					float height = weapon.getVisual().getTextureRegion().getTexture().getHeight();
					weapon.x = currX;
					weapon.y = currY;
					
					if(this.getFlipRight() == true) //Holing weapon on right side
						sb.draw(weapon.getVisual().getTextureRegion(), weapon.x, weapon.y, 0, 0, width, height, 1, 1, weapon.getRotation());
					else //Holing weapon on left side
						sb.draw(weapon.getVisual().getTextureRegion(), weapon.x, weapon.y, width, height, width, height, 1, 1, weapon.getRotation());
				}
			}
		}
	}
	
	/**
	  * Ustawia bohatera sterujacego w grze
	  * @param heros referencja na bohatera
	*/
	
	public static void setHero(Npc heros) {
		Npc.hero = heros;
	}
	
	/**
	  * Pobiera bohatera
	  * @return referencje na bohatera
	*/
	
	public static Npc getHero() {
		return Npc.hero;
	}
	
	/**
	  * Ustawia zycie
	  * @param health ilosc zycia
	*/
	
	public void setHealth(int health) {
		if(health > 0 && health <= 100)
			this.health = health;
		else if(health == 0 && this.health > 0) {
			this.health = 0;
			this.onDeath();
		}
	}
	
	/**
	  * Pobiera ilosc zycia
	  * @return ilosc zycia
	*/
	
	public int getHealth() {
		return this.health;
	}
	
	/**
	  * Sprawdza czy bohater martwy
	  * @return true lub false
	*/
	
	public boolean isDead() {
		if(this.health == 0)
			return true;
		
		return false;
	}
	
	/**
	  * Zdarzenie wywoluje sie gdy bohater umiera
	*/
	
	public void onDeath() {
		if(this != Npc.getHero()) {
			SoundManager.getSoundManager().playSound("Sound//death.wav");
			
			if(this.isWeaponActive() == true) {
				Weapon currWeapon = this.getCurrentWeapon();
				currWeapon.setAmmo(new Random().nextInt(300) + 100);
				this.dropCurrentWeaponUnderLegs();
			}
			World.getWorld().getNpcList().remove(this);
		}
		else
			GameManager.getGameManager().doGameOver();
	}
	
	/**
	  * Owdraca bohatera na lewa strone
	*/
	
	@Override
	public void setFlipLeft() {
		if(this.getFlipRight() == true) {
			this.flipRight = false;
			this.getVisual().getTextureRegion().flip(true, false);
			
			if(this.isWeaponActive() == true) {
				Weapon weapon = this.getCurrentWeapon();
				if(weapon.getFlipRight() == true)
					weapon.setFlipLeft();
			}
		}
	}
	
	/**
	  * Owdraca bohatera na prawa strone
	*/
	
	@Override
	public void setFlipRight() {
		if(this.getFlipRight() == false) {
			this.flipRight = true;
			this.getVisual().getTextureRegion().flip(true, false);
			
			if(this.isWeaponActive() == true) {
				Weapon weapon = this.getCurrentWeapon();
				if(weapon.getFlipRight() == false)
					weapon.setFlipRight();
			}
		}
	}
	
	/**
	  * Ustawia odpowiedni kierunek broni
	*/
	
	public void setProperlyFlipForWeapon() {
		if(this.getFlipRight() == true) {
			
			if(this.isWeaponActive() == true) {
				Weapon weapon = this.getCurrentWeapon();
				if(weapon.getFlipRight() == false) {
					weapon.setFlipRight();
				}
			}
		}else{
			if(this.isWeaponActive() == true) {
				Weapon weapon = this.getCurrentWeapon();
				if(weapon.getFlipRight() == true) {
					weapon.setFlipLeft();

				}
			}	
		}
	}
	
	/**
	  * Pobiera ilosc broni w ekwipunku
	  * @return ilosc broni
	*/
	
	public int getNumberOfWeapons() {
		return this.weapons.size();
	}
	
	/**
	  * Sprawdza czy bron jest aktywna
	  * @return true lub false
	*/
	
	public boolean isWeaponActive() {
		if(this.currentWeapon != null)
			return true;
		
		return false;
	}
	
	/**
	  * Sprawdza czy jest jakas bron w ekwipunku
	  * @return true lub false
	*/
	
	public boolean hasAnyWeaponInEquipment() {
		if(this.getNumberOfWeapons() > 0)
			return true;
		
		return false;
	}
	
	/**
	  * Ustawia posiadanie kombinerek
	  * @param pliers true lub false
	*/
	
	public void setPliers(boolean pliers) {
		this.pliers = pliers;
	}
	
	/**
	  * Sprawdza czy ma kombinerki
	  * @return true lub false
	*/
	
	public boolean HasPliers() {
		return this.pliers;
	}
	
	/**
	  * Pobiera aktualna bron
	  * @return referenecje na bron
	*/
	
	public Weapon getCurrentWeapon() {
		return this.currentWeapon;
	}
	
	/**
	  * Wyrzuca biezaca bron
	  * @return true jezeli sie powiodlo lub false jezeli nie
	*/
	
	public boolean dropCurrentWeapon() {
		if(this.isWeaponActive() == true) {
			this.getCurrentWeapon().setEquipped(false);
			
			if(this.getFlipRight() == true)
				this.getCurrentWeapon().setPosition(this.x + 50, this.y);
			else
				this.getCurrentWeapon().setPosition(this.x - 100, this.y);
				
			this.weapons.remove(this.getCurrentWeapon());
			this.currentWeapon = null;
			return true;
		}
		return false;
	}
	
	/**
	  * Wyrzuca biezaca bron pod nogi
	  * @return true jezeli sie powiodlo lub false jezeli nie
	*/
	
	public boolean dropCurrentWeaponUnderLegs() {
		if(this.isWeaponActive() == true) {
			this.getCurrentWeapon().setEquipped(false);
			
			if(this.getFlipRight() == true)
				this.getCurrentWeapon().setPosition(this.x, this.y);
			else
				this.getCurrentWeapon().setPosition(this.x, this.y);
				
			this.weapons.remove(this.getCurrentWeapon());
			this.currentWeapon = null;
			return true;
		}
		return false;
	}
	
	/**
	  * Wybiera bron
	  * @param slot numer slotu broni
	  * @return zwracca referenecje na bron
	*/
	
	public Weapon selectWeapon(int slot) {
		if(slot >= 0 && slot < this.getNumberOfWeapons()) {
			this.currentSlot = slot;
			this.currentWeapon = this.weapons.get(this.currentSlot);
			
			this.setProperlyFlipForWeapon();
			this.playSoundTakingWeapon();
			return this.currentWeapon;
		}
		return null;
	}
	
	/**
	  * Wybiera nastepna bron
	  * @return zwraca referencje na bron
	*/
	
	public Weapon selectNextWeapon() {
		
		if(this.canChangeWeapon() == true) {
			int currSlot = this.currentSlot + 1;
			
			if(currSlot < this.getNumberOfWeapons()) {
				this.currentSlot = currSlot;
				this.currentWeapon = this.weapons.get(this.currentSlot);
				
				this.setProperlyFlipForWeapon();
				this.playSoundTakingWeapon();
				return this.currentWeapon;
			}
			else if(currSlot >= this.getNumberOfWeapons() && this.getNumberOfWeapons() > 1) {
				this.currentSlot = 0;
				if(this.currentSlot < this.getNumberOfWeapons()) {
					this.currentWeapon = this.weapons.get(this.currentSlot);
					
					this.setProperlyFlipForWeapon();
					this.playSoundTakingWeapon();
					return this.currentWeapon;
				}
			}
		}
		return null;
	}
	
	/**
	  * Wybiera poprzednia bron
	  * @return zwraca referencje na bron
	*/
	
	public Weapon selectPreviousWeapon() {
		
		if(this.canChangeWeapon() == true) {
			int currSlot = this.currentSlot - 1;
			
			if(currSlot >= 0 && currSlot < this.getNumberOfWeapons()) {
				this.currentSlot = currSlot;
				this.currentWeapon = this.weapons.get(this.currentSlot);
				
				this.setProperlyFlipForWeapon();
				this.playSoundTakingWeapon();
				return this.currentWeapon;
			}
			else if(currSlot < 0 && this.getNumberOfWeapons() > 1) {
				return this.selectLastWeapon();
			}
		}
		return null;
	}
	
	/**
	  * Wybiera ostatnia bron
	  * @return zwraca referencje na bron
	*/
	
	public Weapon selectLastWeapon() {
		if(this.getNumberOfWeapons() - 1 >= 0) {
			this.currentSlot = this.getNumberOfWeapons() - 1;
			this.currentWeapon = this.weapons.get(this.currentSlot);
			
			this.setProperlyFlipForWeapon();
			this.playSoundTakingWeapon();
			return this.currentWeapon;
		}
		return null;
	}
	
	/**
	  * Zaklada bron
	  * @param weapon referencja na bron
	*/
	
	private void makeEquipWeapon(Weapon weapon) {
		
		weapon.setEquipped(true);
		this.weapons.add(weapon);
		this.currentWeapon = weapon;
		this.currentSlot = this.getNumberOfWeapons() - 1;
		
		this.setProperlyFlipForWeapon();
		this.playSoundTakingWeapon();
	}
	
	/**
	  * Odtwarza dzdwiek podnoszenia broni
	*/
	
	private void playSoundTakingWeapon() {
		
		if(this.isWeaponActive() == true) {
			SoundManager soundManager = SoundManager.getSoundManager();
			
			WeaponType weaponType = this.currentWeapon.getWeaponType();
			
			switch(weaponType) {
				case AK47: soundManager.playSound("Sound//ak47_take.wav"); break;
				case M4: soundManager.playSound("Sound//m4_take.wav"); break;
				case WUNDERWAFFE: soundManager.playSound("Sound//wunderwaffe_take.wav"); break;
				case MINIGUN: soundManager.playSound("Sound//minigun_take.wav"); break;
				default: break;
			}
		}
	}
	
	/**
	  * Sprawdza czy moze zmienic bron
	  * @return true lub false
	*/
	
	private boolean canChangeWeapon() {
		
		if(System.currentTimeMillis() > this.timeChangeWeapon) {
			this.timeChangeWeapon = System.currentTimeMillis() + Npc.TIME_CHANGE_WEAPON;
			return true;
		}
		return false;
	}
	
	/**
	  * Zaklada bron
	  * @param weapon referencja na bron
	*/
	
	public void equipWeapon(Weapon weapon) {
		
		if(this.getNumberOfWeapons() > 0) {
			for(int i = 0; i < this.getNumberOfWeapons(); i++) {
				if(this.weapons.get(i).getWeaponType() == weapon.getWeaponType() && weapon.isEquipped() == false) {
					this.weapons.get(i).setAmmo(this.weapons.get(i).getAmmo() + weapon.getAmmo());
					
					int size = World.getWorld().getWeaponList().size();
					World.getWorld().getWeaponList().remove(weapon);
					
					this.playSoundTakingWeapon();
					break;
				}
				else if(i == this.weapons.size() - 1 && this.weapons.get(i).getWeaponType() != weapon.getWeaponType() && weapon.isEquipped() == false) {
					this.makeEquipWeapon(weapon);
				}
			}
		}
		else
			this.makeEquipWeapon(weapon);
	}
	
	/**
	  * Dokonuje strzalu z broni
	  * @return true lub false
	*/
	
	public boolean doShoot() {
		if(this.isWeaponActive() == true && this.isDead() == false) {
			Weapon weapon = this.getCurrentWeapon();
			if(weapon.doShoot() == true) {
				return true;
			}
		}
		return false;
	}
	
	/**
	  * Sprawdza czy moze poruszac sie w jednym z 4 kierunkow
	  * @param direction kierunek ruchu
	  * @return dystans jaki moze sie poruszyc w danym kierunku
	*/
	
	
	public float canMove(Direction direction) {
		World world = World.getWorld();
		
		float offsetVob = 5;
		if(world.getVobList().size() > 0) {
			
			for(int i = 0; i < world.getVobList().size(); i++) {
					
				float currOffsetVob = this.checkCollision(world.getVobList().get(i), direction, 5);
				if(currOffsetVob >= 0) {
					if(currOffsetVob < offsetVob)
						offsetVob = currOffsetVob;
				}
			}
		}
		
		float offsetNpc = 5;
		if(world.getNpcList().size() > 1) {
			
			for(int i = 0; i < world.getNpcList().size(); i++) {
				
				Npc currentNpc = world.getNpcList().get(i);
				if(this != currentNpc) {
					float currOffsetNpc = this.checkCollision(currentNpc, direction, 5);
					if(currOffsetNpc >= 0) {
						if(currOffsetNpc < offsetNpc)
							offsetNpc = currOffsetNpc;
					}
				}
			}
		}
		
		if(offsetVob < offsetNpc)
			return offsetVob;
		else
			return offsetNpc;
	}
	
	/**
	  * Porusza bronia
	  * @param gameX wpolrzedna x na mapie
	  * @param gameY wspolrzedna y na mapie
	*/
	
	public void moveWeapon(float gameX, float gameY) {
		
		if(this.isWeaponActive() == true) {
			
			Weapon weapon = this.getCurrentWeapon();
			WeaponType weaponType = weapon.getWeaponType();
			
			float angle = 0;
			float offsetX = 0;
			float offsetY = 0;
			
			if(this.getFlipRight() == true) {
				
				if(weaponType == WeaponType.AK47) 		      { offsetX = 0; offsetY = 20; }
				else if(weaponType == WeaponType.M4) 		  { offsetX = 0; offsetY = 15; }
				else if(weaponType == WeaponType.WUNDERWAFFE) { offsetX = 0; offsetY = 20; }
				else if(weaponType == WeaponType.MINIGUN)     { offsetX = 0; offsetY = 10; }
				
				angle = Feature.getRightVectorAngle(weapon.x + offsetX, weapon.y + offsetY, gameX, gameY);
				
			}else{
				if(weaponType == WeaponType.AK47) 		      { offsetX = 50; offsetY = 20; }
				else if(weaponType == WeaponType.M4) 		  { offsetX = 50; offsetY = 15; }
				else if(weaponType == WeaponType.WUNDERWAFFE) { offsetX = 50; offsetY = 15; }
				else if(weaponType == WeaponType.MINIGUN)     { offsetX = 50; offsetY = 10; }
				
				angle = Feature.getLeftVectorAngle(weapon.x + offsetX, weapon.y + offsetY, gameX, gameY);
				
			}
			
			if((angle >= 0 && angle <= 40) || (angle <= 360 && angle >= 320))
				weapon.setRotation(angle);
			else if(angle > 30 && angle <= 180)
				weapon.setRotation(40);
			else if(angle < 330 && angle > 180)
				weapon.setRotation(320);
		}
	}
}
