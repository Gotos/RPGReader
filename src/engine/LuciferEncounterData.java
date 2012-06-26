package engine;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author alina
 *
 * This class represents an EncounterData of an Maptree-Element (Map or Area) of the RPG-Maker-Game.
 */
public class LuciferEncounterData {

	public int partiesNr	= 0;
	public long[] parties;
	
	/**
	 * Constructs a LuciferEncounterData via a byte[]
	 * 
	 * @param bytes byte[], which is used to construct the LuciferEncounterData
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferEncounterData(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a LuciferEncounter Data via a DataReader
	 * 
	 * @param dr DataReader, which is used to construct the LuciferEncounterData
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferEncounterData(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		partiesNr = (int) sr.nextInt();
		parties = new long[partiesNr];
		for (int i = 0; i < partiesNr; i++) {
			sr.nextInt(); //read id of party
			LuciferBaseUnit unit = sr.nextUnit();
			parties[i] = DataReader.rpgintToInt(unit.content).integer;
			sr.nextInt(); //read terminating 0x00
		}
	}
	
	/**
	 * Returns the byte-representation of this EncounterData
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		byte[] rest = new byte[0];
		for (int i = 0; i < parties.length; i++) {
			rest = Helper.concatAll(rest, DataReader.intToRPGint(i + 1),
					new LuciferBaseUnit(0x01, DataReader.intToRPGint(parties[i])).write(), new byte[]{0});
		}
		return Helper.concatAll(DataReader.intToRPGint(parties.length),
				rest);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + Arrays.hashCode(parties);
		result = prime
				* result + partiesNr;
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
		if (!(obj instanceof LuciferEncounterData)) {
			return false;
		}
		LuciferEncounterData other = (LuciferEncounterData) obj;
		if (!Arrays.equals(
				parties, other.parties)) {
			return false;
		}
		if (partiesNr != other.partiesNr) {
			return false;
		}
		return true;
	}
}