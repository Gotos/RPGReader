package engine;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author gRuFtY
 * 
 * A Vector-like class, created for the RPG-Reader.
 * 
 * THIS SHOULD BE REPLACED BY AN LINKEDLIST!
 *
 * @param <T>
 */
public class BackupOfBetterArray<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -944810908160780087L;
	
	private Object[] internArray;
	private int lastIndex;
	
	private void resizeArray(int newSize) {
		int size = newSize;
		if (newSize < 1) { size = 1; }
		int oldSize = java.lang.reflect.Array.getLength(internArray);
		Object[] newArray = new Object[size];
		int preserveLength = Math.min(oldSize, size);
		if (preserveLength > 0) {
			System.arraycopy(internArray, 0, newArray, 0, preserveLength);
		}
		internArray = newArray;
	}
	
	/**
	 * Appends the specified element to the end of this BetterArray. 
	 * 
	 * @param obj object to be appended to this BetterArray
	 */
	public void add(T obj) {
		while (lastIndex >= internArray.length) {
			resizeArray(lastIndex * 2);
		}
		internArray[lastIndex] = obj;
		lastIndex += 1;
	}
	
	/**
	 * Replaces the element at the specified position in this BetterArray with the specified element.
	 * If the capacity is lower than index, than a capacity of index is ensured.
	 * 
	 * @param index index of the element to replace
	 * @param obj object to be stored at the specified position 
	 */
	public void set(int index, T obj) {
		while (index >= internArray.length) {
			resizeArray(internArray.length * 2);
		}
		internArray[index] = obj;
		if (index >= lastIndex) {
			lastIndex = index + 1;
		}
	}
	
	/**
	 * Returns the element at the specified position in this BetterArray
	 * 
	 * @param index index of the element to return
	 * @throws NullPointerException thrown, if index is higher than capacity
	 * @return object at the specified index 
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) throws NullPointerException {
		if (index > lastIndex) {
			throw new java.lang.NullPointerException();
		}
		return (T) internArray[index];
	}
	
	/**
	 * Constructs an BetterArray which will contain all elements of array. The capacity is array.length.
	 * 
	 * @param array the array used to construct the BetterArray
	 */
	public BackupOfBetterArray(T[] array) {
		internArray = array;
		lastIndex = internArray.length;
	}
	
	/**
	 * Constructs an empty BetterArray with a capacity of length.
	 * 
	 * @param length the capacity of the BetterArray
	 */
	public BackupOfBetterArray(int length) {
		internArray = new Object[length];
		lastIndex = 0;
	}
	
	/**
	 * Constructs an empty BetterArray with a capacity of 1.
	 */
	public BackupOfBetterArray() {
		internArray = new Object[1];
		lastIndex = 0;
	}
	
	/**
	 * Returns the current capacity of this vector. 
	 * 
	 * @return the current capacity (the length of its internal data array, kept in the field elementData of this vector)
	 */
	public int capacity() {
		return internArray.length;
	}
	
	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list
	 */
	public int size() {
		return lastIndex;
	}
	
	/** (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @param obj -
	 * @return -
	 * 
	 **/
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof BackupOfBetterArray<?>)) {
	        return false; // different class
	     }
	     
	     BackupOfBetterArray<T> o = (BackupOfBetterArray<T>) obj;
	     
	     //TODO: lowerLayer und upperLayer werden scheinbar inkorrekt geschrieben.
	     
	     return lastIndex == o.lastIndex
	     		&& Arrays.equals(internArray, o.internArray);
	     		
	}
	
	/**
	 * Testfunctions
	 * 
	 * @param args useless
	 */
	public static void main(String[] args) {
		
		BackupOfBetterArray<String> test = new BackupOfBetterArray<String>(1);
		test.add("Bla");
		test.add("Bla2");
		test.add("Bla3");
		test.set(6, "test");
		System.out.println(test.get(4));
		
		
	}
}