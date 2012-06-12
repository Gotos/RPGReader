package engine;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class LuciferEncounterData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3170291834410195756L;
	public int partiesNr	= 0;
	public long[] parties;
	
	public LuciferEncounterData(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferEncounterData(DataReader sr) throws IOException {
		init(sr);
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
	
	public byte[] write() {
		byte[] rest = new byte[0];
		for (int i = 0; i < parties.length; i++) {
			rest = Helper.concatAll(rest, DataReader.intToRPGint(i + 1),
					new LuciferBaseUnit(0x01, DataReader.intToRPGint(parties[i])).write(), new byte[]{0});
		}
		return Helper.concatAll(DataReader.intToRPGint(parties.length),
				rest);
	}
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferEncounterData)) {
	        return false; // different class
	     }
	     
	     LuciferEncounterData o = (LuciferEncounterData) obj;
	     
	     return partiesNr == o.partiesNr
	     		&& Arrays.equals(parties, o.parties);
	}
}