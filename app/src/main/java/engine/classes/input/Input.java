// Package
package engine.classes.input;

// Imports
import engine.classes.input.Key;
import engine.classes.input.InputHandler;

// Docstring
/**
 * Input.java || Modified: 13/11/23
 * Input API for interacting with keyboard inputs
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Input
 * @version v1.0.0
 */

// Class
public class Input {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private InputHandler _inputHandler;

	// Constructor
	/**
	 * Instances a new {@link Input} object and
	 * a new {@link InputHandler} object
	 * 
	 * @return {@link Input}
	 */
	public Input() {
		// Create a new InputHandler thread
		this._inputHandler = new InputHandler();
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Starts the {@link InputHandler}
	 * 
	 * @return void
	 */
	public void init() {
		// Start the InputHandler thread
		this._inputHandler.start();
	}

	/**
	 * Checks if a key was pressed
	 * 
	 * @param key - They key to check for
	 * @return boolean
	 */
	public boolean isPressed(char key) {
		return this._inputHandler.isPressed(key);
	}

	// return true;

	// }

	// Private Static Methods

	// Private Inherited Methods
}
