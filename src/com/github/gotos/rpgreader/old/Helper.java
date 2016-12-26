package com.github.gotos.rpgreader.old;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

/**
 * @author gRuFtY
 *
 * HelperClass
 * 
 * Designed to make my life easier ;)
 */
public final class Helper {
	
	private Helper() { }
	
	private static int warnAt = 9;
	
	//TODO: slices need length-check.
	
	/**
	 * Slices the array chr as specified below
	 * 
	 * @param chr char[] to be sliced
	 * @param start starting index of the slice
	 * @param length length of the slice
	 * @return sliced char[]
	 */
	public static char[] slice(char[] chr, int start, int length) {
		char[] newchr = new char[length];
		System.arraycopy(chr, start, newchr, 0, length);
		return newchr;
	}
	
	/**
	 * Slices the array chr as specified below
	 * 
	 * @param chr String to be sliced
	 * @param start starting index of the slice
	 * @param length length of the slice
	 * @return sliced String as char[]
	 */
	public static String slice(String chr, int start, int length) {
		String newchr = new String();
		System.arraycopy(chr, start, newchr, 0, length);
		return newchr;
	}
	
	/**
	 * Slices the array chr from start to the end of the array
	 * 
	 * @param chr char[] to be sliced
	 * @param start starting index of the slice
	 * @return sliced char[]
	 */
	public static char[] slice(char[] chr, int start) {
		char[] newchr = new char[chr.length - start];
		System.arraycopy(chr, start, newchr, 0, chr.length - start);
		return newchr;
	}
	
	/**
	 * Returns a new array with the content of chr from index start
	 * 
	 * @param chr array, which is read
	 * @param start first index of returned array
	 * @param length length of the returned array
	 * @return slice of chr
	 */
	public static byte[] slice(byte[] chr, int start, int length) {
		byte[] newchr = new byte[length];
		System.arraycopy(chr, start, newchr, 0, length);
		return newchr;
	}
	
	/**
	 * Returns a new array with the content of chr from index start
	 * 
	 * @param chr array, which is read
	 * @param start first index of returned array
	 * @return slice of chr
	 */
	public static byte[] slice(byte[] chr, int start) {
		byte[] newchr = new byte[chr.length - start];
		System.arraycopy(chr, start, newchr, 0, chr.length - start);
		return newchr;
	}
	
	/**
	 * Returns a new array with the content of chr from index start
	 * 
	 * @param chr array, which is read
	 * @param start first index of returned array
	 * @param length length of the returned array
	 * @return slice of chr
	 */
	public static int[] slice(int[] chr, int start, int length) {
		int[] newchr = new int[length];
		System.arraycopy(chr, start, newchr, 0, length);
		return newchr;
	}
	
	/**
	 * Returns a new array with the content of chr from index start
	 * 
	 * @param chr array, which is read
	 * @param start first index of returned array
	 * @return slice of chr
	 */
	public static int[] slice(int[] chr, int start) {
		int[] newchr = new int[chr.length - start];
		System.arraycopy(chr, start, newchr, 0, chr.length - start);
		return newchr;
	}
	
	/**
	 * Returns an array containing every element of "array" + the element
	 * 
	 * @param array input array
	 * @param element element, which should be appended
	 * @return array + element
	 */
	public static byte[] append(byte[] array, byte element) {
		byte[] newArray = new byte[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, array.length);
		newArray[array.length] = element;
		return newArray;
	}
	
	/**
	 * Returns a new array with the content of chr from index start
	 * 
	 * @param chr array, which is read
	 * @param start first index of returned array
	 * @param length length of the returned array
	 * @param <T> type of Array
	 * @return slice of chr
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] slice(T[] chr, int start, int length) {
		if (chr.length > 0) {
			T[] newchr = (T[]) Array.newInstance(chr[0].getClass(), length);
			System.arraycopy(chr, start, newchr, 0, length);
			return newchr;
		} else {
			return null;
		}
	}
	
	/**
	 * Concatinates the given arrays
	 * 
	 * @param <T> Type of the Arrays
	 * @param first first array
	 * @param rest the other arrays
	 * @return concatination of the given arrays
	 */
	public static <T> T[] concatAll(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			  totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}
	
	/**
	 * Concatinates the given arrays
	 * 
	 * @param first first array
	 * @param rest the other arrays
	 * @return concatination of the given arrays
	 */
	public static byte[] concatAll(byte[] first, byte[]... rest) {
		int totalLength = first.length;
		for (byte[] array : rest) {
			  totalLength += array.length;
		}
		byte[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (byte[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}
	
	/**
	 * Returns the warn-lvl
	 * 
	 * @return the warn-lvl
	 */
	public static int getWarn() {
		return warnAt;
	}
	
	/**
	 * Sets the warn-lvl
	 * 
	 * @param warnlvl new warn-lvl
	 */
	public static void setWarn(int warnlvl) {
		warnAt = warnlvl;
	}
	
	/**
	 * Throws a RuntimeException with message msg, if the global warnlvl is less or equal to the warnlvl given.
	 * If no Exception is triggered, msg will be printed to stdout
	 * 
	 * @param warnlvl warnlvl of this exception
	 * @param msg exception-message
	 * @throws RuntimeException is thrown, if warnlvl triggers
	 */
	public static void warn(int warnlvl, String msg) throws RuntimeException {
		if (warnAt <= warnlvl) {
			throw new RuntimeException("Error:" + msg);
		} else {
			System.out.println("Warning: " + msg);
		}
		
	}
	
	/**
	 * Returns the given bytes just like an hex-editor would.
	 * 
	 * @param bytes The bytes, that should be printed
	 */
	public static void printBytesInHex(byte[] bytes) {
		int offset = 0;
		for (byte b : bytes) {
			System.out.printf("%02X", b);
			offset++;
			if (offset == 16) {
				System.out.printf("\n");
				offset = 0;
			} else {
				System.out.printf(" ");
			}
		}
	}
	
	
	/**
	 * Returns a byte-representation of a list, ready for writing in write()-methods
	 * 
	 * @param list Inputlist
	 * @param <T> a class, that implements UnitInterface
	 * @return byte-representation of list
	 */
	public static <T extends UnitInterface> byte[] listToBytes(ArrayList<T> list) {
		byte[] bytes = new byte[0];
		long nrElements = 0;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) != null) {
				bytes = Helper.concatAll(bytes,
						DataReader.intToRPGint(i),
						list.get(i).write());
				nrElements++;
			}
		}
		bytes = Helper.concatAll(DataReader.intToRPGint(nrElements), bytes);
		return bytes;
	}
}