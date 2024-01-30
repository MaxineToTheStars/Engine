// Package
package engine.classes.input;

// Imports
import java.lang.Thread;
import java.util.Scanner;
import java.lang.InterruptedException;
import java.util.NoSuchElementException;

import engine.classes.core.Core;

// Docstring
/**
 * InputHandler.java || Modified: 13/11/23
 * Handles the collection of inputs into an input buffer
 * DO NOT TOUCH UNLESS YOU *KNOW* WHAT YOU'RE DOING
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Input
 * @version v1.0.0
 */

// Class
public class InputHandler extends Thread {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private int _cursorPosition;
	private Key[] _inputBuffer;
	private Scanner _inputScanner;

	// Constructor
	/**
	 * Instances a new {@link InputHandler} object
	 * 
	 * @return {@link InputHandler}
	 */
	public InputHandler() {
		// Create a new input buffer
		this._inputBuffer = new Key[Core._INPUT_THREAD_MAXIMUM_BUFFER_SIZE]; // Keyboard input buffer

		// Set the cursor position
		this._cursorPosition = 0;

		// Instance a new Scanner
		this._inputScanner = new Scanner(System.in);
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Starts the {@link InputHandler}
	 * 
	 * @return void
	 */
	public void run() {
		while (true) {
			// Declare variables
			Key inputKey;
			char rawInputCharacter;

			// Try to collect a character input
			try {
				// Note: Java waits for the ENTER key to be pressed before
				// recognizing input. Kinda cringe ngl
				rawInputCharacter = this._inputScanner.nextLine().charAt(0);

				// Exception handling
			} catch (NoSuchElementException exception) {
				// Try to sleep
				try {
					// Sleep
					Thread.sleep(Core.updateInterval);
				} catch (InterruptedException exception2) {
					// Assume CTRL+C was hit, close the game
					System.exit(-1);
				}

				// Finalization
				continue;
			} catch (IndexOutOfBoundsException exception) {
				// Try to sleep
				try {
					// Sleep
					Thread.sleep(Core.updateInterval);
				} catch (InterruptedException exception2) {
					// Assume CTRL+C was hit, close the game
					System.exit(-1);
				}

				// Finalization
				continue;
			}

			// Instance a new Key object from the raw character input
			inputKey = new Key(rawInputCharacter);

			// Append the new Key to the input buffer
			this._inputBuffer[this._cursorPosition] = inputKey;

			// Increment the cursor position
			this._incrementCursorPosition();
		}
	}

	/**
	 * Checks if a key was pressed
	 * 
	 * @param key - They key to check for
	 * @return boolean
	 */
	public boolean isPressed(char key) {
		// Loop through the input buffer
		for (int i = 0; i < this._inputBuffer.length; i++) {
			// Get a reference to the current key
			Key currentKey = this._inputBuffer[i];

			// Null check
			if (currentKey == null) {
				// Continue through the buffer
				continue;
			}

			// Value check
			if (currentKey.getKeyValue() == key) {
				// Consume
				this._inputBuffer[i] = null;

				// Shift
				this._shiftArrayLeft();

				// Return
				return true;
			}
		}

		// Nothing found in the buffer
		return false;
	}

	// Private Static Methods

	// Private Inherited Methods
	/**
	 * Nulls out the input buffer
	 * 
	 * @return void
	 */
	private void _clearBuffer() {
		// Loop through the buffer and null it
		for (int i = 0; i < this._inputBuffer.length; i++) {
			this._inputBuffer[i] = null;
		}
	}

	/**
	 * Increments the cursor position. Resets the buffer and position
	 * if it reaches the end
	 * 
	 * @return void
	 */
	private void _incrementCursorPosition() {
		// Check if cursor is at the end of the buffer
		if (this._cursorPosition == this._inputBuffer.length - 1) {
			// Clear the buffer
			this._clearBuffer();

			// Reset cursor position
			this._cursorPosition = 0;
		}

		// Safe to increment
		this._cursorPosition++;
	}

	/**
	 * Shifts the input buffer array to the left by 1
	 * using null as the signal value
	 * 
	 * @return void
	 */
	private void _shiftArrayLeft() {
		// Loop through the buffer
		for (int i = 0; i < this._inputBuffer.length; i++) {
			// Get a reference to the current key
			Key currentKey = this._inputBuffer[i];

			// Null check
			if (currentKey == null) {
				// Check if at the end of the buffer
				if (i == this._inputBuffer.length - 1) {
					return;
				}

				// Shift array left
				this._inputBuffer[i] = this._inputBuffer[i + 1];
				this._inputBuffer[i + 1] = null;
			}
		}
	}

}