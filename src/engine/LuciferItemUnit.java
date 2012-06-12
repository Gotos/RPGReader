package engine;

import java.io.IOException;

public class LuciferItemUnit {
	
	public String name							= "";
	public String explanation					= "";
	public long classification					= 0;
	public long price							= 0;
	public long useNumber						= 1;
	public long attackChange					= 0;
	public long defenceChange					= 0;
	public long mindChange						= 0;
	public long agilityChange					= 0;
	public long mpCost							= 0;
	public long hitChance						= 90;
	public long criticalHitChance				= 0;
	public long battleAnimation				= 1;
	public long hpRecoveryPercentage			= 0;
	public long hpRecoveryFix					= 0;
	public long mpRecoveryPercentage			= 0;
	public long mpRecoveryFix					= 0;
	public long onSwitch						= 0;
	private long nrHeroes						= 0;
	private long nrConditions					= 0;
	private long nrAttributes					= 0;
	public long changingChance					= 0;
	private boolean[] conditions;
	private boolean[] attributes;
	private boolean[] heroes;
	public boolean equipBothHands				= false;
	public boolean preemptiveAttack			= false;
	public boolean doubleAttack				= false;
	public boolean attackAllEnemies			= false;
	public boolean ignoreMonsterEvasion		= false;
	public boolean preventCriticalHit			= false;
	public boolean raiseAvoid					= false;
	public boolean halfMP						= false;
	public boolean noTerrainDamage				= false;
	public boolean effectRangeWholeParty		= false;
	public boolean onlyUseInField				= false;
	public boolean onlyOnUnconsciousHeroes		= false;
	public boolean usingMessageOfSpecialSkill	= false;
	public boolean availableAtField			= true;
	public boolean availableAtCombat			= false;
	public long permanentHPChange				= 0;
	public long permanentMPChange				= 0;
	public long permanentAttackChange			= 0;
	public long permanentDefenceChange			= 0;
	public long permanentMindChange			= 0;
	public long permanentAgilityChange			= 0;
	
	public LuciferItemUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferItemUnit(DataReader sr) throws IOException {
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
				//0->Common Goods
				//1->Arms
				//2->Shield
				//3->Armor
				//4->Helmet
				//5->Other
				//6->Medicine
				//7->Book
				//8->Material
				//9->Unique
				//10->Switch
				classification = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				price = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				//0->Infinite
				useNumber = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				attackChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				defenceChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				mindChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0E:
				agilityChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0F:
				equipBothHands = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x10:
				mpCost = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x11:
				hitChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x12:
				criticalHitChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x14:
				battleAnimation = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				preemptiveAttack = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x16:
				doubleAttack = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x17:
				attackAllEnemies = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x18:
				ignoreMonsterEvasion = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x19:
				preventCriticalHit = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1A:
				raiseAvoid = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1B:
				halfMP = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1C:
				noTerrainDamage = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1F:
				effectRangeWholeParty = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x20:
				hpRecoveryPercentage = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x21:
				hpRecoveryFix = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x22:
				mpRecoveryPercentage = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x23:
				mpRecoveryFix = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x25:
				//Medicine only
				onlyUseInField = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x26:
				onlyOnUnconsciousHeroes = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x29:
				permanentHPChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2A:
				permanentMPChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2B:
				permanentAttackChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2C:
				permanentDefenceChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2D:
				permanentMindChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2E:
				permanentAgilityChange = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x33:
				usingMessageOfSpecialSkill = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x37:
				onSwitch = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x39:
				//switch-Item only
				availableAtField = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x3A:
				//switch-Item only
				availableAtCombat = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x3D:
				nrHeroes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x3E:
				tmp = new DataReader(unit.content);
				heroes = new boolean[(int) nrHeroes + 1];
				for (int i = 1; i <= nrHeroes; i++) {
					heroes[i] = (tmp.nextInt() == 1);
				}
				break;
			case 0x3F:
				nrConditions = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x40:
				tmp = new DataReader(unit.content);
				conditions = new boolean[(int) nrConditions + 1];
				for (int i = 1; i <= nrConditions; i++) {
					conditions[i] = (tmp.nextInt() == 1);
				}
				break;
			case 0x41:
				nrAttributes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x42:
				tmp = new DataReader(unit.content);
				attributes = new boolean[(int) nrAttributes + 1];
				for (int i = 1; i <= nrAttributes; i++) {
					attributes[i] = (tmp.nextInt() == 1);
				}
				break;
			case 0x43:
				changingChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			}
			unit = sr.nextUnit();
		}
	}
}