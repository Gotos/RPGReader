package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author alina
 * 
 * This class represents the Database of the RPG-Maker-Game.
 * 
 */
public class LuciferDatabase {
	
	public ArrayList<LuciferHeroUnit> heroes;
	public ArrayList<LuciferSkillUnit> skills;
	public ArrayList<LuciferItemUnit> items;
	public ArrayList<LuciferMonsterUnit> monsters;
	public ArrayList<LuciferMonsterParty> monsterParties;
	public ArrayList<LuciferTerrainUnit> terrains;
	public ArrayList<LuciferAttributeUnit> attributes;
	public ArrayList<LuciferConditionUnit> conditions;
	public ArrayList<LuciferAnimationUnit> animations;
	public ArrayList<LuciferChipsetData> chipsets;
	public LuciferSystemData system;
	public ArrayList<String> switchNames;
	public ArrayList<String> variableNames;
	public ArrayList<LuciferCommonEvent> commonEvents;
	public byte[] vocab;
	
	public LuciferDatabase(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferDatabase(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			DataReader tmp = new DataReader(unit.content);
			int size;
			switch(unit.id) {
			case 0x0B:
				size = (int) tmp.nextInt(); //read nr
				heroes = new ArrayList<LuciferHeroUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (heroes.size() < id) { heroes.add(null); }
					heroes.add((int) id, new LuciferHeroUnit(tmp));
				}
				break;
			case 0x0C:
				size = (int) tmp.nextInt(); //read nr
				skills = new ArrayList<LuciferSkillUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (skills.size() < id) { skills.add(null); }
					skills.add((int) id, new LuciferSkillUnit(tmp));
				}
				break;
			case 0x0D:
				size = (int) tmp.nextInt(); //read nr
				items = new ArrayList<LuciferItemUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (items.size() < id) { items.add(null); }
					items.add((int) id, new LuciferItemUnit(tmp));
				}
				break;
			case 0x0E:
				size = (int) tmp.nextInt(); //read nr
				monsters = new ArrayList<LuciferMonsterUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (monsters.size() < id) { monsters.add(null); }
					monsters.add((int) id, new LuciferMonsterUnit(tmp));
				}
				break;
			case 0x0F:
				size = (int) tmp.nextInt(); //read nr
				monsterParties = new ArrayList<LuciferMonsterParty>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (monsterParties.size() < id) { monsterParties.add(null); }
					monsterParties.add((int) id, new LuciferMonsterParty(tmp));
				}
				break;
			case 0x10:
				size = (int) tmp.nextInt(); //read nr
				terrains = new ArrayList<LuciferTerrainUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (terrains.size() < id) { terrains.add(null); }
					terrains.add((int) id, new LuciferTerrainUnit(tmp));
				}
				break;
			case 0x11:
				size = (int) tmp.nextInt(); //read nr
				attributes = new ArrayList<LuciferAttributeUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (attributes.size() < id) { attributes.add(null); }
					attributes.add((int) id, new LuciferAttributeUnit(tmp));
				}
				break;
			case 0x12:
				size = (int) tmp.nextInt(); //read nr
				conditions = new ArrayList<LuciferConditionUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (conditions.size() < id) { conditions.add(null); }
					conditions.add((int) id, new LuciferConditionUnit(tmp));
				}
				break;
			case 0x13:
				size = (int) tmp.nextInt(); //read nr
				animations = new ArrayList<LuciferAnimationUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (animations.size() < id) { animations.add(null); }
					animations.add((int) id, new LuciferAnimationUnit(tmp));
				}
				break;
			case 0x14:
				size = (int) tmp.nextInt(); //read nr
				chipsets = new ArrayList<LuciferChipsetData>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (chipsets.size() < id) { chipsets.add(null); }
					chipsets.add((int) id, new LuciferChipsetData(tmp));
				}
				break;
			case 0x15:
				//TODO: parse vocab
				vocab = unit.content;
				break;
			case 0x16:
				system = new LuciferSystemData(tmp);
				break;
			case 0x17:
				size = (int) tmp.nextInt() + 1;
				switchNames = new ArrayList<String>(size);
				for (int i = 0; i < size; i++) {
					i = (int) tmp.nextInt(); //read id of switch
					if (i >= size) { break; }
					LuciferBaseUnit nameUnit = tmp.nextUnit();
					while (switchNames.size() < i) { switchNames.add(null); }
					switchNames.add(i, new String(nameUnit.content, Encoder.ENCODING));
					if (nameUnit.id != 0) {
						tmp.nextInt(); //read 0x00
					}
				}
				break;
			case 0x18:
				size = (int) tmp.nextInt() + 1;
				variableNames = new ArrayList<String>(size);
				for (int i = 0; i < size; i++) {
					i = (int) tmp.nextInt(); //read id of switch
					if (i >= size) { break; }
					LuciferBaseUnit nameUnit = tmp.nextUnit();
					while (variableNames.size() < i) { variableNames.add(null); }
					variableNames.add(i, new String(nameUnit.content, Encoder.ENCODING));
					if (nameUnit.id != 0) {
						tmp.nextInt(); //read 0x00
					}
				}
				break;
			case 0x19:
				size = (int) tmp.nextInt(); //read nr
				commonEvents = new ArrayList<LuciferCommonEvent>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (commonEvents.size() < id) { commonEvents.add(null); }
					commonEvents.add((int) id, new LuciferCommonEvent(tmp));
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferDatabase! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + ((animations == null) ? 0
						: animations.hashCode());
		result = prime
				* result + ((attributes == null) ? 0
						: attributes.hashCode());
		result = prime
				* result + ((chipsets == null) ? 0
						: chipsets.hashCode());
		result = prime
				* result + ((commonEvents == null) ? 0
						: commonEvents.hashCode());
		result = prime
				* result + ((conditions == null) ? 0
						: conditions.hashCode());
		result = prime
				* result + ((heroes == null) ? 0
						: heroes.hashCode());
		result = prime
				* result + ((items == null) ? 0
						: items.hashCode());
		result = prime
				* result + ((monsterParties == null) ? 0
						: monsterParties.hashCode());
		result = prime
				* result + ((monsters == null) ? 0
						: monsters.hashCode());
		result = prime
				* result + ((skills == null) ? 0
						: skills.hashCode());
		result = prime
				* result + ((switchNames == null) ? 0
						: switchNames.hashCode());
		result = prime
				* result + ((system == null) ? 0
						: system.hashCode());
		result = prime
				* result + ((terrains == null) ? 0
						: terrains.hashCode());
		result = prime
				* result + ((variableNames == null) ? 0
						: variableNames.hashCode());
		result = prime
				* result + Arrays.hashCode(vocab);
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
		if (!(obj instanceof LuciferDatabase)) {
			return false;
		}
		LuciferDatabase other = (LuciferDatabase) obj;
		if (animations == null) {
			if (other.animations != null) {
				return false;
			}
		} else if (!animations.equals(other.animations)) {
			return false;
		}
		if (attributes == null) {
			if (other.attributes != null) {
				return false;
			}
		} else if (!attributes.equals(other.attributes)) {
			return false;
		}
		if (chipsets == null) {
			if (other.chipsets != null) {
				return false;
			}
		} else if (!chipsets.equals(other.chipsets)) {
			return false;
		}
		if (commonEvents == null) {
			if (other.commonEvents != null) {
				return false;
			}
		} else if (!commonEvents.equals(other.commonEvents)) {
			return false;
		}
		if (conditions == null) {
			if (other.conditions != null) {
				return false;
			}
		} else if (!conditions.equals(other.conditions)) {
			return false;
		}
		if (heroes == null) {
			if (other.heroes != null) {
				return false;
			}
		} else if (!heroes.equals(other.heroes)) {
			return false;
		}
		if (items == null) {
			if (other.items != null) {
				return false;
			}
		} else if (!items.equals(other.items)) {
			return false;
		}
		if (monsterParties == null) {
			if (other.monsterParties != null) {
				return false;
			}
		} else if (!monsterParties.equals(other.monsterParties)) {
			return false;
		}
		if (monsters == null) {
			if (other.monsters != null) {
				return false;
			}
		} else if (!monsters.equals(other.monsters)) {
			return false;
		}
		if (skills == null) {
			if (other.skills != null) {
				return false;
			}
		} else if (!skills.equals(other.skills)) {
			return false;
		}
		if (switchNames == null) {
			if (other.switchNames != null) {
				return false;
			}
		} else if (!switchNames.equals(other.switchNames)) {
			return false;
		}
		if (system == null) {
			if (other.system != null) {
				return false;
			}
		} else if (!system.equals(other.system)) {
			return false;
		}
		if (terrains == null) {
			if (other.terrains != null) {
				return false;
			}
		} else if (!terrains.equals(other.terrains)) {
			return false;
		}
		if (variableNames == null) {
			if (other.variableNames != null) {
				return false;
			}
		} else if (!variableNames.equals(other.variableNames)) {
			return false;
		}
		if (!Arrays.equals(
				vocab, other.vocab)) {
			return false;
		}
		return true;
	}
}