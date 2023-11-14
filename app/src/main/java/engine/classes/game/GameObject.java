// Package
package engine.classes.game;

// Imports
import engine.classes.game.Position;
import engine.classes.game.GameObjectType;
import engine.classes.graphics.Pixel;
import engine.classes.graphics.Graphics;

// Docstring
/**
 * GameObject.java || Modified: 13/11/23
 * Representation of a game object
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Game
 * @version v1.0.0
 */

// Class
public class GameObject {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private Pixel _gameObjectPixelData;
	private Position _gameObjectPosition;
	private Graphics _libGraphicsReference;
	private GameObjectType _gameObjectType;

	// Constructor
	/**
	 * Instances a new {@link GameObject}
	 * 
	 * @param objectType           - The {@link GameObject}'s {@link GameObjectType}
	 * @param pixel                - The visual representation of the
	 *                             {@link GameObject}
	 * @param libGraphicsReference - The reference to the {@link Graphics}'s API'
	 * @return {@link GameObject}
	 */
	public GameObject(GameObjectType objectType, Pixel pixel, Graphics libGraphicsReference) {
		// Set GameObject data
		this._gameObjectType = objectType;
		this._gameObjectPixelData = pixel;
		this._libGraphicsReference = libGraphicsReference;
	}

	/**
	 * Instances a new {@link GameObject}
	 * 
	 * @param objectType           - The {@link GameObject}'s {@link GameObjectType}
	 * @param pixelValue           - The visual representation of the
	 *                             {@link GameObject}
	 * @param libGraphicsReference - The reference to the {@link Graphics}'s API'
	 * @return {@link GameObject}
	 */
	public GameObject(GameObjectType objectType, char pixelValue, Graphics libGraphicsReference) {
		// Set GameObject data
		this._gameObjectType = objectType;
		this._gameObjectPixelData = new Pixel(pixelValue);
		this._libGraphicsReference = libGraphicsReference;
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Spawns in the given object to the screen
	 * 
	 * @param x - The X spawn coordinate
	 * @param y - The Y spawn coordinate
	 * @return void
	 */
	public void spawn(int x, int y) {
		// Set position data
		this._gameObjectPosition = new Position(x, y);

		// Spawn in the object
		this._libGraphicsReference.drawPixel(x, y, this._gameObjectPixelData);
	}

	/**
	 * Destroys(Removes) the {@link GameObject} from the screen
	 * 
	 * @return void
	 */
	public void destroy() {
		// Create an empty pixel
		Pixel empty = new Pixel();

		// Place pixel
		this._libGraphicsReference.drawPixel(this._gameObjectPosition.getX(), this._gameObjectPosition.getY(), empty);

		// Set position data
		this._gameObjectPosition = null;
	}

	/**
	 * Sets the new position of the {@link GameObject}
	 * 
	 * @param x - The X coordinate
	 * @param y - The Y spawn coordinate
	 * @return void
	 */
	public void setPosition(int x, int y) {
		// Destroy
		this.destroy();

		// Spawn
		this.spawn(x, y);
	}

	/**
	 * Returns the {@link GameObject}'s {@link GameObjectType}
	 * 
	 * @return {@link GameObjectType}
	 */
	public GameObjectType getGameObjectType() {
		return this._gameObjectType;
	}

	/**
	 * Returns the {@link GameObject}'s {@link Position} if spawned
	 * 
	 * @return {@link Position}
	 */
	public Position getPosition() {
		return (this._gameObjectPosition != null) ? this._gameObjectPosition : null;
	}

	// Private Static Methods

	// Private Inherited Methods
}
