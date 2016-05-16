package com.angryfighter.game.feature;

import com.badlogic.gdx.math.Vector2;

/**
 * Klasa zawierajaca uzyteczne funkcje.
*/

public abstract class Feature {

	public final static int resX = 1024;
	public final static int resY = 768;
	public final static float offsetResX = 512;
	public final static float offsetResY = 384;
	
	/* 1024x768
		offsetResX = 512
		offsetResY = 384
	*/
	
	/* 1280x720
		 offsetResX = 640;
		 offsetResY = 360;
	*/
	
	/* 1600x900
		offsetResX = 288
		offsetResY = 66
	*/
	
	/**
	 * Oblicza kierunek z punktu pierwszego do drugiego (na lewo odwrocony uklad wspolrzednych)
	 * @param  fx wspolrzedna x punktu pierwszego
	 * @param  fy wspolrzedna y punktu pierwszego
	 * @param  tx wspolrzedna x punktu drugiego
	 * @param  ty wspolrzedna y punktu drugiego
	 * @return kierunek z punktu pierwszego do drugiego
	*/
	
	public static float getLeftVectorAngle(float fx, float fy, float tx, float ty) {
		float angle = (float)Math.toDegrees(Math.atan2(tx - fx, ty - fy));
		angle = angle + (float)Math.ceil( -angle / 360 ) * 360;
		
		angle = (360 - angle) - 90;
		if(angle < 0)
			angle = angle + 360;
		
		return angle;
	}
	
	/**
	 * Oblicza kierunek z punktu pierwszego do drugiego (na prawo odwrocony uklad wspolrzednych)
	 * @param  fx wspolrzedna x punktu pierwszego
	 * @param  fy wspolrzedna y punktu pierwszego
	 * @param  tx wspolrzedna x punktu drugiego
	 * @param  ty wspolrzedna y punktu drugiego
	 * @return kierunek z punktu pierwszego do drugiego
	*/
	
	public static float getRightVectorAngle(float fx, float fy, float tx, float ty) {
		float angle = (float)Math.toDegrees(Math.atan2(tx - fx, ty - fy));
				
		angle = (360 - angle) + 90;
		if(angle >= 360)
			angle = angle - 360;
		
		return angle;
	}
	
	/**
	 * Oblicza dystans miedzy dwoma punktami
	 * @param  fx wspolrzedna x punktu pierwszego
	 * @param  fy wspolrzedna y punktu pierwszego
	 * @param  tx wspolrzedna x punktu drugiego
	 * @param  ty wspolrzedna y punktu drugiego
	 * @return dystans miedzy dwoma punktami
	*/
	
	public static float getDistance2D(float fx, float fy, float tx, float ty)
	{
		Vector2 vector = new Vector2(fx, fy);
		return vector.dst(tx, ty);
	}
}
