package com.angryfighter.game;

/**
 * Klasa kombinerek umozliwiajacych rozbrojenie bomby.
*/

public class Pliers extends Vob {

	/**
	 * Konstruktor kombinerek
	 * @param  x pozycja x-owa
	 * @param  y pozycja y-owa
	*/
	
	public Pliers(float x, float y) {
		super("VobVisual//pliers.png");
		this.setPosition(x, y);
	}
}
