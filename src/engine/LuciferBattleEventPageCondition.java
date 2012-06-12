package engine;

import java.io.IOException;

/**
 * This class models a BattleEvent-Page-Condition of the RM2k.
 * 
 * @author gRuFtY
 *
 */
public class LuciferBattleEventPageCondition {
	
	private boolean switchA			= false;
	private boolean switchB			= false;
	private boolean variable		= false;
	private boolean turn			= false;
	private boolean exhaustion		= false;
	private boolean monster			= false;
	private boolean hero			= false;
	private long switchAID			= 1;
	private long switchBID			= 1;
	private long variableID			= 1;
	private long variableValue		= 0;
	private long turnA				= 0;
	private long turnB				= 0;
	private long exhaustionHigher	= 0;
	private long exhaustionLower	= 100;
	private long monsterBattleID	= 0;
	private long monsterHPHigher	= 0;
	private long monsterHPLower		= 100;
	private long heroID				= 1;
	private long heroHPHigher		= 0;
	private long heroHPLower		= 100;
	
	/**
	 * Constructs a new LuciferBattleEventPageCondition
	 * 
	 * @param bytes byte-Array which represents the LuciferBattleEventPageCondition
	 * @throws IOException thrown, if something is wrong with the bytes
	 */
	public LuciferBattleEventPageCondition(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferBattleEventPageCondition
	 * 
	 * @param sr StringReader which represents the LuciferBattleEventPageCondition
	 * @throws IOException thrown, if something is wrong with the bytes
	 */
	public LuciferBattleEventPageCondition(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch(unit.id) {
			case 0x01:
				RPGInt tmp = DataReader.rpgintToInt(unit.content);
				switchA		= (tmp.integer % 2 == 1);
				switchB		= ((tmp.integer / 2) % 2 == 1);
				variable	= ((tmp.integer / 4) % 2 == 1);
				turn		= ((tmp.integer / 8) % 2 == 1);
				exhaustion	= ((tmp.integer / 16) % 2 == 1);
				monster		= ((tmp.integer / 32) % 2 == 1);
				hero		= ((tmp.integer / 64) % 2 == 1);
				break;
			case 0x02:
				switchAID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				switchBID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				variableID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				variableValue = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				turnA = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x07:
				turnB = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x08:
				exhaustionHigher = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x09:
				exhaustionLower = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0A:
				//ID of the fighter; although in the maker the counting starts at 1, in the file the first id is 0.
				//So this id is the id of the fighter minus 1.
				monsterBattleID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				monsterHPHigher = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				monsterHPLower = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				heroID = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0E:
				heroHPHigher = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0F:
				heroHPLower = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferBattleEventPageCondition! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}

	/**
	 * Returns, if exhaustion is set as condition
	 * 
	 * @return is exhaustion set as condition?
	 */
	public boolean isExhaustionSet() {
		return exhaustion;
	}
	
	/**
	 * Sets exhaustion as condition
	 */
	public void setExhaustion() {
		exhaustion = true;
	}
	
	/**
	 * Set exhaustion not to be condition
	 */
	public void unsetExhaustion() {
		exhaustion = false;
	}
	
	/**
	 * Sets exhaustion to given exhaustion-value
	 * 
	 * @param exhaustion new exhaustion-value
	 */
	public void setExhaustion(boolean exhaustion) {
		this.exhaustion = exhaustion;
	}
	
	/**
	 * Returns the maximum Exhaustion
	 * 
	 * @return the maximum Exhaustion
	 */
	public long getExhaustionLower() {
		return exhaustionLower;
	}
	
	/**
	 * Returns the maximum Exhaustion
	 * 
	 * @return the maximum Exhaustion
	 */
	public long getExhaustionMax() {
		return exhaustionLower;
	}
	
	/**
	 * Sets the exhaustion maximum
	 * 
	 * @param max the exhaustion maximum
	 */
	public void setExhaustionLower(long max) {
		if (!(exhaustionHigher <= max && max <= 100)) {
			throw new IllegalArgumentException("Exhaustion-maximum has to be between exhaustion-minimum and 100");
		}
		exhaustionLower = max;
	}
	
	/**
	 * Sets the exhaustion maximum
	 * 
	 * @param max the exhaustion maximum
	 */
	public void setExhaustionMax(long max) {
		if (!(exhaustionHigher <= max && max <= 100)) {
			throw new IllegalArgumentException("Exhaustion-maximum has to be between exhaustion-minimum and 100");
		}
		exhaustionLower = max;
	}
	
	/**
	 * Returns the minimum Exhaustion
	 * 
	 * @return the minimum Exhaustion
	 */
	public long getExhaustionHigher() {
		return exhaustionHigher;
	}
	
	/**
	 * Returns the minimum Exhaustion
	 * 
	 * @return the minimum Exhaustion
	 */
	public long getExhaustionMin() {
		return exhaustionHigher;
	}
	
	/**
	 * Sets the exhaustion minimum
	 * 
	 * @param min the exhaustion minimum
	 */
	public void setExhaustionHigher(long min) {
		if (!(0 <= min && min <= exhaustionLower)) {
			throw new IllegalArgumentException("Exhaustion-minimum has to be between 0 and exhaustion-maximum");
		}
		exhaustionHigher = min;
	}
	
	/**
	 * Sets the exhaustion minimum
	 * 
	 * @param min the exhaustion minimum
	 */
	public void setExhaustionMin(long min) {
		if (!(0 <= min && min <= exhaustionLower)) {
			throw new IllegalArgumentException("Exhaustion-minimum has to be between 0 and exhaustion-maximum");
		}
		exhaustionHigher = min;
	}
	
	/**
	 * Returns if switchA is set as condition
	 * 
	 * @return is switchA set as condition?
	 */
	public boolean isSwitchASet() {
		return switchA;
	}

	/**
	 * Sets switchA to the given switchA-value
	 * 
	 * @param switchA new switchA-value
	 */
	public void setSwitchA(boolean switchA) {
		this.switchA = switchA;
	}
	
	/**
	 * Sets switchA to be condition
	 */
	public void setSwitchA() {
		this.switchA = true;
	}
	
	/**
	 * Sets switchA not to be condition
	 */
	public void unsetSwitchA() {
		this.switchA = false;
	}

	/**
	 * Returns if switchB is set as condition
	 * 
	 * @return is switchB set as condition?
	 */
	public boolean isSwitchBSet() {
		return switchB;
	}

	/**
	 * Sets switchB to the given switchB-value
	 * 
	 * @param switchB new switchB-value
	 */
	public void setSwitchB(boolean switchB) {
		this.switchB = switchB;
	}
	
	/**
	 * Sets switchB as condition
	 */
	public void setSwitchB() {
		this.switchB = true;
	}
	
	/**
	 * Sets switchB not to be condition
	 */
	public void unsetSwitchB() {
		this.switchB = false;
	}

	/**
	 * Returns if variable is set as condition
	 * 
	 * @return is variable set as condition?
	 */
	public boolean isVariableSet() {
		return variable;
	}

	/**
	 * Sets variable to the given variable-value
	 * 
	 * @param variable new variable-value
	 */
	public void setVariable(boolean variable) {
		this.variable = variable;
	}
	
	/**
	 * Sets variable as condition
	 */
	public void setVariable() {
		this.variable = true;
	}
	
	/**
	 * Sets variable not to be condition
	 */
	public void unsetVariable() {
		this.variable = false;
	}

	/**
	 * Returns if turn is set as condition
	 * 
	 * @return is turn set as condition?
	 */
	public boolean isTurnSet() {
		return turn;
	}

	/**
	 * Sets turn to the given turn-value
	 * 
	 * @param turn new turn-value
	 */
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	/**
	 * Sets turn as condition
	 */
	public void setTurn() {
		this.turn = true;
	}
	
	/**
	 * Sets turn not to be condition
	 */
	public void unsetTurn() {
		this.turn = false;
	}

	/**
	 * Returns if monster is set as condition
	 * 
	 * @return is monster set as condition?
	 */
	public boolean isMonsterSet() {
		return monster;
	}

	/**
	 * Sets monster to the given monster-value
	 * 
	 * @param monster new monster-value
	 */
	public void setMonster(boolean monster) {
		this.monster = monster;
	}
	
	/**
	 * Sets monster as condition
	 */
	public void setMonster() {
		this.monster = true;
	}
	
	/**
	 * Sets monster not to be condition
	 */
	public void unsetMonster() {
		this.monster = false;
	}

	/**
	 * Returns if hero is set as condition
	 * 
	 * @return is hero set as condition?
	 */
	public boolean isHeroSet() {
		return hero;
	}

	/**
	 * Sets hero to given hero-value
	 * 
	 * @param hero new hero value
	 */
	public void setHero(boolean hero) {
		this.hero = hero;
	}
	
	/**
	 * Sets hero as condition
	 */
	public void setHero() {
		this.hero = true;
	}
	
	/**
	 * Sets hero not to be condition
	 */
	public void unsetHero() {
		this.hero = false;
	}

	/**
	 * Returns the switchA-ID
	 * 
	 * @return the switchA-ID
	 */
	public long getSwitchAID() {
		return switchAID;
	}

	/**
	 * Sets the switchA-ID
	 * 
	 * @param switchAID the new switchA-ID
	 */
	public void setSwitchAID(long switchAID) {
		if (switchAID < 0) {
			throw new IllegalArgumentException("Switch-ID may not be negativ!");
		}
		this.switchAID = switchAID;
	}

	/**
	 * Returns the switchB-ID
	 * 
	 * @return the switchB-ID
	 */
	public long getSwitchBID() {
		return switchBID;
	}

	/**
	 * Sets the switchB-ID
	 * 
	 * @param switchBID the new switchB-ID
	 */
	public void setSwitchBID(long switchBID) {
		if (switchBID < 0) {
			throw new IllegalArgumentException("Switch-ID may not be negativ!");
		}
		this.switchBID = switchBID;
	}

	/**
	 * Returns the variable-ID
	 * 
	 * @return the variable-ID
	 */
	public long getVariableID() {
		return variableID;
	}

	/**
	 * Sets the variable-ID
	 * 
	 * @param variableID the new variable-ID
	 */
	public void setVariableID(long variableID) {
		if (variableID < 0) {
			throw new IllegalArgumentException("Variable-ID may not be negativ!");
		}
		this.variableID = variableID;
	}

	/**
	 * Returns the variable-value
	 * 
	 * @return the variable-value
	 */
	public long getVariableValue() {
		return variableValue;
	}

	/**
	 * Sets the variable-value
	 * 
	 * @param variableValue the new variable-value
	 */
	public void setVariableValue(
			long variableValue) {
		this.variableValue = variableValue;
	}

	/**
	 * Returns the first argument of the turn-condition
	 * 
	 * @return the first argument of the turn-condition
	 */
	public long getTurnA() {
		return turnA;
	}

	/**
	 * Sets the first argument of the turn-condition
	 * 
	 * @param turnA the new first argument of the turn-condition
	 */
	public void setTurnA(long turnA) {
		if (turnA < 0) {
			throw new IllegalArgumentException("Turn may not be negativ!");
		}
		this.turnA = turnA;
	}

	/**
	 * Returns the second argument of the turn-condition
	 * 
	 * @return the second argument of the turn-condition
	 */
	public long getTurnB() {
		return turnB;
	}

	/**
	 * Sets the second argument of the turn-condition
	 * 
	 * @param turnB the new second argument of the turn-condition
	 */
	public void setTurnB(long turnB) {
		if (turnB < 0) {
			throw new IllegalArgumentException("Turn may not be negativ!");
		}
		this.turnB = turnB;
	}

	/**
	 * Returns the ID of the Monster for this Battle (not the Monster-ID of the "Monsters"-Tab,
	 * but the ID of the "Monster Party"-Tab)
	 * 
	 * @return the ID of the Monster for this Battle
	 */
	public long getMonsterBattleID() {
		return monsterBattleID;
	}

	/**
	 * Sets the ID of the Monster for this Condition. You need the ID of the Monster in the Battle
	 * (not the one of the "Monsters"-Tab but the one of the "Monster Party")
	 * 
	 * @param monsterBattleID the Monster ID
	 */
	public void setMonsterBattleID(long monsterBattleID) {
		if (monsterBattleID < 0) {
			throw new IllegalArgumentException("Monster-Battle-ID may not be negativ!");
		}
		this.monsterBattleID = monsterBattleID;
	}

	/**
	 * Returns HeroID
	 * 
	 * @return HeroID
	 */
	public long getHeroID() {
		return heroID;
	}
	
	/**
	 * Sets hero to the given ID
	 * 
	 * @param heroID new HeroID
	 */
	public void setHeroID(long heroID) {
		if (heroID < 0) {
			throw new IllegalArgumentException("Hero-ID may not be negativ!");
		}
		this.heroID = heroID;
	}
	
	/**
	 * Returns the maximum hero HP
	 * 
	 * @return the maximum hero HP
	 */
	public long getHeroHPLower() {
		return heroHPLower;
	}
	
	/**
	 * Returns the maximum hero HP
	 * 
	 * @return the maximum hero HP
	 */
	public long getHeroHPMax() {
		return heroHPLower;
	}
	
	/**
	 * Sets the hero HP maximum
	 * 
	 * @param max the hero HP maximum
	 */
	public void setHeroHPLower(long max) {
		if (!(heroHPHigher <= max && max <= 100)) {
			throw new IllegalArgumentException("HeroHP-maximum has to be between heroHP-minimum and 100");
		}
		heroHPLower = max;
	}
	
	/**
	 * Sets the hero HP maximum
	 * 
	 * @param max the hero HP maximum
	 */
	public void setHeroHPMax(long max) {
		if (!(heroHPHigher <= max && max <= 100)) {
			throw new IllegalArgumentException("HeroHP-maximum has to be between heroHP-minimum and 100");
		}
		heroHPLower = max;
	}
	
	/**
	 * Returns the minimum hero HP
	 * 
	 * @return the minimum hero HP
	 */
	public long getHeroHPHigher() {
		return heroHPHigher;
	}
	
	/**
	 * Returns the minimum hero HP
	 * 
	 * @return the minimum hero HP
	 */
	public long getHeroHPMin() {
		return heroHPHigher;
	}
	
	/**
	 * Sets the hero HP minimum
	 * 
	 * @param min the hero HP minimum
	 */
	public void setHeroHPHigher(long min) {
		if (!(0 <= min && min <= heroHPLower)) {
			throw new IllegalArgumentException("HeroHP-minimum has to be between 0 and heroHP-maximum");
		}
		heroHPHigher = min;
	}
	
	/**
	 * Sets the hero HP minimum
	 * 
	 * @param min the hero HP minimum
	 */
	public void setHeroHPMin(long min) {
		if (!(0 <= min && min <= heroHPLower)) {
			throw new IllegalArgumentException("HeroHP-minimum has to be between 0 and heroHP-maximum");
		}
		heroHPHigher = min;
	}

	/**
	 * Returns the maximum monster HP
	 * 
	 * @return the maximum monster HP
	 */
	public long getMonsterHPLower() {
		return monsterHPLower;
	}
	
	/**
	 * Returns the maximum monster HP
	 * 
	 * @return the maximum monster HP
	 */
	public long getMonsterHPMax() {
		return monsterHPLower;
	}
	
	/**
	 * Sets the monster HP maximum
	 * 
	 * @param max the monster HP maximum
	 */
	public void setMonsterHPLower(long max) {
		if (!(monsterHPHigher <= max && max <= 100)) {
			throw new IllegalArgumentException("MonsterHP-maximum has to be between monsterHP-minimum and 100");
		}
		monsterHPLower = max;
	}
	
	/**
	 * Sets the monster HP maximum
	 * 
	 * @param max the monster HP maximum
	 */
	public void setMonsterHPMax(long max) {
		if (!(monsterHPHigher <= max && max <= 100)) {
			throw new IllegalArgumentException("MonsterHP-maximum has to be between monsterHP-minimum and 100");
		}
		monsterHPLower = max;
	}
	
	/**
	 * Returns the minimum monster HP
	 * 
	 * @return the minimum monster HP
	 */
	public long getMonsterHPHigher() {
		return monsterHPHigher;
	}
	
	/**
	 * Returns the minimum monster HP
	 * 
	 * @return the minimum monster HP
	 */
	public long getMonsterHPMin() {
		return monsterHPHigher;
	}
	
	/**
	 * Sets the monster HP minimum
	 * 
	 * @param min the monster HP minimum
	 */
	public void setMonsterHPHigher(long min) {
		if (!(0 <= min && min <= monsterHPLower)) {
			throw new IllegalArgumentException("MonsterHP-minimum has to be between 0 and monsterHP-maximum");
		}
		monsterHPHigher = min;
	}
	
	/**
	 * Sets the monster HP minimum
	 * 
	 * @param min the monster HP minimum
	 */
	public void setMonsterHPMin(long min) {
		if (!(0 <= min && min <= monsterHPLower)) {
			throw new IllegalArgumentException("MonsterHP-minimum has to be between 0 and monsterHP-maximum");
		}
		monsterHPHigher = min;
	}
	
	/**
	 * Returns the byte-representation of this BattleEventPageCondition
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		long flags = (switchA ? 1 : 0) * 1
				+ (switchB ? 1 : 0) * 2
				+ (variable ? 1 : 0) * 4
				+ (turn ? 1 : 0) * 8
				+ (exhaustion ? 1 : 0) * 16
				+ (monster ? 1 : 0) * 32
				+ (hero ? 1 : 0) * 64;
		return Helper.concatAll(new LuciferBaseUnit(0x01, DataReader.intToRPGint(flags)).write(new byte[0]),
				new LuciferBaseUnit(0x02, DataReader.intToRPGint(switchAID)).write(new byte[1]),
				new LuciferBaseUnit(0x03, DataReader.intToRPGint(switchBID)).write(new byte[1]),
				new LuciferBaseUnit(0x04, DataReader.intToRPGint(variableID)).write(new byte[1]),
				new LuciferBaseUnit(0x05, DataReader.intToRPGint(variableValue)).write(new byte[0]),
				new LuciferBaseUnit(0x06, DataReader.intToRPGint(turnA)).write(new byte[0]),
				new LuciferBaseUnit(0x07, DataReader.intToRPGint(turnB)).write(new byte[0]),
				new LuciferBaseUnit(0x08, DataReader.intToRPGint(exhaustionHigher)).write(new byte[0]),
				new LuciferBaseUnit(0x09, DataReader.intToRPGint(exhaustionLower)).write(DataReader.intToRPGint(100)),
				new LuciferBaseUnit(0x0A, DataReader.intToRPGint(monsterBattleID)).write(new byte[0]),
				new LuciferBaseUnit(0x0B, DataReader.intToRPGint(monsterHPHigher)).write(new byte[0]),
				new LuciferBaseUnit(0x0C, DataReader.intToRPGint(monsterHPLower)).write(DataReader.intToRPGint(100)),
				new LuciferBaseUnit(0x0D, DataReader.intToRPGint(heroID)).write(new byte[1]),
				new LuciferBaseUnit(0x0E, DataReader.intToRPGint(heroHPHigher)).write(new byte[0]),
				new LuciferBaseUnit(0x0F, DataReader.intToRPGint(heroHPLower)).write(DataReader.intToRPGint(100)),
				new byte[]{0}
		);
	}
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferBattleEventPageCondition)) {
	        return false; // different class
	     }
	     
	     LuciferBattleEventPageCondition o = (LuciferBattleEventPageCondition) obj;
	     
	     //TODO: wenn z.B. switchB aus ist, muss nicht switchBID verglichen werden
	     return exhaustion == o.exhaustion
	     		&& exhaustionHigher == o.exhaustionHigher
	     		&& exhaustionLower == o.exhaustionLower
	     		&& hero == o.hero
	     		&& heroID == o.heroID
	     		&& heroHPHigher == o.heroHPHigher
	     		&& heroHPLower == o.heroHPLower
	     		&& monster == o.monster
	     		&& monsterBattleID == o.monsterBattleID
	     		&& monsterHPHigher == o.monsterHPHigher
	     		&& monsterHPLower == o.monsterHPLower
	     		&& turn == o.turn
	     		&& turnA == o.turnA
	     		&& turnB == o.turnB
	     		&& switchA == o.switchA
	     		&& switchAID == o.switchAID
	     		&& switchB == o.switchB
	     		&& switchBID == o.switchBID
	     		&& variable == o.variable
	     		&& variableID == o.variableID
	     		&& variableValue == o.variableValue;
	}
}