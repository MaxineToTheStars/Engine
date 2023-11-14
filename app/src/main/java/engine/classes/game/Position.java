// Package
package engine.classes.game;

// Imports

// Docstring
/**
 * Position.java || Modified: 13/11/23
 * Representation of a position in the game
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Game
 * @version v1.0.0
 */

// Class
public class Position {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private int _x;
	private int _y;

	// Constructor
	/**
	 * Instances a new {@link Position}
	 * 
	 * @param x - The X coordinate
	 * @param y - The Y coordinate
	 * @return {@link Position}
	 */
	public Position(int x, int y) {
		// Set position data
		this._x = x;
		this._y = y;
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Returns the X value
	 * 
	 * @return int
	 */
	public int getX() {
		return this._x;
	}

	/**
	 * Returns the Y value
	 * 
	 * @return int
	 */
	public int getY() {
		return this._y;
	}

	// Private Static Methods

	// Private Inherited Methods
}