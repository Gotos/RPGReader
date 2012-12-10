package de.grufty.rpgreader.engine;

/**
 * @author alina
 *
 * Every class of the RPG-Reader, that represents something present in a RPG-Maker-Game, should implement this interface.
 */
public interface UnitInterface {
	/**
	 * Returns the byte-representation of this MapUnit
	 * 
	 * @return byte-representation
	 */
	byte[] write();
}
