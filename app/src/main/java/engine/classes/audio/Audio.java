// Package
package engine.classes.audio;

// Imports
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import engine.classes.io.File;

// Docstring
/**
 * Audio.java || Modified: 13/11/23
 * Audio API
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Audio
 * @version v1.0.0
 */

// Class
public class Audio implements LineListener {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private File _audioFile;
	private boolean _isPlaying;
	private Clip _currentClipInstance;
	private AudioInputStream _audioStreamEditor;
	private AudioInputStream _audioStreamExported;

	// Constructor
	/**
	 * Instances a new {@link Audio} object
	 * 
	 * @param filepathString - The filepath to the audio clip
	 * @return {@link Audio}
	 */
	public Audio(String filepathString) {
		// Set state
		this._isPlaying = false;

		/**
		 * Ok so this is a bit deranged but basically...
		 * 
		 * We are creating two AudioStreams. One for Editor and one for Exported.
		 * We then just do value checks to play the correct one and avoid issues
		 * 
		 * I hate this language and standard library
		 * 
		 * THIS IS SO BAD IM SO SORRY BUT IT WORKS
		 */

		// Try to open a new AudioStream (Editor)
		try {
			// Instance a new File
			this._audioFile = new File(filepathString);

			// Get a new AudioStream
			this._audioStreamEditor = AudioSystem.getAudioInputStream(this._audioFile.getFileStream());
		} catch (UnsupportedAudioFileException exception) {
			// Close the stream
			this._audioFile.closeFileStream();
		} catch (IOException exception) {
			// Close the stream
			this._audioFile.closeFileStream();
		}

		// Try to open a new AudioStream (Exported)
		try {
			// Instance a new File
			this._audioFile = new File(filepathString);

			// Buffered stream for JAR support
			InputStream bufferedStream = new BufferedInputStream(this._audioFile.getFileStream());

			// Get a new AudioStream
			this._audioStreamExported = AudioSystem.getAudioInputStream(bufferedStream);
		} catch (UnsupportedAudioFileException exception) {
			// Close the stream
			this._audioFile.closeFileStream();
		} catch (IOException exception) {
			// Close the stream
			this._audioFile.closeFileStream();
		}

		// Try to retrieve a Clip instance
		try {
			// Retrieve Clip instance
			this._currentClipInstance = AudioSystem.getClip();
		} catch (LineUnavailableException exception) {
			// Too lazy to handle
			System.out.println(exception.toString());
			return;
		}

		// Register the LineListener
		this._currentClipInstance.addLineListener(this);

		// Try to open Clip audio stream
		try {
			// Open Editor Stream
			if (this._audioStreamEditor != null) {
				this._currentClipInstance.open(this._audioStreamEditor);
			}

			// Open Exported Stream
			if (this._audioStreamExported != null) {
				this._currentClipInstance.open(this._audioStreamExported);
			}
		} catch (LineUnavailableException exception) {
			// Too lazy to handle
			System.out.println(exception.toString());
			return;
		} catch (IOException exception) {
			// Too lazy to handle
			System.out.println(exception.toString());
			return;
		} catch (IllegalStateException exception) {
			// Too lazy to handle
		}
	}

	// Public Static Methods

	// Public Inherited Methods
	@Override
	public void update(LineEvent event) {
		if (event.getType() == LineEvent.Type.START) {
			this._isPlaying = true;
		}

		if (event.getType() == LineEvent.Type.STOP) {
			// Set playing state
			this._isPlaying = false;

			// Stop
			this.stop();

			// Reset
			this.reset();
		}
	}

	/**
	 * Start playing the audio
	 * 
	 * @return void
	 */
	public void play() {
		// Stop
		this.stop();

		// Reset
		this.reset();

		// Play
		this._currentClipInstance.start();
	}

	/**
	 * Stop playing the audio
	 * 
	 * @return void
	 */
	public void stop() {
		// Stop
		this._currentClipInstance.stop();
	}

	/**
	 * Loop the audio
	 * 
	 * @param loopCount - How many times the audio should loop. -1 for indefinite
	 * @return void
	 */
	public void loop(int loopCount) {
		// Loop
		this._currentClipInstance.loop(loopCount);
	}

	/**
	 * Returns if Audio is currently playing
	 * 
	 * @return boolean
	 */
	public boolean isPlaying() {
		return this._isPlaying;
	}

	/**
	 * Resets the audio track back to 0:00
	 * 
	 * @return void
	 */
	public void reset() {
		this._currentClipInstance.setFramePosition(0);
	}

	// Private Static Methods

	// Private Inherited Methods
}