package engine;

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