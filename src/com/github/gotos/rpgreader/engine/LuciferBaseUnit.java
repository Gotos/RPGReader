package com.github.gotos.rpgreader.engine;

import java.util.Arrays;

/**
 * 
 * This class models an BaseUnit. That is just an ID and an content, which is an byte-Array. Most LuciferSomethings
 * have their origin in a LuciferBaseUnit, when being initialized. 
 * 
 * @author gRuFtY
 */
public class LuciferBaseUnit {

	/**
	 * The ID of the LuciferBaseUnit
	 */
	public final int id;
	/**
	 * The Content of the LuciferBaseUnit
	 */
	public final byte[] content;
	
	/**
	 * Constructs a new LuciferBaseUnit
	 * 
	 * @param id ID of the Unit
	 * @param content Content of the Unit
	 */
	public LuciferBaseUnit(int id, byte[] content) {
		this.id = id;
		this.content = content;
	}
	
	/**
	 * Returns the byte-representation of this Unit
	 * 
	 * @return byte-representation of this Unit
	 */
	public byte[] write() {
		return Helper.concatAll(DataReader.intToRPGint(id), DataReader.intToRPGint(content.length), content);
	}
	
	/**
	 * Returns the byte-representation of this Unit, if this Units Content is not equal to defaultValue. In that case
	 * this method returns an empty byte-Array
	 * 
	 * @param defaultValue default Value of this Unit. If this Units content is equal to defaultValue, the byte-
	 * representation should be an empty byte-Array
	 * @return byte-representation of this Unit or empty byte-Array
	 */
	public byte[] write(byte[] defaultValue) {
		if (java.util.Arrays.equals(content, defaultValue)) {
			return new byte[0];
		} else {
			return write();
		}
	}

	/**
	 * Returns the byte-representation of this Unit without the ID, if writeID is false. Otherwise this method returns
	 * this Units byte-representation
	 * 
	 * @param writeID if false, this method returns the byte-representation of this Unit without the ID, otherwise
	 * this method returns the byte-representation of this Unit with the ID
	 * @return byte-representation of this Unit, possibly without the ID
	 */
	public byte[] write(boolean writeID) {
		if (writeID) {
			return write();
		} else {
			return Helper.slice(write(), 1);
		}
	}
	
	/**
	 * If this Units Content is equal to defaultValue, this method returns an empty byte-Array.
	 * If this Units Content is not equal to defaultValue and writeID is false, this method returns the
	 * byte-representation of this Unit without the ID, otherwise this method returns this Units byte-representation.
	 * 
	 * @param defaultValue if this Units Content is equal to defaultValue, this method returns an empty byte-Array.
	 * Otherwise writeID decides what ist returned
	 * @param writeID if false, this method returns the byte-representation of this Unit without the ID, otherwise
	 * this method returns the byte-representation of this Unit with the ID
	 * @return byte-representation of this Unit, possibly without the ID or empty byte-Array
	 */
	public byte[] write(boolean writeID, byte[] defaultValue) {
		if (java.util.Arrays.equals(content, defaultValue)) {
			return new byte[0];
		} else {
			if (writeID) {
				return write();
			} else {
				return Helper.slice(write(), 1);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + Arrays.hashCode(content);
		result = prime
				* result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(
			Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LuciferBaseUnit)) {
			return false;
		}
		LuciferBaseUnit other = (LuciferBaseUnit) obj;
		if (!Arrays.equals(
				content, other.content)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		return true;
	}
}