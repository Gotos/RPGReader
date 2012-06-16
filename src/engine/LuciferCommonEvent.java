package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author alina
 *
 * This class represents an Common Event of the Database of the RPG-Maker-Game.
 */
public class LuciferCommonEvent {
	
	private String name									= "";
	private long eventStartCondition					= 5;
	public long switchID								= 1;
	public long scriptLength							= 0;
	public boolean useSwitch							= false;
	public ArrayList<LuciferEventCommand> commands		= new ArrayList<LuciferEventCommand>();
	
	/**
	 * Constructs a LuciferCommonEvent via a byte[]
	 * 
	 * @param str byte[], which is used to construct the LuciferCommonEvent
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferCommonEvent(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	/**
	 * Constructs a LuciferCommonEvent via a StringReader
	 * 
	 * @param sr StringReader, which is used to construct the LuciferCommonEvent
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferCommonEvent(DataReader sr) throws IOException {
		init(sr);
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
				eventStartCondition = DataReader.rpgintToInt(unit.content).integer;
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
	 * Returns Eventname
	 * 
	 * @return eventname
	 */
	public String name() {
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
	 * Sets Eventname
	 * 
	 * @param name new name
	 */
	public void name(String name) {
		this.name = name;
	}
	
	/**
	 * Returns eventStartCondition
	 * 
	 * @return eventStartCondition
	 */
	public long getEventStartCondition() {
		return eventStartCondition;
	}
	
	/**
	 * Returns eventStartCondition
	 * 
	 * @return eventStartCondition
	 */
	public long eventStartCondition() {
		return eventStartCondition;
	}
	
	/**
	 * Sets eventStartCondition
	 * 
	 * @param eventStartCondition new eventStartCondition
	 */
	public void setEventStartCondition(long eventStartCondition) {
		this.eventStartCondition = eventStartCondition;
	}
	
	/**
	 * Sets eventStartCondition
	 * 
	 * @param eventStartCondition new eventStartCondition
	 */
	public void eventStartCondition(long eventStartCondition) {
		this.eventStartCondition = eventStartCondition;
	}
}

