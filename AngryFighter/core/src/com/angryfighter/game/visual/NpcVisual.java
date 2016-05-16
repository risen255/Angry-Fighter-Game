package com.angryfighter.game.visual;

import com.angryfighter.game.manager.TextureManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Klasa zarzadzajadza wygladem bohatera w grze.
*/

public class NpcVisual extends VobVisual
{
	/**
	 * Konstruktor wygladu bohatera
	 * @param visualID wyglad bohatera
	*/
	
	public NpcVisual(int visualID) {
		this.setVisualID(visualID);
	}
	
	/**
	 * Konstruktor wygladu bohatera
	 * @param visualName wyglad bohatera
	*/
	
	public NpcVisual(String visualName) {
		TextureRegion textureRegion = new TextureRegion(TextureManager.getTextureManager().getNpcTexture(visualName));
		if(textureRegion != null) {
			this.visualID = TextureManager.getTextureManager().getNpcVisualIDByName(visualName);
			this.textureRegion = textureRegion;
		}
	}
	
	/**
	 * Ustawia wyglad bohatera
	 * @param visualID wyglad bohatera
	*/
	
	public void setVisualID(int visualID) {
		TextureRegion textureRegion = new TextureRegion(TextureManager.getTextureManager().getNpcTexture(visualID));
		if(textureRegion != null) {
			this.visualID = visualID;
			this.textureRegion = textureRegion;
		}
	}
	
	/**
	 * Pobiera nazwe wygladu bohatera
	 * @return nazwe wygladu bohatera
	*/
	
	public String getVisualName() {
		return TextureManager.getTextureManager().npcTextureImage[this.visualID];
	}
}
