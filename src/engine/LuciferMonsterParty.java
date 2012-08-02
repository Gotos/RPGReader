package engine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a MonsterParty of the RPG-Maker-Game.
 */
public class LuciferMonsterParty {
	
	private String name									= "";
	private long nrTerrains								= 0;
	private ArrayList<Boolean> appearIn;
	private ArrayList<LuciferMonsterPartyMonster> monsters;
	private ArrayList<LuciferBattleEventPage> battleEventPages;
	
	/**
	 * Constructs a new LuciferMonsterParty
	 */ 
	public LuciferMonsterParty() { }
	
	/**
	 * Constructs a new LuciferMonsterParty
	 * 
	 * @param bytes byte-Array which represents the LuciferMonsterParty
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterParty(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMonsterParty
	 * 
	 * @param dr DataReader which represents the LuciferMonsterParty
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterParty(DataReader dr) throws IOException {
		init(dr);
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
				monsters = new ArrayList<LuciferMonsterPartyMonster>((int) tmp.nextInt() + 1);
				for (int i = 1; i < monsters.size(); i++) {
					tmp.nextInt(); //read id
					monsters.add(new LuciferMonsterPartyMonster(tmp));
				}
				break;
			case 0x04:
				nrTerrains = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				tmp = new DataReader(unit.content);
				appearIn = new ArrayList<Boolean>((int) nrTerrains + 1);
				for (int i = 1; i <= nrTerrains; i++) {
					appearIn.add(tmp.nextInt() == 1);
				}
				break;
			case 0x0B:
				tmp = new DataReader(unit.content);
				battleEventPages = new ArrayList<LuciferBattleEventPage>((int) tmp.nextInt() + 1);
				for (int i = 1; i < battleEventPages.size(); i++) {
					tmp.nextInt(); //read id
					battleEventPages.add(new LuciferBattleEventPage(tmp));
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMonsterParty! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
}