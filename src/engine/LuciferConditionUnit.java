package engine;

import java.io.IOException;

public class LuciferConditionUnit {
	
	public String name 						= "";
	public String allyStateMessage			= "";
	public String enemyStateMessage			= "";
	public String alreadyInThisState		= "";
	public String stateIsRegular			= "";
	public String recoveryStateMessage		= "";
	public int colour						= 6;
	public int priority					= 50;
	public int actionLimitation;
	public int effectChanceA				= 100;
	public int effectChanceB				= 80;
	public int effectChanceC				= 60;
	public int effectChanceD				= 30;
	public int effectChanceE				= 0;
	public int healTurnProbability		= 0;
	public int healPhysicalProbability	= 0;
	public int halfCostAttack				= 0;
	public int halfCostDefense				= 0;
	public int halfCostMind				= 0;
	public int halfCostAgility				= 0;
	public int hitRateChance				= 0;
	public int cantUseHitChanceAbove		= 0;
	public int cantUseMindChanceAbove		= 0;
	public long healAfterTurns			= 0;
	public long hpDownPercent				= 0;
	public long hpDownPoints				= 0;
	public long hpDownMap					= 0;
	public long hpDownMapSteps				= 0;
	public long mpDownPercent				= 0;
	public long mpDownPoints				= 0;
	public long mpDownMap					= 0;
	public long mpDownMapSteps				= 0;
	public boolean classificationMovement	= false;
	public boolean cantUseHitChance		= false;
	public boolean cantUseMindChance		= false;

	public LuciferConditionUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferConditionUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				classificationMovement = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x03:
				colour = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				priority = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				actionLimitation = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				effectChanceA = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				effectChanceB = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				effectChanceC = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0E:
				effectChanceD = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0F:
				effectChanceE = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				healAfterTurns = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x16:
				healTurnProbability = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x17:
				healPhysicalProbability = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x1F:
				halfCostAttack = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x20:
				halfCostDefense = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x21:
				halfCostMind = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x22:
				halfCostAgility = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x23:
				hitRateChance = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x29:
				cantUseHitChance = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x2A:
				cantUseHitChanceAbove = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2B:
				cantUseMindChance = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x2C:
				cantUseMindChanceAbove = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x33:
				allyStateMessage = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x34:
				enemyStateMessage = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x35:
				alreadyInThisState = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x36:
				stateIsRegular = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x37:
				recoveryStateMessage = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x3D:
				hpDownPercent = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x3E:
				hpDownPoints = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x3F:
				hpDownMap = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x40:
				hpDownMapSteps = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x41:
				mpDownPercent = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x42:
				mpDownPoints = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x43:
				mpDownMap = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x44:
				mpDownMapSteps = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferConditionUnit! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
}