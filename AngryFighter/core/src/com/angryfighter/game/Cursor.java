package com.angryfighter.game;

import com.angryfighter.game.feature.Feature;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Klasa kursora.
*/

public class Cursor {

	private static Cursor cursor = new Cursor();
	

	/**
	 * Konstruktor kursora
	*/
	
	public Cursor() {
	}
	

	/**
	 * Przeksztalca pozycje kursora na pozycje w grze
	 * @return pozycje w grze
	*/
	
	public Vector2 getPositionFromCursorToGame() {
		Vector3 cord = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		GameCamera.getGameCamera().GetOrthographicCamera().unproject(cord);
		cord.x = cord.x + Feature.offsetResX;
		cord.y = cord.y + Feature.offsetResY;
		
		Vector2 vector = new Vector2(cord.x, cord.y);
		return vector;
	}
	

	/**
	 * Pobranie kursora
	 * @return referencje na kursor
	*/
	
	public static Cursor getCursor() {
		return Cursor.cursor;
	}
}
