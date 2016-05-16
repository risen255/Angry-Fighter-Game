package com.angryfighter.game.visual;

import com.angryfighter.game.manager.TextureManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Klasa zarzadzajaca wygladem obiektu wizualnego w grze.
*/


public class VobVisual {
	
	protected int visualID;
	protected TextureRegion textureRegion;
	
	/**
	 * Konstruktor wygladu obiektu wizualnego
	*/
	
	public VobVisual() {
		
	}
	
	/**
	 * Konstruktor wygladu obiektu wizualnego
	 * @param visualName wyglad obiektu wizualnego
	*/
	
	public VobVisual(String visualName) {
		TextureRegion textureRegion = new TextureRegion(TextureManager.getTextureManager().getVobTexture(visualName));
		if(textureRegion != null) {
			this.visualID = TextureManager.getTextureManager().getVobVisualIDByName(visualName);
			this.textureRegion = textureRegion;
		}
	}
	
	/**
	 * Konstruktor wygladu obiektu wizualnego
	 * @param visualID wyglad obiektu wizualnego
	*/
	
	public VobVisual(int visualID) {
		this.setVisualID(visualID);
	}
	
	/**
	 * Ustawia wyglad obiektu wizualnego
	 * @param visualID wyglad obiektu wizualnego
	*/
	
	public void setVisualID(int visualID) {
		TextureRegion textureRegion = new TextureRegion(TextureManager.getTextureManager().getVobTexture(visualID));
		if(textureRegion != null) {
			this.visualID = visualID;
			this.textureRegion = textureRegion;
		}
	}
	
	/**
	 * Pobiera ID wygladu obiektu wizualnego
	 * @return ID wygladu obiektu wizualnego
	*/
	
	public int getVisualID() {
		return this.visualID;
	}
	
	/**
	 * Pobiera nazwe wygladu obiektu wizualnego
	 * @return nazwe wygladu obiektu wizualnego
	*/
	
	public String getVisualName() {
		return TextureManager.getTextureManager().vobTextureImage[this.visualID];
	}
	
	/**
	 * Pobiera region tekstury obiektu wizualnego
	 * @return nregion tekstury obiektu wizualnego
	*/
	
	public TextureRegion getTextureRegion() {
		return this.textureRegion;
	}
}
