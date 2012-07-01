package engine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a Frame-Unit of a BattleAnimation of the RPG-Maker-Game.
 */
public class LuciferFrameUnit {
	
	private ArrayList<LuciferCellField> cellFields;

	/**
	 * Returns the cellFields
	 * 
	 * @return the cellFields
	 */
	public ArrayList<LuciferCellField> getCellFields() {
		return cellFields;
	}

	/**
	 * Sets the cellFields
	 * 
	 * @param cellFields the new cellFields
	 */
	public void setCellFields(
			ArrayList<LuciferCellField> cellFields) {
		this.cellFields = cellFields;
	}
	
	/**
	 * Constructs a new LuciferFrameUnit
	 * 
	 * @param bytes byte-Array which represents the LuciferFrameUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferFrameUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferFrameUnit
	 * 
	 * @param dr DataReader which represents the LuciferFrameUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferFrameUnit(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		DataReader innersr = new DataReader(sr.nextUnitReadID().content);
		int size = (int) innersr.nextInt();
		cellFields = new ArrayList<LuciferCellField>();
		for (int i = 0; i < size; i++) {
			innersr.nextInt();
			cellFields.add(new LuciferCellField(innersr));
		}
	}

	/**
	 * Returns the byte-representation of this FrameUnit
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		
		byte[] cellblock = new byte[0];
		long nrSkills = cellFields.size();
		for (int i = 0; i < cellFields.size(); i++) {
			if (cellFields.get(i) != null) {
				cellblock = Helper.concatAll(cellblock,
						DataReader.intToRPGint(i),
						cellFields.get(i).write());
			}
		}
		cellblock = Helper.concatAll(DataReader.intToRPGint(nrSkills), cellblock);
		
		return cellblock;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + ((cellFields == null) ? 0
						: cellFields.hashCode());
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
		if (!(obj instanceof LuciferFrameUnit)) {
			return false;
		}
		LuciferFrameUnit other = (LuciferFrameUnit) obj;
		if (cellFields == null) {
			if (other.cellFields != null) {
				return false;
			}
		} else if (!cellFields.equals(other.cellFields)) {
			return false;
		}
		return true;
	}
}
