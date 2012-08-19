package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author alina
 *
 * This class represents a Music-changer/-setter of the RPG-Maker-Game.
 */
public class LuciferMusicUnit {
	
	private String filename	= "";
	private long fadeIn		= 0;
	private long volume		= 100;
	private long tempo		= 100;
	private long balance	= 50;
	
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
			switch (unit.id) {
			case 0x01:
				filename = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				//the fade-in time in sec*1000
				fadeIn = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				volume = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				tempo = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				//0 -> only left
				//50 -> middel
				//100 -> only right
				balance = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMusicUnit! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the filename
	 * 
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename
	 * 
	 * @param filename the new filename
	 */
	public void setFilename(
			String filename) {
		this.filename = filename;
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
	 * Returns the volume
	 * 
	 * @return the volume
	 */
	public long getVolume() {
		return volume;
	}

	/**
	 * Sets the volume
	 * 
	 * @param volume the new volume
	 */
	public void setVolume(
			long volume) {
		this.volume = volume;
	}

	/**
	 * Returns the tempo
	 * 
	 * @return the tempo
	 */
	public long getTempo() {
		return tempo;
	}

	/**
	 * Sets the tempo
	 * 
	 * @param tempo the new tempo
	 */
	public void setTempo(
			long tempo) {
		this.tempo = tempo;
	}

	/**
	 * Returns the balance. 0 is left, 50 balanced and 100 right.
	 * 
	 * @return the balance
	 */
	public long getBalance() {
		return balance;
	}

	/**
	 * Sets the balance. 0 is left, 50 balanced and 100 right.
	 * 
	 * @param balance the new balance
	 */
	public void setBalance(
			long balance) {
		this.balance = balance;
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