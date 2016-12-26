package com.github.gotos.rpgreader.old;

import java.io.IOException;

/**
 * @author alina
 *
 * This class represents a MapEventPageCondition of the RPG-Maker-Game.
 */
public class LuciferMapEventPageCondition implements UnitInterface {
	
	private boolean switchA		= false;
	private boolean switchB		= false;
	private boolean variable	= false;
	private boolean item		= false;
	private boolean hero		= false;
	private boolean timer		= false;
	private long switchAID		= 1;
	private long switchBID		= 1;
	private long variableID		= 1;
	private long variableValue	= 0;
	private long itemID			= 1;
	private long heroID			= 1;
	private long timeRemaining	= 0;
	
	/**
	 * Returns the switchA
	 * 
	 * @return the switchA
	 */
	public boolean isSwitchA() {
		return switchA;
	}

	/**
	 * Sets the switchA
	 * 
	 * @param switchA the new switchA
	 */
	public void setSwitchA(
			boolean switchA) {
		this.switchA = switchA;
	}

	/**
	 * Returns the switchB
	 * 
	 * @return the switchB
	 */
	public boolean isSwitchB() {
		return switchB;
	}

	/**
	 * Sets the switchB
	 * 
	 * @param switchB the new switchB
	 */
	public void setSwitchB(
			boolean switchB) {
		this.switchB = switchB;
	}

	/**
	 * Returns the variable
	 * 
	 * @return the variable
	 */
	public boolean isVariable() {
		return variable;
	}

	/**
	 * Sets the variable
	 * 
	 * @param variable the new variable
	 */
	public void setVariable(
			boolean variable) {
		this.variable = variable;
	}

	/**
	 * Returns the item
	 * 
	 * @return the item
	 */
	public boolean isItem() {
		return item;
	}

	/**
	 * Sets the item
	 * 
	 * @param item the new item
	 */
	public void setItem(
			boolean item) {
		this.item = item;
	}

	/**
	 * Returns the hero
	 * 
	 * @return the hero
	 */
	public boolean isHero() {
		return hero;
	}

	/**
	 * Sets the hero
	 * 
	 * @param hero the new hero
	 */
	public void setHero(
			boolean hero) {
		this.hero = hero;
	}

	/**
	 * Returns the timer
	 * 
	 * @return the timer
	 */
	public boolean isTimer() {
		return timer;
	}

	/**
	 * Sets the timer
	 * 
	 * @param timer the new timer
	 */
	public void setTimer(
			boolean timer) {
		this.timer = timer;
	}

	/**
	 * Returns the switchAID
	 * 
	 * @return the switchAID
	 */
	public long getSwitchAID() {
		return switchAID;
	}

	/**
	 * Sets the switchAID
	 * 
	 * @param switchAID the new switchAID
	 */
	public void setSwitchAID(
			long switchAID) {
		this.switchAID = switchAID;
	}

	/**
	 * Returns the switchBID
	 * 
	 * @return the switchBID
	 */
	public long getSwitchBID() {
		return switchBID;
	}

	/**
	 * Sets the switchBID
	 * 
	 * @param switchBID the new switchBID
	 */
	public void setSwitchBID(
			long switchBID) {
		this.switchBID = switchBID;
	}

	/**
	 * Returns the variableID
	 * 
	 * @return the variableID
	 */
	public long getVariableID() {
		return variableID;
	}

	/**
	 * Sets the variableID
	 * 
	 * @param variableID the new variableID
	 */
	public void setVariableID(
			long variableID) {
		this.variableID = variableID;
	}

	/**
	 * Returns the variableValue
	 * 
	 * @return the variableValue
	 */
	public long getVariableValue() {
		return variableValue;
	}

	/**
	 * Sets the variableValue
	 * 
	 * @param variableValue the new variableValue
	 */
	public void setVariableValue(
			long variableValue) {
		this.variableValue = variableValue;
	}

	/**
	 * Returns the itemID
	 * 
	 * @return the itemID
	 */
	public long getItemID() {
		return itemID;
	}

	/**
	 * Sets the itemID
	 * 
	 * @param itemID the new itemID
	 */
	public void setItemID(
			long itemID) {
		this.itemID = itemID;
	}

	/**
	 * Returns the heroID
	 * 
	 * @return the heroID
	 */
	public long getHeroID() {
		return heroID;
	}

	/**
	 * Sets the heroID
	 * 
	 * @param heroID the new heroID
	 */
	public void setHeroID(
			long heroID) {
		this.heroID = heroID;
	}

	/**
	 * Returns the timeRemaining
	 * 
	 * @return the timeRemaining
	 */
	public long getTimeRemaining() {
		return timeRemaining;
	}

	/**
	 * Sets the timeRemaining
	 * 
	 * @param timeRemaining the new timeRemaining
	 */
	public void setTimeRemaining(
			long timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

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
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMapEventPageCondition! ID: " + unit.id);
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