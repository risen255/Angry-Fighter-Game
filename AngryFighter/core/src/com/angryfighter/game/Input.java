package com.angryfighter.game;

import com.angryfighter.game.Vob.Direction;
import com.angryfighter.game.feature.Feature;
import com.angryfighter.game.gui.GUI;
import com.angryfighter.game.manager.GameManager;
import com.angryfighter.game.manager.SoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * Klasa obslugujaca ruch w grze tj. myszka, klawiatura.
*/

public class Input implements InputProcessor {
	
	static private Input input = new Input();
	
	/**
	 * Zdarzenie obslugujace myszke i klawiature
	*/
	
	public void handleInput() {
		
		if(GameManager.getGameManager().isPaused() == false && World.getWorld().getBomb() != null && World.getWorld().getBomb().IsExploded() == false) {
			World world = World.getWorld();
		    Npc hero = Npc.getHero();
			
			if(hero != null) {
				
				if(Gdx.input.isKeyPressed(Keys.W) == true) {
					hero.move(Direction.NORTH, hero.canMove(Direction.NORTH));
				}
				
				if(Gdx.input.isKeyPressed(Keys.S) == true) {
					hero.move(Direction.SOUTH, hero.canMove(Direction.SOUTH));
				}
				
				if(Gdx.input.isKeyPressed(Keys.A) == true) {
					hero.move(Direction.WEST, hero.canMove(Direction.WEST));
				}
				
				if(Gdx.input.isKeyPressed(Keys.D) == true) {
					hero.move(Direction.EAST, hero.canMove(Direction.EAST));
				}
				
				if(Gdx.input.isKeyPressed(Keys.E) == true) {
					if(world.getBomb() != null && hero.HasPliers() == true) {
						if(world.getBomb().getDisarmedProgress() < 100) {
							Vector2 v1 = new Vector2();
							Vector2 v2 = new Vector2();
							hero.getPosition(v1);
							world.getBomb().getPosition(v2);
							
							if(Feature.getDistance2D(v1.x, v1.y, v2.x, v2.y) <= Bomb.DISARM_DISTANCE && System.currentTimeMillis() > world.getBomb().disarmTime) {
								world.getBomb().setDisarmedProgress(world.getBomb().getDisarmedProgress() + 1);
								
								if(world.getBomb() != null)
									world.getBomb().disarmTime = System.currentTimeMillis() + Bomb.DISARM_DELAY;
							}
						}
					}
				}
						
				this.takeWeapon();
				this.takePliers();
				this.moveWeaponByCursor();
				
				if(Gdx.input.isButtonPressed(Buttons.LEFT) == true)
					hero.doShoot();
			}
		}
	}
	
	/**
	 * Podnosi broni jezeli jest w zasiegu
	*/
	
	private void takeWeapon() {
		World world = World.getWorld();
	    Npc hero = Npc.getHero();
	    
	    for(Weapon weapon : world.getWeaponList()) {
	    	if(hero.getCurrentWeapon() != weapon && weapon.isEquipped() == false) {
		    	if(hero.overlaps(weapon) == true) {
		    		hero.equipWeapon(weapon);
		    		break;
		    	}
	    	}
	    }
	}
	
	/**
	 * Podnosi kombinerki jezeli sa w zasiegu
	*/
	
	private void takePliers() {
		World world = World.getWorld();
		
		if(world.getPliers() != null) {
			if(Npc.getHero().overlaps(world.getPliers()) == true) {
				world.setPliers(null);
				Npc.getHero().setPliers(true);
				SoundManager.getSoundManager().playSound("Sound//m4_take.wav"); //m4 take sound for pliers
			}
		}
	}
	
	/**
	 * Porusza bronia za pomoca myszki
	*/
	
	private void moveWeaponByCursor() {
		Npc hero = Npc.getHero();
		Vector2 gamePos = Cursor.getCursor().getPositionFromCursorToGame();
		hero.moveWeapon(gamePos.x, gamePos.y);
	}
	
	/**
	 * Zdarzenie podczas nacisniecia klawisza
	 * @param keycode numer klawisza
	*/
	
	@Override
    public boolean keyDown(int keycode) {
		
		GameManager gameManager = GameManager.getGameManager();
		if(gameManager.isPaused() == false) {
			if(keycode == Keys.A) {
				Npc.getHero().setFlipLeft();
			}
			
			if(keycode == Keys.D) {
				Npc.getHero().setFlipRight();
			}
			
			if(keycode == Keys.G) {
				Npc hero = Npc.getHero();
				hero.dropCurrentWeapon();
				hero.selectLastWeapon();
			}
			
			if(keycode == Keys.ESCAPE) {
				gameManager.setPause(true);
			}
		}else{
			if(keycode == Keys.ESCAPE) {
				GUI gui = GUI.getGUI();
				if(gui.buttonBackToGame.isShowed() == true) {
					gui.backToGame();
				}
				else if(gui.buttonBack.isShowed() == true) {
					gui.back();
				}
				else if(gui.buttonBackMap.isShowed() == true) {
					gui.backMap();
				}
			}
		}
        return false;
    }
	
	/**
	 * Zdarzenie podczas puszczenia klawisza
	 * @param keycode numer klawisza
	*/

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    	GUI.getGUI().onButtonUp(screenX, screenY, button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
		
        return false;
    }
    
    /**
	 * Zdarzenie podczas nacisniecia klawisza
	 * @param amount scrollowanie, -1 = przod, inne wartosc = tyl 
	*/

    @Override
    public boolean scrolled(int amount) {
    	
    	if(GameManager.getGameManager().isPaused() == false) {
	    	if(amount == -1) { //Scroll forward
	    		Npc.getHero().selectNextWeapon();
	    	}else{ //Scroll backward
	    		Npc.getHero().selectPreviousWeapon();
	    	}
    	}
        return false;
    }
    
    /**
	 * Pobiera obiekt obslugujacy zdarzenia myszki i klawiatury
	 * @return referenecje na obiekt obslugujacy zdarzenia myszki i klawiatury
	*/
	
	static public Input getInput() {
		return Input.input;
	}
}
