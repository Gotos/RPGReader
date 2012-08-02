package engine;

import java.io.IOException;

/**
 * @author alina
 *
 * This class represents a MonsterAction of the RPG-Maker-Game.
 */
public class LuciferMonsterAction {
	
	public long subject				= 0;
	public long behavior				= 0;
	public long skill					= 1;
	public long transform				= 1;
	public long condition				= 0;
	public long conditionLowerLimit	= 0;
	public long conditionUpperLimit	= 0;
	public long onSwitch				= 0;
	public long offSwitch				= 0;
	public long priority				= 50;
	public boolean turnSwitchOn		= false;
	public boolean turnSwitchOff		= false;
	
	/**
	 * Constructs a new LuciferMonster
	 */ 
	public LuciferMonsterAction() { }
	
	/**
	 * Constructs a new LuciferMonster
	 * 
	 * @param bytes byte-Array which represents the LuciferMonsterAction
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterAction(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMonsterAction
	 * 
	 * @param dr DataReader which represents the LuciferMonsterAction
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterAction(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				//0->behavior
				//1->skill
				//2->transform
				subject = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x02:
				//0->normal hit
				//1->critical hit
				//2->defend
				//3->watch state
				//4->accumulate power
				//5->self-annihilation
				//6->escape
				//7->nothing
				//8->by id (if subject!=0)
				behavior = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				skill = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				transform = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				//0->usually
				//1->switch (defined in conditionLowerLimit)
				//2->turn no (defined in conditionLowerLimit and conditionUpperLimit)
				//3->monster quantity (defined in conditionLowerLimit and conditionUpperLimit)
				//4->oneself HP (defined in conditionLowerLimit and conditionUpperLimit)
				//5->oneself MP (defined in conditionLowerLimit and conditionUpperLimit)
				//6->Hero Average level (defined in conditionLowerLimit and conditionUpperLimit)
				//7->hero exhaustion (defined in conditionLowerLimit and conditionUpperLimit)
				condition = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				conditionLowerLimit = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x07:
				conditionUpperLimit = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x09:
				turnSwitchOn = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0A:
				onSwitch = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				turnSwitchOff = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0C:
				offSwitch = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				priority = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMonsterAction! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this MonsterAction
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		return Helper.concatAll(new LuciferBaseUnit(0x01, DataReader.intToRPGint(subject)).write(new byte[0]),
				new LuciferBaseUnit(0x02, DataReader.intToRPGint(behavior)).write(new byte[0]),
				new LuciferBaseUnit(0x03, DataReader.intToRPGint(skill)).write(new byte[0]),
				new LuciferBaseUnit(0x04, DataReader.intToRPGint(transform)).write(new byte[0]),
				new LuciferBaseUnit(0x05, DataReader.intToRPGint(condition)).write(new byte[0]),
				new LuciferBaseUnit(0x06, DataReader.intToRPGint(conditionLowerLimit)).write(new byte[0]),
				new LuciferBaseUnit(0x07, DataReader.intToRPGint(conditionUpperLimit)).write(new byte[0]),
				new LuciferBaseUnit(0x09, DataReader.intToRPGint(turnSwitchOn ? 1 : 0)).write(new byte[0]),
				new LuciferBaseUnit(0x0A, DataReader.intToRPGint(onSwitch)).write(new byte[0]),
				new LuciferBaseUnit(0x0B, DataReader.intToRPGint(turnSwitchOff ? 1 : 0)).write(new byte[0]),
				new LuciferBaseUnit(0x0C, DataReader.intToRPGint(offSwitch)).write(new byte[0]),
				new LuciferBaseUnit(0x0D, DataReader.intToRPGint(priority)).write(new byte[0]),
				new byte[]{0}
				);
	}
}