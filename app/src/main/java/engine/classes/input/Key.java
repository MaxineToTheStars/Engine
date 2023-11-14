// Package
package engine.classes.input;

// Imports

// Docstring
/**
 * Key.java || Modified: 13/11/23
 * Representation of a Key
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Input
 * @version v1.0.0
 */

// Class
public class Key {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private char _keyValue;

	// Constructor
	/**
	 * Instances a new {@link Key} object
	 * 
	 * @param keyValue - The character value
	 * @return {@link Key}
	 */
	public Key(char keyValue) {
		// Set key data
		this._keyValue = keyValue;
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Returns the character associated with the {@link Key}
	 * 
	 * @return char
	 */
	public char getKeyValue() {
		return this._keyValue;
	}

	// Private Static Methods

	// Private Inherited Methods
}