package engine;

import java.io.IOException;

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
	
	public LuciferMonsterAction(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferMonsterAction(DataReader sr) throws IOException {
		init(sr);
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
			}
			unit = sr.nextUnit();
		}
	}
}