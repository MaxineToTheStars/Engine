// Package
package engine.classes.graphics;

// Imports
import java.lang.Character;

// Docstring
/**
 * Pixel.java || Modified: 13/11/23
 * Representation of a Pixel on screen
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Graphics
 * @version v1.0.0
 */

// Class
public class Pixel {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private char _pixelCharValue;

	// Constructor
	/**
	 * Instances a new empty {@link Pixel} object
	 * 
	 * @return {@link Pixel}
	 */
	public Pixel() {
		// Set data
		this._pixelCharValue = ' ';
	}

	/**
	 * Instances a new {@link Pixel} object
	 * 
	 * @param value - Value of the {@link Pixel}
	 * @return {@link Pixel}
	 */
	public Pixel(char value) {
		// Set data
		this._pixelCharValue = value;
	}

	/**
	 * Instances a new {@link Pixel} object
	 * 
	 * @param value - Value of the {@link Pixel}
	 * @return {@link Pixel}
	 */
	public Pixel(String value) {
		// Set data
		this._pixelCharValue = value.charAt(0);
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Returns the {@link Pixel} as a String
	 * 
	 * @return String
	 * @implNote Well technically it returns the actually {@link Pixel} data but eh
	 *           whatever
	 * @implNote Names are hard
	 */
	public String getPixelString() {
		return Character.toString(this._pixelCharValue);
	}

	// Private Static Methods

	// Private Inherited Methods
}