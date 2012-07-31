package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author alina
 *
 * This class represents a Map in the MapTree of the RPG-Maker-Game.
 */
public class LuciferMapTreeMap {
	
	private long id;
	private String name			= "";
	private String battleBGName	= "";
	private long parent			= 0;
	private long depth			= 0;
	private short type			= 1;
	private short music			= 0;
	private long xScrollBar		= 0;
	private long yScrollBar		= 0;
	private short battleBG		= 0;
	private short teleport		= 0;
	private long escape			= 0;
	private long save			= 0;
	private long encounterRate	= 25;
	private boolean expanded	= false;
	private long[] area			= new long[]{-1, -1, -1, -1};
	private LuciferMusicUnit musicTrack;
	private LuciferEncounterData encounter;
	
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
				type = (short) DataReader.rpgintToInt(unit.content).integer;
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
				music = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0xC:
				musicTrack = new LuciferMusicUnit(unit.content);
				break;
			case 0x15:
				//0 -> use bg of parent map
				//1 -> use terrains bg
				//2 -> use bg defined in 0x16
				battleBG = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x16:
				battleBGName = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x1F:
				//0 -> teleport like parent map
				//1 -> allow teleport
				//2 -> disallow teleport
				teleport = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x20:
				//0 -> escape like parent map
				//1 -> allow escape
				//2 -> disallow escape
				escape = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x21:
				//0 -> save like parent map
				//1 -> allow save
				//2 -> disallow save
				save = (short) DataReader.rpgintToInt(unit.content).integer;
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
	 * Returns the id
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(
			long id) {
		this.id = id;
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
	 * Returns the battleBGName
	 * 
	 * @return the battleBGName
	 */
	public String getBattleBGName() {
		return battleBGName;
	}

	/**
	 * Sets the battleBGName
	 * 
	 * @param battleBGName the new battleBGName
	 */
	public void setBattleBGName(
			String battleBGName) {
		this.battleBGName = battleBGName;
	}

	/**
	 * Returns the parent
	 * 
	 * @return the parent
	 */
	public long getParent() {
		return parent;
	}

	/**
	 * Sets the parent
	 * 
	 * @param parent the new parent
	 */
	public void setParent(
			long parent) {
		this.parent = parent;
	}

	/**
	 * Returns the depth
	 * 
	 * @return the depth
	 */
	public long getDepth() {
		return depth;
	}

	/**
	 * Sets the depth
	 * 
	 * @param depth the new depth
	 */
	public void setDepth(
			long depth) {
		this.depth = depth;
	}

	/**
	 * Returns the type. Should be:
	 * 		0 -> Root
	 *		1 -> Map
	 *		2 -> Area
	 * 
	 * @return the type
	 */
	public short getType() {
		return type;
	}

	/**
	 * Sets the type. Should be:
	 * 		0 -> Root
	 *		1 -> Map
	 *		2 -> Area
	 * 
	 * @param type the new type
	 */
	public void setType(
			short type) {
		this.type = type;
	}

	/**
	 * Returns the music. Should be:
	 * 		0 -> use music of parent map
	 *		1 -> don't change playing track
	 *		2 -> use own track
	 * 
	 * @return the music
	 */
	public short getMusic() {
		return music;
	}

	/**
	 * Sets the music. Should be:
	 * 		0 -> use music of parent map
	 *		1 -> don't change playing track
	 *		2 -> use own track
	 * 
	 * @param music the new music
	 */
	public void setMusic(
			short music) {
		this.music = music;
	}

	/**
	 * Returns the xScrollBar
	 * 
	 * @return the xScrollBar
	 */
	public long getxScrollBar() {
		return xScrollBar;
	}

	/**
	 * Sets the xScrollBar
	 * 
	 * @param xScrollBar the new xScrollBar
	 */
	public void setxScrollBar(
			long xScrollBar) {
		this.xScrollBar = xScrollBar;
	}

	/**
	 * Returns the yScrollBar
	 * 
	 * @return the yScrollBar
	 */
	public long getyScrollBar() {
		return yScrollBar;
	}

	/**
	 * Sets the yScrollBar
	 * 
	 * @param yScrollBar the new yScrollBar
	 */
	public void setyScrollBar(
			long yScrollBar) {
		this.yScrollBar = yScrollBar;
	}

	/**
	 * Returns the battleBG. Should be:
	 * 		0 -> use BG of parent map
	 *		1 -> use terrains BG
	 *		2 -> use own BG
	 * 
	 * @return the battleBG
	 */
	public short getBattleBG() {
		return battleBG;
	}

	/**
	 * Sets the battleBG. Should be:
	 * 		0 -> use BG of parent map
	 *		1 -> use terrains BG
	 *		2 -> use own BG
	 * 
	 * @param battleBG the new battleBG
	 */
	public void setBattleBG(
			short battleBG) {
		this.battleBG = battleBG;
	}

	/**
	 * Returns the teleport. Should be:
	 * 		0 -> teleport like parent map
	 *		1 -> allow teleport
	 *		2 -> disallow teleport
	 * 
	 * @return the teleport
	 */
	public short getTeleport() {
		return teleport;
	}

	/**
	 * Sets the teleport. Should be:
	 * 		0 -> teleport like parent map
	 *		1 -> allow teleport
	 *		2 -> disallow teleport
	 * 
	 * @param teleport the new teleport
	 */
	public void setTeleport(
			short teleport) {
		this.teleport = teleport;
	}

	/**
	 * Returns the escape. Should be:
	 * 		0 -> escape like parent map
	 *		1 -> allow escape
	 *		2 -> disallow escape
	 * 
	 * @return the escape
	 */
	public long getEscape() {
		return escape;
	}

	/**
	 * Sets the escape. Should be:
	 * 		0 -> escape like parent map
	 *		1 -> allow escape
	 *		2 -> disallow escape
	 * 
	 * @param escape the new escape
	 */
	public void setEscape(
			long escape) {
		this.escape = escape;
	}

	/**
	 * Returns the save. Should be:
	 * 		0 -> save like parent map
	 *		1 -> allow save
	 *		2 -> disallow save
	 * 
	 * @return the save
	 */
	public long getSave() {
		return save;
	}

	/**
	 * Sets the save. Should be:
	 * 		0 -> save like parent map
	 *		1 -> allow save
	 *		2 -> disallow save
	 * 
	 * @param save the new save
	 */
	public void setSave(
			long save) {
		this.save = save;
	}

	/**
	 * Returns the encounterRate
	 * 
	 * @return the encounterRate
	 */
	public long getEncounterRate() {
		return encounterRate;
	}

	/**
	 * Sets the encounterRate
	 * 
	 * @param encounterRate the new encounterRate
	 */
	public void setEncounterRate(
			long encounterRate) {
		this.encounterRate = encounterRate;
	}

	/**
	 * Returns the expanded
	 * 
	 * @return the expanded
	 */
	public boolean isExpanded() {
		return expanded;
	}

	/**
	 * Sets the expanded
	 * 
	 * @param expanded the new expanded
	 */
	public void setExpanded(
			boolean expanded) {
		this.expanded = expanded;
	}

	/**
	 * Returns the area. The area is a lenght-4 array, where the first two entrys are the x and y coordinates of the upper
	 * left corner and the secound two entries are the coordinates of the lower right corner, exclusive. E.g. 2,2,3,3
	 * will only contain the field 2,2.
	 * 
	 * @return the area
	 */
	public long[] getArea() {
		return area;
	}

	/**
	 * Sets the area. The area is a lenght-4 array, where the first two entrys are the x and y coordinates of the upper
	 * left corner and the secound two entries are the coordinates of the lower right corner, exclusive. E.g. 2,2,3,3
	 * will only contain the field 2,2.
	 * 
	 * @param area the new area
	 */
	public void setArea(
			long[] area) {
		this.area = area;
	}

	/**
	 * Returns the musicTrack
	 * 
	 * @return the musicTrack
	 */
	public LuciferMusicUnit getMusicTrack() {
		return musicTrack;
	}

	/**
	 * Sets the musicTrack
	 * 
	 * @param musicTrack the new musicTrack
	 */
	public void setMusicTrack(
			LuciferMusicUnit musicTrack) {
		this.musicTrack = musicTrack;
	}

	/**
	 * Returns the encounter
	 * 
	 * @return the encounter
	 */
	public LuciferEncounterData getEncounter() {
		return encounter;
	}

	/**
	 * Sets the encounter
	 * 
	 * @param encounter the new encounter
	 */
	public void setEncounter(
			LuciferEncounterData encounter) {
		this.encounter = encounter;
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