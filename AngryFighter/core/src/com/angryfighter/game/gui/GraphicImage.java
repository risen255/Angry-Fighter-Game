package com.angryfighter.game.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Klasa obrazka do wyswietlania w GUI
*/

public class GraphicImage extends Widget {

	/**
	 * Konstruktor grafiki
	 * @param graphicPath sciezka do grafiki
	*/
	
	public GraphicImage(String graphicPath) {
		this.widgetTexture = new TextureRegion(new Texture(graphicPath));
	}
	
	/**
	 * Konstruktor grafiki
	 * @param graphicPath sciezka do grafiki
	 * @param posX pozycja x grafiki
	 * @param posY pozycja y grafiki
	 * @param showed czy grafika ma byc wyswietlana od samego poczatku
	*/
	
	public GraphicImage(String graphicPath, float posX, float posY, boolean showed) {
		this.widgetTexture = new TextureRegion(new Texture(graphicPath));
		this.x = posX;
		this.y = posY;
		this.showed = showed;
	}
	
	/**
	 * Renderuje grafike
	 * @param sb powierzchnia do renderowania
	*/
	
	public void render(SpriteBatch sb) {
		sb.draw(this.widgetTexture, this.x, this.y);
	}
}
