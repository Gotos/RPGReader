package engine;

import java.io.IOException;

public class LuciferHeroSpell {
	public long level	= 1;
	public long spell	= 1;
	
	public LuciferHeroSpell(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				level = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x02:
				spell = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferHeroSpell! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
}