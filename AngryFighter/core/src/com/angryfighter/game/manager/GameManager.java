package com.angryfighter.game.manager;

import com.angryfighter.game.World;
import com.angryfighter.game.gui.GUI;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa menedzera zarzadzajacego rozgrywka.
*/

public class GameManager {

	private static GameManager gameManager = new GameManager();
	private BitmapFont font;
	
	private boolean paused = true;
	private boolean gameOver = false;
	private boolean missionComplete = true;
	private long gameManagerTime = 0;
	
	/**
	 * Konstruktor menedzera gry
	*/
	
	public GameManager() {
		this.font = new BitmapFont();
	}
	
	/**
	 * Renderuje swiat
	 * @param sb powierzchnia do renderowania
	*/
	
	public void render(SpriteBatch sb) {
		GUI gui = GUI.getGUI();
		
		World.getWorld().render(sb);
		gui.render(sb);
		
		//Game Over
		if(System.currentTimeMillis() > this.gameManagerTime && this.gameOver == true) {
			
			gui.gameOver.hide();
			
			gui.logoGame.show();
			gui.buttonStartGame.show();
			gui.buttonOptions.show();
			gui.buttonExitGame.show();
			
			this.gameOver = false;
		}
		else if(System.currentTimeMillis() > this.gameManagerTime && this.missionComplete == true) { //Mission complete
			
			gui.missionComplete.hide();
			
			gui.logoGame.show();
			gui.buttonStartGame.show();
			gui.buttonOptions.show();
			gui.buttonExitGame.show();
			
			this.missionComplete = false;
		}
	}
	
	/**
	 * Wyswietla pause w grze
	 * @param pause true lub false
	*/
	
	public void setPause(boolean pause) {
		this.paused = pause;
		
		if(this.paused == true) {
			GUI gui = GUI.getGUI();
			
			gui.logoGame.show();
			
			if(World.getWorld().getCurrentMap() != null)
				gui.buttonBackToGame.show();
			else
				gui.buttonStartGame.show();
			
			gui.buttonOptions.show();
			gui.buttonExitGame.show();
		}
	}
	
	/**
	 * Sprawdza czy wyswietlona zostala pausa
	 * @return true lub false
	*/
	
	public boolean isPaused() {
		return this.paused;
	}
	
	/**
	 * Dokonuje mission complete
	*/
	
	public void doMissionComplete() {
		GUI.getGUI().missionComplete.show();
		World.getWorld().clearWorld();
		SoundManager.getSoundManager().playSound("Sound//mission_complete.mp3");
		
		this.missionComplete = true;
		this.paused = true;
		this.gameManagerTime = System.currentTimeMillis() + 6000;
	}
	
	/**
	 * Dokonuje game over
	*/
	
	public void doGameOver() {
		GUI.getGUI().gameOver.show();
		World.getWorld().clearWorld();
		SoundManager.getSoundManager().playSound("Sound//gameover.wav");
		
		this.gameOver = true;
		this.paused = true;
		this.gameManagerTime = System.currentTimeMillis() + 4000;
	}
	
	/**
	 * Pobiera czcionke
	 * @return czcionke
	*/
	
	public BitmapFont getFont() {
		return this.font;
	}
	
	/**
	 * Pobiera referencje na obiekt menedzera gry
	 * @return referencje na obiekt menedzera gry
	*/
	
	public static GameManager getGameManager() {
		return GameManager.gameManager;
	}
}
