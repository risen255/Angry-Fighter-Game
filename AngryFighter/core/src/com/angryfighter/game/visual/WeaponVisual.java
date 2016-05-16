package com.angryfighter.game.visual;

import com.angryfighter.game.manager.TextureManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Klasa zarzadzajaca wygladem broni w grze.
*/


public class WeaponVisual extends VobVisual {

	/**
	 * Konstruktor wygladu broni
	 * @param visualName wyglad broni
	*/
	
	public WeaponVisual(String visualName) {
		TextureRegion textureRegion = new TextureRegion(TextureManager.getTextureManager().getWeaponTexture(visualName));
		if(textureRegion != null) {
			this.visualID = TextureManager.getTextureManager().getWeaponVisualIDByName(visualName);
			this.textureRegion = textureRegion;
		}
	}
	
	/**
	 * Konstruktor wygladu broni
	 * @param visualID wyglad broni
	*/
	
	public WeaponVisual(int visualID) {
		this.setVisualID(visualID);
	}
	
	/**
	 * Ustawia wyglad broni
	 * @param visualID wyglad broni
	*/
	
	public void setVisualID(int visualID) {
		TextureRegion textureRegion = new TextureRegion(TextureManager.getTextureManager().getWeaponTexture(visualID));
		if(textureRegion != null) {
			this.visualID = visualID;
			this.textureRegion = textureRegion;
		}
	}
	
	/**
	 * Pobiera nazwe wygladu broni
	 * @return nazwe wygladu broni
	*/
	
	public String getVisualName() {
		return TextureManager.getTextureManager().weaponTextureImage[this.visualID];
	}
}
