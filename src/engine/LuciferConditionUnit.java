package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.naming.LimitExceededException;

/**
 * @author alina
 * 
 * This class represents a Condition of the Database of the RPG-Maker-Game. The static final ints of this class are actionLimitations.
 * Limitations can be (names are static final ints of this class):
 * RESTRICTION / NO_LIMITATION: This limitation doesn't change the control of the character
 * INSIDE_BATTLE / NO_ATTACK: This limitation disallows the character to attack
 * COMMON_ENEMY_ATTACK / ATTACK_RANDOM_ENEMY: This limitation means, that the character will attack a random enemy until healt.
 * COMMON_ALLY_ATTACK / ATTACK_RANDOM_ALLY: As ATTACK_RANDOM_ENEMY, but attacks a random hero
 */
public class LuciferConditionUnit {
	
	public static final int RESTRICTION = 0;
	public static final int NO_LIMITATION = 0;
	public static final int INSIDE_BATTLE = 1;
	public static final int CANT_ATTACK = 1;
	public static final int COMMON_ENEMY_ATTACK = 2;
	public static final int ATTACK_RANDOM_ENEMY = 2;
	public static final int COMMON_ALLY_ATTACK = 3;
	public static final int ATTACK_RANDOM_ALLY = 3;
	
	private String name 					= "";
	private String allyStateMessage			= "";
	private String enemyStateMessage		= "";
	private String alreadyInThisState		= "";
	private String stateIsRegular			= "";
	private String recoveryStateMessage		= "";
	private long colour						= 6;
	private long priority					= 50;
	private long actionLimitation			= 0;
	private long effectChanceA				= 100;
	private long effectChanceB				= 80;
	private long effectChanceC				= 60;
	private long effectChanceD				= 30;
	private long effectChanceE				= 0;
	private long healTurnProbability		= 0;
	private long healPhysicalProbability	= 0;
	private long halfCostAttack				= 0;
	private long halfCostDefense			= 0;
	private long halfCostMind				= 0;
	private long halfCostAgility			= 0;
	private long hitRateChance				= 0;
	private long cantUseHitChanceAbove		= 0;
	private long cantUseMindChanceAbove		= 0;
	private long healAfterTurns				= 0;
	private long hpDownPercent				= 0;
	private long hpDownPoints				= 0;
	private long hpDownMap					= 0;
	private long hpDownMapSteps				= 0;
	private long mpDownPercent				= 0;
	private long mpDownPoints				= 0;
	private long mpDownMap					= 0;
	private long mpDownMapSteps				= 0;
	private boolean classificationMovement	= false;
	private boolean cantUseHitChance		= false;
	private boolean cantUseMindChance		= false;
	
	/**
	 * Returns the actionLimitation. Limitations can be (names are static final ints of this class):
	 * RESTRICTION / NO_LIMITATION: This limitation doesn't change the control of the character
	 * INSIDE_BATTLE / NO_ATTACK: This limitation disallows the character to attack
	 * COMMON_ENEMY_ATTACK / ATTACK_RANDOM_ENEMY: This limitation means, that the character will attack a random enemy until healt.
	 * COMMON_ALLY_ATTACK / ATTACK_RANDOM_ALLY: As ATTACK_RANDOM_ENEMY, but attacks a random hero
	 * 
	 * @return the actionLimitation
	 */
	public long getActionLimitation() {
		return actionLimitation;
	}

	/**
	 * Sets the actionLimitation. Limitations can be (names are static final ints of this class):
	 * RESTRICTION / NO_LIMITATION: This limitation doesn't change the control of the character
	 * INSIDE_BATTLE / NO_ATTACK: This limitation disallows the character to attack
	 * COMMON_ENEMY_ATTACK / ATTACK_RANDOM_ENEMY: This limitation means, that the character will attack a random enemy until healt.
	 * COMMON_ALLY_ATTACK / ATTACK_RANDOM_ALLY: As ATTACK_RANDOM_ENEMY, but attacks a random hero
	 * 
	 * @param actionLimitation the new actionLimitation
	 */
	public void setActionLimitation(
			long actionLimitation) {
		this.actionLimitation = actionLimitation;
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
	 * Returns the allyStateMessage
	 * 
	 * @return the allyStateMessage
	 */
	public String getAllyStateMessage() {
		return allyStateMessage;
	}

	/**
	 * Sets the allyStateMessage
	 * 
	 * @param allyStateMessage the new allyStateMessage
	 */
	public void setAllyStateMessage(
			String allyStateMessage) {
		this.allyStateMessage = allyStateMessage;
	}

	/**
	 * Returns the enemyStateMessage
	 * 
	 * @return the enemyStateMessage
	 */
	public String getEnemyStateMessage() {
		return enemyStateMessage;
	}

	/**
	 * Sets the enemyStateMessage
	 * 
	 * @param enemyStateMessage the new enemyStateMessage
	 */
	public void setEnemyStateMessage(
			String enemyStateMessage) {
		this.enemyStateMessage = enemyStateMessage;
	}

	/**
	 * Returns the alreadyInThisState
	 * 
	 * @return the alreadyInThisState
	 */
	public String getAlreadyInThisState() {
		return alreadyInThisState;
	}

	/**
	 * Sets the alreadyInThisState
	 * 
	 * @param alreadyInThisState the new alreadyInThisState
	 */
	public void setAlreadyInThisState(
			String alreadyInThisState) {
		this.alreadyInThisState = alreadyInThisState;
	}

	/**
	 * Returns the stateIsRegular
	 * 
	 * @return the stateIsRegular
	 */
	public String getStateIsRegular() {
		return stateIsRegular;
	}

	/**
	 * Sets the stateIsRegular
	 * 
	 * @param stateIsRegular the new stateIsRegular
	 */
	public void setStateIsRegular(
			String stateIsRegular) {
		this.stateIsRegular = stateIsRegular;
	}

	/**
	 * Returns the recoveryStateMessage
	 * 
	 * @return the recoveryStateMessage
	 */
	public String getRecoveryStateMessage() {
		return recoveryStateMessage;
	}

	/**
	 * Sets the recoveryStateMessage
	 * 
	 * @param recoveryStateMessage the new recoveryStateMessage
	 */
	public void setRecoveryStateMessage(
			String recoveryStateMessage) {
		this.recoveryStateMessage = recoveryStateMessage;
	}

	/**
	 * Returns the colour as a long
	 * 
	 * @return the colour
	 */
	public long getColour() {
		return colour;
	}

	/**
	 * Sets the colour to the given value; usualy this should be between 0 and 19, but it can be higher.
	 * I haven't tested, what will happen, if the color is set > 19.
	 * 
	 * @param colour the new colour
	 */
	public void setColour(
			long colour) {
		this.colour = colour;
	}

	/**
	 * Returns the priority
	 * 
	 * @return the priority
	 */
	public long getPriority() {
		return priority;
	}

	/**
	 * Sets the priority
	 * 
	 * @param priority the new priority
	 */
	public void setPriority(
			long priority) {
		this.priority = priority;
	}

	/**
	 * Returns the effectChanceA
	 * 
	 * @return the effectChanceA
	 */
	public long getEffectChanceA() {
		return effectChanceA;
	}

	/**
	 * Sets the effectChanceA
	 * 
	 * @param effectChanceA the new effectChanceA
	 */
	public void setEffectChanceA(
			long effectChanceA) {
		this.effectChanceA = effectChanceA;
	}

	/**
	 * Returns the effectChanceB
	 * 
	 * @return the effectChanceB
	 */
	public long getEffectChanceB() {
		return effectChanceB;
	}

	/**
	 * Sets the effectChanceB
	 * 
	 * @param effectChanceB the new effectChanceB
	 */
	public void setEffectChanceB(
			long effectChanceB) {
		this.effectChanceB = effectChanceB;
	}

	/**
	 * Returns the effectChanceC
	 * 
	 * @return the effectChanceC
	 */
	public long getEffectChanceC() {
		return effectChanceC;
	}

	/**
	 * Sets the effectChanceC
	 * 
	 * @param effectChanceC the new effectChanceC
	 */
	public void setEffectChanceC(
			long effectChanceC) {
		this.effectChanceC = effectChanceC;
	}

	/**
	 * Returns the effectChanceD
	 * 
	 * @return the effectChanceD
	 */
	public long getEffectChanceD() {
		return effectChanceD;
	}

	/**
	 * Sets the effectChanceD
	 * 
	 * @param effectChanceD the new effectChanceD
	 */
	public void setEffectChanceD(
			long effectChanceD) {
		this.effectChanceD = effectChanceD;
	}

	/**
	 * Returns the effectChanceE
	 * 
	 * @return the effectChanceE
	 */
	public long getEffectChanceE() {
		return effectChanceE;
	}

	/**
	 * Sets the effectChanceE
	 * 
	 * @param effectChanceE the new effectChanceE
	 */
	public void setEffectChanceE(
			long effectChanceE) {
		this.effectChanceE = effectChanceE;
	}

	/**
	 * Returns the healTurnProbability
	 * 
	 * @return the healTurnProbability
	 */
	public long getHealTurnProbability() {
		return healTurnProbability;
	}

	/**
	 * Sets the healTurnProbability
	 * 
	 * @param healTurnProbability the new healTurnProbability
	 */
	public void setHealTurnProbability(
			long healTurnProbability) {
		this.healTurnProbability = healTurnProbability;
	}

	/**
	 * Returns the healPhysicalProbability
	 * 
	 * @return the healPhysicalProbability
	 */
	public long getHealPhysicalProbability() {
		return healPhysicalProbability;
	}

	/**
	 * Sets the healPhysicalProbability
	 * 
	 * @param healPhysicalProbability the new healPhysicalProbability
	 */
	public void setHealPhysicalProbability(
			long healPhysicalProbability) {
		this.healPhysicalProbability = healPhysicalProbability;
	}

	/**
	 * Returns the halfCostAttack
	 * 
	 * @return the halfCostAttack
	 */
	public long getHalfCostAttack() {
		return halfCostAttack;
	}

	/**
	 * Sets the halfCostAttack
	 * 
	 * @param halfCostAttack the new halfCostAttack
	 */
	public void setHalfCostAttack(
			long halfCostAttack) {
		this.halfCostAttack = halfCostAttack;
	}

	/**
	 * Returns the halfCostDefense
	 * 
	 * @return the halfCostDefense
	 */
	public long getHalfCostDefense() {
		return halfCostDefense;
	}

	/**
	 * Sets the halfCostDefense
	 * 
	 * @param halfCostDefense the new halfCostDefense
	 */
	public void setHalfCostDefense(
			long halfCostDefense) {
		this.halfCostDefense = halfCostDefense;
	}

	/**
	 * Returns the halfCostMind
	 * 
	 * @return the halfCostMind
	 */
	public long getHalfCostMind() {
		return halfCostMind;
	}

	/**
	 * Sets the halfCostMind
	 * 
	 * @param halfCostMind the new halfCostMind
	 */
	public void setHalfCostMind(
			long halfCostMind) {
		this.halfCostMind = halfCostMind;
	}

	/**
	 * Returns the halfCostAgility
	 * 
	 * @return the halfCostAgility
	 */
	public long getHalfCostAgility() {
		return halfCostAgility;
	}

	/**
	 * Sets the halfCostAgility
	 * 
	 * @param halfCostAgility the new halfCostAgility
	 */
	public void setHalfCostAgility(
			long halfCostAgility) {
		this.halfCostAgility = halfCostAgility;
	}

	/**
	 * Returns the hitRateChance
	 * 
	 * @return the hitRateChance
	 */
	public long getHitRateChance() {
		return hitRateChance;
	}

	/**
	 * Sets the hitRateChance
	 * 
	 * @param hitRateChance the new hitRateChance
	 */
	public void setHitRateChance(
			long hitRateChance) {
		this.hitRateChance = hitRateChance;
	}

	/**
	 * Returns the cantUseHitChanceAbove
	 * 
	 * @return the cantUseHitChanceAbove
	 */
	public long getCantUseHitChanceAbove() {
		return cantUseHitChanceAbove;
	}

	/**
	 * Sets the cantUseHitChanceAbove
	 * 
	 * @param cantUseHitChanceAbove the new cantUseHitChanceAbove
	 */
	public void setCantUseHitChanceAbove(
			long cantUseHitChanceAbove) {
		this.cantUseHitChanceAbove = cantUseHitChanceAbove;
	}

	/**
	 * Returns the cantUseMindChanceAbove
	 * 
	 * @return the cantUseMindChanceAbove
	 */
	public long getCantUseMindChanceAbove() {
		return cantUseMindChanceAbove;
	}

	/**
	 * Sets the cantUseMindChanceAbove
	 * 
	 * @param cantUseMindChanceAbove the new cantUseMindChanceAbove
	 */
	public void setCantUseMindChanceAbove(
			long cantUseMindChanceAbove) {
		this.cantUseMindChanceAbove = cantUseMindChanceAbove;
	}

	/**
	 * Returns the healAfterTurns
	 * 
	 * @return the healAfterTurns
	 */
	public long getHealAfterTurns() {
		return healAfterTurns;
	}

	/**
	 * Sets the healAfterTurns
	 * 
	 * @param healAfterTurns the new healAfterTurns
	 */
	public void setHealAfterTurns(
			long healAfterTurns) {
		this.healAfterTurns = healAfterTurns;
	}

	/**
	 * Returns the hpDownPercent
	 * 
	 * @return the hpDownPercent
	 */
	public long getHpDownPercent() {
		return hpDownPercent;
	}

	/**
	 * Sets the hpDownPercent
	 * 
	 * @param hpDownPercent the new hpDownPercent
	 */
	public void setHpDownPercent(
			long hpDownPercent) {
		this.hpDownPercent = hpDownPercent;
	}

	/**
	 * Returns the hpDownPoints
	 * 
	 * @return the hpDownPoints
	 */
	public long getHpDownPoints() {
		return hpDownPoints;
	}

	/**
	 * Sets the hpDownPoints
	 * 
	 * @param hpDownPoints the new hpDownPoints
	 */
	public void setHpDownPoints(
			long hpDownPoints) {
		this.hpDownPoints = hpDownPoints;
	}

	/**
	 * Returns the hpDownMap
	 * 
	 * @return the hpDownMap
	 */
	public long getHpDownMap() {
		return hpDownMap;
	}

	/**
	 * Sets the hpDownMap
	 * 
	 * @param hpDownMap the new hpDownMap
	 */
	public void setHpDownMap(
			long hpDownMap) {
		this.hpDownMap = hpDownMap;
	}

	/**
	 * Returns the hpDownMapSteps
	 * 
	 * @return the hpDownMapSteps
	 */
	public long getHpDownMapSteps() {
		return hpDownMapSteps;
	}

	/**
	 * Sets the hpDownMapSteps
	 * 
	 * @param hpDownMapSteps the new hpDownMapSteps
	 */
	public void setHpDownMapSteps(
			long hpDownMapSteps) {
		this.hpDownMapSteps = hpDownMapSteps;
	}

	/**
	 * Returns the mpDownPercent
	 * 
	 * @return the mpDownPercent
	 */
	public long getMpDownPercent() {
		return mpDownPercent;
	}

	/**
	 * Sets the mpDownPercent
	 * 
	 * @param mpDownPercent the new mpDownPercent
	 */
	public void setMpDownPercent(
			long mpDownPercent) {
		this.mpDownPercent = mpDownPercent;
	}

	/**
	 * Returns the mpDownPoints
	 * 
	 * @return the mpDownPoints
	 */
	public long getMpDownPoints() {
		return mpDownPoints;
	}

	/**
	 * Sets the mpDownPoints
	 * 
	 * @param mpDownPoints the new mpDownPoints
	 */
	public void setMpDownPoints(
			long mpDownPoints) {
		this.mpDownPoints = mpDownPoints;
	}

	/**
	 * Returns the mpDownMap
	 * 
	 * @return the mpDownMap
	 */
	public long getMpDownMap() {
		return mpDownMap;
	}

	/**
	 * Sets the mpDownMap
	 * 
	 * @param mpDownMap the new mpDownMap
	 */
	public void setMpDownMap(
			long mpDownMap) {
		this.mpDownMap = mpDownMap;
	}

	/**
	 * Returns the mpDownMapSteps
	 * 
	 * @return the mpDownMapSteps
	 */
	public long getMpDownMapSteps() {
		return mpDownMapSteps;
	}

	/**
	 * Sets the mpDownMapSteps
	 * 
	 * @param mpDownMapSteps the new mpDownMapSteps
	 */
	public void setMpDownMapSteps(
			long mpDownMapSteps) {
		this.mpDownMapSteps = mpDownMapSteps;
	}

	/**
	 * Returns the classificationMovement
	 * 
	 * @return the classificationMovement
	 */
	public boolean isClassificationMovement() {
		return classificationMovement;
	}

	/**
	 * Sets the classificationMovement
	 * 
	 * @param classificationMovement the new classificationMovement
	 */
	public void setClassificationMovement(
			boolean classificationMovement) {
		this.classificationMovement = classificationMovement;
	}

	/**
	 * Returns the cantUseHitChance
	 * 
	 * @return the cantUseHitChance
	 */
	public boolean isCantUseHitChance() {
		return cantUseHitChance;
	}

	/**
	 * Sets the cantUseHitChance
	 * 
	 * @param cantUseHitChance the new cantUseHitChance
	 */
	public void setCantUseHitChance(
			boolean cantUseHitChance) {
		this.cantUseHitChance = cantUseHitChance;
	}

	/**
	 * Returns the cantUseMindChance
	 * 
	 * @return the cantUseMindChance
	 */
	public boolean isCantUseMindChance() {
		return cantUseMindChance;
	}

	/**
	 * Sets the cantUseMindChance
	 * 
	 * @param cantUseMindChance the new cantUseMindChance
	 */
	public void setCantUseMindChance(
			boolean cantUseMindChance) {
		this.cantUseMindChance = cantUseMindChance;
	}

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
	
	/**
	 * Returns the byte-representation of this CommonEvent
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x02, DataReader.intToRPGint(classificationMovement ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x03, DataReader.intToRPGint(colour)).write(new byte[]{6}),
					new LuciferBaseUnit(0x04, DataReader.intToRPGint(priority)).write(DataReader.intToRPGint(50)),
					new LuciferBaseUnit(0x05, DataReader.intToRPGint(actionLimitation)).write(new byte[]{0}),
					new LuciferBaseUnit(0x0B, DataReader.intToRPGint(effectChanceA)).write(DataReader.intToRPGint(100)),
					new LuciferBaseUnit(0x0C, DataReader.intToRPGint(effectChanceB)).write(DataReader.intToRPGint(80)),
					new LuciferBaseUnit(0x0D, DataReader.intToRPGint(effectChanceC)).write(DataReader.intToRPGint(60)),
					new LuciferBaseUnit(0x0E, DataReader.intToRPGint(effectChanceD)).write(DataReader.intToRPGint(30)),
					new LuciferBaseUnit(0x0F, DataReader.intToRPGint(effectChanceE)).write(DataReader.intToRPGint(0)),
					new LuciferBaseUnit(0x15, DataReader.intToRPGint(healAfterTurns)).write(new byte[]{0}),
					new LuciferBaseUnit(0x16, DataReader.intToRPGint(healTurnProbability)).write(new byte[]{0}),
					new LuciferBaseUnit(0x17, DataReader.intToRPGint(healPhysicalProbability)).write(new byte[]{0}),
					new LuciferBaseUnit(0x1F, DataReader.intToRPGint(halfCostAttack)).write(new byte[]{0}),
					new LuciferBaseUnit(0x20, DataReader.intToRPGint(halfCostDefense)).write(new byte[]{0}),
					new LuciferBaseUnit(0x21, DataReader.intToRPGint(halfCostMind)).write(new byte[]{0}),
					new LuciferBaseUnit(0x22, DataReader.intToRPGint(halfCostAgility)).write(new byte[]{0}),
					new LuciferBaseUnit(0x23, DataReader.intToRPGint(hitRateChance)).write(new byte[]{0}),
					new LuciferBaseUnit(0x29, DataReader.intToRPGint(cantUseHitChance ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x2A, DataReader.intToRPGint(cantUseHitChanceAbove)).write(new byte[]{0}),
					new LuciferBaseUnit(0x2B, DataReader.intToRPGint(cantUseMindChance ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x2C, DataReader.intToRPGint(cantUseMindChanceAbove)).write(new byte[]{0}),
					new LuciferBaseUnit(0x33, allyStateMessage.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x34, enemyStateMessage.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x35, alreadyInThisState.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x36, stateIsRegular.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x37, recoveryStateMessage.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x3D, DataReader.intToRPGint(hpDownPercent)).write(new byte[]{0}),
					new LuciferBaseUnit(0x3E, DataReader.intToRPGint(hpDownPoints)).write(new byte[]{0}),
					new LuciferBaseUnit(0x3F, DataReader.intToRPGint(hpDownMap)).write(new byte[]{0}),
					new LuciferBaseUnit(0x40, DataReader.intToRPGint(hpDownMapSteps)).write(new byte[]{0}),
					new LuciferBaseUnit(0x41, DataReader.intToRPGint(mpDownPercent)).write(new byte[]{0}),
					new LuciferBaseUnit(0x42, DataReader.intToRPGint(mpDownPoints)).write(new byte[]{0}),
					new LuciferBaseUnit(0x43, DataReader.intToRPGint(mpDownMap)).write(new byte[]{0}),
					new LuciferBaseUnit(0x44, DataReader.intToRPGint(mpDownMapSteps)).write(new byte[]{0}),
					new byte[]{0}
					);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}