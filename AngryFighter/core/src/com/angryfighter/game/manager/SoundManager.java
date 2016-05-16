package com.angryfighter.game.manager;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Klasa menedzera do zarzadzania dzwiekami w grze
*/

public class SoundManager {

	private static SoundManager soundManager = new SoundManager();
	
	private float soundVolume = 1;
	
	public final String[] soundSource = 
	{
		"Sound//ak47_take.wav",
		"Sound//ak47_shoot.wav",
		"Sound//m4_take.wav",
		"Sound//m4_shoot.wav",
		"Sound//wunderwaffe_take.wav",
		"Sound//wunderwaffe_shoot.wav",
		"Sound//minigun_take.wav",
		"Sound//minigun_shoot.wav",
		"Sound//noammo.wav",
		"Sound//clickbutton.mp3",
		"Sound//death.wav",
		"Sound//gameover.wav",
		"Sound//mission_complete.mp3",
		"Sound//explosion.wav"
	};
	
	private ArrayList<Sound> sounds = new ArrayList<Sound>();
	
	/**
	 * Konstruktor menedzera dzwiekow
	*/
	
	public SoundManager() {
		for(String soundName : this.soundSource) {
			Sound sound = Gdx.audio.newSound(Gdx.files.local(soundName));
			this.sounds.add(sound);
		}
	}
	
	/**
	 * Pobiera dzwiek
	 * @param soundName nazwa dzwieku
	 * @return obiekt dzwieku
	*/
	
	public Sound getSound(String soundName) {
		
		for(int i = 0; i < this.soundSource.length; i++) {
			if(this.soundSource[i].equalsIgnoreCase(soundName) == true)
				return this.sounds.get(i);
		}
		return null;
	}
	
	/**
	 * Odtwarza dzwiek
	 * @param soundName nazwa dzwieku
	 * @return true jezeli sie powiodlo, false jezeli nie
	*/
	
	public boolean playSound(String soundName) {
		
		Sound sound = this.getSound(soundName);
		if(sound != null) {
			sound.play(this.soundVolume);
			return true;
		}
		return false;
	}
	
	/**
	 * Ustawia glosnosc dzwiekow
	 * @param soundVolume glosnosc dzwiekow
	*/
	
	public void setVolume(float soundVolume) {
		if(soundVolume >= 0 && soundVolume <= 1)
			this.soundVolume = soundVolume;
	}
	
	/**
	 * Pobiera glosnosc dzwiekow
	 * @return glosnosc dzwiekow
	*/
	
	public float getVolume() {
		return this.soundVolume;
	}
	
	/**
	 * Pobiera referencje na obiekt menedzera dzwiekow
	 * @return referencje na obiekt menedzera dzwiekow
	*/
	
	public static SoundManager getSoundManager() {
		return SoundManager.soundManager;
	}
}
