package engine;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class LuciferMoveCommand {
	public long type;
	public long[] data		= new long[0];
	public String filename	= "";
	
	public LuciferMoveCommand(Long unit) {
		type = unit;
	}
	
	public LuciferMoveCommand(Long unit, long[] gdata) {
		type = unit;
		data = gdata;
	}
	
	public LuciferMoveCommand(Long unit, long[] gdata, byte[] gfilename) {
		type = unit;
		data = gdata;
		try {
			filename = new String(gfilename, Encoder.ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			// Nothing to do. Won't happen.
		}
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
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferMoveCommand)) {
	        return false; // different class
	     }
	     
	     LuciferMoveCommand o = (LuciferMoveCommand) obj;
	     
	     return type == o.type
	     		&& Arrays.equals(data, o.data)
	     		&& filename.equals(o.filename);
	}
}