package engine;

import java.io.IOException;

/**
 * @author alina
 *
 * This class represents a MapEventPageCondition of the RPG-Maker-Game.
 */
public class LuciferMapEventPageCondition {
	
	public boolean switchA		= false;
	public boolean switchB		= false;
	public boolean variable	= false;
	public boolean item		= false;
	public boolean hero		= false;
	public boolean timer		= false;
	public long switchAID		= 1;
	public long switchBID		= 1;
	public long variableID		= 1;
	public long variableValue	= 0;
	public long itemID			= 1;
	public long heroID			= 1;
	public long timeRemaining	= 0;
	
	/**
	 * Constructs a new LuciferMapEventPageCondition
	 * 
	 * @param bytes byte-Array which represents the LuciferMapEventPageCondition
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapEventPageCondition(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMapEventPageCondition
	 * 
	 * @param dr DataReader which represents the LuciferMapEventPageCondition
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapEventPageCondition(DataReader dr) throws IOException {
		init(dr);
	}
	/**
	 * Constructs a new LuciferMapEventPageCondition
	 */
	public LuciferMapEventPageCondition() {	}

	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				switchA		= (DataReader.rpgintToInt(unit.content).integer % 2 == 1);
				switchB		= ((DataReader.rpgintToInt(unit.content).integer / 2) % 2 == 1);
				variable	= ((DataReader.rpgintToInt(unit.content).integer / 4) % 2 == 1);
				item		= ((DataReader.rpgintToInt(unit.content).integer / 8) % 2 == 1);
				hero		= ((DataReader.rpgintToInt(unit.content).integer / 16) % 2 == 1);
				timer		= ((DataReader.rpgintToInt(unit.content).integer / 32) % 2 == 1);
				break;
			case 0x02:
				switchAID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				switchBID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				variableID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				variableValue = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				itemID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x07:
				heroID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x08:
				timeRemaining = DataReader.rpgintToInt(unit.content).integer;
				break;
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this MapEventPageCondition
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		long activeConditions = 0;
		activeConditions += (switchA) ? 1 : 0;
		activeConditions += ((switchB) ? 1 : 0) * 2;
		activeConditions += ((variable) ? 1 : 0) * 4;
		activeConditions += ((item) ? 1 : 0) * 8;
		activeConditions += ((hero) ? 1 : 0) * 16;
		activeConditions += ((timer) ? 1 : 0) * 32;
		return Helper.concatAll(new LuciferBaseUnit(0x01, DataReader.intToRPGint(activeConditions)).write(),
				new LuciferBaseUnit(0x02, DataReader.intToRPGint(switchAID)).write(new byte[]{1}),
				new LuciferBaseUnit(0x03, DataReader.intToRPGint(switchBID)).write(new byte[]{1}),
				new LuciferBaseUnit(0x04, DataReader.intToRPGint(variableID)).write(new byte[]{1}),
				new LuciferBaseUnit(0x05, DataReader.intToRPGint(variableValue)).write(new byte[]{0}),
				new LuciferBaseUnit(0x06, DataReader.intToRPGint(itemID)).write(new byte[]{1}),
				new LuciferBaseUnit(0x07, DataReader.intToRPGint(heroID)).write(),
				new LuciferBaseUnit(0x08, DataReader.intToRPGint(timeRemaining)).write(new byte[]{0}),
				new byte[]{0}
				);
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + (hero ? 1231
						: 1237);
		result = prime
				* result + (int) (heroID ^ (heroID >>> 32));
		result = prime
				* result + (item ? 1231
						: 1237);
		result = prime
				* result + (int) (itemID ^ (itemID >>> 32));
		result = prime
				* result + (switchA ? 1231
						: 1237);
		result = prime
				* result + (int) (switchAID ^ (switchAID >>> 32));
		result = prime
				* result + (switchB ? 1231
						: 1237);
		result = prime
				* result + (int) (switchBID ^ (switchBID >>> 32));
		result = prime
				* result + (int) (timeRemaining ^ (timeRemaining >>> 32));
		result = prime
				* result + (timer ? 1231
						: 1237);
		result = prime
				* result + (variable ? 1231
						: 1237);
		result = prime
				* result + (int) (variableID ^ (variableID >>> 32));
		result = prime
				* result + (int) (variableValue ^ (variableValue >>> 32));
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
		if (!(obj instanceof LuciferMapEventPageCondition)) {
			return false;
		}
		LuciferMapEventPageCondition other = (LuciferMapEventPageCondition) obj;
		if (hero != other.hero) {
			return false;
		}
		if (heroID != other.heroID) {
			return false;
		}
		if (item != other.item) {
			return false;
		}
		if (itemID != other.itemID) {
			return false;
		}
		if (switchA != other.switchA) {
			return false;
		}
		if (switchAID != other.switchAID) {
			return false;
		}
		if (switchB != other.switchB) {
			return false;
		}
		if (switchBID != other.switchBID) {
			return false;
		}
		if (timeRemaining != other.timeRemaining) {
			return false;
		}
		if (timer != other.timer) {
			return false;
		}
		if (variable != other.variable) {
			return false;
		}
		if (variableID != other.variableID) {
			return false;
		}
		if (variableValue != other.variableValue) {
			return false;
		}
		return true;
	}
}