package Utility;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	public static void RunMusic(String path) throws LineUnavailableException {
		
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(100);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
