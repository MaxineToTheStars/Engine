// Package
package engine;

// Imports
import engine.classes.core.Core;

// Docstring
/**
 * App.java || Modified: 13/11/23
 * < So what's my purpose?
 * > Your goal is to start the engine followed by the game
 * < oh....
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Core
 * @version v1.0.0
 */

// Class
public class App {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables

	// Constructor

	// Public Static Methods
	/**
	 * Executes the {@link Core}
	 * 
	 * @param args - Command line arguments
	 * @return void
	 */
	public static void main(String[] args) {
		int windowHeight = 32;
		int windowWidth = 90;

		// Check for arguments
		if (args.length > 0) {
			// Parse CLI arguments if applicable
			for (int i = 0; i < args.length; i++) {
				// Get the current argument
				String currentArgumentString = args[i];

				// Split it
				String[] keyValue = currentArgumentString.split("=");

				// Match
				switch (keyValue[0]) {
					case "--height":
						// Try to set the specified height
						try {
							// Set the new window height
							windowHeight = Integer.parseInt(keyValue[1]);
						} catch (NumberFormatException exception) {
							// Reset
							windowHeight = 32;
						}
						break;
					
					case "--width":
						// Try to set the specified width
						try {
							// Set the new window width
							windowWidth = Integer.parseInt(keyValue[1]);
						} catch (NumberFormatException exception) {
							// Reset
							windowWidth = 90;
						}
						break;
					default:
						break;
				}
			}
		}
		
		// Instances a new Core and initializes it
		new Core(windowHeight, windowWidth).init();
	}

	// Public Inherited Methods

	// Private Static Methods

	// Private Inherited Methods
}