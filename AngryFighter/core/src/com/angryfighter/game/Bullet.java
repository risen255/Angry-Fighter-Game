package com.angryfighter.game;

import com.angryfighter.game.feature.Feature;
import com.angryfighter.game.visual.VobVisual;

/**
 * Klasa pocisku do broni.
*/

public class Bullet extends Vob{
	
	private float startX = 0;
	private float startY = 0;
	private float rotDirection = 0;
	private boolean flying = true;
	private boolean invisible = false;

	public final static float BULLET_RANGE = 1000;
	
	/**
	 * Konstruktor pocisku do broni
	 * @param  startX poczatkowa pozycja x
	 * @param  startY poczatkowa pozycja y
	 * @param  rotDirection kierunek lotu pocisku
	*/
	
	public Bullet(float startX, float startY, float rotDirection) {
		super("VobVisual//bullet.png");

		this.startX = startX;
		this.startY = startY;
		this.x = this.startX;
		this.y = this.startY;
		this.rotDirection = rotDirection;
	}
	
	/**
	 * Konstruktor pocisku do broni
	 * @param  startX poczatkowa pozycja x
	 * @param  startY poczatkowa pozycja y
	 * @param  rotDirection kierunek lotu pocisku
	 * @param  invisible widocznosc pocisku
	*/

	//Invisible bullet
	public Bullet(float startX, float startY, float rotDirection, boolean invisible) {
		super("VobVisual//invisiblebullet2.png");

		this.invisible = invisible;
		this.startX = startX;
		this.startY = startY;
		this.x = this.startX;
		this.y = this.startY;
		this.rotDirection = rotDirection;
	}
	
	/**
	 * Ustawia poczatkowa pozycje pocisku X
	 * @param  startX poczatkowa pozycja X
	*/
	
	public void setStartX(float startX) {
		this.startX = startX;
		this.x = this.startX;
	}
	
	/**
	 * Ustawia poczatkowa pozycje pocisku Y
	 * @param  startY poczatkowa pozycja Y
	*/
	
	public void setStartY(float startY) {
		this.startY = startY;
		this.y = this.startY;
	}
	
	/**
	 * Ustawia poczatkowa pozycje pocisku
	 * @param  startX poczatkowa pozycja X
	 * @param  startY poczatkowa pozycja Y
	*/
	
	public void setStart(float startX, float startY) {
		this.startX = startX;
		this.startY = startY;
		this.x = this.startX;
		this.y = this.startY;
	}
	
	/**
	 * Pobiera poczatkowa pozycje pocisku
	 * @return  pozycje X
	*/
	
	public float getStartX() {
		return this.startX;
	}
	
	/**
	 * Pobiera poczatkowa pozycje pocisku
	 * @return  pozycje Y
	*/
	
	public float getStartY() {
		return this.startY;
	}
	
	/**
	 * Ustawia kierunek lotu pocisku
	 * @param  rotDirection kierunek lotu
	*/
	
	public void setDirection(float rotDirection) {
		this.rotDirection = rotDirection;
	}
	
	/**
	 * Pobiera kierunek lotu pocisku
	 * @return kierunek lotu pocisku
	*/
	
	public float getDirection() {
		return this.rotDirection;
	}
	
	/**
	 * Sprawdza czy pocisk jest po za dopuszczalnym obszarem lotu
	 * @return true lub false
	*/
	
	public boolean IsOutOfArea() {
		if(Feature.getDistance2D(this.getStartX(), this.getStartY(), this.x, this.y) > Bullet.BULLET_RANGE)
			return true;
		
		return false;
	}
	
	/**
	 * Ustawia stan lotu pocisku
	 * @param flying true lub false
	*/
	
	public void setFlying(boolean flying) {
		this.flying = flying;
	}
	
	/**
	 * Sprawdza czy pocisk aktualnie leci
	 * @return true lub false
	*/
	
	public boolean isFlying() {
		return this.flying;
	}
}
