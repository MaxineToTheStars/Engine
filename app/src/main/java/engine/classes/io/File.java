// Package
package engine.classes.io;

import java.io.IOException;
// Imports
import java.io.InputStream;

// Docstring
/**
 * File.java || Modified: 13/11/23
 * Representation of a File
 * 
 * @author https://github.com/MaxineToTheStars
 * @category Engine/Audio
 * @version v1.0.0
 */

// Class
public class File {
	// Enums

	// Interfaces

	// Constants

	// Public Variables

	// Private Variables
	private InputStream _fileStream;

	// Constructor
	/**
	 * Instances a new {@link File} object
	 * 
	 * @param filePathString - The filepath of the resource
	 * @return {@link File}
	 */
	public File(String filePathString) {
		// Instance a new InputStream
		this._fileStream = this.getClass().getClassLoader().getResourceAsStream(filePathString);

		// Null check
		if (this._fileStream == null) {
			System.out.println("ERROR: File not found");
		}
	}

	// Public Static Methods

	// Public Inherited Methods
	/**
	 * Returns the {@link File} as an InputStream
	 * 
	 * @return InputStream
	 */
	public InputStream getFileStream() {
		return this._fileStream;
	}

	/**
	 * Closes the file stream
	 * 
	 * @return void
	 */
	public void closeFileStream() {
		try {
			this._fileStream.close();
		} catch (IOException exception) {
			System.out.println(exception.toString());
		}
	}

	// Private Static Methods

	// Private Inherited Methods
}