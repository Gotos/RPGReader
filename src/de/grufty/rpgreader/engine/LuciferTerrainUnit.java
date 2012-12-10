package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LuciferTerrainUnit implements UnitInterface {
	
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
	
	/**
	 * Returns the byte-representation of this Terrain
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x02, DataReader.intToRPGint(damage)).write(new byte[]{0}),
					new LuciferBaseUnit(0x03, DataReader.intToRPGint(enemyMagnify)).write(DataReader.intToRPGint(100)),
					new LuciferBaseUnit(0x04, battleBackground.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x05, DataReader.intToRPGint(passableByBoat ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x06, DataReader.intToRPGint(passableByShip ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x07, DataReader.intToRPGint(passableByAirship ? 1 : 0)).write(new byte[]{1}),
					new LuciferBaseUnit(0x09, DataReader.intToRPGint(airshipLanding ? 1 : 0)).write(new byte[]{1}),
					new LuciferBaseUnit(0x0B, DataReader.intToRPGint(charaDisplayMethod)).write(new byte[]{0}),
					new byte[]{0}
					);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}
