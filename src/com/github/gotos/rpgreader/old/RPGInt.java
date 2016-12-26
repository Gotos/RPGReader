package com.github.gotos.rpgreader.old;

/**
 * @author gRuFtY
 *
 * This class models an RPGInt. Simple as that ;-)
 */
public class RPGInt {
	/**
	 * "Integer"-Value of the RPGInt (In fact it is a "long"-Value, but how says that?)
	 */
	public final long integer;
	
	/**
	 * length of the RPGInt in bytes
	 */
	public final long length;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + (int) (integer ^ (integer >>> 32));
		result = prime
				* result + (int) (length ^ (length >>> 32));
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
		if (!(obj instanceof RPGInt)) {
			return false;
		}
		RPGInt other = (RPGInt) obj;
		if (integer != other.integer) {
			return false;
		}
		if (length != other.length) {
			return false;
		}
		return true;
	}

	/**
	 * Constructs a new RPGInt.
	 * 
	 * @param integer Long-Value of the RPGInt. (There you go. Sounds strange, doesn't it?)
	 * @param length Length of the RPGInt in bytes
	 */
	public RPGInt(long integer, long length) {
		this.integer = integer;
		this.length = length;
	}
}