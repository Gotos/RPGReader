package de.grufty.rpgreader.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;


/**
 * @author alina
 * 
 * This class is used to read maps, the database, etc. of an rpg-maker-project.
 * 
 */
public class DataReader {
	
	
	
	private int pos;
	private byte[] string;
	
	/**
	 * Creates a new DataReader based of the given bytes
	 * 
	 * @param bytes base of the DataReader
	 */
	public DataReader(byte[] bytes) {
		pos = 0;
		string = bytes;
	}
	
	private void move(long num) {
		pos += num;
	}
	
	/**
	 * Returns the current position of the read-cursor
	 * 
	 * @return the current position of the read-cursor
	 */
	public int getPos() {
		return pos;
	}
	
	/*
	 * rpgintToInt-methods
	 */

	
	/**
	 * Returns the next RPGInt of the DataReader
	 * 
	 * @return the next RPGInt of the DataReader
	 * @throws IOException is thrown on any reading error
	 */
	public RPGInt rpgintToInt() throws IOException {
		
		RPGInt rpg = rpgintToInt(Helper.slice(string, pos));
		this.move(rpg.length);
		return rpg;
	}
	
	/**
	 * Returns the next RPGInt of bytes
	 * 
	 * @param bytes the bytes, from which the RPGInt is extracted
	 * @return the next RPGInt of bytes
	 * @throws IOException is thrown on any reading error
	 */
	public static RPGInt rpgintToInt(byte[] bytes) throws IOException {
			int pos = 0;
			int chr = bytes[pos];
			String bin = "";
			String tmpStr = "";
			
			while ((int) chr < 0) {
				tmpStr = Integer.toBinaryString(((int) chr) + 128);
				
				while (tmpStr.length() < 7) {
					tmpStr = "0" + tmpStr;
				}
				
				bin += tmpStr; //String.format("%08b", chr-128);
				pos += 1;
				chr = bytes[pos];
			}
			
			tmpStr = Integer.toBinaryString(((int) chr));
			
			while (tmpStr.length() < 7) {
				tmpStr = "0" + tmpStr;
			}
			
			bin += tmpStr; //String.format("%07b", chr);
			return new RPGInt(Long.parseLong(bin, 2), (pos + 1));
	}
	
	/**
	 * Returns the given long as an RPGInt
	 * 
	 * @param orgLong the long, which should be converted to an RPGInt
	 * @return the given long as an RPGInt
	 */
	public static byte[] intToRPGint(long orgLong) {
		//if(orgInt<0){
		//	return new byte[0];
		//}
		String orgBin = new StringBuffer(Integer.toBinaryString((int) orgLong)).reverse().toString();
		String newBin = "";
		while (orgBin.length() > 0) {
			if (newBin.length() == 7) {
				newBin = "0" + newBin;
			} else if ((newBin.length()) % 8 == 7) {
				newBin = "1" + newBin;
			} else {
				newBin = orgBin.charAt(0) + newBin;
				orgBin = orgBin.substring(1, orgBin.length());
			}
		}
		while (newBin.length() % 8 != 0) {
			if (newBin.length() == 7) {
				newBin = "0" + newBin;
			} else if ((newBin.length()) % 8 == 7) {
				newBin = "1" + newBin;
			} else {
				newBin = "0" + newBin;
			}
		}
		byte[] result = new byte[newBin.length() / 8];
		int i = 0;
		while (newBin.length() > 0) {
			result[i] = (byte) Integer.parseInt(newBin.substring(0, 8), 2);
			i++;
			newBin = newBin.substring(8);
		}
		return result;
	}
	
	
	/*
	 * read-methodes
	 * 
	 * returns the rest of the string, the whole string or a part of the rest of the string.
	 * note, that the read-methods will usually not update the position, only exception is read(long,true).
	 */
	
	/**
	 * Returns the unread bytes
	 * 
	 * @return the unread bytes
	 */
	public byte[] readRest() {
		return Helper.slice(string, pos);
	}
	
	/**
	 * Returns the unread bytes
	 * 
	 * @return the unread bytes
	 */
	public byte[] read() {
		return readRest();
	}
	
	/**
	 * Returns the bytes, which the DataReader is based on
	 * 
	 * @return the bytes, which the DataReader is based on
	 */
	public byte[] readAll() {
		return string;
	}
	
	/**
	 * Returns the next length bytes
	 * 
	 * @param length number of bytes, which should be returned
	 * @return the next length bytes
	 */
	public byte[] read(long length) {
		return Helper.slice(string, pos, (int) length);
	}
	
	/**
	 * Returns the next length bytes. If move is true, the cursur is updated, else it isn't.
	 * 
	 * @param length number of bytes, which should be returned
	 * @param move decides, if the cursur is updated
	 * @return the next length bytes
	 */
	public byte[] read(long length, boolean move) {
		byte[] str = Helper.slice(string, pos, (int) length);
		if (move) {
			pos += length;
		}
		return str;
	}
	
	/*
	 * nextUnit-methods
	 * 
	 * return the next Unit
	 */
	
	/**
	 * Returns the next RPGInt as an int
	 * 
	 * @return the next RPGInt as an int
	 * @throws IOException is thrown on any reading error
	 */
	public long nextInt() throws IOException {
		RPGInt rpgint = this.rpgintToInt();
		return rpgint.integer;
	}
	
	/**
	 * Returns the next LuciferBaseUnit without an ID. That means, you should not try to read a BaseUnit
	 * which has an ID with this method! The ID will be set to -1.
	 * 
	 * @return the next LuciferBaseUnit without an ID
	 * @throws IOException is thrown on any reading error
	 */
	public LuciferBaseUnit nextUnitZeroID() throws IOException {
		int id = -1;
		RPGInt rpgint = this.rpgintToInt();
		byte[] content = Helper.slice(string, pos, (int) rpgint.integer);
		this.move(rpgint.integer);
		return new LuciferBaseUnit(id, content);
	}
	
	/**
	 * Returns the next LuciferBaseUnit with an ID. That means, you should not try to read a BaseUnit
	 * which has no ID with this method!
	 * 
	 * @return the next LuciferBaseUnit with an ID
	 * @throws IOException is thrown on any reading error
	 */
	public LuciferBaseUnit nextUnitReadID() throws IOException {
		RPGInt rpgintID = this.rpgintToInt();
		int id = (int) rpgintID.integer;
		if (id == 0) {
			return new LuciferBaseUnit(0, new byte[0x0]);
		}
		RPGInt rpgint = this.rpgintToInt();
		byte[] content = Helper.slice(string, pos, (int) rpgint.integer);
		this.move(rpgint.integer);
		return new LuciferBaseUnit(id, content);
	}
	
	/**
	 * Returns the next LuciferBaseUnit with an ID. That means, you should not try to read a BaseUnit
	 * which has no ID with this method!
	 * This method will always return. If any reading error occurs, a new BaseUnit with ID 0 and no content
	 * will be returned.
	 * 
	 * @return the next LuciferBaseUnit with an ID or zero-ID, contentless LuciferBaseUnit
	 */
	public LuciferBaseUnit nextUnit() {
		try {
			//if(pos == 0){
			//	return this.nextUnitZeroID();
			//} else {
				return this.nextUnitReadID();
			//}
		} catch (IOException e) {
			return new LuciferBaseUnit(0, new byte[0x0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return new LuciferBaseUnit(0, new byte[0x0]);
		}
	}
	
	/**
	 * Returns the next 2 byte as an long. Interpretes these bytes as little endian
	 * 
	 * @return the next 2 byte as an long. Interpretes these bytes as little endian
	 */
	public long next16bitle() {
		//next 16 bit little endian
		long unsigned = string[pos];
		if (unsigned < 0) {
			unsigned += 256;
		}
		move(1);
		int secondByte = string[pos];
		if (secondByte < 0) {
			secondByte += 256;
		}
		unsigned += secondByte << 8;
		move(1);
		
		return unsigned;
	}
	
	/**
	 * Returns the next 4 byte as an long. Interpretes these bytes as little endian
	 * 
	 * @return the next 4 byte as an long. Interpretes these bytes as little endian
	 */
	public long next32bitle() {
		//next 32 bit little endian
		long unsigned = string[pos];
		if (unsigned < 0) {
			unsigned += 256;
		}
		move(1);
		int nextByte = string[pos];
		if (nextByte < 0) {
			nextByte += 256;
		}
		unsigned += nextByte << 8;
		move(1);
		nextByte = string[pos];
		if (nextByte < 0) {
			nextByte += 256;
		}
		unsigned += nextByte << 16;
		move(1);
		nextByte = string[pos];
		if (nextByte < 0) {
			nextByte += 256;
		}
		unsigned += nextByte << 24;
		move(1);
		
		return unsigned;
	}
	
	//TODO: Verhalten, das hier beschrieben ist, sollte eigentlich anders sein => Exceptions ;)
	/**
	 * Returns the given number as a 2byte little endian. If the number is to high, the most significant
	 * parts will be lost, so check that your number is < 2^16! Also, you shouldn't use negative numbers
	 * as their binary representation will be stored, but on loading they will be interpreted as an unsigned
	 * number!
	 * 
	 * @param number the number, which will be converted
	 * @return the given number as a 2byte little endian
	 */
	public static byte[] to16bitle(long number) {
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.putInt((int) number);
		return Helper.slice(bb.array(), 0, 2);
	}
	
	/**
	 * Returns the given number as a 4byte little endian. If the number is to high, the most significant
	 * parts will be lost, so check that your number is < 2^32! Also, you shouldn't use negative numbers
	 * as their binary representation will be stored, but on loading they will be interpreted as an unsigned
	 * number!
	 * 
	 * @param number the number, which will be converted
	 * @return the given number as a 4byte little endian
	 */
	public static byte[] to32bitle(long number) {
		ByteBuffer bb = ByteBuffer.allocate(8);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.putLong((long) number);
		return Helper.slice(bb.array(), 0, 4);
	}
	
	/**
	 * Reads a file to bytes an creates a new DataReader with those
	 * 
	 * @param filename Filename of the file, which should be read
	 * @return DataReader of the given file or, if there is an error, a DataReader of an empty bytearray
	 */
	public static DataReader parseFile(char[] filename) {
		try {
			return DataReader.parseFileIntern(new String(filename));
		} catch (IOException e) {
			e.printStackTrace();
			return new DataReader(new byte[0x0]);
		}
	}
	
	/**
	 * Reads a file to bytes an creates a new DataReader with those
	 * 
	 * @param filename Filename of the file, which should be read
	 * @return DataReader of the given file or, if there is an error, a DataReader of an empty bytearray
	 */
	public static DataReader parseFile(String filename) {
		try {
			return DataReader.parseFileIntern(filename);
		} catch (IOException e) {
			e.printStackTrace();
			return new DataReader(new byte[0x0]);
		}
	}
	
	private static DataReader parseFileIntern(String filename) throws IOException {
		File file = new File(filename);
		InputStream is = new FileInputStream(file); // Get the size of the file
		long length = file.length(); // You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			throw new IOException("Could not completely read file " + file.getName());
		} // Create the byte array to hold the data
		byte[] bytes = new byte[(int) length]; // Read in the bytes
		int offset = 0;
		long numRead = is.read(bytes, offset, bytes.length - offset);
		while (offset < bytes.length && numRead >= 0) {
			offset += numRead;
			numRead = is.read(bytes, offset, bytes.length - offset);
		} // Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		} // Close the input stream and return bytes
		is.close();
		
		
		return new DataReader(bytes);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + pos;
		result = prime
				* result + Arrays.hashCode(string);
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
		if (!(obj instanceof DataReader)) {
			return false;
		}
		DataReader other = (DataReader) obj;
		if (pos != other.pos) {
			return false;
		}
		if (!Arrays.equals(
				string, other.string)) {
			return false;
		}
		return true;
	}
}
