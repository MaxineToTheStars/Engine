// Package
package engine.classes.graphics;

// Imports
import engine.classes.graphics.Renderer;

// Docstring
/**
 * Graphics.java || Modified: 13/11/23
 * Graphics API. Allows for interaction with the rendering pipeline
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Graphics
 * @version v1.0.0
 */

// Class
public class Graphics {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private int _screenHeight;
	private int _screenWidth;
	private Renderer _renderingThread;

	// Constructor
	/**
	 * Instances a new {@link Graphics} object and
	 * a new {@link Renderer} object
	 * 
	 * @param screenHeight - The height of the screen (X)
	 * @param screenWidth  - The width of the screen (Y)
	 * @return {@link Graphics}
	 */
	public Graphics(int screenHeight, int screenWidth) {
		// Set terminal height and width
		this._screenHeight = screenHeight;
		this._screenWidth = screenWidth;

		// Create a new rendering thread
		this._renderingThread = new Renderer(screenHeight, screenWidth);
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Starts the {@link Renderer}
	 * 
	 * @return void
	 */
	public void init() {
		// Start the rendering thread
		this._renderingThread.start();
	}

	/**
	 * Draws a {@link Pixel} at the given screen position
	 * 
	 * @param positionX - The X position of the screen
	 * @param positionY - The Y position of the screen
	 * @param pixel     - The {@link Pixel} object
	 * @return void
	 */
	public void drawPixel(int positionX, int positionY, Pixel pixel) {
		this._renderingThread.placePixel(positionX, positionY, pixel);
	}

	/**
	 * Draws text on screen
	 * 
	 * @param positionX - The X position of the screen
	 * @param positionY - The Y position of the screen
	 * @param text      - The text
	 * @param center    - Should the text be centered
	 * @return void
	 */
	public void drawText(int positionX, int positionY, String text, boolean center) {
		// Get the text length
		int textLength = text.length();

		// Center the text
		if (center) {
			// Get the offset of the Y
			int offsetY = (textLength / 2);

			// Loop it
			for (int i = 0; i < textLength; i++) {
				// Place the pixel, applying the offset
				this.drawPixel(positionX, (positionY + i) - offsetY, new Pixel(text.charAt(i)));
			}

		} else {
			// Loop it
			for (int i = 0; i < textLength; i++) {
				// Place pixel
				this.drawPixel(positionX, positionY + i, new Pixel(text.charAt(i)));
			}
		}

	}

	/**
	 * Draws an empty rectangle on screen
	 * 
	 * @param startX     - The starting X position
	 * @param startY     - The starting Y position
	 * @param endX       - The ending X position
	 * @param endY       - The ending Y position
	 * @param borderChar - The border character
	 * @return void
	 */
	public void drawRectangle(int startX, int startY, int endX, int endY, char borderChar) {
		// Top and Bottom
		for (int i = startY; i < endY + 1; i++) {
			this.drawPixel(startX, i, new Pixel(borderChar));
			this.drawPixel(endX, i, new Pixel(borderChar));
		}

		// Left and Right
		for (int i = startX; i < endX + 1; i++) {
			this.drawPixel(i, startY, new Pixel(borderChar));
			this.drawPixel(i, endY, new Pixel(borderChar));
		}

	}

	/**
	 * Draws a filled rectangle on screen
	 * 
	 * @param startX     - The starting X position
	 * @param startY     - The starting Y position
	 * @param endX       - The ending X position
	 * @param endY       - The ending Y position
	 * @param borderChar - The border character
	 * @param fillChar   - The fill character
	 * @return void
	 */
	public void drawRectangle(int startX, int startY, int endX, int endY, char borderChar, char fillChar) {
		// Top and Bottom
		for (int i = startY; i < endY + 1; i++) {
			this.drawPixel(startX, i, new Pixel(borderChar));
			this.drawPixel(endX, i, new Pixel(borderChar));
		}

		// Left and Right
		for (int i = startX; i < endX + 1; i++) {
			this.drawPixel(i, startY, new Pixel(borderChar));
			this.drawPixel(i, endY, new Pixel(borderChar));
		}

		// Fill
		for (int i = startX + 1; i < endX; i++) {
			for (int j = startY + 1; j < endY; j++) {
				this.drawPixel(i, j, new Pixel(fillChar));
			}
		}
	}

	/**
	 * Draws an empty circle on screen
	 * 
	 * @param startX     - The starting X position
	 * @param startY     - The starting Y position
	 * @param width      - The width of the circle
	 * @param height     - The height of the circle
	 * @param borderChar - The border character
	 * @return void
	 */
	public void drawCircle(int startX, int startY, int width, int height, char borderChar) {
		// Get the radius of both the width and height
		double radiusWidth = (width * 1.0 / 2 * 1.0);
		double radiusHeight = (height * 1.0 / 2 * 1.0);

		// Get the ratio of the circle (width / height)
		double circleRatio = radiusWidth / radiusHeight;

		// Declare max pixels
		double maxPixelsX;
		double maxPixelsY;

		// Set max pixels
		if ((radiusWidth * 2) % 2 == 0) {
			maxPixelsX = Math.ceil(radiusWidth - 0.5) * 2 + 1;
		} else {
			maxPixelsX = Math.ceil(radiusWidth) * 2;
		}

		if ((radiusHeight * 2) % 2 == 0) {
			maxPixelsY = Math.ceil(radiusHeight - 0.5) * 2 + 1;
		} else {
			maxPixelsY = Math.ceil(radiusHeight) * 2;
		}

		// Draw
		for (int y = (int) ((-maxPixelsY / 2) + 1); y <= (int) ((maxPixelsY / 2) - 1); y++) {
			for (int x = (int) ((-maxPixelsX / 2) + 1); x <= (int) ((maxPixelsX / 2) - 1); x++) {
				// Declare
				boolean shouldPlacePixel;

				// Set
				shouldPlacePixel = this._drawCircleIsFatFilledHelper(x, y, radiusWidth, circleRatio)
						&& !(this._drawCircleIsFatFilledHelper(x + (x > 0 ? 1 : -1), y, radiusWidth, circleRatio)
								&& this._drawCircleIsFatFilledHelper(x, y + (y > 0 ? 1 : -1), radiusHeight,
										circleRatio));

				// Check
				if (shouldPlacePixel) {
					this.drawPixel(x + startX, y + startY, new Pixel(borderChar));
				}
			}
		}
	}

	/**
	 * Draws a filled circle on screen
	 * 
	 * @param startX     - The starting X position
	 * @param startY     - The starting Y position
	 * @param width      - The width of the circle
	 * @param height     - The height of the circle
	 * @param borderChar - The border character
	 * @param fillChar   - The fill character
	 * @return void
	 */
	public void drawCircle(int startX, int startY, int width, int height, char borderChar, char fillChar) {
		// Get the radius of both the width and height
		double radiusWidth = (width * 1.0 / 2 * 1.0);
		double radiusHeight = (height * 1.0 / 2 * 1.0);

		// Get the ratio of the circle (width / height)
		double circleRatio = radiusWidth / radiusHeight;

		// Declare max pixels
		double maxPixelsX;
		double maxPixelsY;

		// Set max pixels
		if ((radiusWidth * 2) % 2 == 0) {
			maxPixelsX = Math.ceil(radiusWidth - 0.5) * 2 + 1;
		} else {
			maxPixelsX = Math.ceil(radiusWidth) * 2;
		}

		if ((radiusHeight * 2) % 2 == 0) {
			maxPixelsY = Math.ceil(radiusHeight - 0.5) * 2 + 1;
		} else {
			maxPixelsY = Math.ceil(radiusHeight) * 2;
		}

		// Draw
		for (int y = (int) ((-maxPixelsY / 2) + 1); y <= (int) ((maxPixelsY / 2) - 1); y++) {
			for (int x = (int) ((-maxPixelsX / 2) + 1); x <= (int) ((maxPixelsX / 2) - 1); x++) {
				// Declare
				boolean shouldPlacePixel;

				// Set
				shouldPlacePixel = this._drawCircleIsFatFilledHelper(x, y, radiusWidth, circleRatio)
						&& !(this._drawCircleIsFatFilledHelper(x + (x > 0 ? 1 : -1), y, radiusWidth, circleRatio)
								&& this._drawCircleIsFatFilledHelper(x, y + (y > 0 ? 1 : -1), radiusHeight,
										circleRatio));

				// Check
				if (shouldPlacePixel) {
					// Place border pixel
					this.drawPixel(x + startX, y + startY, new Pixel(borderChar));
				}
			}
		}
	}

	/**
	 * Clears the screen buffer
	 * 
	 * @return void
	 */
	public void clearBuffer() {
		this._renderingThread.clearBuffer();
	}

	/**
	 * Returns the screen height
	 * 
	 * @return int
	 */
	public int getScreenHeight() {
		return this._screenHeight;
	}

	/**
	 * Return the screen width
	 * 
	 * @return int
	 */
	public int getScreenWidth() {
		return this._screenWidth;
	}

	// Private Static Methods

	// Private Inherited Methods
	/**
	 * Helper function for drawCircle call
	 * 
	 * @param x
	 * @param y
	 * @param ratio
	 * @return double
	 */
	private double _drawCircleDistanceHelper(int x, int y, double ratio) {
		return Math.sqrt((Math.pow(y * ratio, 2)) + Math.pow(x, 2));
	}

	/**
	 * Helper function for drawCircle call
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @param ratio
	 * @return boolean
	 */
	private boolean _drawCircleIsFilledHelper(int x, int y, double radius, double ratio) {
		return this._drawCircleDistanceHelper(x, y, ratio) <= radius;
	}

	/**
	 * Helper function for drawCircle call
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @param ratio
	 * @return boolean
	 */
	private boolean _drawCircleIsFatFilledHelper(int x, int y, double radius, double ratio) {
		return this._drawCircleIsFilledHelper(x, y, radius, ratio)
				&& !(this._drawCircleIsFilledHelper(x + 1, y, radius, ratio) &&
						this._drawCircleIsFilledHelper(x - 1, y, radius, ratio) &&
						this._drawCircleIsFilledHelper(x, y + 1, radius, ratio) &&
						this._drawCircleIsFilledHelper(x, y - 1, radius, ratio) &&
						this._drawCircleIsFilledHelper(x + 1, y + 1, radius, ratio) &&
						this._drawCircleIsFilledHelper(x + 1, y - 1, radius, ratio) &&
						this._drawCircleIsFilledHelper(x - 1, y - 1, radius, ratio) &&
						this._drawCircleIsFilledHelper(x - 1, y + 1, radius, ratio));
	}
}