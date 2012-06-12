package engine;

import java.io.IOException;

public class LuciferSkillUnit {
	
	public String name					= "";
	public String explanation			= "";
	public String usingMessage			= "";
	public String usingMessage2			= "";
	public String failureMessage		= "";
	public long classification			= 0;
	public long mpCost					= 0;
	public long effectRange			= 0;
	public long onSwitch				= 1;
	public long battleAnimation		= 1;
	public long hitChance				= 0; //Strength effect
	public long mindChance				= 3; //Mind effect
	public long variance				= 4;
	public long baseEffect				= 0;
	public long baseSuccessRate		= 100;
	public long nrAttributes			= 0;
	public long nrConditions			= 0;
	public boolean[] conditions;
	public boolean[] attributes;
	public boolean availableAtField	= true;
	public boolean availableAtCombat	= false;
	public boolean abilityDownHP		= false;
	public boolean abilityDownMP		= false;
	public boolean abilityDownAttack	= false;
	public boolean abilityDownDefence	= false;
	public boolean abilityDownMind		= false;
	public boolean abilityDownAgility	= false;
	public boolean absorption			= false;
	public boolean defenceIgnore		= false;
	public boolean defenceDown			= false;
	public LuciferSoundUnit soundEffect;


	
	public LuciferSkillUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferSkillUnit(DataReader sr) throws IOException {
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
				explanation = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x03:
				usingMessage = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x04:
				usingMessage2 = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x07:
				failureMessage = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x08:
				//0->norm
				//1->Teleport
				//2->Escape
				//3->Switch
				classification = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				mpCost = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				//0->Single Enemy
				//1->all Enemies
				//2->User
				//3->Single Ally
				//4->Whole Party
				effectRange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				onSwitch = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0E:
				battleAnimation = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x10:
				soundEffect = new LuciferSoundUnit(new DataReader(unit.content));
				break;
			case 0x12:
				availableAtField = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x13:
				availableAtCombat = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x15:
				hitChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x16:
				mindChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x17:
				variance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x18:
				baseEffect = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x19:
				baseSuccessRate = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x1F:
				abilityDownHP = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x20:
				abilityDownMP = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x21:
				abilityDownAttack = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x22:
				abilityDownDefence = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x23:
				abilityDownMind = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x24:
				abilityDownAgility = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x25:
				absorption = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x26:
				defenceIgnore = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x29:
				nrConditions = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2A:
				tmp = new DataReader(unit.content);
				conditions = new boolean[(int) nrConditions + 1];
				for (int i = 1; i <= nrConditions; i++) {
					conditions[i] = (tmp.nextInt() == 1);
				}
				break;
			case 0x2B:
				nrAttributes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2C:
				tmp = new DataReader(unit.content);
				attributes = new boolean[(int) nrAttributes + 1];
				for (int i = 1; i <= nrAttributes; i++) {
					attributes[i] = (tmp.nextInt() == 1);
				}
				break;
			case 0x2D:
				defenceDown = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			}
			unit = sr.nextUnit();
		}
	}
}