package engine;

import java.io.IOException;

public class LuciferMonsterPartyMonster {
	
	public long id		= 1;
	public long xPos	= 0;
	public long yPos	= 0;
	
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
			}
			unit = sr.nextUnit();
		}
	}	
}