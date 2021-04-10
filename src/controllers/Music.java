package controllers;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class Music {
	public Music() {
		// TODO Auto-generated constructor stub
	
		try {
			
			String path = "E:\\Kingsoul\\Projects Java\\Games\\Music\\pikachu.wav";
			File filesound = new File(path);
			//url = this.getClass().getClassLoader().getResource("E:\\Kingsoul\\Projects Java\\Games\\Music\\pikachu.wav");
			AudioInputStream audio = AudioSystem.getAudioInputStream(filesound);
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
