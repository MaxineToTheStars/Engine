// Package
package engine.classes.core;

// Imports
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import engine.game.classes.Game;
import engine.classes.input.Input;
import engine.classes.graphics.Graphics;

// Docstring
/**
 * Core.java || Modified: 30/01/24
 * Core process of the game. Handles the instancing and initialization of
 * libraries and systems
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Core
 * @version v1.0.0
 */

// Class
public class Core {
	// Enums

	// Interfaces

	// Constants
	public static int updateInterval;
	public static final String VERSION_STRING = "v1.1.0";
	// public static final int _INPUT_THREAD_UPDATE_DELAY = 100; [Deprecated]
	public static final int _INPUT_THREAD_MAXIMUM_BUFFER_SIZE = 2;
	// public static final int _RENDERING_THREAD_UPDATE_DELAY = 100; [Deprecated]

	// Public Variables

	// Private Variables
	private int _screenHeight;
	private int _screenWidth;

	private Input _libInput;
	private Graphics _libGraphics;

	// Constructor
	/**
	 * Instances a new {@link Core} object
	 * 
	 * @param screenSizeX - The size of the screen along the X axis (Height)
	 * @param screenSizeY - The size of the screen along the Y axis (Width)
	 * @return {@link Core}
	 */
	public Core(int screenSizeX, int screenSizeY) {
		// Set screen size
		this._screenHeight = screenSizeX;
		this._screenWidth = screenSizeY;
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Initializes the Engine™ then executes
	 * the game's main method
	 * 
	 * @return void
	 */
	public void init() {
		// Get the current GraphicsEnvironment context
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

		// Get currently attached display outputs
		GraphicsDevice displayOutputsDevice = graphicsEnvironment.getDefaultScreenDevice();

		// Get the current monitor refresh rate
		int monitorRefreshRate = displayOutputsDevice.getDisplayMode().getRefreshRate();

		// Get the needed update tick for constant 24Hz
		Core.updateInterval = monitorRefreshRate / 24;

		// Instance the libraries
		this._libInput = new Input();
		this._libGraphics = new Graphics(this._screenHeight, this._screenWidth);

		// Initialize
		this._libInput.init();
		this._libGraphics.init(); // LibGraphics should always be initialized last due to clearing the entire
									// screen

		// Show splash screen
		_showSplashScreen();

		// Instance a new Game object
		Game myGame = new Game(this._libInput, this._libGraphics);

		// Clear the buffer
		this._libGraphics.clearBuffer();

		// Run it
		myGame.init();
	}

	// Private Static Methods

	// Private Inherited Methods
	/**
	 * Shows the Engine's splash screen
	 * 
	 * @return void
	 */
	private void _showSplashScreen() {
		// Border up
		this._libGraphics.drawRectangle(0, 0, this._screenHeight - 1, this._screenWidth - 1, 'X');

		// Show logo
		this._libGraphics.drawText((this._screenHeight / 2) - 5, (this._screenWidth / 2), "Made with Engine™", true);

		// Show GitHub
		this._libGraphics.drawText((this._screenHeight / 2) - 2, (this._screenWidth / 2),
				"https://github.com/MaxineToTheStars/Engine", true);

		// Show version (bottom left)
		this._libGraphics.drawText(this._screenHeight - 2, (this._screenWidth - 1) - Core.VERSION_STRING.length(),
				VERSION_STRING, true);

		// Try to sleep
		try {
			// Sleep
			Thread.sleep(5000);
		} catch (InterruptedException exception) {
			// Assume CTRL+C was hit, close the game
			System.exit(-1);
		}
	}
}