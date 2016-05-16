package com.angryfighter.game;

import com.angryfighter.game.manager.GameManager;
import com.angryfighter.game.visual.VobVisual;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Glowna klasa obiektow wizualnych w grze.
*/

public class Vob extends Rectangle {
	
	private String name;
	protected VobVisual visual;
	private boolean collision = true;
	protected boolean flipRight = true;
	private float rotation = 0;
	
	enum Direction {
		NORTH,
		SOUTH,
		WEST,
		EAST
	}
	

	/**
	 * Konstruktor obiektu wizuzalnego
	*/
	
	public Vob() {
		
	}
	
	/**
	 * Konstruktor obiektu wizuzalnego
	 * @param vobVisual wyglad obiektu
	*/
	
	public Vob(VobVisual vobVisual) {
		this.visual = vobVisual;
		this.makeDimensions();
	}
	
	/**
	 * Konstruktor obiektu wizuzalnego
	 * @param visualName wyglad obiektu
	*/
	
	public Vob(String visualName) {
		this.visual = new VobVisual(visualName);
		this.makeDimensions();
	}
	
	/**
	 * Konstruktor obiektu wizuzalnego
	 * @param visualID wyglad obiektu
	*/
	
	public Vob(int visualID) {
		this.visual = new VobVisual(visualID);
		this.makeDimensions();
	}
	
	/**
	 * Renderuje obiekt wizualny
	 * @param sb powierzchnia do renderowania
	*/
	
	public void render(SpriteBatch sb) {
		if(GameManager.getGameManager().isPaused() == false)
			sb.draw(this.getVisual().getTextureRegion().getTexture(), this.x, this.y);
	}
	
	/**
	 * Okresla wymiary obiektu na podstawie tekstury
	*/
	
	protected void makeDimensions() {
		this.width = this.visual.getTextureRegion().getTexture().getWidth();
		this.height = this.visual.getTextureRegion().getTexture().getHeight();
	}
	
	/**
	 * Pobiera nazwe obiektu
	 * @return nazwe obiektu
	*/

	public final String getName() {
		return name;
	}
	
	/**
	 * Ustawia nazwe obiektu
	 * @param name nazwa obiektu
	*/

	public final void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Pobiera wyglad obiektu
	 * @return referencje na wyglad obiektu
	*/
	
	public VobVisual getVisual() {
		return this.visual;
	}
	
	/**
	 * Sprawdza czy obiekt jest obrocony w prawo
	 * @return true lub false
	*/
	
	public boolean getFlipRight() {
		return this.flipRight;
	}
	
	/**
	 * Ustawia obiekt na lewo
	*/
	
	public void setFlipLeft() {
		if(this.getFlipRight() == true) {
			this.flipRight = false;
			this.getVisual().getTextureRegion().flip(true, false);
		}
	}
	
	/**
	 * Ustawia obiekt na prawo
	*/
	
	public void setFlipRight() {
		if(this.getFlipRight() == false) {
			this.flipRight = true;
			this.getVisual().getTextureRegion().flip(true, false);
		}
	}
	
	/**
	 * Ustawia rotacje obiektu
	 * @param rotation rotacja
	*/
	
	public void setRotation(float rotation) {
		if(rotation >= 360 || this.rotation < 0)
			this.rotation = 0;
		else
			this.rotation = rotation;
	}
	
	/**
	 * Pobiera rotacje obiektu
	 * @return rotacje obiektu
	*/

	public float getRotation() {
		return this.rotation;
	}
	
	/**
	 * Sprawdza kolizje obiektu z innym obiektem
	 * @param vob obiekt z ktorym bedzie sprawdzana kolizja
	 * @param direct kierunek poruszania sie obiektu
	 * @param positionOffset oczekiwane przesuniecie obiektu w danym kierunku
	 * @return mozliwa dlugosc przesuniecia obiektu po sprawdzeniu kolizji
	*/

	public float checkCollision(Vob vob, Direction direct, int positionOffset) {
		
		if(this.getCollision() == true && vob.getCollision() == true) {
			
			float offset = 0;
			
			if(direct == Direction.NORTH && Intersector.overlaps(new Rectangle(this.x, this.y + positionOffset, this.width, this.height), vob) == true) {
				
				if(this.y + this.height + positionOffset >= vob.y)
					offset = (vob.y) - (this.y + this.height); 
			}
			else if(direct == Direction.SOUTH && Intersector.overlaps(new Rectangle(this.x, this.y - positionOffset, this.width, this.height), vob) == true) {
				
				if(this.y - positionOffset <= vob.y + vob.height)
					offset = (this.y) - (vob.y + vob.height);
			}
			else if(direct == Direction.WEST && Intersector.overlaps(new Rectangle(this.x - positionOffset, this.y, this.width, this.height), vob) == true) {
				
				if(this.x - positionOffset <= vob.x + vob.width)
					offset = (this.x) - (vob.x + vob.width);
			}
			else if(direct == Direction.EAST && Intersector.overlaps(new Rectangle(this.x + positionOffset, this.y, this.width, this.height), vob) == true) {
				
				if(this.x + this.width + positionOffset >= vob.x)
					offset = (vob.x) - (this.x + this.width);
			}
			else
				offset = positionOffset;
			
			return offset;
		}
		
		//Collision off
		return -1;
	}
	
	/**
	 * Ustawia czy obiekt ma byc kolizyjny
	 * @param coll true lub false
	*/
	
	public void setCollision(boolean coll) {
		this.collision = coll;
	}
	
	/**
	 * Sprawdza czy obiekt jest kolizyjny
	 * @return true lub false
	*/
	
	public boolean getCollision() {
		return this.collision;
	}
	
	/**
	 * Przesuwa obiekt w danym kierunku
	 * @param direction kierunek ruchu
	 * @param offset odleglosc przesuniecia obiektu
	*/
	
	public void move(Direction direction, float offset) {
		if(direction == Direction.NORTH)
			this.y = this.y + offset;
		else if(direction == Direction.SOUTH)
			this.y = this.y - offset;
		else if(direction == Direction.WEST)
			this.x = this.x - offset;
		else if(direction == Direction.EAST)
			this.x = this.x + offset;
	}
}
