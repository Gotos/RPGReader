package engine;

import java.io.IOException;

public class LuciferTimingUnit {
	
	private long frame			= 1;
	private LuciferSoundUnit sound;
	private short flashRange	= 0;
	private int flashRed		= 31;
	private int flashGreen		= 31;
	private int flashBlue		= 31;
	private int flashPower		= 31;
	
	//TODO: missing getters/setters
	
	public LuciferTimingUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferTimingUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				frame = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x02:
				sound = new LuciferSoundUnit(unit.content);
				break;
			case 0x03:
				flashRange = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				flashRed = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				flashGreen = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				flashBlue = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x07:
				flashPower = (int) DataReader.rpgintToInt(unit.content).integer;
				break;
			}
		unit = sr.nextUnit();
		}
	}

	/**
	 * Returns the byte-representation of this TimingUnit
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		return Helper.concatAll(new LuciferBaseUnit(0x01, DataReader.intToRPGint(frame)).write(new byte[]{1}),
				new LuciferBaseUnit(0x02, sound.write()).write(new byte[0]),
				new LuciferBaseUnit(0x03, DataReader.intToRPGint(flashRange)).write(new byte[]{0}),
				new LuciferBaseUnit(0x04, DataReader.intToRPGint(flashRed)).write(new byte[]{31}),
				new LuciferBaseUnit(0x05, DataReader.intToRPGint(flashGreen)).write(new byte[]{31}),
				new LuciferBaseUnit(0x07, DataReader.intToRPGint(flashPower)).write(new byte[]{31}),
				new byte[]{0}
				);
	}
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferTimingUnit)) {
	        return false; // different class
	     }
	     
	     LuciferTimingUnit o = (LuciferTimingUnit) obj;
	     
	     return frame == o.frame
	     		&& flashRange == o.flashRange
	     		&& flashRed == o.flashRed
	     		&& flashGreen == o.flashGreen
	     		&& flashBlue == o.flashBlue
	     		&& flashPower == o.flashPower
	     		&& sound.equals(o.sound);
	}
}
