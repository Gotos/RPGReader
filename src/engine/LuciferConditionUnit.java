package engine;

import java.io.IOException;

public class LuciferConditionUnit {
	
	public String name 						= "";
	public String allyStateMessage			= "";
	public String enemyStateMessage			= "";
	public String alreadyInThisState		= "";
	public String stateIsRegular			= "";
	public String recoveryStateMessage		= "";
	public long colour						= 6;
	public long priority					= 50;
	public long actionLimitation			= 0;
	public long effectChanceA				= 100;
	public long effectChanceB				= 80;
	public long effectChanceC				= 60;
	public long effectChanceD				= 30;
	public long effectChanceE				= 0;
	public long healTurnProbability		= 0;
	public long healPhysicalProbability	= 0;
	public long halfCostAttack				= 0;
	public long halfCostDefense				= 0;
	public long halfCostMind				= 0;
	public long halfCostAgility				= 0;
	public long hitRateChance				= 0;
	public long cantUseHitChanceAbove		= 0;
	public long cantUseMindChanceAbove		= 0;
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
	
	/**
	 * Constructs a new LuciferConditionData
	 * 
	 * @param bytes byte-Array which represents the LuciferConditionData
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferConditionUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferConditionData
	 * 
	 * @param dr DataReader which represents the LuciferConditionData
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferConditionUnit(DataReader dr) throws IOException {
		init(dr);
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
				colour = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				priority = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				actionLimitation = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				effectChanceA = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				effectChanceB = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				effectChanceC = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0E:
				effectChanceD = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0F:
				effectChanceE = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				healAfterTurns = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x16:
				healTurnProbability = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x17:
				healPhysicalProbability = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x1F:
				halfCostAttack = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x20:
				halfCostDefense = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x21:
				halfCostMind = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x22:
				halfCostAgility = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x23:
				hitRateChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x29:
				cantUseHitChance = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x2A:
				cantUseHitChanceAbove = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2B:
				cantUseMindChance = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x2C:
				cantUseMindChanceAbove = DataReader.rpgintToInt(unit.content).integer;
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
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferConditionUnit)) {
	        return false; // different class
	     }
	     
	     LuciferConditionUnit o = (LuciferConditionUnit) obj;
	     
	     return name.equals(o.name)
	     		&& allyStateMessage.equals(o.allyStateMessage)
	     		&& enemyStateMessage.equals(o.enemyStateMessage)
	     		&& alreadyInThisState.equals(o.alreadyInThisState)
	     		&& stateIsRegular.equals(o.stateIsRegular)
	     		&& recoveryStateMessage.equals(recoveryStateMessage)
	     		&& colour == o.colour
	     		&& priority == o.priority
	     		&& actionLimitation == o.actionLimitation
	     		&& effectChanceA == o.effectChanceA
	     		&& effectChanceB == o.effectChanceB
	     		&& effectChanceC == o.effectChanceC
	     		&& effectChanceD == o.effectChanceD
	     		&& effectChanceE == o.effectChanceE
	     		&& healTurnProbability == o.healTurnProbability
	     		&& healPhysicalProbability == o.healPhysicalProbability
	     		&& halfCostAttack == o.halfCostAttack
	     		&& halfCostDefense == o.halfCostDefense
	     		&& halfCostMind == o.halfCostMind
	     		&& halfCostAgility == o.halfCostAgility
	     		&& hitRateChance == o.hitRateChance
	     		&& cantUseHitChanceAbove == o.cantUseHitChanceAbove
	     		&& cantUseMindChanceAbove == o.cantUseMindChanceAbove
	     		&& healAfterTurns == o.healAfterTurns
	     		&& hpDownPercent == o.hpDownPercent
	     		&& hpDownPoints == o.hpDownPoints
	     		&& hpDownMap == o.hpDownMap
	     		&& hpDownMapSteps == o.hpDownMapSteps
	     		&& mpDownPercent == o.mpDownPercent
	     		&& mpDownPoints == o.mpDownPoints
	     		&& mpDownMap == o.mpDownMap
	     		&& mpDownMapSteps == o.mpDownMapSteps
	     		&& classificationMovement == o.classificationMovement
	     		&& cantUseHitChance == o.cantUseHitChance
	     		&& cantUseMindChance == o.cantUseMindChance;
	}
}