package com.angryfighter.game;

import com.angryfighter.game.gui.GUI;
import com.angryfighter.game.manager.GameManager;
import com.angryfighter.game.manager.SoundManager;
import com.angryfighter.game.manager.TextureManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa glowna gry, rdzen renderowania.
*/

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	
	GameManager gameManager;
	World world;
	Input input;
	Cursor cc;
	GameCamera camera;
	GUI gui;
	
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		input = Input.getInput();
		Gdx.input.setInputProcessor(input);
		
		gameManager = GameManager.getGameManager();
		world = World.getWorld();
		camera = GameCamera.getGameCamera();
		cc = Cursor.getCursor();
		gui = GUI.getGUI();
	}

	@Override
	public void render () {
		this.input.handleInput();

		//View
		//Gdx.gl.glClearColor(0, 0.7f, 1, 0);
		if(world.getCurrentMap() == null || gameManager.isPaused() == true)
			Gdx.gl.glClearColor(0, 0.5f, 0.7f, 0);
		else if(world.getCurrentMap().equals("DesertMap") == true)
			Gdx.gl.glClearColor(0.8f, 0.5f, 0.0f, 0);
		else if(world.getCurrentMap().equals("StoneMap") == true)
			Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 0);
		else if(world.getCurrentMap().equals("GroundMap") == true)
			Gdx.gl.glClearColor(0.7f, 0.3f, 0.0f, 0);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		this.gameManager.render(batch);			
		batch.end();
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
		
		batch.dispose();
		font.dispose();
		TextureManager.getTextureManager().dispose();
		
		System.out.println("dispooose");
	}
}
