package engine;

import java.io.IOException;

public class LuciferTerrainUnit {
	
	public String name					= "";
	public String battleBackground		= "";
	public long damage					= 0;
	public long enemyMagnify			= 100;
	public boolean passableByBoat		= false;
	public boolean passableByShip		= false;
	public boolean passableByAirship	= true;
	public boolean airshipLanding		= true;
	public short charaDisplayMethod	= 0;
	
	public LuciferTerrainUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferTerrainUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	public void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				damage = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				enemyMagnify = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				battleBackground = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x05:
				passableByBoat = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x06:
				passableByShip = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x07:
				passableByAirship = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x09:
				airshipLanding = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0B:
				charaDisplayMethod = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			}
			unit = sr.nextUnit();
		}
	}
}
