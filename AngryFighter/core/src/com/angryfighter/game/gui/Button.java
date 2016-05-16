package com.angryfighter.game.gui;

import com.angryfighter.game.Cursor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Klasa przycisku do GUI
*/

public class Button extends Widget {

	/**
	 * Konstruktor przycisku
	 * @param graphicPath sciezka do grafiki
	*/
	
	public Button(String graphicPath) {
		this.widgetTexture = new TextureRegion(new Texture(graphicPath));
	}
	
	/**
	 * Konstruktor przycisku
	 * @param graphicPath sciezka do grafiki
	 * @param posX pozycja x przycisku
	 * @param posY pozycja y przycisku
	 * @param showed czy przycisk ma byc wyswietlany od samego poczatku
	*/
	
	public Button(String graphicPath, float posX, float posY, boolean showed) {
		this.widgetTexture = new TextureRegion(new Texture(graphicPath));
		this.x = posX;
		this.y = posY;
		this.showed = showed;
	}
	
	/**
	 * Renderuje przycisk
	 * @param sb powierzchnia do renderowania
	*/
	
	public void render(SpriteBatch sb) {
		sb.draw(this.widgetTexture, this.x, this.y);
	}
	
	/**
	 * Sprawdza czy mozna przycisnac przycisk
	 * @return true lub false
	*/
	
	public boolean canSelect() {
		if(this.isShowed() == true) {
			Vector2 vector = Cursor.getCursor().getPositionFromCursorToGame();
			float pX = this.x;
			float pY = this.y;
			float width = this.widgetTexture.getTexture().getWidth();
			float height = this.widgetTexture.getTexture().getHeight();
			if((vector.x >= pX && vector.x <= pX + width) && (vector.y >= pY && vector.y <= pY + height))
				return true;
		}	
		return false;
	}
	
	/**
	 * Pobiera region tekstury
	 * @return region tekstury
	*/
	
	public TextureRegion getTextureRegion() {
		return this.widgetTexture;
	}
}
