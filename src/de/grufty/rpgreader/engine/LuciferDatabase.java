package de.grufty.rpgreader.engine;

import java.io.IOException;

public class LuciferDatabase {
	
	public LuciferHeroUnit[] heroes;
	public LuciferSkillUnit[] skills;
	public LuciferItemUnit[] items;
	public LuciferMonsterUnit[] monsters;
	public LuciferMonsterParty[] monsterParties;
	public LuciferTerrainUnit[] terrains;
	public LuciferAttributeUnit[] attributes;
	public LuciferConditionUnit[] conditions;
	public LuciferAnimationUnit[] animations;
	public LuciferChipsetData[] chipsets;
	public LuciferSystemData system;
	public String[] switchNames;
	public String[] variableNames;
	public LuciferCommonEvent[] commonEvents;
	
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
			switch(unit.id) {
			case 0x0B:
				heroes = new LuciferHeroUnit[(int) tmp.nextInt() + 1];
				for (int i = 1; i < heroes.length; i++) {
					tmp.nextInt(); //read id of hero
					heroes[i] = new LuciferHeroUnit(tmp);
				}
				break;
			case 0x0C:
				skills = new LuciferSkillUnit[(int) tmp.nextInt() + 1];
				for (int i = 1; i < skills.length; i++) {
					tmp.nextInt(); //read id of skill
					skills[i] = new LuciferSkillUnit(tmp);
				}
				break;
			case 0x0D:
				items = new LuciferItemUnit[(int) tmp.nextInt() + 1];
				for (int i = 1; i < items.length; i++) {
					tmp.nextInt(); //read id of item
					items[i] = new LuciferItemUnit(tmp);
				}
				break;
			case 0x0E:
				monsters = new LuciferMonsterUnit[(int) tmp.nextInt() + 1];
				for (int i = 1; i < monsters.length; i++) {
					tmp.nextInt(); //read id of monster
					monsters[i] = new LuciferMonsterUnit(tmp);
				}
				break;
			case 0x0F:
				monsterParties = new LuciferMonsterParty[(int) tmp.nextInt() + 1];
				for (int i = 1; i < monsterParties.length; i++) {
					i = (int) tmp.nextInt(); //read id of monsterparty
					monsterParties[i] = new LuciferMonsterParty(tmp);
				}
				break;
			case 0x10:
				terrains = new LuciferTerrainUnit[(int) tmp.nextInt() + 1];
				for (int i = 1; i < terrains.length; i++) {
					i = (int) tmp.nextInt(); //read id of terrain
					terrains[i] = new LuciferTerrainUnit(tmp);
				}
				break;
			case 0x11:
				attributes = new LuciferAttributeUnit[(int) tmp.nextInt() + 1];
				for (int i = 1; i < attributes.length; i++) {
					i = (int) tmp.nextInt(); //read id of attribute
					attributes[i] = new LuciferAttributeUnit(tmp);
				}
				break;
			case 0x12:
				conditions = new LuciferConditionUnit[(int) tmp.nextInt() + 1];
				for (int i = 1; i < conditions.length; i++) {
					i = (int) tmp.nextInt(); //read id of attribute
					conditions[i] = new LuciferConditionUnit(tmp);
				}
				break;
			case 0x13:
				//TODO: TEST THIS, BITCH!
				animations = new LuciferAnimationUnit[(int) tmp.nextInt() + 1];
				for (int i = 1; i < animations.length; i++) {
					i = (int) tmp.nextInt(); //read id of animation
					animations[i] = new LuciferAnimationUnit(tmp);
				}
				break;
			case 0x14:
				chipsets = new LuciferChipsetData[(int) tmp.nextInt() + 1];
				for (int i = 1; i < chipsets.length; i++) {
					i = (int) tmp.nextInt(); //read id of animation
					chipsets[i] = new LuciferChipsetData(tmp);
				}
				break;
			case 0x16:
				system = new LuciferSystemData(tmp);
				break;
			case 0x17:
				switchNames = new String[(int) tmp.nextInt() + 1];
				for (int i = 1; i < switchNames.length; i++) {
					i = (int) tmp.nextInt(); //read id of switch
					if (i >= switchNames.length) { break; }
					LuciferBaseUnit nameUnit = tmp.nextUnit();
					switchNames[i] = new String(nameUnit.content, Encoder.ENCODING);
					if (nameUnit.id != 0) {
						tmp.nextInt(); //read 0x00
					}
				}
				break;
			case 0x18:
				variableNames = new String[(int) tmp.nextInt() + 1];
				for (int i = 1; i < variableNames.length; i++) {
					i = (int) tmp.nextInt(); //read id of switch
					if (i >= variableNames.length) { break; }
					LuciferBaseUnit nameUnit = tmp.nextUnit();
					variableNames[i] = new String(nameUnit.content, Encoder.ENCODING);
					if (nameUnit.id != 0) {
						tmp.nextInt(); //read 0x00
					}
				}
				break;
			case 0x19:
				commonEvents = new LuciferCommonEvent[(int) tmp.nextInt() + 1];
				for (int i = 1; i < commonEvents.length; i++) {
					i = (int) tmp.nextInt(); //read id of event
					commonEvents[i] = new LuciferCommonEvent(tmp);
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferDatabase! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
}