package de.grufty.rpgreader.engine;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class LuciferMoveCommand {
	public final long type;
	public final long[] data;
	public final String filename;
	
	public LuciferMoveCommand(Long unit) {
		type = unit;
		data = new long[0];
		filename = "";
	}
	
	public LuciferMoveCommand(Long unit, long[] gdata) {
		type = unit;
		data = gdata;
		filename = "";
	}
	
	public LuciferMoveCommand(Long unit, long[] gdata, byte[] gfilename) throws UnsupportedEncodingException {
		type = unit;
		data = gdata;
		filename = new String(gfilename, Encoder.ENCODING);
	}
	
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
}