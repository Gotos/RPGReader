package de.grufty.rpgreader.engine;

import java.io.IOException;

/**
 * @author alina
 *
 * This class represents a MonsterPartyMonster of the RPG-Maker-Game.
 */
public class LuciferMonsterPartyMonster implements UnitInterface {
	
	private long id		= 1;
	private long xPos	= 0;
	private long yPos	= 0;
	
	/**
	 * Constructs a new LuciferMonsterPartyMonster
	 */
	public LuciferMonsterPartyMonster() { }
	
	/**
	 * Constructs a new LuciferMonsterPartyMonster
	 * 
	 * @param bytes byte-Array which represents the LuciferMonsterPartyMonster
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterPartyMonster(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMonsterAction
	 * 
	 * @param dr DataReader which represents the LuciferMonsterAction
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterPartyMonster(DataReader dr) throws IOException {
		init(dr);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + (int) (id ^ (id >>> 32));
		result = prime
				* result + (int) (xPos ^ (xPos >>> 32));
		result = prime
				* result + (int) (yPos ^ (yPos >>> 32));
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
		if (!(obj instanceof LuciferMonsterPartyMonster)) {
			return false;
		}
		LuciferMonsterPartyMonster other = (LuciferMonsterPartyMonster) obj;
		if (id != other.id) {
			return false;
		}
		if (xPos != other.xPos) {
			return false;
		}
		if (yPos != other.yPos) {
			return false;
		}
		return true;
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