package engine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a Monster of the RPG-Maker-Game.
 */
public class LuciferMonsterUnit {
	
	private String name				= "";
	private String graphic			= "";
	private long changeFellowColour	= 0;
	private long maxHP				= 10;
	private long maxMP				= 10;
	private long attack				= 10;
	private long defence			= 10;
	private long mind				= 10;
	private long agility			= 10;
	private long experience			= 0;
	private long money				= 0;
	private long item				= 0;
	private long itemChance			= 0;
	private long criticalHitChance	= 30;
	private long nrConditions		= 0;
	private long nrAttributes		= 0;
	private ArrayList<Long> conditions;
	private ArrayList<Long> attributes;
	private boolean transparent		= false;
	private boolean criticalHit		= false;
	private boolean usuallyMiss		= false;
	private boolean air				= false;
	private ArrayList<LuciferMonsterAction> actions;

	/**
	 * Constructs a new LuciferMonsterUnit
	 * 
	 * @param bytes byte-Array which represents the LuciferMonsterUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMonsterUnit
	 * 
	 * @param dr DataReader which represents the LuciferMonsterUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMonsterUnit(DataReader dr) throws IOException {
		init(dr);
	}
	
	/**
	 * Constructs a new LuciferMonsterUnit
	 */
	public LuciferMonsterUnit() { }
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		DataReader tmp;
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				graphic = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x03:
				changeFellowColour = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				maxHP = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				maxMP = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				attack = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x07:
				defence = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x08:
				mind = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x09:
				agility = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0A:
				transparent = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0B:
				experience = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				money = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				item = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0E:
				itemChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				criticalHit = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x16:
				criticalHitChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x1A:
				usuallyMiss = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1C:
				air = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1F:
				nrConditions = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x20:
				tmp = new DataReader(unit.content);
				conditions = new ArrayList<Long>((int) nrConditions);
				for (int i = 1; i <= nrConditions; i++) {
					conditions.add(tmp.nextInt());
				}
				break;
			case 0x21:
				nrAttributes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x22:
				tmp = new DataReader(unit.content);
				attributes = new ArrayList<Long>((int) nrAttributes);
				for (int i = 1; i <= nrAttributes; i++) {
					attributes.add(tmp.nextInt());
				}
				break;
			case 0x2A:
				tmp = new DataReader(unit.content);
				actions = new ArrayList<LuciferMonsterAction>((int) tmp.nextInt());
				for (int i = 1; i < actions.size(); i++) {
					actions.add((int) tmp.nextInt(), new LuciferMonsterAction(tmp));
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMonsterUnit! ID: " + unit.id);
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
	 * Returns the graphic
	 * 
	 * @return the graphic
	 */
	public String getGraphic() {
		return graphic;
	}

	/**
	 * Sets the graphic
	 * 
	 * @param graphic the new graphic
	 */
	public void setGraphic(
			String graphic) {
		this.graphic = graphic;
	}

	/**
	 * Returns the changeFellowColour
	 * 
	 * @return the changeFellowColour
	 */
	public long getChangeFellowColour() {
		return changeFellowColour;
	}

	/**
	 * Sets the changeFellowColour
	 * 
	 * @param changeFellowColour the new changeFellowColour
	 */
	public void setChangeFellowColour(
			long changeFellowColour) {
		this.changeFellowColour = changeFellowColour;
	}

	/**
	 * Returns the maxHP
	 * 
	 * @return the maxHP
	 */
	public long getMaxHP() {
		return maxHP;
	}

	/**
	 * Sets the maxHP
	 * 
	 * @param maxHP the new maxHP
	 */
	public void setMaxHP(
			long maxHP) {
		this.maxHP = maxHP;
	}

	/**
	 * Returns the maxMP
	 * 
	 * @return the maxMP
	 */
	public long getMaxMP() {
		return maxMP;
	}

	/**
	 * Sets the maxMP
	 * 
	 * @param maxMP the new maxMP
	 */
	public void setMaxMP(
			long maxMP) {
		this.maxMP = maxMP;
	}

	/**
	 * Returns the attack
	 * 
	 * @return the attack
	 */
	public long getAttack() {
		return attack;
	}

	/**
	 * Sets the attack
	 * 
	 * @param attack the new attack
	 */
	public void setAttack(
			long attack) {
		this.attack = attack;
	}

	/**
	 * Returns the defence
	 * 
	 * @return the defence
	 */
	public long getDefence() {
		return defence;
	}

	/**
	 * Sets the defence
	 * 
	 * @param defence the new defence
	 */
	public void setDefence(
			long defence) {
		this.defence = defence;
	}

	/**
	 * Returns the mind
	 * 
	 * @return the mind
	 */
	public long getMind() {
		return mind;
	}

	/**
	 * Sets the mind
	 * 
	 * @param mind the new mind
	 */
	public void setMind(
			long mind) {
		this.mind = mind;
	}

	/**
	 * Returns the agility
	 * 
	 * @return the agility
	 */
	public long getAgility() {
		return agility;
	}

	/**
	 * Sets the agility
	 * 
	 * @param agility the new agility
	 */
	public void setAgility(
			long agility) {
		this.agility = agility;
	}

	/**
	 * Returns the experience
	 * 
	 * @return the experience
	 */
	public long getExperience() {
		return experience;
	}

	/**
	 * Sets the experience
	 * 
	 * @param experience the new experience
	 */
	public void setExperience(
			long experience) {
		this.experience = experience;
	}

	/**
	 * Returns the money
	 * 
	 * @return the money
	 */
	public long getMoney() {
		return money;
	}

	/**
	 * Sets the money
	 * 
	 * @param money the new money
	 */
	public void setMoney(
			long money) {
		this.money = money;
	}

	/**
	 * Returns the item
	 * 
	 * @return the item
	 */
	public long getItem() {
		return item;
	}

	/**
	 * Sets the item
	 * 
	 * @param item the new item
	 */
	public void setItem(
			long item) {
		this.item = item;
	}

	/**
	 * Returns the itemChance
	 * 
	 * @return the itemChance
	 */
	public long getItemChance() {
		return itemChance;
	}

	/**
	 * Sets the itemChance
	 * 
	 * @param itemChance the new itemChance
	 */
	public void setItemChance(
			long itemChance) {
		this.itemChance = itemChance;
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
	 * Returns the transparent
	 * 
	 * @return the transparent
	 */
	public boolean isTransparent() {
		return transparent;
	}

	/**
	 * Sets the transparent
	 * 
	 * @param transparent the new transparent
	 */
	public void setTransparent(
			boolean transparent) {
		this.transparent = transparent;
	}

	/**
	 * Returns the criticalHit
	 * 
	 * @return the criticalHit
	 */
	public boolean isCriticalHit() {
		return criticalHit;
	}

	/**
	 * Sets the criticalHit
	 * 
	 * @param criticalHit the new criticalHit
	 */
	public void setCriticalHit(
			boolean criticalHit) {
		this.criticalHit = criticalHit;
	}

	/**
	 * Returns the usuallyMiss
	 * 
	 * @return the usuallyMiss
	 */
	public boolean isUsuallyMiss() {
		return usuallyMiss;
	}

	/**
	 * Sets the usuallyMiss
	 * 
	 * @param usuallyMiss the new usuallyMiss
	 */
	public void setUsuallyMiss(
			boolean usuallyMiss) {
		this.usuallyMiss = usuallyMiss;
	}

	/**
	 * Returns the air
	 * 
	 * @return the air
	 */
	public boolean isAir() {
		return air;
	}

	/**
	 * Sets the air
	 * 
	 * @param air the new air
	 */
	public void setAir(
			boolean air) {
		this.air = air;
	}

	/**
	 * Returns the actions
	 * 
	 * @return the actions
	 */
	public ArrayList<LuciferMonsterAction> getActions() {
		return actions;
	}

	/**
	 * Sets the actions
	 * 
	 * @param actions the new actions
	 */
	public void setActions(
			ArrayList<LuciferMonsterAction> actions) {
		this.actions = actions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + ((actions == null) ? 0
						: actions.hashCode());
		result = prime
				* result + (int) (agility ^ (agility >>> 32));
		result = prime
				* result + (air ? 1231
						: 1237);
		result = prime
				* result + (int) (attack ^ (attack >>> 32));
		result = prime
				* result + ((attributes == null) ? 0
						: attributes.hashCode());
		result = prime
				* result + (int) (changeFellowColour ^ (changeFellowColour >>> 32));
		result = prime
				* result + ((conditions == null) ? 0
						: conditions.hashCode());
		result = prime
				* result + (criticalHit ? 1231
						: 1237);
		result = prime
				* result + (int) (criticalHitChance ^ (criticalHitChance >>> 32));
		result = prime
				* result + (int) (defence ^ (defence >>> 32));
		result = prime
				* result + (int) (experience ^ (experience >>> 32));
		result = prime
				* result + ((graphic == null) ? 0
						: graphic.hashCode());
		result = prime
				* result + (int) (item ^ (item >>> 32));
		result = prime
				* result + (int) (itemChance ^ (itemChance >>> 32));
		result = prime
				* result + (int) (maxHP ^ (maxHP >>> 32));
		result = prime
				* result + (int) (maxMP ^ (maxMP >>> 32));
		result = prime
				* result + (int) (mind ^ (mind >>> 32));
		result = prime
				* result + (int) (money ^ (money >>> 32));
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + (transparent ? 1231
						: 1237);
		result = prime
				* result + (usuallyMiss ? 1231
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
		if (!(obj instanceof LuciferMonsterUnit)) {
			return false;
		}
		LuciferMonsterUnit other = (LuciferMonsterUnit) obj;
		if (actions == null) {
			if (other.actions != null) {
				return false;
			}
		} else if (!actions.equals(other.actions)) {
			return false;
		}
		if (agility != other.agility) {
			return false;
		}
		if (air != other.air) {
			return false;
		}
		if (attack != other.attack) {
			return false;
		}
		if (attributes == null) {
			if (other.attributes != null) {
				return false;
			}
		} else if (!attributes.equals(other.attributes)) {
			return false;
		}
		if (changeFellowColour != other.changeFellowColour) {
			return false;
		}
		if (conditions == null) {
			if (other.conditions != null) {
				return false;
			}
		} else if (!conditions.equals(other.conditions)) {
			return false;
		}
		if (criticalHit != other.criticalHit) {
			return false;
		}
		if (criticalHitChance != other.criticalHitChance) {
			return false;
		}
		if (defence != other.defence) {
			return false;
		}
		if (experience != other.experience) {
			return false;
		}
		if (graphic == null) {
			if (other.graphic != null) {
				return false;
			}
		} else if (!graphic.equals(other.graphic)) {
			return false;
		}
		if (item != other.item) {
			return false;
		}
		if (itemChance != other.itemChance) {
			return false;
		}
		if (maxHP != other.maxHP) {
			return false;
		}
		if (maxMP != other.maxMP) {
			return false;
		}
		if (mind != other.mind) {
			return false;
		}
		if (money != other.money) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (transparent != other.transparent) {
			return false;
		}
		if (usuallyMiss != other.usuallyMiss) {
			return false;
		}
		return true;
	}
}