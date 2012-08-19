package de.grufty.rpgreader.engine;

import java.io.IOException;

/**
 * @author alina
 *
 * This class represents a Spell of an Hero of the RPG-Maker-Game.
 */
public class LuciferHeroSpell {
	private long level	= 1;
	private long spell	= 1;
	
	/**
	 * Returns the level
	 * 
	 * @return the level
	 */
	public long getLevel() {
		return level;
	}

	/**
	 * Sets the level
	 * 
	 * @param level the new level
	 */
	public void setLevel(
			long level) {
		this.level = level;
	}

	/**
	 * Returns the spell
	 * 
	 * @return the spell
	 */
	public long getSpell() {
		return spell;
	}

	/**
	 * Sets the spell
	 * 
	 * @param spell the new spell
	 */
	public void setSpell(
			long spell) {
		this.spell = spell;
	}

	/**
	 * Constructs a new LuciferHeroSpell
	 * 
	 * @param bytes byte-Array which represents the LuciferHeroSpell
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferHeroSpell(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferHeroSpell
	 * 
	 * @param dr DataReader which represents the LuciferHeroSpell
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferHeroSpell(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				level = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x02:
				spell = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferHeroSpell! ID: " + unit.id);
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
				* result + (int) (level ^ (level >>> 32));
		result = prime
				* result + (int) (spell ^ (spell >>> 32));
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
		if (!(obj instanceof LuciferHeroSpell)) {
			return false;
		}
		LuciferHeroSpell other = (LuciferHeroSpell) obj;
		if (level != other.level) {
			return false;
		}
		if (spell != other.spell) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the byte-representation of this HeroSpell
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		return Helper.concatAll(new LuciferBaseUnit(0x01, DataReader.intToRPGint(level)).write(new byte[]{1}),
				new LuciferBaseUnit(0x02, DataReader.intToRPGint(spell)).write(new byte[]{1}),
				new byte[]{0}
				);
	}
}