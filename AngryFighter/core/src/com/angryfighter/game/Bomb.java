package com.angryfighter.game;

import com.angryfighter.game.manager.GameManager;

import com.angryfighter.game.manager.SoundManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa tykajacej bomby o okreœlonym czasie.
*/

public class Bomb extends Vob {

	public static final int DISARM_DISTANCE = 100;
	public static final long DISARM_DELAY = 50;
	
	public long disarmTime = 0;
	private int disarmedProgress = 0;
	
	public long bombTickingTime = 0;
	public int bombCurrentTime = 0;
	
	public long explodeTime = 0;
	private boolean exploded = false;
	
	/**
	 * Konstruktor bomby
	 * @param  x pozycja x-owa
	 * @param  y pozycja y-owa
	 * @param  bombTime czas w sekundach do wybuchu bomby
	*/
	
	public Bomb(float x, float y, int bombTime) {
		super("VobVisual//bomb.png");
		this.setPosition(x, y);
		this.bombCurrentTime = bombTime;
	}
	
	/**
	 * Progres rozbrojenia bomby
	 * @param  disarmedProgress progress w procentach
	*/
	
	public void setDisarmedProgress(int disarmedProgress) {
		if(disarmedProgress >= 0 && disarmedProgress <= 100) {
			this.disarmedProgress = disarmedProgress;
			
			if(this.disarmedProgress == 100)
				this.onDisarmed();
		}
	}
	
	/**
	 * Progres rozbrojenia bomby
	 * @return progress w procentach
	*/
	
	public int getDisarmedProgress() {
		return this.disarmedProgress;
	}
	
	/**
	 * Zdarzenie wywolane gdy bomba jest rozbrojona
	*/
	
	public void onDisarmed() {
		GameManager.getGameManager().doMissionComplete();
	}
	
	/**
	 * Eksplozja bomby
	*/
	
	public void doExplode() {
		this.exploded = true;
		this.explodeTime = System.currentTimeMillis() + 2000;
		
		World world = World.getWorld();
		world.createVob("VobVisual//explode.png", this.x - 200, this.y - 200);
		world.getNpcList().clear();
		world.getWeaponList().clear();
		world.setPliers(null);
		SoundManager.getSoundManager().playSound("Sound//explosion.wav");
	}
	
	/**
	 * Sprawdza czy bomba wybuchla
	 * @return czy bomba wybuchla
	*/
	
	public boolean IsExploded() {
		return this.exploded;
	}
}
