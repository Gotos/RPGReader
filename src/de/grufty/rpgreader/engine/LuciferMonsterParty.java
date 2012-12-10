package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a MonsterParty of the RPG-Maker-Game.
 */
public class LuciferMonsterParty implements UnitInterface {
	
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

	/**
	 * Returns the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name the new name
	 */
	public void setName(
			String name) {
		this.name = name;
	}

	/**
	 * Returns the nrTerrains
	 * 
	 * @return the nrTerrains
	 */
	public long getNrTerrains() {
		return nrTerrains;
	}

	/**
	 * Sets the nrTerrains
	 * 
	 * @param nrTerrains the new nrTerrains
	 */
	public void setNrTerrains(
			long nrTerrains) {
		this.nrTerrains = nrTerrains;
	}

	/**
	 * Returns the appearIn
	 * 
	 * @return the appearIn
	 */
	public ArrayList<Boolean> getAppearIn() {
		return appearIn;
	}

	/**
	 * Sets the appearIn
	 * 
	 * @param appearIn the new appearIn
	 */
	public void setAppearIn(
			ArrayList<Boolean> appearIn) {
		this.appearIn = appearIn;
	}

	/**
	 * Returns the monsters
	 * 
	 * @return the monsters
	 */
	public ArrayList<LuciferMonsterPartyMonster> getMonsters() {
		return monsters;
	}

	/**
	 * Sets the monsters
	 * 
	 * @param monsters the new monsters
	 */
	public void setMonsters(
			ArrayList<LuciferMonsterPartyMonster> monsters) {
		this.monsters = monsters;
	}

	/**
	 * Returns the battleEventPages
	 * 
	 * @return the battleEventPages
	 */
	public ArrayList<LuciferBattleEventPage> getBattleEventPages() {
		return battleEventPages;
	}

	/**
	 * Sets the battleEventPages
	 * 
	 * @param battleEventPages the new battleEventPages
	 */
	public void setBattleEventPages(
			ArrayList<LuciferBattleEventPage> battleEventPages) {
		this.battleEventPages = battleEventPages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + ((appearIn == null) ? 0
						: appearIn.hashCode());
		result = prime
				* result + ((battleEventPages == null) ? 0
						: battleEventPages.hashCode());
		result = prime
				* result + ((monsters == null) ? 0
						: monsters.hashCode());
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + (int) (nrTerrains ^ (nrTerrains >>> 32));
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
		if (!(obj instanceof LuciferMonsterParty)) {
			return false;
		}
		LuciferMonsterParty other = (LuciferMonsterParty) obj;
		if (appearIn == null) {
			if (other.appearIn != null) {
				return false;
			}
		} else if (!appearIn.equals(other.appearIn)) {
			return false;
		}
		if (battleEventPages == null) {
			if (other.battleEventPages != null) {
				return false;
			}
		} else if (!battleEventPages.equals(other.battleEventPages)) {
			return false;
		}
		if (monsters == null) {
			if (other.monsters != null) {
				return false;
			}
		} else if (!monsters.equals(other.monsters)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (nrTerrains != other.nrTerrains) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the byte-representation of this MonsterParty
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		byte[] appearInArray = new byte[0];
		for (int i = 1; i <= appearIn.size(); i++) {
			if (appearIn.get(i) != null) {
				appearInArray = Helper.concatAll(appearInArray, DataReader.intToRPGint(i), DataReader.intToRPGint(appearIn.get(i) ? 1 : 0));
			}
		}
		byte[] monsterArray = new byte[0];
		for (int i = 1; i <= monsters.size(); i++) {
			if (monsters.get(i) != null) {
				monsterArray = Helper.concatAll(monsterArray, DataReader.intToRPGint(i), monsters.get(i).write());
			}
		}
		byte[] eventArray = new byte[0];
		for (int i = 1; i <= battleEventPages.size(); i++) {
			if (battleEventPages.get(i) != null) {
				eventArray = Helper.concatAll(eventArray, DataReader.intToRPGint(i), battleEventPages.get(i).write());
			}
		}
		try {
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(),
					new LuciferBaseUnit(0x02, monsterArray).write(new byte[0]),
					new LuciferBaseUnit(0x04, DataReader.intToRPGint(nrTerrains)).write(new byte[0]),
					new LuciferBaseUnit(0x05, appearInArray).write(new byte[0]),
					new LuciferBaseUnit(0x0B, eventArray).write(new byte[0]),
					new byte[]{0}
					);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}