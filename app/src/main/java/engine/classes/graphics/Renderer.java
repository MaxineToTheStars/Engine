// Package
package engine.classes.graphics;

// Imports
import java.lang.Thread;
import java.lang.InterruptedException;

import engine.classes.core.Core;
import engine.classes.graphics.Pixel;

// Docstring
/**
 * Renderer.java || Modified: 13/11/23
 * Custom Graphics Rendering Pipeline
 * DO NOT TOUCH UNLESS YOU *KNOW* WHAT YOU'RE DOING
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Graphics
 * @version v1.0.0
 */

// Class
public class Renderer extends Thread {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private Pixel[][] _frameBufferArray;

	// Constructor
	/**
	 * Instances a new {@link Renderer} object and frame buffer based of the screen
	 * X and Y
	 * 
	 * @param screenHeight - The height of the screen (X)
	 * @param screenWidth  - The width of the screen (Y)
	 * @return {@link Renderer}
	 */
	public Renderer(int screenHeight, int screenWidth) {
		// Create a new frame buffer
		this._frameBufferArray = new Pixel[screenHeight][screenWidth];
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Starts the {@link Renderer}
	 * 
	 * @return void
	 */
	public void run() {
		// Do forever
		while (true) {
			// Clear the screen
			this._clearScreen();

			// Draw to the screen
			this._drawCall();

			// Try to sleep the thread
			try {
				// Sleep
				Thread.sleep(Core._RENDERING_THREAD_UPDATE_DELAY);
			} catch (InterruptedException exception) {
				// Assume CTRL+C was hit, close the game
				System.exit(-1);
			}
		}
	}

	/**
	 * Places a given {@link Pixel} at the specified X and Y
	 * 
	 * @param screenLocationX - The X value of the {@link Pixel} location
	 * @param screenLocationY - The Y value of the {@link Pixel} location
	 * @param pixel           - The {@link Pixel} object
	 * @return void
	 * @implNote This method DIRECTLY modifies the frame buffer array and SHOULD be
	 *           the only method to do so
	 */
	public void placePixel(int screenLocationX, int screenLocationY, Pixel pixel) {
		/**
		 * Basically checking if the pixel location is out of bounds or not
		 * 
		 * Pretty simple
		 */
		if (screenLocationX < 0 || screenLocationY < 0) {
			return;
		}
		if (screenLocationX >= this._frameBufferArray.length || screenLocationY >= this._frameBufferArray[0].length) {
			return;
		}

		// Modify the frame buffer
		this._frameBufferArray[screenLocationX][screenLocationY] = pixel;
	}

	/**
	 * Clears the screen buffer
	 * 
	 * @return void
	 * @implNote I feel like giving that amount of low level power in an engine is a
	 *           bad idea
	 *           but then again, this entire project was a bad idea from the start
	 *           :p
	 */
	public void clearBuffer() {
		// Clear the array
		this._frameBufferArray = new Pixel[this._frameBufferArray.length][this._frameBufferArray[0].length];
	}

	// Private Static Methods

	// Private Inherited Methods
	/**
	 * Clears the screen
	 * 
	 * @return void
	 */
	private void _clearScreen() {
		// Black magic is real
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * Draws to the screen based off the frame buffer data
	 * 
	 * @return void
	 */
	private void _drawCall() {
		// Formatted output buffer
		String outBuffer = "";

		// Loop through the frame buffer
		for (int i = 0; i < this._frameBufferArray.length; i++) {
			for (int j = 0; j < this._frameBufferArray[i].length; j++) {
				// Check if the Pixel is not null
				if (this._frameBufferArray[i][j] != null) {
					// Add to buffer
					outBuffer += this._frameBufferArray[i][j].getPixelString();
					// Continue
					continue;
				}

				// Pixel is null
				outBuffer += new Pixel().getPixelString();
			}
			// Create a new line after going through one row
			outBuffer += "\n";
		}

		// Print it
		System.out.println(outBuffer);
	}
}