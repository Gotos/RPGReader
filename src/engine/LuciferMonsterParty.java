package engine;

import java.io.IOException;

public class LuciferMonsterParty {
	
	public String name									= "";
	public long nrTerrains								= 0;
	public boolean[] appearIn;
	public LuciferMonsterPartyMonster[] monsters;
	public LuciferBattleEventPage[] battleEventPages;
	
	public LuciferMonsterParty(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferMonsterParty(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		DataReader tmp;
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				tmp = new DataReader(unit.content);
				monsters = new LuciferMonsterPartyMonster[(int) tmp.nextInt() + 1];
				for (int i = 1; i < monsters.length; i++) {
					tmp.nextInt(); //read id
					monsters[i] = new LuciferMonsterPartyMonster(tmp);
				}
				break;
			case 0x04:
				nrTerrains = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				tmp = new DataReader(unit.content);
				appearIn = new boolean[(int) nrTerrains + 1];
				for (int i = 1; i <= nrTerrains; i++) {
					appearIn[i] = (tmp.nextInt() == 1);
				}
				break;
			case 0x0B:
				tmp = new DataReader(unit.content);
				battleEventPages = new LuciferBattleEventPage[(int) tmp.nextInt() + 1];
				for (int i = 1; i < battleEventPages.length; i++) {
					tmp.nextInt(); //read id
					battleEventPages[i] = new LuciferBattleEventPage(tmp);
				}
				break;
			}
			unit = sr.nextUnit();
		}
	}
}