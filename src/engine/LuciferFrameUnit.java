package engine;

import java.io.IOException;
import java.util.Arrays;

public class LuciferFrameUnit {
	
	public LuciferCellField[] cellFields;

	public LuciferFrameUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferFrameUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		DataReader innersr = new DataReader(sr.nextUnitReadID().content);
		cellFields = new LuciferCellField[(int) innersr.nextInt()];
		for (int i = 0; i < cellFields.length; i++) {
			innersr.nextInt();
			cellFields[i] = new LuciferCellField(innersr);
			//innersr.nextInt();
		}
	}

	/**
	 * Returns the byte-representation of this FrameUnit
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		byte[] cellblock = new byte[0];
		for (int i = 0; i < cellFields.length; i++) {
			cellblock = Helper.concatAll(cellblock, cellFields[i].write());
		}
		return cellblock;
	}
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferFrameUnit)) {
	        return false; // different class
	     }
	     
	     LuciferFrameUnit o = (LuciferFrameUnit) obj;
	     
	     return Arrays.equals(cellFields, o.cellFields);
	}
}
