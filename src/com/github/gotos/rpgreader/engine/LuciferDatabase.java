package com.github.gotos.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author alina
 * 
 * This class represents the Database of the RPG-Maker-Game.
 * 
 */
public class LuciferDatabase implements UnitInterface {
	
	private ArrayList<LuciferHeroUnit> heroes;
	private ArrayList<LuciferSkillUnit> skills;
	private ArrayList<LuciferItemUnit> items;
	private ArrayList<LuciferMonsterUnit> monsters;
	private ArrayList<LuciferMonsterParty> monsterParties;
	private ArrayList<LuciferTerrainUnit> terrains;
	private ArrayList<LuciferAttributeUnit> attributes;
	private ArrayList<LuciferConditionUnit> conditions;
	private ArrayList<LuciferAnimationUnit> animations;
	private ArrayList<LuciferChipsetData> chipsets;
	private LuciferSystemData system;
	private ArrayList<String> switchNames;
	private ArrayList<String> variableNames;
	private ArrayList<LuciferCommonEvent> commonEvents;
	private byte[] vocab;
	
	/**
	 * Returns the heroes
	 * 
	 * @return the heroes
	 */
	public ArrayList<LuciferHeroUnit> getHeroes() {
		return heroes;
	}

	/**
	 * Sets the heroes
	 * 
	 * @param heroes the new heroes
	 */
	public void setHeroes(
			ArrayList<LuciferHeroUnit> heroes) {
		this.heroes = heroes;
	}

	/**
	 * Returns the skills
	 * 
	 * @return the skills
	 */
	public ArrayList<LuciferSkillUnit> getSkills() {
		return skills;
	}

	/**
	 * Sets the skills
	 * 
	 * @param skills the new skills
	 */
	public void setSkills(
			ArrayList<LuciferSkillUnit> skills) {
		this.skills = skills;
	}

	/**
	 * Returns the items
	 * 
	 * @return the items
	 */
	public ArrayList<LuciferItemUnit> getItems() {
		return items;
	}

	/**
	 * Sets the items
	 * 
	 * @param items the new items
	 */
	public void setItems(
			ArrayList<LuciferItemUnit> items) {
		this.items = items;
	}

	/**
	 * Returns the monsters
	 * 
	 * @return the monsters
	 */
	public ArrayList<LuciferMonsterUnit> getMonsters() {
		return monsters;
	}

	/**
	 * Sets the monsters
	 * 
	 * @param monsters the new monsters
	 */
	public void setMonsters(
			ArrayList<LuciferMonsterUnit> monsters) {
		this.monsters = monsters;
	}

	/**
	 * Returns the monsterParties
	 * 
	 * @return the monsterParties
	 */
	public ArrayList<LuciferMonsterParty> getMonsterParties() {
		return monsterParties;
	}

	/**
	 * Sets the monsterParties
	 * 
	 * @param monsterParties the new monsterParties
	 */
	public void setMonsterParties(
			ArrayList<LuciferMonsterParty> monsterParties) {
		this.monsterParties = monsterParties;
	}

	/**
	 * Returns the terrains
	 * 
	 * @return the terrains
	 */
	public ArrayList<LuciferTerrainUnit> getTerrains() {
		return terrains;
	}

	/**
	 * Sets the terrains
	 * 
	 * @param terrains the new terrains
	 */
	public void setTerrains(
			ArrayList<LuciferTerrainUnit> terrains) {
		this.terrains = terrains;
	}

	/**
	 * Returns the attributes
	 * 
	 * @return the attributes
	 */
	public ArrayList<LuciferAttributeUnit> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes
	 * 
	 * @param attributes the new attributes
	 */
	public void setAttributes(
			ArrayList<LuciferAttributeUnit> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Returns the conditions
	 * 
	 * @return the conditions
	 */
	public ArrayList<LuciferConditionUnit> getConditions() {
		return conditions;
	}

	/**
	 * Sets the conditions
	 * 
	 * @param conditions the new conditions
	 */
	public void setConditions(
			ArrayList<LuciferConditionUnit> conditions) {
		this.conditions = conditions;
	}

	/**
	 * Returns the animations
	 * 
	 * @return the animations
	 */
	public ArrayList<LuciferAnimationUnit> getAnimations() {
		return animations;
	}

	/**
	 * Sets the animations
	 * 
	 * @param animations the new animations
	 */
	public void setAnimations(
			ArrayList<LuciferAnimationUnit> animations) {
		this.animations = animations;
	}

	/**
	 * Returns the chipsets
	 * 
	 * @return the chipsets
	 */
	public ArrayList<LuciferChipsetData> getChipsets() {
		return chipsets;
	}

	/**
	 * Sets the chipsets
	 * 
	 * @param chipsets the new chipsets
	 */
	public void setChipsets(
			ArrayList<LuciferChipsetData> chipsets) {
		this.chipsets = chipsets;
	}

	/**
	 * Returns the system
	 * 
	 * @return the system
	 */
	public LuciferSystemData getSystem() {
		return system;
	}

	/**
	 * Sets the system
	 * 
	 * @param system the new system
	 */
	public void setSystem(
			LuciferSystemData system) {
		this.system = system;
	}

	/**
	 * Returns the switchNames
	 * 
	 * @return the switchNames
	 */
	public ArrayList<String> getSwitchNames() {
		return switchNames;
	}

	/**
	 * Sets the switchNames
	 * 
	 * @param switchNames the new switchNames
	 */
	public void setSwitchNames(
			ArrayList<String> switchNames) {
		this.switchNames = switchNames;
	}

	/**
	 * Returns the variableNames
	 * 
	 * @return the variableNames
	 */
	public ArrayList<String> getVariableNames() {
		return variableNames;
	}

	/**
	 * Sets the variableNames
	 * 
	 * @param variableNames the new variableNames
	 */
	public void setVariableNames(
			ArrayList<String> variableNames) {
		this.variableNames = variableNames;
	}

	/**
	 * Returns the commonEvents
	 * 
	 * @return the commonEvents
	 */
	public ArrayList<LuciferCommonEvent> getCommonEvents() {
		return commonEvents;
	}

	/**
	 * Sets the commonEvents
	 * 
	 * @param commonEvents the new commonEvents
	 */
	public void setCommonEvents(
			ArrayList<LuciferCommonEvent> commonEvents) {
		this.commonEvents = commonEvents;
	}

	/**
	 * Returns the vocab
	 * 
	 * @return the vocab
	 */
	public byte[] getVocab() {
		return vocab;
	}

	/**
	 * Sets the vocab
	 * 
	 * @param vocab the new vocab
	 */
	public void setVocab(
			byte[] vocab) {
		this.vocab = vocab;
	}

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
				System.out.println("Skills done");
				System.out.flush();
				break;
			case 0x0D:
				size = (int) tmp.nextInt(); //read nr
				items = new ArrayList<LuciferItemUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (items.size() < id) { items.add(null); }
					items.add((int) id, new LuciferItemUnit(tmp));
				}
				System.out.println("Items done");
				System.out.flush();
				break;
			case 0x0E:
				size = (int) tmp.nextInt(); //read nr
				monsters = new ArrayList<LuciferMonsterUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (monsters.size() < id) { monsters.add(null); }
					monsters.add((int) id, new LuciferMonsterUnit(tmp));
				}

				System.out.println("Monsters done");
				System.out.flush();
				break;
			case 0x0F:
				size = (int) tmp.nextInt(); //read nr
				monsterParties = new ArrayList<LuciferMonsterParty>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (monsterParties.size() < id) { monsterParties.add(null); }
					monsterParties.add((int) id, new LuciferMonsterParty(tmp));
				}

				System.out.println("MParties done");
				System.out.flush();
				break;
			case 0x10:
				size = (int) tmp.nextInt(); //read nr
				terrains = new ArrayList<LuciferTerrainUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (terrains.size() < id) { terrains.add(null); }
					terrains.add((int) id, new LuciferTerrainUnit(tmp));
				}
				System.out.println("Terrain done");
				System.out.flush();
				break;
			case 0x11:
				size = (int) tmp.nextInt(); //read nr
				attributes = new ArrayList<LuciferAttributeUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (attributes.size() < id) { attributes.add(null); }
					attributes.add((int) id, new LuciferAttributeUnit(tmp));
				}
				System.out.println("Attr done");
				System.out.flush();
				break;
			case 0x12:
				size = (int) tmp.nextInt(); //read nr
				conditions = new ArrayList<LuciferConditionUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (conditions.size() < id) { conditions.add(null); }
					conditions.add((int) id, new LuciferConditionUnit(tmp));
				}
				System.out.println("Cond done");
				System.out.flush();
				break;
			case 0x13:
				size = (int) tmp.nextInt(); //read nr
				animations = new ArrayList<LuciferAnimationUnit>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (animations.size() < id) { animations.add(null); }
					animations.add((int) id, new LuciferAnimationUnit(tmp));
				}
				System.out.println("Ani done");
				System.out.flush();
				break;
			case 0x14:
				size = (int) tmp.nextInt(); //read nr
				chipsets = new ArrayList<LuciferChipsetData>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (chipsets.size() < id) { chipsets.add(null); }
					chipsets.add((int) id, new LuciferChipsetData(tmp));
				}
				System.out.println("Chip done");
				System.out.flush();
				break;
			case 0x15:
				//TODO: parse vocab
				vocab = unit.content;

				System.out.println("Vocab done");
				System.out.flush();
				break;
			case 0x16:
				system = new LuciferSystemData(tmp);
				System.out.println("system done");
				System.out.flush();
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
				System.out.println("switch done");
				System.out.flush();
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
				System.out.println("vari done");
				System.out.flush();
				break;
			case 0x19:
				size = (int) tmp.nextInt(); //read nr
				commonEvents = new ArrayList<LuciferCommonEvent>(size);
				for (int i = 0; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (commonEvents.size() < id) { commonEvents.add(null); }
					commonEvents.add((int) id, new LuciferCommonEvent(tmp));
				}
				System.out.println("CE done");
				System.out.flush();
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferDatabase! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this Database
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			byte[] switchesArray = new byte[0];
			for (int i = 1; i < switchNames.size(); i++) {
				if (switchNames.get(i) != null) {
					switchesArray = Helper.concatAll(switchesArray,
							DataReader.intToRPGint(i),
							(switchNames.get(i) != null)
								? new LuciferBaseUnit(1, switchNames.get(i).getBytes(Encoder.ENCODING)).write() : new byte[0],
							new byte[]{0});
				}
			}
			switchesArray = Helper.concatAll(DataReader.intToRPGint(switchNames.size()), switchesArray);
			byte[] variArray = new byte[0];
			for (int i = 1; i < variableNames.size(); i++) {
				if (variableNames.get(i) != null) {
					variArray = Helper.concatAll(variArray,
							DataReader.intToRPGint(i),
							(variableNames.get(i) != null)
								? new LuciferBaseUnit(1, variableNames.get(i).getBytes(Encoder.ENCODING)).write() : new byte[0],
							new byte[]{0});
				}
			}
			variArray = Helper.concatAll(DataReader.intToRPGint(variableNames.size()), variArray);
			return Helper.concatAll(new LuciferBaseUnit(0x01, "LcfDatabase".getBytes(Encoder.ENCODING)).write(false),
					new LuciferBaseUnit(0x0B, Helper.listToBytes(heroes)).write(new byte[0]),
					new LuciferBaseUnit(0x0C, Helper.listToBytes(skills)).write(new byte[0]),
					new LuciferBaseUnit(0x0D, Helper.listToBytes(items)).write(new byte[0]),
					new LuciferBaseUnit(0x0E, Helper.listToBytes(monsters)).write(new byte[0]),
					new LuciferBaseUnit(0x0F, Helper.listToBytes(monsterParties)).write(new byte[0]),
					new LuciferBaseUnit(0x10, Helper.listToBytes(terrains)).write(new byte[0]),
					new LuciferBaseUnit(0x11, Helper.listToBytes(attributes)).write(new byte[0]),
					new LuciferBaseUnit(0x12, Helper.listToBytes(conditions)).write(new byte[0]),
					new LuciferBaseUnit(0x13, Helper.listToBytes(animations)).write(new byte[0]),
					new LuciferBaseUnit(0x14, Helper.listToBytes(chipsets)).write(new byte[0]),
					new LuciferBaseUnit(0x16, system.write()).write(new byte[0]),
					new LuciferBaseUnit(0x17, switchesArray).write(new byte[0]),
					new LuciferBaseUnit(0x18, variArray).write(new byte[0]),
					new LuciferBaseUnit(0x19, Helper.listToBytes(commonEvents)).write(new byte[0]),
					new byte[]{0}
					);
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
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