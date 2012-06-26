package engine;

import java.io.IOException;

/**
 * @author alina
 *
 * This class represents a Spell of an Hero of the RPG-Maker-Game.
 */
public class LuciferHeroSpell {
	public long level	= 1;
	public long spell	= 1;
	
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
}