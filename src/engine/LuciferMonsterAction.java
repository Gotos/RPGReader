package engine;

import java.io.IOException;

/**
 * @author alina
 *
 * This class represents a MonsterAction of the RPG-Maker-Game.
 */
public class LuciferMonsterAction {
	
	private long subject				= 0;
	private long behavior				= 0;
	private long skill					= 1;
	private long transform				= 1;
	private long condition				= 0;
	private long conditionLowerLimit	= 0;
	private long conditionUpperLimit	= 0;
	private long onSwitch				= 0;
	private long offSwitch				= 0;
	private long priority				= 50;
	private boolean turnSwitchOn		= false;
	private boolean turnSwitchOff		= false;
	
	/**
	 * Constructs a new LuciferMonsterAction
	 */ 
	public LuciferMonsterAction() { }
	
	/**
	 * Constructs a new LuciferMonsterAction
	 * 
	 * @param bytes byte-Array which represents the LuciferMonsterAction
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterAction(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMonsterAction
	 * 
	 * @param dr DataReader which represents the LuciferMonsterAction
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterAction(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				//0->behavior
				//1->skill
				//2->transform
				subject = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x02:
				//0->normal hit
				//1->critical hit
				//2->defend
				//3->watch state
				//4->accumulate power
				//5->self-annihilation
				//6->escape
				//7->nothing
				//8->by id (if subject!=0)
				behavior = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				skill = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				transform = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				//0->usually
				//1->switch (defined in conditionLowerLimit)
				//2->turn no (defined in conditionLowerLimit and conditionUpperLimit)
				//3->monster quantity (defined in conditionLowerLimit and conditionUpperLimit)
				//4->oneself HP (defined in conditionLowerLimit and conditionUpperLimit)
				//5->oneself MP (defined in conditionLowerLimit and conditionUpperLimit)
				//6->Hero Average level (defined in conditionLowerLimit and conditionUpperLimit)
				//7->hero exhaustion (defined in conditionLowerLimit and conditionUpperLimit)
				condition = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				conditionLowerLimit = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x07:
				conditionUpperLimit = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x09:
				turnSwitchOn = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0A:
				onSwitch = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				turnSwitchOff = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0C:
				offSwitch = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				priority = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMonsterAction! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the subject. Should be:
	 * 		0->behavior
	 *		1->skill
	 *		2->transform
	 * 
	 * @return the subject
	 */
	public long getSubject() {
		return subject;
	}

	/**
	 * Sets the subject. Should be:
	 * 		0->behavior
	 *		1->skill
	 *		2->transform
	 * 
	 * @param subject the new subject
	 */
	public void setSubject(
			long subject) {
		this.subject = subject;
	}

	/**
	 * Returns the behavior. Should be:
	 * 		0->normal hit
	 *		1->critical hit
	 *		2->defend
	 *		3->watch state
	 *		4->accumulate power
	 *		5->self-annihilation
	 *		6->escape
	 *		7->nothing
	 *		8->by id (if subject!=0)
	 * 
	 * @return the behavior
	 */
	public long getBehavior() {
		return behavior;
	}

	/**
	 * Sets the behavior. Should be:
	 * 		0->normal hit
	 *		1->critical hit
	 *		2->defend
	 *		3->watch state
	 *		4->accumulate power
	 *		5->self-annihilation
	 *		6->escape
	 *		7->nothing
	 *		8->by id (if subject!=0)
	 * 
	 * @param behavior the new behavior
	 */
	public void setBehavior(
			long behavior) {
		this.behavior = behavior;
	}

	/**
	 * Returns the skill
	 * 
	 * @return the skill
	 */
	public long getSkill() {
		return skill;
	}

	/**
	 * Sets the skill
	 * 
	 * @param skill the new skill
	 */
	public void setSkill(
			long skill) {
		this.skill = skill;
	}

	/**
	 * Returns the transform
	 * 
	 * @return the transform
	 */
	public long getTransform() {
		return transform;
	}

	/**
	 * Sets the transform
	 * 
	 * @param transform the new transform
	 */
	public void setTransform(
			long transform) {
		this.transform = transform;
	}

	/**
	 * Returns the condition. Should be:
	 * 		0->usually
	 *		1->switch (defined in conditionLowerLimit)
	 *		2->turn no (defined in conditionLowerLimit and conditionUpperLimit)
	 *		3->monster quantity (defined in conditionLowerLimit and conditionUpperLimit)
	 *		4->oneself HP (defined in conditionLowerLimit and conditionUpperLimit)
	 *		5->oneself MP (defined in conditionLowerLimit and conditionUpperLimit)
	 *		6->Hero Average level (defined in conditionLowerLimit and conditionUpperLimit)
	 *		7->hero exhaustion (defined in conditionLowerLimit and conditionUpperLimit)
	 * 
	 * @return the condition
	 */
	public long getCondition() {
		return condition;
	}

	/**
	 * Sets the condition. Should be:
	 * 		0->usually
	 *		1->switch (defined in conditionLowerLimit)
	 *		2->turn no (defined in conditionLowerLimit and conditionUpperLimit)
	 *		3->monster quantity (defined in conditionLowerLimit and conditionUpperLimit)
	 *		4->oneself HP (defined in conditionLowerLimit and conditionUpperLimit)
	 *		5->oneself MP (defined in conditionLowerLimit and conditionUpperLimit)
	 *		6->Hero Average level (defined in conditionLowerLimit and conditionUpperLimit)
	 *		7->hero exhaustion (defined in conditionLowerLimit and conditionUpperLimit)
	 * 
	 * @param condition the new condition
	 */
	public void setCondition(
			long condition) {
		this.condition = condition;
	}

	/**
	 * Returns the conditionLowerLimit
	 * 
	 * @return the conditionLowerLimit
	 */
	public long getConditionLowerLimit() {
		return conditionLowerLimit;
	}

	/**
	 * Sets the conditionLowerLimit
	 * 
	 * @param conditionLowerLimit the new conditionLowerLimit
	 */
	public void setConditionLowerLimit(
			long conditionLowerLimit) {
		this.conditionLowerLimit = conditionLowerLimit;
	}

	/**
	 * Returns the conditionUpperLimit
	 * 
	 * @return the conditionUpperLimit
	 */
	public long getConditionUpperLimit() {
		return conditionUpperLimit;
	}

	/**
	 * Sets the conditionUpperLimit
	 * 
	 * @param conditionUpperLimit the new conditionUpperLimit
	 */
	public void setConditionUpperLimit(
			long conditionUpperLimit) {
		this.conditionUpperLimit = conditionUpperLimit;
	}

	/**
	 * Returns the onSwitch
	 * 
	 * @return the onSwitch
	 */
	public long getOnSwitch() {
		return onSwitch;
	}

	/**
	 * Sets the onSwitch
	 * 
	 * @param onSwitch the new onSwitch
	 */
	public void setOnSwitch(
			long onSwitch) {
		this.onSwitch = onSwitch;
	}

	/**
	 * Returns the offSwitch
	 * 
	 * @return the offSwitch
	 */
	public long getOffSwitch() {
		return offSwitch;
	}

	/**
	 * Sets the offSwitch
	 * 
	 * @param offSwitch the new offSwitch
	 */
	public void setOffSwitch(
			long offSwitch) {
		this.offSwitch = offSwitch;
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
	 * Returns the turnSwitchOn
	 * 
	 * @return the turnSwitchOn
	 */
	public boolean isTurnSwitchOn() {
		return turnSwitchOn;
	}

	/**
	 * Sets the turnSwitchOn
	 * 
	 * @param turnSwitchOn the new turnSwitchOn
	 */
	public void setTurnSwitchOn(
			boolean turnSwitchOn) {
		this.turnSwitchOn = turnSwitchOn;
	}

	/**
	 * Returns the turnSwitchOff
	 * 
	 * @return the turnSwitchOff
	 */
	public boolean isTurnSwitchOff() {
		return turnSwitchOff;
	}

	/**
	 * Sets the turnSwitchOff
	 * 
	 * @param turnSwitchOff the new turnSwitchOff
	 */
	public void setTurnSwitchOff(
			boolean turnSwitchOff) {
		this.turnSwitchOff = turnSwitchOff;
	}

	/**
	 * Returns the byte-representation of this MonsterAction
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		return Helper.concatAll(new LuciferBaseUnit(0x01, DataReader.intToRPGint(subject)).write(new byte[0]),
				new LuciferBaseUnit(0x02, DataReader.intToRPGint(behavior)).write(new byte[0]),
				new LuciferBaseUnit(0x03, DataReader.intToRPGint(skill)).write(new byte[0]),
				new LuciferBaseUnit(0x04, DataReader.intToRPGint(transform)).write(new byte[0]),
				new LuciferBaseUnit(0x05, DataReader.intToRPGint(condition)).write(new byte[0]),
				new LuciferBaseUnit(0x06, DataReader.intToRPGint(conditionLowerLimit)).write(new byte[0]),
				new LuciferBaseUnit(0x07, DataReader.intToRPGint(conditionUpperLimit)).write(new byte[0]),
				new LuciferBaseUnit(0x09, DataReader.intToRPGint(turnSwitchOn ? 1 : 0)).write(new byte[0]),
				new LuciferBaseUnit(0x0A, DataReader.intToRPGint(onSwitch)).write(new byte[0]),
				new LuciferBaseUnit(0x0B, DataReader.intToRPGint(turnSwitchOff ? 1 : 0)).write(new byte[0]),
				new LuciferBaseUnit(0x0C, DataReader.intToRPGint(offSwitch)).write(new byte[0]),
				new LuciferBaseUnit(0x0D, DataReader.intToRPGint(priority)).write(new byte[0]),
				new byte[]{0}
				);
	}
}