package engine;

import java.io.IOException;

public class LuciferMonsterPartyMonster {
	
	private long id		= 1;
	private long xPos	= 0;
	private long yPos	= 0;
	
	public LuciferMonsterPartyMonster(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferMonsterPartyMonster(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				id = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x02:
				xPos = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				yPos = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMonsterPartyMonster! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the id
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id
	 * 
	 * @param id the new id
	 */
	public void setId(
			long id) {
		this.id = id;
	}

	/**
	 * Returns the xPos
	 * 
	 * @return the xPos
	 */
	public long getxPos() {
		return xPos;
	}

	/**
	 * Sets the xPos
	 * 
	 * @param xPos the new xPos
	 */
	public void setxPos(
			long xPos) {
		this.xPos = xPos;
	}

	/**
	 * Returns the yPos
	 * 
	 * @return the yPos
	 */
	public long getyPos() {
		return yPos;
	}

	/**
	 * Sets the yPos
	 * 
	 * @param yPos the new yPos
	 */
	public void setyPos(
			long yPos) {
		this.yPos = yPos;
	}

	/**
	 * Returns the byte-representation of this MonsterPartyMonster
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		return Helper.concatAll(new LuciferBaseUnit(0x01, DataReader.intToRPGint(id)).write(),
				new LuciferBaseUnit(0x02, DataReader.intToRPGint(xPos)).write(),
				new LuciferBaseUnit(0x03, DataReader.intToRPGint(yPos)).write(),
				new byte[]{0}
				);
	}
}