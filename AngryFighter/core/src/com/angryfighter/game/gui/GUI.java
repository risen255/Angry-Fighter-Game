package com.angryfighter.game.gui;

import java.util.ArrayList;

import com.angryfighter.game.World;
import com.angryfighter.game.manager.GameManager;
import com.angryfighter.game.manager.SoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa wyswietalajaca GUI, menu, pause.
*/

public class GUI {

	private static GUI gui = new GUI();
	
	public ArrayList<Widget> widgetList;
	public boolean active = true;
	public GraphicImage logoGame;
	public GraphicImage gameOver;
	public GraphicImage missionComplete;
	public Button buttonStartGame;
	public Button buttonOptions;
	public Button buttonExitGame;
	public Button buttonBack;
	public Button buttonBackToGame;
	public Button buttonLeft;
	public Button buttonRight;
	public Button buttonDesertMap;
	public Button buttonStoneMap;
	public Button buttonGroundMap;
	public Button buttonBackMap;
	
	private long timeChangeVolume = 0;
	
	/**
	 * Konstruktor tworzacy GUI
	*/
	
	public GUI() {
		this.widgetList = new ArrayList<Widget>();
		this.loadButtons();
	}
	
	/**
	 * Pobiera referencje na obiekt GUI
	 * @return referencje na obiekt GUI
	*/
	
	public static GUI getGUI() {
		return GUI.gui;
	}
	
	/**
	 * Laduje przyciski do GUI
	*/
	
	private void loadButtons() {
		this.logoGame = new GraphicImage("GUI//logo.png", 330, 380, true);
		this.widgetList.add(this.logoGame);
		
		this.gameOver = new GraphicImage("GUI//gameover.png", 250, 150, false);
		this.widgetList.add(this.gameOver);
		
		this.missionComplete = new GraphicImage("GUI//mission_complete.png", 200, 250, false);
		this.widgetList.add(this.missionComplete);
		
		this.buttonStartGame = new Button("GUI//startgame.png", 350, 300, true);
		this.widgetList.add(this.buttonStartGame);
		
		this.buttonOptions = new Button("GUI//options.png", 350, 240, true);
		this.widgetList.add(this.buttonOptions);
		
		this.buttonExitGame = new Button("GUI//exitgame.png", 350, 180, true);
		this.widgetList.add(this.buttonExitGame);
		
		this.buttonBack= new Button("GUI//back.png", 350, 180, false);
		this.widgetList.add(this.buttonBack);
		
		this.buttonBackToGame = new Button("GUI//backtogame.png", 350, 300, false);
		this.widgetList.add(this.buttonBackToGame);
		
		this.buttonLeft = new Button("GUI//left.png", 400, 250, false);
		this.widgetList.add(this.buttonLeft);
		
		this.buttonRight = new Button("GUI//right.png", 500, 250, false);
		this.widgetList.add(this.buttonRight);
		
		this.buttonDesertMap = new Button("GUI//desertmap.png", 350, 300, false);
		this.widgetList.add(this.buttonDesertMap);
		
		this.buttonStoneMap = new Button("GUI//stonemap.png", 350, 240, false);
		this.widgetList.add(this.buttonStoneMap);
		
		this.buttonGroundMap = new Button("GUI//groundmap.png", 350, 180, false);
		this.widgetList.add(this.buttonGroundMap);
		
		this.buttonBackMap = new Button("GUI//back.png", 350, 120, false);
		this.widgetList.add(this.buttonBackMap);
	}
	
	/**
	 * Sprawdza czy GUI jest aktywne
	 * @return true lub false
	*/
	
	public boolean isActive() {
		return this.active;
	}
	
	/**
	 * Zdarzenie wywolane gdy przycisk myszki zostal puszczony
	 * @param screenX pozycja x ekranu
	 * @param screenY pozycja y ekranu
	 * @param button numer przycisku
	*/
	
	public void onButtonUp(int screenX, int screenY, int button) {
		if(button == Buttons.LEFT) {
			if(this.buttonStartGame.canSelect() == true) { //start game
				this.buttonStartGame.hide();
				this.buttonOptions.hide();
				this.buttonExitGame.hide();
				
				this.buttonDesertMap.show();
				this.buttonStoneMap.show();
				this.buttonGroundMap.show();
				this.buttonBackMap.show();
				
				SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
			}
			else if(this.buttonDesertMap.canSelect() == true) { //Desert Map
				this.logoGame.hide();
				this.buttonDesertMap.hide();
				this.buttonStoneMap.hide();
				this.buttonGroundMap.hide();
				this.buttonBackMap.hide();
				
				World.getWorld().loadMap("DesertMap");
				
				SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
				GameManager.getGameManager().setPause(false);
			}
			else if(this.buttonStoneMap.canSelect() == true) { //Stone Map
				this.logoGame.hide();
				this.buttonDesertMap.hide();
				this.buttonStoneMap.hide();
				this.buttonGroundMap.hide();
				this.buttonBackMap.hide();
				
				World.getWorld().loadMap("StoneMap");
				
				SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
				GameManager.getGameManager().setPause(false);
			}
			else if(this.buttonGroundMap.canSelect() == true) { //Ground Map
				this.logoGame.hide();
				this.buttonDesertMap.hide();
				this.buttonStoneMap.hide();
				this.buttonGroundMap.hide();
				this.buttonBackMap.hide();
				
				World.getWorld().loadMap("GroundMap");
				
				SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
				GameManager.getGameManager().setPause(false);
			}
			else if(this.buttonOptions.canSelect() == true) { //options
				if(World.getWorld().getCurrentMap() != null)
					this.buttonBackToGame.hide();
				else
					this.buttonStartGame.hide();
				
				this.buttonOptions.hide();
				this.buttonExitGame.hide();
				
				this.buttonLeft.show();
				this.buttonRight.show();
				this.buttonBack.show();
				SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
			}
			else if(this.buttonBack.canSelect() == true) { //back
				this.back();
			}
			else if(this.buttonBackMap.canSelect() == true) { //button back map
				this.buttonDesertMap.hide();
				this.buttonStoneMap.hide();
				this.buttonGroundMap.hide();
				this.buttonBackMap.hide();
				
				this.buttonStartGame.show();
				this.buttonOptions.show();
				this.buttonExitGame.show();
				SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
			}
			else if(this.buttonBackToGame.canSelect() == true) { //button back to game
				this.backToGame();
			}
			else if(this.buttonExitGame.canSelect() == true) { //exit game
				SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
				try {
					Thread.sleep(300);
				}
				catch(InterruptedException e) {
					
				}
				Gdx.app.exit();
			}
		}
	}
	
	/**
	 * Powraca do poprzedniego menu w GUI
	*/
	
	public void back() {
		this.buttonLeft.hide();
		this.buttonRight.hide();
		this.buttonBack.hide();

		if(World.getWorld().getCurrentMap() != null) {
			this.buttonBackToGame.show();
		}else{
			this.buttonStartGame.show();
		}
		
		this.buttonOptions.show();
		this.buttonExitGame.show();
		SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
	}
	
	/**
	 * Powraca z map do poprzedniego menu w GUI
	*/
	
	public void backMap() {
		this.buttonDesertMap.hide();
		this.buttonStoneMap.hide();
		this.buttonGroundMap.hide();
		this.buttonBackMap.hide();
		
		this.buttonStartGame.show();
		this.buttonOptions.show();
		this.buttonExitGame.show();
		SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
	}
	
	/**
	 * Powraca do gry
	*/
	
	public void backToGame() {
		this.logoGame.hide();
		this.buttonBackToGame.hide();
		this.buttonOptions.hide();
		this.buttonExitGame.hide();
		
		GameManager.getGameManager().setPause(false);
		SoundManager.getSoundManager().playSound("Sound//clickbutton.mp3");
	}
	
	/**
	 * Zdarzenie wywolane podczas klikniecia myszki
	*/
	
	private void onInput() {
		
		if(Gdx.input.isButtonPressed(Keys.LEFT) == true) {
			if(this.buttonLeft.canSelect() == true && System.currentTimeMillis() > this.timeChangeVolume) { //left
				SoundManager soundManager = SoundManager.getSoundManager();
				soundManager.setVolume(soundManager.getVolume() - 0.01f);
				this.timeChangeVolume = System.currentTimeMillis() + 50;
			}
			else if(this.buttonRight.canSelect() == true && System.currentTimeMillis() > this.timeChangeVolume) { //right
				SoundManager soundManager = SoundManager.getSoundManager();
				soundManager.setVolume(soundManager.getVolume() + 0.01f);
				this.timeChangeVolume = System.currentTimeMillis() + 50;
			}
		}
	}
	
	/**
	 * Renderuje GUI
	 * @param sb powierzchnia do renderowania
	*/

	public void render(SpriteBatch sb) {
		
		this.onInput();
		
		for(Widget widget : this.widgetList) {
			if(widget.isShowed() == true) {
				widget.render(sb);
				
				if(this.buttonLeft.isShowed() == true) { //Change volume
					GameManager gameManager = GameManager.getGameManager();
					gameManager.getFont().setColor(Color.WHITE);
					int volume = (int)(SoundManager.getSoundManager().getVolume() * 100);
					
					if(volume > 0)
						gameManager.getFont().draw(sb, "Glosnosc dzwiekow: " + (int)(SoundManager.getSoundManager().getVolume() * 100) + "%", 400, 350);
					else
						gameManager.getFont().draw(sb, "Glosnosc dzwiekow: 0% (Wyciszone)", 400, 350);
				}
				else if(this.buttonBackMap.isShowed() == true) { //select map
					GameManager gameManager = GameManager.getGameManager();
					gameManager.getFont().setColor(Color.WHITE);
					gameManager.getFont().draw(sb, "Wybierz mape", 450, 370);
				}
			}
		}
	}
	
	/**
	 * Pozbywa sie wszystkich skladowych GUI
	*/
	
	public void dispose() {
		for(Widget widget : this.widgetList)
			widget.dispose();
	}
}
