package com.github.gotos.rpgreader.engine;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author alina
 *
 * This class represents a single Move command of the RPG-Maker-Game.
 */
public class LuciferMoveCommand implements UnitInterface {
	/** The type of the Move; list will follow! */ //TODO: add list
	public final long type;
	/** The data append to the MoveCommand */
	public final long[] data;
	/** The filename append to the MoveCommand; changeGraphic and playSound only! */
	public final String filename;
	
	/**
	 * Constructs a new MoveCommand of the given type with no filename and an empty data-array.
	 * 
	 * @param type The type of the MoveCommand
	 */
	public LuciferMoveCommand(Long type) {
		this.type = type;
		data = new long[0];
		filename = "";
	}
	
	/**
	 * Constructs a new MoveCommand of the given type and data-array with no filename .
	 * 
	 * @param type The type of the MoveCommand
	 * @param data The appended data of the MoveCommand
	 */
	public LuciferMoveCommand(Long type, long[] data) {
		this.type = type;
		this.data = data;
		filename = "";
	}
	
	/**
	 * Constructs a new MoveCommand of the given type, data-array and filename .
	 * 
	 * @param type The type of the MoveCommand
	 * @param data The appended data of the MoveCommand
	 * @param filename The appended filename of the MoveCommand
	 * @throws UnsupportedEncodingException thrown, if there is a problem encoding the filename
	 */
	public LuciferMoveCommand(Long type, long[] data, byte[] filename) throws UnsupportedEncodingException {
		this.type = type;
		this.data = data;
		this.filename = new String(filename, Encoder.ENCODING);
	}
	
	/**
	 * Constructs a new MoveCommand of the given type, data-array and filename .
	 * 
	 * @param type The type of the MoveCommand
	 * @param data The appended data of the MoveCommand
	 * @param filename The appended filename of the MoveCommand
	 */
	public LuciferMoveCommand(Long type, long[] data, String filename) {
		this.type = type;
		this.data = data;
		this.filename = filename;
	}
	
	/**
	 * Returns the byte-representation of this MoveCommand
	 * 
	 * @return byte-representation
	 */
	public byte[] write() { //TODO: NOT TESTED YET!
		try {
			byte[] dataarray = new byte[0];
			for (int i = 0; i < data.length; i++) {
				dataarray = Helper.concatAll(dataarray, DataReader.intToRPGint(data[i]));
			}
			if (filename.length() > 0) {
				return Helper.concatAll(DataReader.intToRPGint(type),
						DataReader.intToRPGint(filename.length()),
						filename.getBytes(Encoder.ENCODING),
						dataarray
						);
			} else {
				return Helper.concatAll(DataReader.intToRPGint(type),
						dataarray
						);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
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
				* result + Arrays.hashCode(data);
		result = prime
				* result + ((filename == null) ? 0
						: filename.hashCode());
		result = prime
				* result + (int) (type ^ (type >>> 32));
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
		if (!(obj instanceof LuciferMoveCommand)) {
			return false;
		}
		LuciferMoveCommand other = (LuciferMoveCommand) obj;
		if (!Arrays.equals(
				data, other.data)) {
			return false;
		}
		if (filename == null) {
			if (other.filename != null) {
				return false;
			}
		} else if (!filename.equals(other.filename)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Assembles a list of LuciferMoveCommands from a single LuciferEventCommand of type MOVE_EVENT
	 * 
	 * @param eventCommand The MOVE_EVENT that should be converted
	 * @return ArrayList of the LuciferMoveCommands
	 * @throws UnsupportedEncodingException thrown, if Encoding of Strings is unsupported
	 * @throws IllegalArgumentException thrown, if the LuciferEventCommand is not of type MOVE_EVENT
	 */
	public static ArrayList<LuciferMoveCommand> assembleMoveCommands(LuciferEventCommand eventCommand)
			throws UnsupportedEncodingException, IllegalArgumentException {
		if (eventCommand.type != LuciferEventCommand.MOVE_EVENT) {
			throw new IllegalArgumentException();
		}
		ArrayList<LuciferMoveCommand> commands = new ArrayList<LuciferMoveCommand>();
		long move;
		long[] data;
		long length;
		for (int i = 4; i < eventCommand.data.length; i++) {
			move = eventCommand.data[i];
			//System.out.println(move.integer);
			if ((move == 0x20) || (move == 0x21)) {
				data = new long[1];
				data[0] = eventCommand.data[i + 1];
				commands.add(i, new LuciferMoveCommand(move, data));
				i++;
			} else if ((move == 0x22)) {
				data = new long[1];
				length = eventCommand.data[i + 1];
				byte[] filename = new byte[(int) length];
				for (int k = 0; k < length; k++) {
					filename[k] = (byte) eventCommand.data[i + 2 + k];
				}
				data[0] = eventCommand.data[(int) (i + 2 + length)];
				commands.add(i, new LuciferMoveCommand(move, data, filename));
				i += 2 + length;
			} else if ((move == 0x23)) {
				data = new long[3];
				length = eventCommand.data[i + 1];
				byte[] filename = new byte[(int) length];
				for (int k = 0; k < length; k++) {
					filename[k] = (byte) eventCommand.data[i + 2 + k];
				}
				for (int j = 0; j < 3; j++) {
					data[j] = eventCommand.data[(int) (i + j + 2 + length)];
				}
				commands.add(i, new LuciferMoveCommand(move, data, filename));
				i += 5 + length;
			} else {
				commands.add(i, new LuciferMoveCommand(move));
			}
		}
		return null;
	}
}