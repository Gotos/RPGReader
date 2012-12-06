package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.util.ArrayList;

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
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (heroes.size() < id) { heroes.add(null); }
					heroes.add((int) id, new LuciferHeroUnit(tmp));
				}
				break;
			case 0x0C:
				size = (int) tmp.nextInt(); //read nr
				skills = new ArrayList<LuciferSkillUnit>(size);
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (skills.size() < id) { skills.add(null); }
					skills.add((int) id, new LuciferSkillUnit(tmp));
				}
				break;
			case 0x0D:
				size = (int) tmp.nextInt(); //read nr
				items = new ArrayList<LuciferItemUnit>(size);
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (items.size() < id) { items.add(null); }
					items.add((int) id, new LuciferItemUnit(tmp));
				}
				break;
			case 0x0E:
				size = (int) tmp.nextInt(); //read nr
				monsters = new ArrayList<LuciferMonsterUnit>(size);
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (monsters.size() < id) { monsters.add(null); }
					monsters.add((int) id, new LuciferMonsterUnit(tmp));
				}
				break;
			case 0x0F:
				size = (int) tmp.nextInt(); //read nr
				monsterParties = new ArrayList<LuciferMonsterParty>(size);
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (monsterParties.size() < id) { monsterParties.add(null); }
					monsterParties.add((int) id, new LuciferMonsterParty(tmp));
				}
			case 0x10:
				size = (int) tmp.nextInt(); //read nr
				terrains = new ArrayList<LuciferTerrainUnit>(size);
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (terrains.size() < id) { terrains.add(null); }
					terrains.add((int) id, new LuciferTerrainUnit(tmp));
				}
				break;
			case 0x11:
				size = (int) tmp.nextInt(); //read nr
				attributes = new ArrayList<LuciferAttributeUnit>(size);
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (attributes.size() < id) { attributes.add(null); }
					attributes.add((int) id, new LuciferAttributeUnit(tmp));
				}
				break;
			case 0x12:
				size = (int) tmp.nextInt(); //read nr
				conditions = new ArrayList<LuciferConditionUnit>(size);
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (conditions.size() < id) { conditions.add(null); }
					conditions.add((int) id, new LuciferConditionUnit(tmp));
				}
			case 0x13:
				size = (int) tmp.nextInt(); //read nr
				animations = new ArrayList<LuciferAnimationUnit>(size);
				for (int i = 1; i < size; i++) {
					int id = (int) tmp.nextInt(); //read id of hero
					while (animations.size() < id) { animations.add(null); }
					animations.add((int) id, new LuciferAnimationUnit(tmp));
				}
				break;
			case 0x14:
				size = (int) tmp.nextInt(); //read nr
				chipsets = new ArrayList<LuciferChipsetData>(size);
				for (int i = 1; i < size; i++) {
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
				for (int i = 1; i < size; i++) {
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
				for (int i = 1; i < size; i++) {
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
				for (int i = 1; i < size; i++) {
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
}