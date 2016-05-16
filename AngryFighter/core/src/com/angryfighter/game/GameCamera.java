package com.angryfighter.game;

import com.angryfighter.game.feature.Feature;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Klasa kamery w grze
*/

public class GameCamera {

	private static GameCamera gameCamera = new GameCamera();
	
	private OrthographicCamera ortCam;
	

	/**
	 * Konstruktor kamery
	*/
	
	public GameCamera() {
		System.out.println("GameCamera");
		this.ortCam = new OrthographicCamera(Feature.resX, Feature.resY);
	}
	

	/**
	 * Pobiera ortograficzna kamere
	 * @return ortograficzna kamere
	*/
	
	public OrthographicCamera GetOrthographicCamera() {
		return this.ortCam;
	}
	
	/**
	 * Pobiera kamere
	 * @return referencje na kamere
	*/
	
	public static GameCamera getGameCamera() {
		return GameCamera.gameCamera;
	}
}
