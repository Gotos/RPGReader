package engine;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class LuciferMusicUnit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6637202279807438472L;
	//private byte[][] content;
	public String name	= "";
	public long fadeIn	= 0;
	public long volume	= 100;
	public long tempo	= 100;
	public long balance	= 50;
	
	public LuciferMusicUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferMusicUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
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
			}
			unit = sr.nextUnit();
		}
	}
	
	public byte[] write() {
		try {
			return Helper.concatAll(
					new LuciferBaseUnit(1, name.getBytes(Encoder.ENCODING)).write(),
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
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferMusicUnit)) {
	        return false; // different class
	     }
	     
	     LuciferMusicUnit o = (LuciferMusicUnit) obj;
	     
	     return fadeIn == o.fadeIn
	     		&& volume == o.volume
	     		&& tempo == o.tempo
 				&& balance == o.balance
 				&& name.equals(o.name);
	}
}