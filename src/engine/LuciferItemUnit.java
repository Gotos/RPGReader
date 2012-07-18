package engine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author alina
 * 
 * This class represents an Item of the Database of the RPG-Maker-Game.
 * 
 */
public class LuciferItemUnit {
	
	private String name							= "";
	private String explanation					= "";
	private long classification					= 0;
	private long price							= 0;
	private long useNumber						= 1;
	private long attackChange					= 0;
	private long defenceChange					= 0;
	private long mindChange						= 0;
	private long agilityChange					= 0;
	private long mpCost							= 0;
	private long hitChance						= 90;
	private long criticalHitChance				= 0;
	private long battleAnimation				= 1;
	private long hpRecoveryPercentage			= 0;
	private long hpRecoveryFix					= 0;
	private long mpRecoveryPercentage			= 0;
	private long mpRecoveryFix					= 0;
	private long onSwitch						= 0;
	private long nrHeroes						= 0;
	private long nrConditions					= 0;
	private long nrAttributes					= 0;
	private long changingChance					= 0;
	private ArrayList<Boolean> conditions		= new ArrayList<Boolean>();
	private ArrayList<Boolean> attributes		= new ArrayList<Boolean>();
	private ArrayList<Boolean> heroes			= new ArrayList<Boolean>();
	private boolean equipBothHands				= false;
	private boolean preemptiveAttack			= false;
	private boolean doubleAttack				= false;
	private boolean attackAllEnemies			= false;
	private boolean ignoreMonsterEvasion		= false;
	private boolean preventCriticalHit			= false;
	private boolean raiseAvoid					= false;
	private boolean halfMP						= false;
	private boolean noTerrainDamage				= false;
	private boolean effectRangeWholeParty		= false;
	private boolean onlyUseInField				= false;
	private boolean onlyOnUnconsciousHeroes		= false;
	private boolean usingMessageOfSpecialSkill	= false;
	private boolean availableAtField			= true;
	private boolean availableAtCombat			= false;
	private long permanentHPChange				= 0;
	private long permanentMPChange				= 0;
	private long permanentAttackChange			= 0;
	private long permanentDefenceChange			= 0;
	private long permanentMindChange			= 0;
	private long permanentAgilityChange			= 0;
	
	/**
	 * Constructs a new LuciferItemUnit
	 * 
	 * @param bytes byte-Array which represents the LuciferItemUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferItemUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferHeroUnit
	 * 
	 * @param dr DataReader which represents the LuciferItemUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferItemUnit(DataReader dr) throws IOException {
		init(dr);
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
				classification = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				price = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
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
				heroes = new ArrayList<Boolean>((int) nrHeroes);
				for (int i = 0; i < nrHeroes; i++) {
					heroes.add(tmp.nextInt() == 1);
				}
				break;
			case 0x3F:
				nrConditions = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x40:
				tmp = new DataReader(unit.content);
				conditions = new ArrayList<Boolean>((int) nrConditions);
				for (int i = 0; i < nrConditions; i++) {
					conditions.add(tmp.nextInt() == 1);
				}
				break;
			case 0x41:
				nrAttributes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x42:
				tmp = new DataReader(unit.content);
				attributes = new ArrayList<Boolean>((int) nrAttributes);
				for (int i = 1; i <= nrAttributes; i++) {
					attributes.add(tmp.nextInt() == 1);
				}
				break;
			case 0x43:
				changingChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferItemUnit! ID: " + unit.id);
				break;
			}
			unit = sr.nextUnit();
		}
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
	 * Returns the explanation
	 * 
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * Sets the explanation
	 * 
	 * @param explanation the new explanation
	 */
	public void setExplanation(
			String explanation) {
		this.explanation = explanation;
	}

	/**
	 * Returns the classification
	 * 
	 * 0->Common Goods
	 * 1->Arms
	 * 2->Shield
	 * 3->Armor
	 * 4->Helmet
	 * 5->Other
	 * 6->Medicine
	 * 7->Book
	 * 8->Material
	 * 9->Unique
	 * 10->Switch
	 * 
	 * @return the classification
	 */
	public long getClassification() {
		return classification;
	}

	/**
	 * Sets the classification
	 * 
	 * 0->Common Goods
	 * 1->Arms
	 * 2->Shield
	 * 3->Armor
	 * 4->Helmet
	 * 5->Other
	 * 6->Medicine
	 * 7->Book
	 * 8->Material
	 * 9->Unique
	 * 10->Switch
	 * 
	 * @param classification the new classification
	 */
	public void setClassification(
			long classification) {
		this.classification = classification;
	}

	/**
	 * Returns the price
	 * 
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * Sets the price
	 * 
	 * @param price the new price
	 */
	public void setPrice(
			long price) {
		this.price = price;
	}

	/**
	 * Returns the useNumber. 0 means infinite uses.
	 * 
	 * @return the useNumber
	 */
	public long getUseNumber() {
		return useNumber;
	}

	/**
	 * Sets the useNumber. 0 means infinite uses.
	 * 
	 * @param useNumber the new useNumber
	 */
	public void setUseNumber(
			long useNumber) {
		this.useNumber = useNumber;
	}

	/**
	 * Returns the attackChange
	 * 
	 * @return the attackChange
	 */
	public long getAttackChange() {
		return attackChange;
	}

	/**
	 * Sets the attackChange
	 * 
	 * @param attackChange the new attackChange
	 */
	public void setAttackChange(
			long attackChange) {
		this.attackChange = attackChange;
	}

	/**
	 * Returns the defenceChange
	 * 
	 * @return the defenceChange
	 */
	public long getDefenceChange() {
		return defenceChange;
	}

	/**
	 * Sets the defenceChange
	 * 
	 * @param defenceChange the new defenceChange
	 */
	public void setDefenceChange(
			long defenceChange) {
		this.defenceChange = defenceChange;
	}

	/**
	 * Returns the mindChange
	 * 
	 * @return the mindChange
	 */
	public long getMindChange() {
		return mindChange;
	}

	/**
	 * Sets the mindChange
	 * 
	 * @param mindChange the new mindChange
	 */
	public void setMindChange(
			long mindChange) {
		this.mindChange = mindChange;
	}

	/**
	 * Returns the agilityChange
	 * 
	 * @return the agilityChange
	 */
	public long getAgilityChange() {
		return agilityChange;
	}

	/**
	 * Sets the agilityChange
	 * 
	 * @param agilityChange the new agilityChange
	 */
	public void setAgilityChange(
			long agilityChange) {
		this.agilityChange = agilityChange;
	}

	/**
	 * Returns the mpCost
	 * 
	 * @return the mpCost
	 */
	public long getMpCost() {
		return mpCost;
	}

	/**
	 * Sets the mpCost
	 * 
	 * @param mpCost the new mpCost
	 */
	public void setMpCost(
			long mpCost) {
		this.mpCost = mpCost;
	}

	/**
	 * Returns the hitChance
	 * 
	 * @return the hitChance
	 */
	public long getHitChance() {
		return hitChance;
	}

	/**
	 * Sets the hitChance
	 * 
	 * @param hitChance the new hitChance
	 */
	public void setHitChance(
			long hitChance) {
		this.hitChance = hitChance;
	}

	/**
	 * Returns the criticalHitChance
	 * 
	 * @return the criticalHitChance
	 */
	public long getCriticalHitChance() {
		return criticalHitChance;
	}

	/**
	 * Sets the criticalHitChance
	 * 
	 * @param criticalHitChance the new criticalHitChance
	 */
	public void setCriticalHitChance(
			long criticalHitChance) {
		this.criticalHitChance = criticalHitChance;
	}

	/**
	 * Returns the battleAnimation
	 * 
	 * @return the battleAnimation
	 */
	public long getBattleAnimation() {
		return battleAnimation;
	}

	/**
	 * Sets the battleAnimation
	 * 
	 * @param battleAnimation the new battleAnimation
	 */
	public void setBattleAnimation(
			long battleAnimation) {
		this.battleAnimation = battleAnimation;
	}

	/**
	 * Returns the hpRecoveryPercentage
	 * 
	 * @return the hpRecoveryPercentage
	 */
	public long getHpRecoveryPercentage() {
		return hpRecoveryPercentage;
	}

	/**
	 * Sets the hpRecoveryPercentage
	 * 
	 * @param hpRecoveryPercentage the new hpRecoveryPercentage
	 */
	public void setHpRecoveryPercentage(
			long hpRecoveryPercentage) {
		this.hpRecoveryPercentage = hpRecoveryPercentage;
	}

	/**
	 * Returns the hpRecoveryFix
	 * 
	 * @return the hpRecoveryFix
	 */
	public long getHpRecoveryFix() {
		return hpRecoveryFix;
	}

	/**
	 * Sets the hpRecoveryFix
	 * 
	 * @param hpRecoveryFix the new hpRecoveryFix
	 */
	public void setHpRecoveryFix(
			long hpRecoveryFix) {
		this.hpRecoveryFix = hpRecoveryFix;
	}

	/**
	 * Returns the mpRecoveryPercentage
	 * 
	 * @return the mpRecoveryPercentage
	 */
	public long getMpRecoveryPercentage() {
		return mpRecoveryPercentage;
	}

	/**
	 * Sets the mpRecoveryPercentage
	 * 
	 * @param mpRecoveryPercentage the new mpRecoveryPercentage
	 */
	public void setMpRecoveryPercentage(
			long mpRecoveryPercentage) {
		this.mpRecoveryPercentage = mpRecoveryPercentage;
	}

	/**
	 * Returns the mpRecoveryFix
	 * 
	 * @return the mpRecoveryFix
	 */
	public long getMpRecoveryFix() {
		return mpRecoveryFix;
	}

	/**
	 * Sets the mpRecoveryFix
	 * 
	 * @param mpRecoveryFix the new mpRecoveryFix
	 */
	public void setMpRecoveryFix(
			long mpRecoveryFix) {
		this.mpRecoveryFix = mpRecoveryFix;
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
	 * Returns the changingChance
	 * 
	 * @return the changingChance
	 */
	public long getChangingChance() {
		return changingChance;
	}

	/**
	 * Sets the changingChance
	 * 
	 * @param changingChance the new changingChance
	 */
	public void setChangingChance(
			long changingChance) {
		this.changingChance = changingChance;
	}

	/**
	 * Returns the conditions
	 * 
	 * @return the conditions
	 */
	public ArrayList<Boolean> getConditions() {
		return conditions;
	}

	/**
	 * Sets the conditions
	 * 
	 * @param conditions the new conditions
	 */
	public void setConditions(
			ArrayList<Boolean> conditions) {
		this.conditions = conditions;
	}

	/**
	 * Returns the attributes
	 * 
	 * @return the attributes
	 */
	public ArrayList<Boolean> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes
	 * 
	 * @param attributes the new attributes
	 */
	public void setAttributes(
			ArrayList<Boolean> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Returns the heroes
	 * 
	 * @return the heroes
	 */
	public ArrayList<Boolean> getHeroes() {
		return heroes;
	}

	/**
	 * Sets the heroes
	 * 
	 * @param heroes the new heroes
	 */
	public void setHeroes(
			ArrayList<Boolean> heroes) {
		this.heroes = heroes;
	}

	/**
	 * Returns the equipBothHands
	 * 
	 * @return the equipBothHands
	 */
	public boolean isEquipBothHands() {
		return equipBothHands;
	}

	/**
	 * Sets the equipBothHands
	 * 
	 * @param equipBothHands the new equipBothHands
	 */
	public void setEquipBothHands(
			boolean equipBothHands) {
		this.equipBothHands = equipBothHands;
	}

	/**
	 * Returns the preemptiveAttack
	 * 
	 * @return the preemptiveAttack
	 */
	public boolean isPreemptiveAttack() {
		return preemptiveAttack;
	}

	/**
	 * Sets the preemptiveAttack
	 * 
	 * @param preemptiveAttack the new preemptiveAttack
	 */
	public void setPreemptiveAttack(
			boolean preemptiveAttack) {
		this.preemptiveAttack = preemptiveAttack;
	}

	/**
	 * Returns the doubleAttack
	 * 
	 * @return the doubleAttack
	 */
	public boolean isDoubleAttack() {
		return doubleAttack;
	}

	/**
	 * Sets the doubleAttack
	 * 
	 * @param doubleAttack the new doubleAttack
	 */
	public void setDoubleAttack(
			boolean doubleAttack) {
		this.doubleAttack = doubleAttack;
	}

	/**
	 * Returns the attackAllEnemies
	 * 
	 * @return the attackAllEnemies
	 */
	public boolean isAttackAllEnemies() {
		return attackAllEnemies;
	}

	/**
	 * Sets the attackAllEnemies
	 * 
	 * @param attackAllEnemies the new attackAllEnemies
	 */
	public void setAttackAllEnemies(
			boolean attackAllEnemies) {
		this.attackAllEnemies = attackAllEnemies;
	}

	/**
	 * Returns the ignoreMonsterEvasion
	 * 
	 * @return the ignoreMonsterEvasion
	 */
	public boolean isIgnoreMonsterEvasion() {
		return ignoreMonsterEvasion;
	}

	/**
	 * Sets the ignoreMonsterEvasion
	 * 
	 * @param ignoreMonsterEvasion the new ignoreMonsterEvasion
	 */
	public void setIgnoreMonsterEvasion(
			boolean ignoreMonsterEvasion) {
		this.ignoreMonsterEvasion = ignoreMonsterEvasion;
	}

	/**
	 * Returns the preventCriticalHit
	 * 
	 * @return the preventCriticalHit
	 */
	public boolean isPreventCriticalHit() {
		return preventCriticalHit;
	}

	/**
	 * Sets the preventCriticalHit
	 * 
	 * @param preventCriticalHit the new preventCriticalHit
	 */
	public void setPreventCriticalHit(
			boolean preventCriticalHit) {
		this.preventCriticalHit = preventCriticalHit;
	}

	/**
	 * Returns the raiseAvoid
	 * 
	 * @return the raiseAvoid
	 */
	public boolean isRaiseAvoid() {
		return raiseAvoid;
	}

	/**
	 * Sets the raiseAvoid
	 * 
	 * @param raiseAvoid the new raiseAvoid
	 */
	public void setRaiseAvoid(
			boolean raiseAvoid) {
		this.raiseAvoid = raiseAvoid;
	}

	/**
	 * Returns the halfMP
	 * 
	 * @return the halfMP
	 */
	public boolean isHalfMP() {
		return halfMP;
	}

	/**
	 * Sets the halfMP
	 * 
	 * @param halfMP the new halfMP
	 */
	public void setHalfMP(
			boolean halfMP) {
		this.halfMP = halfMP;
	}

	/**
	 * Returns the noTerrainDamage
	 * 
	 * @return the noTerrainDamage
	 */
	public boolean isNoTerrainDamage() {
		return noTerrainDamage;
	}

	/**
	 * Sets the noTerrainDamage
	 * 
	 * @param noTerrainDamage the new noTerrainDamage
	 */
	public void setNoTerrainDamage(
			boolean noTerrainDamage) {
		this.noTerrainDamage = noTerrainDamage;
	}

	/**
	 * Returns the effectRangeWholeParty
	 * 
	 * @return the effectRangeWholeParty
	 */
	public boolean isEffectRangeWholeParty() {
		return effectRangeWholeParty;
	}

	/**
	 * Sets the effectRangeWholeParty
	 * 
	 * @param effectRangeWholeParty the new effectRangeWholeParty
	 */
	public void setEffectRangeWholeParty(
			boolean effectRangeWholeParty) {
		this.effectRangeWholeParty = effectRangeWholeParty;
	}

	/**
	 * Returns the onlyUseInField. Only relevant for medicine.
	 * 
	 * @return the onlyUseInField
	 */
	public boolean isOnlyUseInField() {
		return onlyUseInField;
	}

	/**
	 * Sets the onlyUseInField-. Only relevant for medicine.
	 * 
	 * @param onlyUseInField the new onlyUseInField
	 */
	public void setOnlyUseInField(
			boolean onlyUseInField) {
		this.onlyUseInField = onlyUseInField;
	}

	/**
	 * Returns the onlyOnUnconsciousHeroes
	 * 
	 * @return the onlyOnUnconsciousHeroes
	 */
	public boolean isOnlyOnUnconsciousHeroes() {
		return onlyOnUnconsciousHeroes;
	}

	/**
	 * Sets the onlyOnUnconsciousHeroes
	 * 
	 * @param onlyOnUnconsciousHeroes the new onlyOnUnconsciousHeroes
	 */
	public void setOnlyOnUnconsciousHeroes(
			boolean onlyOnUnconsciousHeroes) {
		this.onlyOnUnconsciousHeroes = onlyOnUnconsciousHeroes;
	}

	/**
	 * Returns the usingMessageOfSpecialSkill
	 * 
	 * @return the usingMessageOfSpecialSkill
	 */
	public boolean isUsingMessageOfSpecialSkill() {
		return usingMessageOfSpecialSkill;
	}

	/**
	 * Sets the usingMessageOfSpecialSkill
	 * 
	 * @param usingMessageOfSpecialSkill the new usingMessageOfSpecialSkill
	 */
	public void setUsingMessageOfSpecialSkill(
			boolean usingMessageOfSpecialSkill) {
		this.usingMessageOfSpecialSkill = usingMessageOfSpecialSkill;
	}

	/**
	 * Returns the availableAtField
	 * 
	 * @return the availableAtField
	 */
	public boolean isAvailableAtField() {
		return availableAtField;
	}

	/**
	 * Sets the availableAtField
	 * 
	 * @param availableAtField the new availableAtField
	 */
	public void setAvailableAtField(
			boolean availableAtField) {
		this.availableAtField = availableAtField;
	}

	/**
	 * Returns the availableAtCombat
	 * 
	 * @return the availableAtCombat
	 */
	public boolean isAvailableAtCombat() {
		return availableAtCombat;
	}

	/**
	 * Sets the availableAtCombat
	 * 
	 * @param availableAtCombat the new availableAtCombat
	 */
	public void setAvailableAtCombat(
			boolean availableAtCombat) {
		this.availableAtCombat = availableAtCombat;
	}

	/**
	 * Returns the permanentHPChange
	 * 
	 * @return the permanentHPChange
	 */
	public long getPermanentHPChange() {
		return permanentHPChange;
	}

	/**
	 * Sets the permanentHPChange
	 * 
	 * @param permanentHPChange the new permanentHPChange
	 */
	public void setPermanentHPChange(
			long permanentHPChange) {
		this.permanentHPChange = permanentHPChange;
	}

	/**
	 * Returns the permanentMPChange
	 * 
	 * @return the permanentMPChange
	 */
	public long getPermanentMPChange() {
		return permanentMPChange;
	}

	/**
	 * Sets the permanentMPChange
	 * 
	 * @param permanentMPChange the new permanentMPChange
	 */
	public void setPermanentMPChange(
			long permanentMPChange) {
		this.permanentMPChange = permanentMPChange;
	}

	/**
	 * Returns the permanentAttackChange
	 * 
	 * @return the permanentAttackChange
	 */
	public long getPermanentAttackChange() {
		return permanentAttackChange;
	}

	/**
	 * Sets the permanentAttackChange
	 * 
	 * @param permanentAttackChange the new permanentAttackChange
	 */
	public void setPermanentAttackChange(
			long permanentAttackChange) {
		this.permanentAttackChange = permanentAttackChange;
	}

	/**
	 * Returns the permanentDefenceChange
	 * 
	 * @return the permanentDefenceChange
	 */
	public long getPermanentDefenceChange() {
		return permanentDefenceChange;
	}

	/**
	 * Sets the permanentDefenceChange
	 * 
	 * @param permanentDefenceChange the new permanentDefenceChange
	 */
	public void setPermanentDefenceChange(
			long permanentDefenceChange) {
		this.permanentDefenceChange = permanentDefenceChange;
	}

	/**
	 * Returns the permanentMindChange
	 * 
	 * @return the permanentMindChange
	 */
	public long getPermanentMindChange() {
		return permanentMindChange;
	}

	/**
	 * Sets the permanentMindChange
	 * 
	 * @param permanentMindChange the new permanentMindChange
	 */
	public void setPermanentMindChange(
			long permanentMindChange) {
		this.permanentMindChange = permanentMindChange;
	}

	/**
	 * Returns the permanentAgilityChange
	 * 
	 * @return the permanentAgilityChange
	 */
	public long getPermanentAgilityChange() {
		return permanentAgilityChange;
	}

	/**
	 * Sets the permanentAgilityChange
	 * 
	 * @param permanentAgilityChange the new permanentAgilityChange
	 */
	public void setPermanentAgilityChange(
			long permanentAgilityChange) {
		this.permanentAgilityChange = permanentAgilityChange;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + (int) (agilityChange ^ (agilityChange >>> 32));
		result = prime
				* result + (attackAllEnemies ? 1231
						: 1237);
		result = prime
				* result + (int) (attackChange ^ (attackChange >>> 32));
		result = prime
				* result + ((attributes == null) ? 0
						: attributes.hashCode());
		result = prime
				* result + (availableAtCombat ? 1231
						: 1237);
		result = prime
				* result + (availableAtField ? 1231
						: 1237);
		result = prime
				* result + (int) (battleAnimation ^ (battleAnimation >>> 32));
		result = prime
				* result + (int) (changingChance ^ (changingChance >>> 32));
		result = prime
				* result + (int) (classification ^ (classification >>> 32));
		result = prime
				* result + ((conditions == null) ? 0
						: conditions.hashCode());
		result = prime
				* result + (int) (criticalHitChance ^ (criticalHitChance >>> 32));
		result = prime
				* result + (int) (defenceChange ^ (defenceChange >>> 32));
		result = prime
				* result + (doubleAttack ? 1231
						: 1237);
		result = prime
				* result + (effectRangeWholeParty ? 1231
						: 1237);
		result = prime
				* result + (equipBothHands ? 1231
						: 1237);
		result = prime
				* result + ((explanation == null) ? 0
						: explanation.hashCode());
		result = prime
				* result + (halfMP ? 1231
						: 1237);
		result = prime
				* result + ((heroes == null) ? 0
						: heroes.hashCode());
		result = prime
				* result + (int) (hitChance ^ (hitChance >>> 32));
		result = prime
				* result + (int) (hpRecoveryFix ^ (hpRecoveryFix >>> 32));
		result = prime
				* result + (int) (hpRecoveryPercentage ^ (hpRecoveryPercentage >>> 32));
		result = prime
				* result + (ignoreMonsterEvasion ? 1231
						: 1237);
		result = prime
				* result + (int) (mindChange ^ (mindChange >>> 32));
		result = prime
				* result + (int) (mpCost ^ (mpCost >>> 32));
		result = prime
				* result + (int) (mpRecoveryFix ^ (mpRecoveryFix >>> 32));
		result = prime
				* result + (int) (mpRecoveryPercentage ^ (mpRecoveryPercentage >>> 32));
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + (noTerrainDamage ? 1231
						: 1237);
		result = prime
				* result + (int) (onSwitch ^ (onSwitch >>> 32));
		result = prime
				* result + (onlyOnUnconsciousHeroes ? 1231
						: 1237);
		result = prime
				* result + (onlyUseInField ? 1231
						: 1237);
		result = prime
				* result + (int) (permanentAgilityChange ^ (permanentAgilityChange >>> 32));
		result = prime
				* result + (int) (permanentAttackChange ^ (permanentAttackChange >>> 32));
		result = prime
				* result + (int) (permanentDefenceChange ^ (permanentDefenceChange >>> 32));
		result = prime
				* result + (int) (permanentHPChange ^ (permanentHPChange >>> 32));
		result = prime
				* result + (int) (permanentMPChange ^ (permanentMPChange >>> 32));
		result = prime
				* result + (int) (permanentMindChange ^ (permanentMindChange >>> 32));
		result = prime
				* result + (preemptiveAttack ? 1231
						: 1237);
		result = prime
				* result + (preventCriticalHit ? 1231
						: 1237);
		result = prime
				* result + (int) (price ^ (price >>> 32));
		result = prime
				* result + (raiseAvoid ? 1231
						: 1237);
		result = prime
				* result + (int) (useNumber ^ (useNumber >>> 32));
		result = prime
				* result + (usingMessageOfSpecialSkill ? 1231
						: 1237);
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
		if (!(obj instanceof LuciferItemUnit)) {
			return false;
		}
		LuciferItemUnit other = (LuciferItemUnit) obj;
		if (agilityChange != other.agilityChange) {
			return false;
		}
		if (attackAllEnemies != other.attackAllEnemies) {
			return false;
		}
		if (attackChange != other.attackChange) {
			return false;
		}
		if (attributes == null) {
			if (other.attributes != null) {
				return false;
			}
		} else if (!attributes.equals(other.attributes)) {
			return false;
		}
		if (availableAtCombat != other.availableAtCombat) {
			return false;
		}
		if (availableAtField != other.availableAtField) {
			return false;
		}
		if (battleAnimation != other.battleAnimation) {
			return false;
		}
		if (changingChance != other.changingChance) {
			return false;
		}
		if (classification != other.classification) {
			return false;
		}
		if (conditions == null) {
			if (other.conditions != null) {
				return false;
			}
		} else if (!conditions.equals(other.conditions)) {
			return false;
		}
		if (criticalHitChance != other.criticalHitChance) {
			return false;
		}
		if (defenceChange != other.defenceChange) {
			return false;
		}
		if (doubleAttack != other.doubleAttack) {
			return false;
		}
		if (effectRangeWholeParty != other.effectRangeWholeParty) {
			return false;
		}
		if (equipBothHands != other.equipBothHands) {
			return false;
		}
		if (explanation == null) {
			if (other.explanation != null) {
				return false;
			}
		} else if (!explanation.equals(other.explanation)) {
			return false;
		}
		if (halfMP != other.halfMP) {
			return false;
		}
		if (heroes == null) {
			if (other.heroes != null) {
				return false;
			}
		} else if (!heroes.equals(other.heroes)) {
			return false;
		}
		if (hitChance != other.hitChance) {
			return false;
		}
		if (hpRecoveryFix != other.hpRecoveryFix) {
			return false;
		}
		if (hpRecoveryPercentage != other.hpRecoveryPercentage) {
			return false;
		}
		if (ignoreMonsterEvasion != other.ignoreMonsterEvasion) {
			return false;
		}
		if (mindChange != other.mindChange) {
			return false;
		}
		if (mpCost != other.mpCost) {
			return false;
		}
		if (mpRecoveryFix != other.mpRecoveryFix) {
			return false;
		}
		if (mpRecoveryPercentage != other.mpRecoveryPercentage) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (noTerrainDamage != other.noTerrainDamage) {
			return false;
		}
		if (onSwitch != other.onSwitch) {
			return false;
		}
		if (onlyOnUnconsciousHeroes != other.onlyOnUnconsciousHeroes) {
			return false;
		}
		if (onlyUseInField != other.onlyUseInField) {
			return false;
		}
		if (permanentAgilityChange != other.permanentAgilityChange) {
			return false;
		}
		if (permanentAttackChange != other.permanentAttackChange) {
			return false;
		}
		if (permanentDefenceChange != other.permanentDefenceChange) {
			return false;
		}
		if (permanentHPChange != other.permanentHPChange) {
			return false;
		}
		if (permanentMPChange != other.permanentMPChange) {
			return false;
		}
		if (permanentMindChange != other.permanentMindChange) {
			return false;
		}
		if (preemptiveAttack != other.preemptiveAttack) {
			return false;
		}
		if (preventCriticalHit != other.preventCriticalHit) {
			return false;
		}
		if (price != other.price) {
			return false;
		}
		if (raiseAvoid != other.raiseAvoid) {
			return false;
		}
		if (useNumber != other.useNumber) {
			return false;
		}
		if (usingMessageOfSpecialSkill != other.usingMessageOfSpecialSkill) {
			return false;
		}
		return true;
	}
}