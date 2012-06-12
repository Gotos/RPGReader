package engine;

import java.io.IOException;

//The SoundUnit is nearly the same as the MusicUnit.
//The only difference is, that the Sound can't fade in.
//At the moment every LuciferSoundUnit has a fadeIn-value,
//which will always be 0, but this might change.

public class LuciferSoundUnit extends LuciferMusicUnit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7982423181303903422L;

	public LuciferSoundUnit(byte[] str) throws IOException {
		super(str);
	}

	public LuciferSoundUnit(DataReader sr) throws IOException {
		super(sr);
	}

}