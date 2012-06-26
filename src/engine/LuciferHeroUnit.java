package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LuciferHeroUnit {
	
	public String name							= "";
	public String degree						= "";
	public String graphicFile					= "";
	public String faceGraphicFile				= "";
	public String skillName						= "";
	public long graphicIndex					= 0;
	public long minLevel						= 1;
	public long maxLevel						= 50;
	public long chanceOnCritical				= 30;
	public long faceGraphicIndex				= 0;
	public long expBase						= 30;
	public long expAdditional					= 30;
	public long expCorrection					= 30;
	public long weapon							= 0;
	public long shild							= 0;
	//public long secondWeapon					= shild;
	public long armor							= 0;
	public long helmed							= 0;
	public long other							= 0;
	public long unarmedAnimation				= 0;
	private long nrConditions					= 0;
	private long nrAttributes					= 0;
	public long[] hp							= new long[51];
	public long[] mp							= new long[51];
	public long[] attack						= new long[51];
	public long[] defense						= new long[51];
	public long[] mind							= new long[51];
	public long[] agility						= new long[51];
	public ArrayList<Long> conditions			= new ArrayList<Long>();
	public ArrayList<Long> attributes			= new ArrayList<Long>();
	public ArrayList<LuciferHeroSpell> skills	= new ArrayList<LuciferHeroSpell>();
	public boolean graphicTransparent			= false;
	public boolean allowCritical				= true;
	public boolean dualWield					= false;
	public boolean fixedEquipment				= false;
	public boolean aiControl					= false;
	public boolean strongDefense				= false;
	public boolean renameSkill					= false;
	
	public LuciferHeroUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferHeroUnit(DataReader sr) throws IOException {
		init(sr);
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
				graphicIndex = DataReader.rpgintToInt(unit.content).integer;
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
				helmed = tmp.next16bitle();
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
				* result + (int) (helmed ^ (helmed >>> 32));
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
		if (helmed != other.helmed) {
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
}