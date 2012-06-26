package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author alina
 *
 * This class represents a Frame-Unit of a BattleAnimation of the RPG-Maker-Game.
 */
public class LuciferFrameUnit {
	
	public ArrayList<LuciferCellField> cellFields;

	public LuciferFrameUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferFrameUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		DataReader innersr = new DataReader(sr.nextUnitReadID().content);
		cellFields = new ArrayList<LuciferCellField>();
		for (int i = 0; i < cellFields.size(); i++) {
			innersr.nextInt();
			cellFields.add(new LuciferCellField(innersr));
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
		for (int i = 0; i < cellFields.size(); i++) {
			cellblock = Helper.concatAll(cellblock, cellFields.get(i).write());
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
	     
	     return cellFields.equals(o.cellFields);
	}
}
