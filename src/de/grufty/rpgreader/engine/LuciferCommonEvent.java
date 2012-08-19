package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a Common Event of the Database of the RPG-Maker-Game.
 * EventStartConditions are the static final ints of this class.
 */
public class LuciferCommonEvent {
	
	public static final int PARALLEL = 4;
	public static final int AUTOSTART = 3;
	public static final int ON_CALL = 5;
	
	private String name								= "";
	private int eventStartCondition					= 5;
	private long switchID							= 1;
	private long scriptLength						= 0;
	private boolean useSwitch						= false;
	private ArrayList<LuciferEventCommand> commands	= new ArrayList<LuciferEventCommand>();
	
	
	/**
	 * Returns the ID of the switch
	 * 
	 * @return the ID of the switch
	 */
	public long getSwitchID() {
		return switchID;
	}

	/**
	 * Sets the ID of the switch
	 * 
	 * @param switchID the new ID of the switch
	 */
	public void setSwitchID(long switchID) {
		this.switchID = switchID;
	}

	/**
	 * Returns if the switch is set as condition
	 * 
	 * @return true, if the switch is set as condition
	 */
	public boolean isSwitchEnabled() {
		return useSwitch;
	}

	/**
	 * Sets weather or not the switch is condition
	 * 
	 * @param useSwitch if true, the switch is a condition
	 */
	public void setSwitchEnabled(boolean useSwitch) {
		this.useSwitch = useSwitch;
	}

	/**
	 * Returns the EventCommands
	 * 
	 * @return the EventCommands
	 */
	public ArrayList<LuciferEventCommand> getCommands() {
		return commands;
	}

	/**
	 * Sets the EventCommands
	 * 
	 * @param commands the new EventCommands
	 */
	public void setCommands(ArrayList<LuciferEventCommand> commands) {
		this.commands = commands;
	}

	/**
	 * Constructs a LuciferCommonEvent via a byte[]
	 * 
	 * @param bytes byte[], which is used to construct the LuciferCommonEvent
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferCommonEvent(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a LuciferCommonEvent via a DataReader
	 * 
	 * @param dr DataReader, which is used to construct the LuciferCommonEvent
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferCommonEvent(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		DataReader tmp;
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x0B:
				eventStartCondition = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				useSwitch = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0D:
				switchID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				scriptLength = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x16:
				tmp = new DataReader(unit.content);
				commands = new ArrayList<LuciferEventCommand>();
				int finalPos = tmp.getPos() + (int) scriptLength;
				while (tmp.getPos() < finalPos) {
					commands.add(new LuciferEventCommand(tmp));
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferCommonEvents! ID: " + unit.id);
			}
		unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns Eventname
	 * 
	 * @return eventname
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets Eventname
	 * 
	 * @param name new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns eventStartCondition
	 * 
	 * @return eventStartCondition
	 */
	public int getEventStartCondition() {
		return eventStartCondition;
	}
		
	/**
	 * Sets eventStartCondition
	 * 
	 * @param eventStartCondition new eventStartCondition
	 */
	public void setEventStartCondition(int eventStartCondition) {
		this.eventStartCondition = eventStartCondition;
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
				* result + eventStartCondition;
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + (int) (switchID ^ (switchID >>> 32));
		result = prime
				* result + (useSwitch ? 1231
						: 1237);
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
		if (!(obj instanceof LuciferCommonEvent)) {
			return false;
		}
		LuciferCommonEvent other = (LuciferCommonEvent) obj;
		if (commands == null) {
			if (other.commands != null) {
				return false;
			}
		} else if (!commands.equals(other.commands)) {
			return false;
		}
		if (eventStartCondition != other.eventStartCondition) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (switchID != other.switchID) {
			return false;
		}
		if (useSwitch != other.useSwitch) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the byte-representation of this CommonEvent
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			byte[] commandList = new byte[0];
			for (LuciferEventCommand command : commands) {
				commandList = Helper.concatAll(commandList, command.write());
			}
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x0B, DataReader.intToRPGint(eventStartCondition)).write(new byte[]{5}),
					new LuciferBaseUnit(0x0C, DataReader.intToRPGint(useSwitch ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x0D, DataReader.intToRPGint(switchID)).write(new byte[]{1}),
					new LuciferBaseUnit(0x15, DataReader.intToRPGint(commandList.length)).write(new byte[]{0}),
					new LuciferBaseUnit(0x16, commandList).write(new byte[0]),
					new byte[]{0}
					);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}