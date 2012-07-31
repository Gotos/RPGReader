package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author alina
 *
 * This class represents a Map in the MapTree of the RPG-Maker-Game.
 */
public class LuciferMapTreeMap{
	
	public long id;
	public String name			= "";
	public String battleBGName	= "";
	public long parent			= 0;
	public long depth			= 0;
	public long type			= 1;
	public long music			= 0;
	public long xScrollBar		= 0;
	public long yScrollBar		= 0;
	public long battleBG		= 0;
	public long teleport		= 0;
	public long escape			= 0;
	public long save			= 0;
	public long encounterRate	= 25;
	public boolean expanded	= false;
	public long[] area			= new long[]{-1, -1, -1, -1};
	public LuciferMusicUnit musicTrack;
	public LuciferEncounterData encounter;
	
	/**
	 * Constructs a new LuciferMapTreeMap
	 */
	public LuciferMapTreeMap() { }
	
	/**
	 * Constructs a new LuciferMapTreeMap
	 * 
	 * @param dr DataReader which represents the LuciferMapTreeMap
	 * @param id MapID of this Map
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapTreeMap(DataReader dr, long id) throws IOException {
		init(dr, id);
	}
	
	/**
	 * Constructs a new LuciferMapTreeMap
	 * 
	 * @param bytes byte-Array which represents the LuciferMapTreeMap
	 * @param id MapID of this Map
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapTreeMap(byte[] bytes, long id) throws IOException {
		init(new DataReader(bytes), id);
	}
	
	private void init(DataReader sr, long gotid) throws IOException {	
		id = gotid;
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x1:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x2:
				parent = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x3:
				depth = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x4:
				//0 -> Root
				//1 -> Map
				//2 -> Area
				type = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x5:
				xScrollBar = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x6:
				yScrollBar = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x7:
				expanded = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0xB:
				//0 -> use music of parent map
				//1 -> don't change playing track
				//2 -> use own track
				music = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0xC:
				musicTrack = new LuciferMusicUnit(unit.content);
				break;
			case 0x15:
				//0 -> use bg of parent map
				//1 -> use terrains bg
				//2 -> use bg defined in 0x16
				battleBG = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x16:
				battleBGName = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x1F:
				//0 -> teleport like parent map
				//1 -> allow teleport
				//2 -> disallow teleport
				teleport = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x20:
				//0 -> escape like parent map
				//1 -> allow escape
				//2 -> disallow escape
				escape = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x21:
				//0 -> save like parent map
				//1 -> allow save
				//2 -> disallow save
				save = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x29:
				encounter = new LuciferEncounterData(unit.content);
				break;
			case 0x2C:
				encounterRate = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x33:
				//RECT-Structure
				DataReader tmp = new DataReader(unit.content);
				area[0] = tmp.next32bitle(); //BeginX
				area[1] = tmp.next32bitle(); //BeginY
				area[2] = tmp.next32bitle(); //EndX (exclusive)
				area[3] = tmp.next32bitle(); //EndY (exclusive)
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMapTreeMap! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this MapTreeMap
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		byte[] areaRect = new byte[0];
		areaRect = Helper.concatAll(areaRect,
				DataReader.to32bitle(area[0]),
				DataReader.to32bitle(area[1]),
				DataReader.to32bitle(area[2]),
				DataReader.to32bitle(area[3]));
		try {
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(),
					new LuciferBaseUnit(0x02, DataReader.intToRPGint(parent)).write(new byte[]{0}),
					new LuciferBaseUnit(0x03, DataReader.intToRPGint(depth)).write(new byte[]{0}),
					new LuciferBaseUnit(0x04, DataReader.intToRPGint(type)).write(),
					new LuciferBaseUnit(0x05, DataReader.intToRPGint(xScrollBar)).write(new byte[]{0}),
					new LuciferBaseUnit(0x06, DataReader.intToRPGint(yScrollBar)).write(new byte[]{0}),
					new LuciferBaseUnit(0x07, DataReader.intToRPGint(expanded ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x0B, DataReader.intToRPGint(music)).write(),
					new LuciferBaseUnit(0x0C, musicTrack.write()).write(new byte[0]),
					new LuciferBaseUnit(0x15, DataReader.intToRPGint(battleBG)).write(),
					new LuciferBaseUnit(0x16, battleBGName.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x1F, DataReader.intToRPGint(teleport)).write(),
					new LuciferBaseUnit(0x20, DataReader.intToRPGint(escape)).write(),
					new LuciferBaseUnit(0x21, DataReader.intToRPGint(save)).write(),
					new LuciferBaseUnit(0x29, encounter.write()).write(),
					new LuciferBaseUnit(0x2C, DataReader.intToRPGint(encounterRate)).write(new byte[]{25}),
					new LuciferBaseUnit(0x33, areaRect).write(),
					new byte[]{0}
					);
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
	     if (!(obj instanceof LuciferMapTreeMap)) {
	        return false; // different class
	     }
	     
	     LuciferMapTreeMap o = (LuciferMapTreeMap) obj;
	     
	     return id == o.id
	     		&& parent == o.parent
	     		&& depth == o.depth
  				&& type == o.type
  				&& music == o.music
  				&& xScrollBar == o.xScrollBar
  				&& yScrollBar == o.yScrollBar
  				&& battleBG == o.battleBG
  				&& teleport == o.teleport
  				&& escape == o.escape
  				&& save == o.save
  				&& encounterRate == o.encounterRate
  				&& expanded == o.expanded
  				&& name.equals(o.name)
	     		&& battleBGName.equals(o.battleBGName)
	     		&& musicTrack.equals(o.musicTrack)
	     		&& encounter.equals(o.encounter)
	     		&& Arrays.equals(area, o.area);
	}
}