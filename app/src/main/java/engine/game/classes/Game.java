// Package
package engine.game.classes;

// Imports
import engine.classes.audio.Audio;
import engine.classes.input.Input;
import engine.classes.game.Position;
import engine.classes.game.GameObject;
import engine.classes.graphics.Graphics;
import engine.classes.game.GameObjectType;

// Docstring
/**
 * Game.java || Modified: 13/11/23
 * Index of the game
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Game/Main
 * @version v1.0.0
 */

// Class
public class Game {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private Input _libInput;
	private Graphics _libGraphics;

	// Constructor
	/**
	 * Instances a new {@link Game} object
	 * 
	 * @param libInput    - Reference to the {@link Input} API
	 * @param libGraphics - Reference to the {@link Graphics} API
	 * @return {@link Game}
	 */
	public Game(Input libInput, Graphics libGraphics) {
		// Set data
		this._libInput = libInput;
		this._libGraphics = libGraphics;
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Initializes the game
	 * 
	 * @return void
	 */
	public void init() {
		/**
		 * A simple example game showcasing all the various API's working together
		 * 
		 * To write your own game simply delete the example code below OR
		 * export to a .jar file to test the game yourself!
		 */

		// Get the screen height and width
		int screenHeight = this._libGraphics.getScreenHeight();
		int screenWidth = this._libGraphics.getScreenWidth();

		// Create a new player character
		GameObject myPlayer = new GameObject(GameObjectType.PLAYER, 'ඞ', this._libGraphics);

		// Load up our audio
		Audio walkAudio = new Audio("walk.wav");
		Audio blockedAudio = new Audio("blocked.wav");

		// Draw our map first
		this._libGraphics.drawRectangle(0, 0, screenHeight - 1, screenWidth - 1, 'X', '.');

		// Draw our character in the center
		myPlayer.spawn(screenHeight / 2, screenWidth / 2);

		// Game loop
		while (true) {

			// Check for movement
			if (this._libInput.isPressed('w')) {
				// Get out player position
				Position playerPosition = myPlayer.getPosition();

				// Check if valid
				if (playerPosition.getX() <= 1) {
					// Play blocked SFX
					blockedAudio.play();
					continue;
				}

				// Move player up 1
				myPlayer.setPosition(playerPosition.getX() - 1, playerPosition.getY());

				// Play audio
				walkAudio.play();
			}

			if (this._libInput.isPressed('a')) {
				// Get out player position
				Position playerPosition = myPlayer.getPosition();

				// Check if valid
				if (playerPosition.getY() <= 1) {
					// Play blocked SFX
					blockedAudio.play();
					continue;
				}

				// Move player left 1
				myPlayer.setPosition(playerPosition.getX(), playerPosition.getY() - 1);

				// Play audio
				walkAudio.play();
			}
			if (this._libInput.isPressed('s')) {
				// Get out player position
				Position playerPosition = myPlayer.getPosition();

				// Check if valid
				if (playerPosition.getX() >= screenHeight - 2) {
					// Play blocked SFX
					blockedAudio.play();
					continue;
				}

				// Move player down 1
				myPlayer.setPosition(playerPosition.getX() + 1, playerPosition.getY());

				// Play audio
				walkAudio.play();
			}
			if (this._libInput.isPressed('d')) {
				// Get out player position
				Position playerPosition = myPlayer.getPosition();

				// Check if valid
				if (playerPosition.getX() >= screenWidth - 2) {
					// Play blocked SFX
					blockedAudio.play();
					continue;
				}

				// Move player right 1
				myPlayer.setPosition(playerPosition.getX(), playerPosition.getY() + 1);

				// Play audio
				walkAudio.play();
			}
		}
	}

	// Private Static Methods

	// Private Inherited Methods
}