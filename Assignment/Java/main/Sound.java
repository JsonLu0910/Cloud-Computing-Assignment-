package dev.marianoalipi.balloonbattle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	private static float MASTER_VOLUME = 1.0f;
	
	public static final Sound flap		= new Sound("assets/sounds/flap.wav"),
							  hit		= new Sound("assets/sounds/hit.wav"),
							  navigate	= new Sound("assets/sounds/menuNavigate.wav"),
							  silence	= new Sound("assets/sounds/silence.wav");

	private Clip clip;

	private Sound(String path) {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
			clip = AudioSystem.getClip();
			clip.open(sound);
			setVolume(0.5f * MASTER_VOLUME);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}
	
	// Conversion:
	// amplitude = Math.pow(10.0, gain / 20.0)
	// gain = 20 * Math.log10(amplitude / amp_reference)
	/**
	 * Set the gain of the sound
	 * @param value the gain (- 6.0206)
	 */
	public void setGain(float value) {		
		FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gain.setValue(value);
	}
	
	/**
	 * Set the volume of the sound
	 * @param level the volume level (0.0 - 1.0)
	 */
	public void setVolume(float level) {
		if (level < 0.0f || level > 1.0f) {
			level = 0.5f;
		}
		
		FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float db = (float) (20 * Math.log10(level));
		gain.setValue(db);

	}
	
	/**
	 * Set the master volume value
	 * @param value the value (0.0f - 1.0f)
	 */
	public static void setMasterVolume(float value) {
		if (value < 0.0f || value > 1.0f) {
			value = 0.5f;
		}
		
		MASTER_VOLUME = value;

	}
	
	/**
	 * Get the master volume value
	 * @return the value (0.0f - 1.0f)
	 */
	public static float getMasterVolume() {
		return MASTER_VOLUME;
	}
	
}