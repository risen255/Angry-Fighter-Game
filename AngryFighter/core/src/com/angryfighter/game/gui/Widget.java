package com.angryfighter.game.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Klasa nadrzedna do rozszerzania elementow GUI
*/

public class Widget extends Rectangle {

	protected TextureRegion widgetTexture;
	protected boolean showed = true;
	
	/**
	 * Sprawdza czy widget jest pokazany
	 * @return true lub false
	*/
	
	public boolean isShowed() {
		return this.showed;
	}
	
	/**
	 * Wyswietla widget
	*/
	
	public void show() {
		this.showed = true;
	}
	
	/**
	 * Chowa widget
	*/
	
	public void hide() {
		this.showed = false;
	}
	
	/**
	 * Renderuje widget
	 * @param sb powierzchnia do renderowania
	*/
	
	public void render(SpriteBatch sb) {
	}
	
	/**
	 * Pozbywa sie skladowych widgetu
	*/
	
	public void dispose() {
		if(this.widgetTexture != null)
			this.widgetTexture.getTexture().dispose();
	}
}
