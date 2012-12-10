package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class models a BattleEvent-Page of the RM2k.
 * 
 * @author gRuFtY
 * 
 */
public class LuciferBattleEventPage implements UnitInterface {
	
	private LuciferBattleEventPageCondition conditions;
	private long scriptLength;
	private ArrayList<LuciferEventCommand> commands;
	
	/**
	 * Return Condition of this BattleEventPage as LuciferBattleEventPageCondition
	 * 
	 * @return Condition of this BattleEventPage
	 */
	public LuciferBattleEventPageCondition getConditions() {
		return conditions;
	}
	
	/**
	 * Sets Condition of this BattleEventPage
	 * 
	 * @param conditions new Condition of this BattleEventPage
	 */
	public void setConditions(LuciferBattleEventPageCondition conditions) {
		this.conditions = conditions;
	}
	
	/**
	 * Return Condition of this BattleEventPage as LuciferBattleEventPageCondition
	 * 
	 * @return Condition of this BattleEventPage
	 */
	public LuciferBattleEventPageCondition getTrigger() {
		return conditions;
	}
	
	/**
	 * Sets Condition of this BattleEventPage
	 * 
	 * @param conditions new Condition of this BattleEventPage
	 */
	public void setTrigger(LuciferBattleEventPageCondition conditions) {
		this.conditions = conditions;
	}
	
	/**
	 * Returns the Event-Commands of this BattleEvent-Page
	 * 
	 * @return Event-Commands of this BattleEvent-Page
	 */
	public ArrayList<LuciferEventCommand> getCommands() {
		return commands;
	}
	
	/**
	 * Returns the Event-Commands of this BattleEvent-Page
	 * 
	 * @return Event-Commands of this BattleEvent-Page
	 */
	public ArrayList<LuciferEventCommand> getEventCommands() {
		return commands;
	}
	
	/**
	 * Returns the Event-Commands at index of this BattleEvent-Page
	 * 
	 * @param index Index of the Event-Command
	 * @return Event-Commands at index of this BattleEvent-Page
	 * @throws IndexOutOfBoundsException thrown, if Index is out of Bounds. Surprise, surprise ;-)
	 */
	public LuciferEventCommand getCommand(int index) throws IndexOutOfBoundsException {
		return commands.get(index);
	}
	
	/**
	 * Returns the Event-Commands at index of this BattleEvent-Page
	 * 
	 * @param index Index of the Event-Command
	 * @return Event-Commands at index of this BattleEvent-Page
	 * @throws IndexOutOfBoundsException thrown, if Index is out of Bounds. Surprise, surprise ;-)
	 */
	public LuciferEventCommand getEventCommand(int index) throws IndexOutOfBoundsException {
		return commands.get(index);
	}
	
	/**
	 * Sets the Event-Commands of this BattleEvent-Page
	 * 
	 * @param commands new Event-Commands
	 */
	public void setCommands(ArrayList<LuciferEventCommand> commands) {
		this.commands = commands;
	}
	
	/**
	 * Sets the Event-Commands of this BattleEvent-Page
	 * 
	 * @param commands new Event-Commands
	 */
	public void setEventCommands(ArrayList<LuciferEventCommand> commands) {
		this.commands = commands;
	}
	
	/**
	 * Sets Event-Command of this BattleEvent-Page at index to command
	 * 
	 * @param index Index of the Event-Command
	 * @param command new Event-Command
	 * @throws ArrayIndexOutOfBoundsException thrown, if Array-Index is out of Bounds. Surprising, huh?
	 */
	public void setCommand(int index, LuciferEventCommand command) throws ArrayIndexOutOfBoundsException {
		commands.set(index, command);
	}
	
	/**
	 * Sets Event-Command of this BattleEvent-Page at index to command
	 * 
	 * @param index Index of the Event-Command
	 * @param command new Event-Command
	 * @throws ArrayIndexOutOfBoundsException thrown, if Array-Index is out of Bounds. Surprising, huh?
	 */
	public void setEventCommand(int index, LuciferEventCommand command) throws ArrayIndexOutOfBoundsException {
		commands.set(index, command);
	}
	
	/**
	 * Constructs a new LuciferBattleEventPage
	 * 
	 * @param bytes byte-Array which represents the LuciferBattleEventPage
	 * @throws IOException thrown, if something is wrong with the bytes
	 */
	public LuciferBattleEventPage(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}

	/**
	 * Constructs a new LuciferBattleEventPage
	 * 
	 * @param dr DataReader which represents the LuciferBattleEventPage
	 * @throws IOException thrown, if something is wrong with the DataReader
	 */
	public LuciferBattleEventPage(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			DataReader tmp;
			switch(unit.id) {
			case 0x02:
				conditions = new LuciferBattleEventPageCondition(unit.content);
				break;
			case 0x0B:
				scriptLength = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				tmp = new DataReader(unit.content);
				commands = new ArrayList<LuciferEventCommand>((int) scriptLength);
				int finalPos = tmp.getPos() + (int) scriptLength;
				while (tmp.getPos() < finalPos) {
					commands.add(new LuciferEventCommand(tmp));
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferBattleEventPage! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this BattleEventPage
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		byte[] commandblock = new byte[0];
		for (int i = 0; i < commands.size(); i++) {
			if (commands.get(i) != null) {
				commandblock = Helper.concatAll(commandblock, commands.get(i).write());
			}
		}
		return Helper.concatAll(new LuciferBaseUnit(0x02, conditions.write()).write(new byte[0]),
				new LuciferBaseUnit(0x0B, DataReader.intToRPGint(commandblock.length)).write(new byte[]{0}),
				new LuciferBaseUnit(0x0C, commandblock).write(new byte[0]),
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
				* result + ((commands == null) ? 0
						: commands.hashCode());
		result = prime
				* result + ((conditions == null) ? 0
						: conditions.hashCode());
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
		if (!(obj instanceof LuciferBattleEventPage)) {
			return false;
		}
		LuciferBattleEventPage other = (LuciferBattleEventPage) obj;
		if (commands == null) {
			if (other.commands != null) {
				return false;
			}
		} else if (!commands.equals(other.commands)) {
			return false;
		}
		if (conditions == null) {
			if (other.conditions != null) {
				return false;
			}
		} else if (!conditions.equals(other.conditions)) {
			return false;
		}
		return true;
	}
}