package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author alina
 *
 * This class represents a Music-changer/-setter of the RPG-Maker-Game.
 */
public class LuciferMusicUnit extends LuciferSoundUnit {
	
	private long fadeIn	= 0;
	
	/**
	 * Constructs a new LuciferMusicUnit
	 * 
	 * @param bytes byte-Array which represents the LuciferMusicUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMusicUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMusicUnit
	 * 
	 * @param dr DataReader which represents the LuciferMusicUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMusicUnit(DataReader dr) throws IOException {
		init(dr);
	}
	
	/**
	 * Constructs a new LuciferMusicUnit
	 */
	public LuciferMusicUnit() { }
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			idSetter(unit);
			unit = sr.nextUnit();
		}
	}
	
	protected void idSetter(LuciferBaseUnit unit) throws IOException {
		switch (unit.id) {
		case 0x02:
			//the fade-in time in sec*1000
			fadeIn = DataReader.rpgintToInt(unit.content).integer;
			break;
		default:
			super.idSetter(unit);
		}
	}
	
	/**
	 * Returns the fadeIn-time in sec/1000
	 * 
	 * @return the fadeIn-time
	 */
	public long getFadeIn() {
		return fadeIn;
	}

	/**
	 * Sets the fadeIn-time in sec/1000
	 * 
	 * @param fadeIn the new fadeIn-time
	 */
	public void setFadeIn(
			long fadeIn) {
		this.fadeIn = fadeIn;
	}

	/**
	 * Returns the byte-representation of this MusicUnit
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			return Helper.concatAll(
					new LuciferBaseUnit(1, filename.getBytes(Encoder.ENCODING)).write(),
					new LuciferBaseUnit(2, DataReader.intToRPGint(fadeIn)).write(new byte[]{0}),
					new LuciferBaseUnit(3, DataReader.intToRPGint(volume)).write(new byte[]{100}),
					new LuciferBaseUnit(4, DataReader.intToRPGint(tempo)).write(new byte[]{100}),
					new LuciferBaseUnit(5, DataReader.intToRPGint(balance)).write(new byte[]{50}),
					new byte[]{0});
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
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
				* result + (int) (balance ^ (balance >>> 32));
		result = prime
				* result + (int) (fadeIn ^ (fadeIn >>> 32));
		result = prime
				* result + ((filename == null) ? 0
						: filename.hashCode());
		result = prime
				* result + (int) (tempo ^ (tempo >>> 32));
		result = prime
				* result + (int) (volume ^ (volume >>> 32));
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
		if (!(obj instanceof LuciferMusicUnit)) {
			return false;
		}
		LuciferMusicUnit other = (LuciferMusicUnit) obj;
		if (balance != other.balance) {
			return false;
		}
		if (fadeIn != other.fadeIn) {
			return false;
		}
		if (filename == null) {
			if (other.filename != null) {
				return false;
			}
		} else if (!filename.equals(other.filename)) {
			return false;
		}
		if (tempo != other.tempo) {
			return false;
		}
		if (volume != other.volume) {
			return false;
		}
		return true;
	}
}