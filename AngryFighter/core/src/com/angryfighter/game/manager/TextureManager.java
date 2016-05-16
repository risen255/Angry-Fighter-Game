package com.angryfighter.game.manager;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

/**
 * Klasa menedzera zarzadzajacego teksturami
*/

public class TextureManager {
	
	private static TextureManager textureManager = new TextureManager();
	
	public final String[] vobTextureImage =
	{	
		"VobVisual//bullet.png",
		"VobVisual//cegla01.png",
		"VobVisual//ziemia01.png",
		"VobVisual//kamien01.png",
		"VobVisual//kamien02.png",
		"VobVisual//kamien03.png",
		"VobVisual//drzewo01.png",
		"VobVisual//sand01.png",
		"VobVisual//sand02.png",
		"VobVisual//sand03.png",
		"VobVisual//sandwall01.png",
		"VobVisual//sandwall02.png",
		"VobVisual//invisiblebullet.png",
		"VobVisual//invisiblebullet2.png",
		"VobVisual//bomb.png",
		"VobVisual//pliers.png",
		"VobVisual//pliers_small.png",
		"VobVisual//explode.png",
		"VobVisual//stonewall01.png",
		"VobVisual//stonewall02.png",
		"VobVisual//groundwall01.png",
		"VobVisual//groundwall02.png"
	};
	
	public final String[] npcTextureImage = 
	{	
		"NpcVisual//NpcVisual01.png",
		"NpcVisual//NpcVisual02.png",
		"NpcVisual//NpcVisual03.png",
		"NpcVisual//NpcVisual04.png"
	};
	
	public final String[] weaponTextureImage = 
	{	
		"WeaponVisual//AK-47.png",
		"WeaponVisual//M4.png",
		"WeaponVisual//Wunderwaffe.png",
		"WeaponVisual//Minigun.png"
	};
	
	private ArrayList<Texture> vobTexture = new ArrayList<Texture>();
	private ArrayList<Texture> npcTexture = new ArrayList<Texture>();
	private ArrayList<Texture> weaponTexture = new ArrayList<Texture>();
	
	/**
	 * Konstruktor menedzera tekstur
	*/
	
	public TextureManager() {
		this.loadTextures();
	}
	
	/**
	 * Pobiera referenecje na obiekt menedzera tekstur
	 * @return Pobiera referenecje na obiekt menedzera tekstur
	*/
	
	public static TextureManager getTextureManager() {
		return TextureManager.textureManager;
	}
	
	/**
	 * Laduje wszystkie tekstury
	 * @return true jezeli sie powiodlo, false jezeli nie
	*/
	
	public boolean loadTextures() {
		
		if(this.loadVobTextures() == true && this.loadNpcTextures() == true && this.loadWeaponTextures() == true)
			return true;
		
		return false;
	}
	
	/**
	 * Laduje tekstury obiektow wizualnych
	 * @return true jezeli sie powiodlo, false jezeli nie
	*/
	
	private boolean loadVobTextures() {
		
		for(String textureImage : this.vobTextureImage) {
			try {
				Texture texture= new Texture(textureImage);
				this.vobTexture.add(texture);
			}
			catch(Exception e) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Laduje tekstury bohaterow
	 * @return true jezeli sie powiodlo, false jezeli nie
	*/
	
	private boolean loadNpcTextures() {

		for(String textureImage : this.npcTextureImage) {		
			try {
				Texture texture = new Texture(textureImage);
				this.npcTexture.add(texture);
			}
			catch(Exception e) {	
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Laduje tekstury broni
	 * @return true jezeli sie powiodlo, false jezeli nie
	*/
	
	private boolean loadWeaponTextures() {
		for(String textureImage : this.weaponTextureImage) {
			try {
				Texture texture = new Texture(textureImage);
				this.weaponTexture.add(texture);
			}
			catch(Exception e) {		
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Pobiera teksture dla obiektu wizualnego
	 * @param visualName wyglad obiektu wizualnego
	 * @return teksture dla obiektu wizualnego
	*/
	
	public Texture getVobTexture(String visualName) {
		int visualID = this.getVobVisualIDByName(visualName);
		if(visualID != -1)
			return this.vobTexture.get(visualID);
		
		return null;
	}
	
	/**
	 * Pobiera teksture dla obiektu wizualnego
	 * @param visualID wyglad obiektu wizualnego
	 * @return teksture dla obiektu wizualnego
	*/
	
	public Texture getVobTexture(int visualID) {
		if(visualID >= 0 && visualID < this.vobTextureImage.length) {
			return this.vobTexture.get(visualID);
		}
		return null;
	}
	
	/**
	 * Pobiera teksture dla bohatera
	 * @param visualName wyglad bohatera
	 * @return teksture dla bohatera
	*/
	
	public Texture getNpcTexture(String visualName) {
		int visualID = this.getNpcVisualIDByName(visualName);
		if(visualID != -1)
			return this.npcTexture.get(visualID);
			
		return null;
	}
	
	/**
	 * Pobiera teksture dla obiektu wizualnego
	 * @param visualID wyglad bohatera
	 * @return teksture dla obiektu wizualnego
	*/
	
	public Texture getNpcTexture(int visualID) {
		if(visualID >= 0 && visualID < this.npcTextureImage.length) {
			return this.npcTexture.get(visualID);
		}
		return null;
	}
	
	/**
	 * Pobiera teksture dla broni
	 * @param visualName  wyglad broni
	 * @return teksture dla broni
	*/
	
	public Texture getWeaponTexture(String visualName) {
		int visualID = this.getWeaponVisualIDByName(visualName);
		if(visualID != -1)
			return this.weaponTexture.get(visualID);
			
		return null;
	}
	
	/**
	 * Pobiera teksture dla broni
	 * @param visualID wyglad broni
	 * @return teksture dla broni
	*/
	
	public Texture getWeaponTexture(int visualID) {
		if(visualID >= 0 && visualID < this.weaponTextureImage.length) {
			return this.weaponTexture.get(visualID);
		}
		return null;
	}
	
	/**
	 * Pobiera ID wygladu dla obiektu wizualnego
	 * @param visualName wyglad obiektu wizualnego
	 * @return  ID wygladu dla obiektu wizualnego
	*/
	
	public int getVobVisualIDByName(String visualName) {
		for(int i = 0; i < this.vobTextureImage.length; i++) {
			if(this.vobTextureImage[i].equalsIgnoreCase(visualName) == true)
				return i;
		}
		return -1;
	}
	
	/**
	 * Pobiera ID wygladu dla bohatera
	 * @param visualName wyglad bohatera
	 * @return  ID wygladu dla bohatera
	*/
	
	public int getNpcVisualIDByName(String visualName) {
		for(int i = 0; i < this.npcTextureImage.length; i++) {
			if(this.npcTextureImage[i].equalsIgnoreCase(visualName) == true)
				return i;
		}
		return -1;
	}
	
	/**
	 * Pobiera ID wygladu dla broni
	 * @param visualName wyglad broni
	 * @return  ID wygladu dla obroni
	*/
	
	public int getWeaponVisualIDByName(String visualName) {
		for(int i = 0; i < this.weaponTextureImage.length; i++) {
			if(this.weaponTextureImage[i].equalsIgnoreCase(visualName) == true)
				return i;
		}
		return -1;
	}
	
	/**
	 * Pozbywa sie wszystkich skladowych menedzera tekstur
	*/
	
	public void dispose() {
		for(Texture texture : this.vobTexture)
			texture.dispose();

		for(Texture texture : this.npcTexture)
			texture.dispose();
		
		for(Texture texture : this.weaponTexture)
			texture.dispose();
	}
}
