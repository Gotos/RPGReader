package de.grufty.rpgreader.engine;

import java.io.IOException;

//The SoundUnit is nearly the same as the MusicUnit.
//The only difference is, that the Sound can't fade in.
//At the moment every LuciferSoundUnit has a fadeIn-value,
//which will always be 0, but this might change.

public class LuciferSoundUnit {
	
	protected String filename	= "";
	protected long volume		= 100;
	protected long tempo		= 100;
	protected long balance		= 50;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7982423181303903422L;

	public LuciferSoundUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}

	public LuciferSoundUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	public LuciferSoundUnit() { }
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			idSetter(unit);
			unit = sr.nextUnit();
		}
	}
	
	protected void idSetter(LuciferBaseUnit unit) throws IOException {
		switch (unit.id) {
		case 0x01:
			filename = new String(unit.content, Encoder.ENCODING);
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

}