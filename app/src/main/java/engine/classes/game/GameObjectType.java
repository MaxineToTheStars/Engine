// Package
package engine.classes.game;

// Imports

// Docstring
/**
 * GameObjectType.java || Modified: 13/11/23
 * Allows for defying the type of game object. Does
 * not affect any logic systems or anything. Just adds for
 * ease of use
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Game
 * @version v1.0.0
 */

// Class
public enum GameObjectType {
	// Enums
	PLAYER(0, "PLAYER"), ENEMY(1, "ENEMY"), OBJECT(2, "OBJECT"), UI(3, "USER_INTERFACE");

	// Interfaces

	// Constants
	private final int _GAME_OBJECT_TYPE_INT_VALUE;
	private final String _GAME_OBJECT_TYPE_STRING_VALUE;

	// Public Variables

	// Private Variables

	// Constructor
	private GameObjectType(int value0, String value1) {
		// Set GameObjectType values
		this._GAME_OBJECT_TYPE_INT_VALUE = value0;
		this._GAME_OBJECT_TYPE_STRING_VALUE = value1;
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Returns the int value of the {@link GameObjectType} enum
	 * 
	 * @return int
	 */
	public int getGameObjectTypeInt() {
		return this._GAME_OBJECT_TYPE_INT_VALUE;
	}

	/**
	 * Returns the String value of the {@link GameObjectType} enum
	 * 
	 * @return String
	 */
	public String getGameObjectTypeString() {
		return this._GAME_OBJECT_TYPE_STRING_VALUE;
	}

	// Private Static Methods

	// Private Inherited Methods
}