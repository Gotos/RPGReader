package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * @author alina
 * 
 * This class represents a Hero of the Database of the RPG-Maker-Game.
 * 
 */
public class LuciferHeroUnit {

	private String name							= "";
	private String degree						= "";
	private String graphicFile					= "";
	private String faceGraphicFile				= "";
	private String skillName						= "";
	private short graphicIndex					= 0;
	private long minLevel						= 1;
	private long maxLevel						= 50;
	private long chanceOnCritical				= 30;
	private long faceGraphicIndex				= 0;
	private long expBase						= 30;
	private long expAdditional					= 30;
	private long expCorrection					= 30;
	private long weapon							= 0;
	private long shild							= 0;
	private long armor							= 0;
	private long helmet							= 0;
	private long other							= 0;
	private long unarmedAnimation				= 0;
	private long nrConditions					= 0;
	private long nrAttributes					= 0;
	private long[] hp							= new long[51];
	private long[] mp							= new long[51];
	private long[] attack						= new long[51];
	private long[] defense						= new long[51];
	private long[] mind							= new long[51];
	private long[] agility						= new long[51];
	private ArrayList<Long> conditions			= new ArrayList<Long>();
	private ArrayList<Long> attributes			= new ArrayList<Long>();
	private ArrayList<LuciferHeroSpell> skills	= new ArrayList<LuciferHeroSpell>();
	private boolean graphicTransparent			= false;
	private boolean allowCritical				= true;
	private boolean dualWield					= false;
	private boolean fixedEquipment				= false;
	private boolean aiControl					= false;
	private boolean strongDefense				= false;
	private boolean renameSkill					= false;
	
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
	 * Returns the degree
	 * 
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * Sets the degree
	 * 
	 * @param degree the new degree
	 */
	public void setDegree(
			String degree) {
		this.degree = degree;
	}

	/**
	 * Returns the graphicFile
	 * 
	 * @return the graphicFile
	 */
	public String getGraphicFile() {
		return graphicFile;
	}

	/**
	 * Sets the graphicFile
	 * 
	 * @param graphicFile the new graphicFile
	 */
	public void setGraphicFile(
			String graphicFile) {
		this.graphicFile = graphicFile;
	}

	/**
	 * Returns the faceGraphicFile
	 * 
	 * @return the faceGraphicFile
	 */
	public String getFaceGraphicFile() {
		return faceGraphicFile;
	}

	/**
	 * Sets the faceGraphicFile
	 * 
	 * @param faceGraphicFile the new faceGraphicFile
	 */
	public void setFaceGraphicFile(
			String faceGraphicFile) {
		this.faceGraphicFile = faceGraphicFile;
	}

	/**
	 * Returns the skillName
	 * 
	 * @return the skillName
	 */
	public String getSkillName() {
		return skillName;
	}

	/**
	 * Sets the skillName
	 * 
	 * @param skillName the new skillName
	 */
	public void setSkillName(
			String skillName) {
		this.skillName = skillName;
	}

	/**
	 * Returns the graphicIndex
	 * 
	 * @return the graphicIndex
	 */
	public short getGraphicIndex() {
		return graphicIndex;
	}

	/**
	 * Sets the graphicIndex
	 * 
	 * @param graphicIndex the new graphicIndex
	 */
	public void setGraphicIndex(
			short graphicIndex) {
		this.graphicIndex = graphicIndex;
	}

	/**
	 * Returns the minLevel
	 * 
	 * @return the minLevel
	 */
	public long getMinLevel() {
		return minLevel;
	}

	/**
	 * Sets the minLevel
	 * 
	 * @param minLevel the new minLevel
	 */
	public void setMinLevel(
			long minLevel) {
		this.minLevel = minLevel;
	}

	/**
	 * Returns the maxLevel
	 * 
	 * @return the maxLevel
	 */
	public long getMaxLevel() {
		return maxLevel;
	}

	/**
	 * Sets the maxLevel
	 * 
	 * @param maxLevel the new maxLevel
	 */
	public void setMaxLevel(
			long maxLevel) {
		this.maxLevel = maxLevel;
	}

	/**
	 * Returns the chanceOnCritical; Percentage is a mistranslation - proper format is 1 in X
	 * 
	 * @return the chanceOnCritical
	 */
	public long getChanceOnCritical() {
		return chanceOnCritical;
	}

	/**
	 * Sets the chanceOnCritical; Percentage is a mistranslation - proper format is 1 in X
	 * 
	 * @param chanceOnCritical the new chanceOnCritical
	 */
	public void setChanceOnCritical(
			long chanceOnCritical) {
		this.chanceOnCritical = chanceOnCritical;
	}

	/**
	 * Returns the faceGraphicIndex
	 * 
	 * @return the faceGraphicIndex
	 */
	public long getFaceGraphicIndex() {
		return faceGraphicIndex;
	}

	/**
	 * Sets the faceGraphicIndex
	 * 
	 * @param faceGraphicIndex the new faceGraphicIndex
	 */
	public void setFaceGraphicIndex(
			long faceGraphicIndex) {
		this.faceGraphicIndex = faceGraphicIndex;
	}

	/**
	 * Returns the expBase
	 * 
	 * @return the expBase
	 */
	public long getExpBase() {
		return expBase;
	}

	/**
	 * Sets the expBase
	 * 
	 * @param expBase the new expBase
	 */
	public void setExpBase(
			long expBase) {
		this.expBase = expBase;
	}

	/**
	 * Returns the expAdditional
	 * 
	 * @return the expAdditional
	 */
	public long getExpAdditional() {
		return expAdditional;
	}

	/**
	 * Sets the expAdditional
	 * 
	 * @param expAdditional the new expAdditional
	 */
	public void setExpAdditional(
			long expAdditional) {
		this.expAdditional = expAdditional;
	}

	/**
	 * Returns the expCorrection
	 * 
	 * @return the expCorrection
	 */
	public long getExpCorrection() {
		return expCorrection;
	}

	/**
	 * Sets the expCorrection
	 * 
	 * @param expCorrection the new expCorrection
	 */
	public void setExpCorrection(
			long expCorrection) {
		this.expCorrection = expCorrection;
	}

	/**
	 * Returns the weapon
	 * 
	 * @return the weapon
	 */
	public long getWeapon() {
		return weapon;
	}

	/**
	 * Sets the weapon
	 * 
	 * @param weapon the new weapon
	 */
	public void setWeapon(
			long weapon) {
		this.weapon = weapon;
	}

	/**
	 * Returns the shild or secound Weapon
	 * 
	 * @return the shild or secound Weapon
	 */
	public long getShild() {
		return shild;
	}

	/**
	 * Sets the shild or secound Weapon
	 * 
	 * @param shild the new shild or secound Weapon
	 */
	public void setShild(
			long shild) {
		this.shild = shild;
	}

	/**
	 * Returns the secound Weapon or shild
	 * 
	 * @return the secound Weapon or shild
	 */
	public long getSecoundWeapon() {
		return shild;
	}

	/**
	 * Sets the secound Weapon or Shild
	 * 
	 * @param secoundWeapon the new secound Weapon or Shild
	 */
	public void setSecoundWeapon(
			long secoundWeapon) {
		this.shild = secoundWeapon;
	}
	
	/**
	 * Returns the armor
	 * 
	 * @return the armor
	 */
	public long getArmor() {
		return armor;
	}

	/**
	 * Sets the armor
	 * 
	 * @param armor the new armor
	 */
	public void setArmor(
			long armor) {
		this.armor = armor;
	}

	/**
	 * Returns the helmet
	 * 
	 * @return the helmet
	 */
	public long getHelmet() {
		return helmet;
	}

	/**
	 * Sets the helmet
	 * 
	 * @param helmet the new helmet
	 */
	public void setHelmet(
			long helmet) {
		this.helmet = helmet;
	}

	/**
	 * Returns the 'other'-equipment
	 * 
	 * @return the 'other'-equipment
	 */
	public long getOther() {
		return other;
	}

	/**
	 * Sets the 'other'-equipment
	 * 
	 * @param other the new 'other'-equipment
	 */
	public void setOther(
			long other) {
		this.other = other;
	}

	/**
	 * Returns the unarmedAnimation
	 * 
	 * @return the unarmedAnimation
	 */
	public long getUnarmedAnimation() {
		return unarmedAnimation;
	}

	/**
	 * Sets the unarmedAnimation
	 * 
	 * @param unarmedAnimation the new unarmedAnimation
	 */
	public void setUnarmedAnimation(
			long unarmedAnimation) {
		this.unarmedAnimation = unarmedAnimation;
	}

	/**
	 * Returns the hp
	 * 
	 * @return the hp
	 */
	public long[] getHp() {
		return hp;
	}

	/**
	 * Sets the hp
	 * 
	 * @param hp the new hp
	 */
	public void setHp(
			long[] hp) {
		if (hp.length != 51) { throw new IllegalArgumentException("Array needs a size of 51!"); }
		this.hp = hp;
	}

	/**
	 * Returns the mp
	 * 
	 * @return the mp
	 */
	public long[] getMp() {
		return mp;
	}

	/**
	 * Sets the mp
	 * 
	 * @param mp the new mp
	 */
	public void setMp(
			long[] mp) {
		if (mp.length != 51) { throw new IllegalArgumentException("Array needs a size of 51!"); }
		this.mp = mp;
	}

	/**
	 * Returns the attack
	 * 
	 * @return the attack
	 */
	public long[] getAttack() {
		return attack;
	}

	/**
	 * Sets the attack
	 * 
	 * @param attack the new attack
	 */
	public void setAttack(
			long[] attack) {
		if (attack.length != 51) { throw new IllegalArgumentException("Array needs a size of 51!"); }
		this.attack = attack;
	}

	/**
	 * Returns the defense
	 * 
	 * @return the defense
	 */
	public long[] getDefense() {
		return defense;
	}

	/**
	 * Sets the defense
	 * 
	 * @param defense the new defense
	 */
	public void setDefense(
			long[] defense) {
		if (defense.length != 51) { throw new IllegalArgumentException("Array needs a size of 51!"); }
		this.defense = defense;
	}

	/**
	 * Returns the mind
	 * 
	 * @return the mind
	 */
	public long[] getMind() {
		return mind;
	}

	/**
	 * Sets the mind
	 * 
	 * @param mind the new mind
	 */
	public void setMind(
			long[] mind) {
		if (mind.length != 51) { throw new IllegalArgumentException("Array needs a size of 51!"); }
		this.mind = mind;
	}

	/**
	 * Returns the agility
	 * 
	 * @return the agility
	 */
	public long[] getAgility() {
		return agility;
	}

	/**
	 * Sets the agility
	 * 
	 * @param agility the new agility
	 */
	public void setAgility(
			long[] agility) {
		if (agility.length != 51) { throw new IllegalArgumentException("Array needs a size of 51!"); }
		this.agility = agility;
	}

	/**
	 * Returns the conditions
	 * 
	 * @return the conditions
	 */
	public ArrayList<Long> getConditions() {
		return conditions;
	}

	/**
	 * Sets the conditions
	 * 
	 * @param conditions the new conditions
	 */
	public void setConditions(
			ArrayList<Long> conditions) {
		this.conditions = conditions;
	}

	/**
	 * Returns the attributes
	 * 
	 * @return the attributes
	 */
	public ArrayList<Long> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes
	 * 
	 * @param attributes the new attributes
	 */
	public void setAttributes(
			ArrayList<Long> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Returns the skills
	 * 
	 * @return the skills
	 */
	public ArrayList<LuciferHeroSpell> getSkills() {
		return skills;
	}

	/**
	 * Sets the skills
	 * 
	 * @param skills the new skills
	 */
	public void setSkills(
			ArrayList<LuciferHeroSpell> skills) {
		this.skills = skills;
	}

	/**
	 * Returns the graphicTransparent
	 * 
	 * @return the graphicTransparent
	 */
	public boolean isGraphicTransparent() {
		return graphicTransparent;
	}

	/**
	 * Sets the graphicTransparent
	 * 
	 * @param graphicTransparent the new graphicTransparent
	 */
	public void setGraphicTransparent(
			boolean graphicTransparent) {
		this.graphicTransparent = graphicTransparent;
	}

	/**
	 * Returns the allowCritical
	 * 
	 * @return the allowCritical
	 */
	public boolean isAllowCritical() {
		return allowCritical;
	}

	/**
	 * Sets the allowCritical
	 * 
	 * @param allowCritical the new allowCritical
	 */
	public void setAllowCritical(
			boolean allowCritical) {
		this.allowCritical = allowCritical;
	}

	/**
	 * Returns the dualWield
	 * 
	 * @return the dualWield
	 */
	public boolean isDualWield() {
		return dualWield;
	}

	/**
	 * Sets the dualWield
	 * 
	 * @param dualWield the new dualWield
	 */
	public void setDualWield(
			boolean dualWield) {
		this.dualWield = dualWield;
	}

	/**
	 * Returns the fixedEquipment
	 * 
	 * @return the fixedEquipment
	 */
	public boolean isFixedEquipment() {
		return fixedEquipment;
	}

	/**
	 * Sets the fixedEquipment
	 * 
	 * @param fixedEquipment the new fixedEquipment
	 */
	public void setFixedEquipment(
			boolean fixedEquipment) {
		this.fixedEquipment = fixedEquipment;
	}

	/**
	 * Returns the aiControl
	 * 
	 * @return the aiControl
	 */
	public boolean isAiControl() {
		return aiControl;
	}

	/**
	 * Sets the aiControl
	 * 
	 * @param aiControl the new aiControl
	 */
	public void setAiControl(
			boolean aiControl) {
		this.aiControl = aiControl;
	}

	/**
	 * Returns the strongDefense
	 * 
	 * @return the strongDefense
	 */
	public boolean isStrongDefense() {
		return strongDefense;
	}

	/**
	 * Sets the strongDefense
	 * 
	 * @param strongDefense the new strongDefense
	 */
	public void setStrongDefense(
			boolean strongDefense) {
		this.strongDefense = strongDefense;
	}

	/**
	 * Returns the renameSkill
	 * 
	 * @return the renameSkill
	 */
	public boolean isRenameSkill() {
		return renameSkill;
	}

	/**
	 * Sets the renameSkill
	 * 
	 * @param renameSkill the new renameSkill
	 */
	public void setRenameSkill(
			boolean renameSkill) {
		this.renameSkill = renameSkill;
	}
	
	/**
	 * Constructs a new LuciferHeroUnit
	 * 
	 * @param bytes byte-Array which represents the LuciferHeroUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferHeroUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferHeroUnit
	 * 
	 * @param dr DataReader which represents the LuciferHeroUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferHeroUnit(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		DataReader tmp;
		while (unit.id != 0) {
			switch(unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				degree = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x03:
				graphicFile = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x04:
				graphicIndex = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				graphicTransparent = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x07:
				minLevel = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x08:
				maxLevel = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x09:
				allowCritical = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0A:
				//Percentage is a mistranslation; proper format is 1 in X
				chanceOnCritical = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0F:
				faceGraphicFile = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x10:
				faceGraphicIndex = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				dualWield = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x16:
				fixedEquipment = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x17:
				aiControl = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x18:
				strongDefense = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1F:
				tmp = new DataReader(unit.content);
				for (int i = 1; i < 51; i++) {
					hp[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					mp[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					attack[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					defense[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					mind[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					agility[i] = tmp.next16bitle();
				}
				break;
			case 0x29:
				expBase = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2A:
				expAdditional = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2B:
				expCorrection = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x33:
				tmp = new DataReader(unit.content);
				weapon = tmp.next16bitle();
				shild = tmp.next16bitle();
				armor = tmp.next16bitle();
				helmet = tmp.next16bitle();
				other = tmp.next16bitle();
				break;
			case 0x38:
				unarmedAnimation = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x3F:
				tmp = new DataReader(unit.content);
				int nr = (int) tmp.nextInt();
				for (int i = 0; i < nr; i++) {
					tmp.nextInt(); //read id
					skills.add(new LuciferHeroSpell(tmp));
				}
				break;
			case 0x42:
				renameSkill = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x43:
				skillName = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x47:
				nrConditions = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x48:
				tmp = new DataReader(unit.content);
				for (int i = 1; i <= nrConditions; i++) {
					conditions.add(tmp.nextInt());
				}
				break;
			case 0x49:
				nrAttributes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x4A:
				tmp = new DataReader(unit.content);
				for (int i = 1; i <= nrAttributes; i++) {
					attributes.add(tmp.nextInt());
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferHeroUnit! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + Arrays.hashCode(agility);
		result = prime
				* result + (aiControl ? 1231
						: 1237);
		result = prime
				* result + (allowCritical ? 1231
						: 1237);
		result = prime
				* result + (int) (armor ^ (armor >>> 32));
		result = prime
				* result + Arrays.hashCode(attack);
		result = prime
				* result + ((attributes == null) ? 0
						: attributes.hashCode());
		result = prime
				* result + (int) (chanceOnCritical ^ (chanceOnCritical >>> 32));
		result = prime
				* result + ((conditions == null) ? 0
						: conditions.hashCode());
		result = prime
				* result + Arrays.hashCode(defense);
		result = prime
				* result + ((degree == null) ? 0
						: degree.hashCode());
		result = prime
				* result + (dualWield ? 1231
						: 1237);
		result = prime
				* result + (int) (expAdditional ^ (expAdditional >>> 32));
		result = prime
				* result + (int) (expBase ^ (expBase >>> 32));
		result = prime
				* result + (int) (expCorrection ^ (expCorrection >>> 32));
		result = prime
				* result + ((faceGraphicFile == null) ? 0
						: faceGraphicFile.hashCode());
		result = prime
				* result + (int) (faceGraphicIndex ^ (faceGraphicIndex >>> 32));
		result = prime
				* result + (fixedEquipment ? 1231
						: 1237);
		result = prime
				* result + ((graphicFile == null) ? 0
						: graphicFile.hashCode());
		result = prime
				* result + (int) (graphicIndex ^ (graphicIndex >>> 32));
		result = prime
				* result + (graphicTransparent ? 1231
						: 1237);
		result = prime
				* result + (int) (helmet ^ (helmet >>> 32));
		result = prime
				* result + Arrays.hashCode(hp);
		result = prime
				* result + (int) (maxLevel ^ (maxLevel >>> 32));
		result = prime
				* result + (int) (minLevel ^ (minLevel >>> 32));
		result = prime
				* result + Arrays.hashCode(mind);
		result = prime
				* result + Arrays.hashCode(mp);
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + (int) (other ^ (other >>> 32));
		result = prime
				* result + (renameSkill ? 1231
						: 1237);
		result = prime
				* result + (int) (shild ^ (shild >>> 32));
		result = prime
				* result + ((skillName == null) ? 0
						: skillName.hashCode());
		result = prime
				* result + ((skills == null) ? 0
						: skills.hashCode());
		result = prime
				* result + (strongDefense ? 1231
						: 1237);
		result = prime
				* result + (int) (unarmedAnimation ^ (unarmedAnimation >>> 32));
		result = prime
				* result + (int) (weapon ^ (weapon >>> 32));
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
		if (!(obj instanceof LuciferHeroUnit)) {
			return false;
		}
		LuciferHeroUnit other = (LuciferHeroUnit) obj;
		if (!Arrays.equals(
				agility, other.agility)) {
			return false;
		}
		if (aiControl != other.aiControl) {
			return false;
		}
		if (allowCritical != other.allowCritical) {
			return false;
		}
		if (armor != other.armor) {
			return false;
		}
		if (!Arrays.equals(
				attack, other.attack)) {
			return false;
		}
		if (attributes == null) {
			if (other.attributes != null) {
				return false;
			}
		} else if (!attributes.equals(other.attributes)) {
			return false;
		}
		if (chanceOnCritical != other.chanceOnCritical) {
			return false;
		}
		if (conditions == null) {
			if (other.conditions != null) {
				return false;
			}
		} else if (!conditions.equals(other.conditions)) {
			return false;
		}
		if (!Arrays.equals(
				defense, other.defense)) {
			return false;
		}
		if (degree == null) {
			if (other.degree != null) {
				return false;
			}
		} else if (!degree.equals(other.degree)) {
			return false;
		}
		if (dualWield != other.dualWield) {
			return false;
		}
		if (expAdditional != other.expAdditional) {
			return false;
		}
		if (expBase != other.expBase) {
			return false;
		}
		if (expCorrection != other.expCorrection) {
			return false;
		}
		if (faceGraphicFile == null) {
			if (other.faceGraphicFile != null) {
				return false;
			}
		} else if (!faceGraphicFile.equals(other.faceGraphicFile)) {
			return false;
		}
		if (faceGraphicIndex != other.faceGraphicIndex) {
			return false;
		}
		if (fixedEquipment != other.fixedEquipment) {
			return false;
		}
		if (graphicFile == null) {
			if (other.graphicFile != null) {
				return false;
			}
		} else if (!graphicFile.equals(other.graphicFile)) {
			return false;
		}
		if (graphicIndex != other.graphicIndex) {
			return false;
		}
		if (graphicTransparent != other.graphicTransparent) {
			return false;
		}
		if (helmet != other.helmet) {
			return false;
		}
		if (!Arrays.equals(
				hp, other.hp)) {
			return false;
		}
		if (maxLevel != other.maxLevel) {
			return false;
		}
		if (minLevel != other.minLevel) {
			return false;
		}
		if (!Arrays.equals(
				mind, other.mind)) {
			return false;
		}
		if (!Arrays.equals(
				mp, other.mp)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (this.other != other.other) {
			return false;
		}
		if (renameSkill != other.renameSkill) {
			return false;
		}
		if (shild != other.shild) {
			return false;
		}
		if (skillName == null) {
			if (other.skillName != null) {
				return false;
			}
		} else if (!skillName.equals(other.skillName)) {
			return false;
		}
		if (skills == null) {
			if (other.skills != null) {
				return false;
			}
		} else if (!skills.equals(other.skills)) {
			return false;
		}
		if (strongDefense != other.strongDefense) {
			return false;
		}
		if (unarmedAnimation != other.unarmedAnimation) {
			return false;
		}
		if (weapon != other.weapon) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the byte-representation of this Hero
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			
			byte[] stats = new byte[600];
			for (int i = 0; i < 50; i++) {
				stats[i] = DataReader.to16bitle(hp[i + 1])[0];
				stats[i + 1] = DataReader.to16bitle(hp[i + 1])[1];
				stats[i * 50] = DataReader.to16bitle(mp[i + 1])[0];
				stats[(i * 50) + 1] = DataReader.to16bitle(mp[i + 1])[1];
				stats[i * 100] = DataReader.to16bitle(attack[i + 1])[0];
				stats[(i * 100) + 1] = DataReader.to16bitle(attack[i + 1])[1];
				stats[i * 150] = DataReader.to16bitle(defense[i + 1])[0];
				stats[(i * 150) + 1] = DataReader.to16bitle(defense[i + 1])[1];
				stats[i * 200] = DataReader.to16bitle(mind[i + 1])[0];
				stats[(i * 200) + 1] = DataReader.to16bitle(mind[i + 1])[1];
				stats[i * 250] = DataReader.to16bitle(agility[i + 1])[0];
				stats[(i * 250) + 1] = DataReader.to16bitle(agility[i + 1])[1];
			}
			byte[] equipment = new byte[10];
			equipment[0] = DataReader.to16bitle(weapon)[0];
			equipment[1] = DataReader.to16bitle(weapon)[1];
			equipment[2] = DataReader.to16bitle(shild)[0];
			equipment[3] = DataReader.to16bitle(shild)[1];
			equipment[4] = DataReader.to16bitle(armor)[0];
			equipment[5] = DataReader.to16bitle(armor)[1];
			equipment[6] = DataReader.to16bitle(helmet)[0];
			equipment[7] = DataReader.to16bitle(helmet)[1];
			equipment[8] = DataReader.to16bitle(other)[0];
			equipment[9] = DataReader.to16bitle(other)[1];

			byte[] skilllist = new byte[0];
			long nrSkills = skills.size();
			for (int i = 0; i < skills.size(); i++) {
				if (skills.get(i) != null) {
					skilllist = Helper.concatAll(skilllist,
							DataReader.intToRPGint(i),
							skills.get(i).write());
				}
			}
			skilllist = Helper.concatAll(DataReader.intToRPGint(nrSkills), skilllist);
			byte[] conditionlist = new byte[0];
			for (int i = 0; i < conditions.size(); i++) {
				if (conditions.get(i) != null) {
					conditionlist = Helper.concatAll(conditionlist,
							DataReader.intToRPGint(conditions.get(i)));
				}
			}
			byte[] attributelist = new byte[0];
			for (int i = 0; i < attributes.size(); i++) {
				if (attributes.get(i) != null) {
					attributelist = Helper.concatAll(attributelist,
							DataReader.intToRPGint(attributes.get(i)));
				}
			}
			
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x02, degree.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x03, graphicFile.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x04, DataReader.intToRPGint(graphicIndex)).write(new byte[]{0}),
					new LuciferBaseUnit(0x05, DataReader.intToRPGint(graphicTransparent ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x07, DataReader.intToRPGint(minLevel)).write(DataReader.intToRPGint(1)),
					new LuciferBaseUnit(0x08, DataReader.intToRPGint(maxLevel)).write(DataReader.intToRPGint(50)),
					new LuciferBaseUnit(0x09, DataReader.intToRPGint(allowCritical ? 1 : 0)).write(DataReader.intToRPGint(1)),
					new LuciferBaseUnit(0x0A, DataReader.intToRPGint(chanceOnCritical)).write(DataReader.intToRPGint(30)),
					new LuciferBaseUnit(0x0F, faceGraphicFile.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x10, DataReader.intToRPGint(faceGraphicIndex)).write(new byte[]{0}),
					new LuciferBaseUnit(0x15, DataReader.intToRPGint(dualWield ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x16, DataReader.intToRPGint(fixedEquipment ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x17, DataReader.intToRPGint(aiControl ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x18, DataReader.intToRPGint(strongDefense ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x1F, stats).write(), //TODO: add default. Possibly all zero?
					new LuciferBaseUnit(0x29, DataReader.intToRPGint(expBase)).write(DataReader.intToRPGint(30)),
					new LuciferBaseUnit(0x2A, DataReader.intToRPGint(expAdditional)).write(DataReader.intToRPGint(30)),
					new LuciferBaseUnit(0x2B, DataReader.intToRPGint(expCorrection)).write(DataReader.intToRPGint(30)),
					new LuciferBaseUnit(0x33, equipment).write(new byte[]{0}),
					new LuciferBaseUnit(0x38, DataReader.intToRPGint(unarmedAnimation)).write(new byte[]{0}),
					new LuciferBaseUnit(0x3F, skilllist).write(),
					new LuciferBaseUnit(0x42, DataReader.intToRPGint(renameSkill ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x43, skillName.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x47, DataReader.intToRPGint(nrConditions)).write(new byte[0]),
					new LuciferBaseUnit(0x48, conditionlist).write(new byte[0]),
					new LuciferBaseUnit(0x49, DataReader.intToRPGint(nrAttributes)).write(new byte[0]),
					new LuciferBaseUnit(0x4A, attributelist).write(new byte[0]),
					new byte[]{0}
					);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}