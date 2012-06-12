package engine;

import java.io.IOException;

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
	
	
	public LuciferMapEventPageCondition(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferMapEventPageCondition(DataReader sr) throws IOException {
		init(sr);
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferMapEventPageCondition)) {
	        return false; // different class
	     }
	     
	     LuciferMapEventPageCondition o = (LuciferMapEventPageCondition) obj;
	     
	     return switchA == o.switchA
	     		&& switchB == o.switchB
	     		&& variable == o.variable
	     		&& item == o.item
	     		&& hero == o.hero
	     		&& timer == o.timer
	     		&& switchAID == o.switchAID
	     		&& switchBID == o.switchBID
	     		&& variableID == o.variableID
	     		&& variableValue == o.variableValue
	     		&& itemID == o.itemID
	     		&& heroID == o.heroID
	     		&& timeRemaining == o.timeRemaining;
	}
}