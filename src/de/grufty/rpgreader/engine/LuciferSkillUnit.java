package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.util.ArrayList;

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
	private long nrAttributes			= 0;
	private long nrConditions			= 0;
	public ArrayList<Boolean> conditions;
	public ArrayList<Boolean> attributes;
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
				conditions = new ArrayList<Boolean>((int) nrConditions);
				for (int i = 0; i < nrConditions; i++) {
					conditions.add((tmp.nextInt() == 1));
				}
				break;
			case 0x2B:
				nrAttributes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2C:
				tmp = new DataReader(unit.content);
				attributes = new ArrayList<Boolean>((int) nrAttributes);
				for (int i = 0; i < nrAttributes; i++) {
					attributes.add((tmp.nextInt() == 1));
				}
				break;
			case 0x2D:
				defenceDown = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferSkillUnit! ID: " + unit.id);
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
				* result + (abilityDownAgility ? 1231
						: 1237);
		result = prime
				* result + (abilityDownAttack ? 1231
						: 1237);
		result = prime
				* result + (abilityDownDefence ? 1231
						: 1237);
		result = prime
				* result + (abilityDownHP ? 1231
						: 1237);
		result = prime
				* result + (abilityDownMP ? 1231
						: 1237);
		result = prime
				* result + (abilityDownMind ? 1231
						: 1237);
		result = prime
				* result + (absorption ? 1231
						: 1237);
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
				* result + (int) (baseEffect ^ (baseEffect >>> 32));
		result = prime
				* result + (int) (baseSuccessRate ^ (baseSuccessRate >>> 32));
		result = prime
				* result + (int) (battleAnimation ^ (battleAnimation >>> 32));
		result = prime
				* result + (int) (classification ^ (classification >>> 32));
		result = prime
				* result + ((conditions == null) ? 0
						: conditions.hashCode());
		result = prime
				* result + (defenceDown ? 1231
						: 1237);
		result = prime
				* result + (defenceIgnore ? 1231
						: 1237);
		result = prime
				* result + (int) (effectRange ^ (effectRange >>> 32));
		result = prime
				* result + ((explanation == null) ? 0
						: explanation.hashCode());
		result = prime
				* result + ((failureMessage == null) ? 0
						: failureMessage.hashCode());
		result = prime
				* result + (int) (hitChance ^ (hitChance >>> 32));
		result = prime
				* result + (int) (mindChance ^ (mindChance >>> 32));
		result = prime
				* result + (int) (mpCost ^ (mpCost >>> 32));
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + (int) (onSwitch ^ (onSwitch >>> 32));
		result = prime
				* result + ((soundEffect == null) ? 0
						: soundEffect.hashCode());
		result = prime
				* result + ((usingMessage == null) ? 0
						: usingMessage.hashCode());
		result = prime
				* result + ((usingMessage2 == null) ? 0
						: usingMessage2.hashCode());
		result = prime
				* result + (int) (variance ^ (variance >>> 32));
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
		if (!(obj instanceof LuciferSkillUnit)) {
			return false;
		}
		LuciferSkillUnit other = (LuciferSkillUnit) obj;
		if (abilityDownAgility != other.abilityDownAgility) {
			return false;
		}
		if (abilityDownAttack != other.abilityDownAttack) {
			return false;
		}
		if (abilityDownDefence != other.abilityDownDefence) {
			return false;
		}
		if (abilityDownHP != other.abilityDownHP) {
			return false;
		}
		if (abilityDownMP != other.abilityDownMP) {
			return false;
		}
		if (abilityDownMind != other.abilityDownMind) {
			return false;
		}
		if (absorption != other.absorption) {
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
		if (baseEffect != other.baseEffect) {
			return false;
		}
		if (baseSuccessRate != other.baseSuccessRate) {
			return false;
		}
		if (battleAnimation != other.battleAnimation) {
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
		if (defenceDown != other.defenceDown) {
			return false;
		}
		if (defenceIgnore != other.defenceIgnore) {
			return false;
		}
		if (effectRange != other.effectRange) {
			return false;
		}
		if (explanation == null) {
			if (other.explanation != null) {
				return false;
			}
		} else if (!explanation.equals(other.explanation)) {
			return false;
		}
		if (failureMessage == null) {
			if (other.failureMessage != null) {
				return false;
			}
		} else if (!failureMessage.equals(other.failureMessage)) {
			return false;
		}
		if (hitChance != other.hitChance) {
			return false;
		}
		if (mindChance != other.mindChance) {
			return false;
		}
		if (mpCost != other.mpCost) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (onSwitch != other.onSwitch) {
			return false;
		}
		if (soundEffect == null) {
			if (other.soundEffect != null) {
				return false;
			}
		} else if (!soundEffect.equals(other.soundEffect)) {
			return false;
		}
		if (usingMessage == null) {
			if (other.usingMessage != null) {
				return false;
			}
		} else if (!usingMessage.equals(other.usingMessage)) {
			return false;
		}
		if (usingMessage2 == null) {
			if (other.usingMessage2 != null) {
				return false;
			}
		} else if (!usingMessage2.equals(other.usingMessage2)) {
			return false;
		}
		if (variance != other.variance) {
			return false;
		}
		return true;
	}
}