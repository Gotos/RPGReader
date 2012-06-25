package engine;

import java.io.IOException;
import java.util.Arrays;

/**
 * This class models an Chipset of the RM2k.
 * 
 * @author gRuFtY
 */
public class LuciferChipsetData {
	
	private String name					= "";
	private String graphic				= "";
	private int[] terrainIds			= newFilledIntArray(162, 1);
	private boolean[] lowerLayerDown	= newFilledBooleanArray(162, true);
	private boolean[] lowerLayerLeft	= newFilledBooleanArray(162, true); //TODO: Check these defaults!
	private boolean[] lowerLayerRight	= newFilledBooleanArray(162, true);
	private boolean[] lowerLayerUp		= newFilledBooleanArray(162, true);
	private boolean[] lowerLayerAbove	= newFilledBooleanArray(162, true);
	private boolean[] lowerLayerWall	= newFilledBooleanArray(162, true);
	public byte[] upperLayer			= new byte[144]; //Flags?
	
	//TODO: write()-methode!
	//TODO: more getter/setter for Layers?
	//TODO: can upperLayers use wall/lowerlayers use counter?
	
	private boolean[] newFilledBooleanArray(int length, boolean def) {
		boolean[] bool = new boolean[length];
		Arrays.fill(bool, def);
		return bool;
	}
	
	private int[] newFilledIntArray(int length, int def) {
		int[] i = new int[length];
		Arrays.fill(i, def);
		return i;
	}

	/**
	 * Sets if the tile at the given position of the LowerLayer is drawn above hero
	 * 
	 * @param pos Position of the tile on the LowerLayer
	 * @param bool new above-value
	 */
	public void setLowerLayerAbove(int pos, boolean bool) {
		lowerLayerAbove[pos] = bool;
	}
	
	/**
	 * Returns if the tile at the given position of the LowerLayer is drawn above hero
	 * 
	 * @param pos Position of the tile on the LowerLayer
	 * @return true if the tile at the given position of the LowerLayer is drawn above hero
	 */
	public boolean isLowerLayerAbove(int pos) {
		return lowerLayerAbove[pos];
	}
	
	/**
	 * Sets if the tile at the given position of the LowerLayer is drawn as a wall
	 * 
	 * @param pos Position of the tile on the LowerLayer
	 * @param bool new wall-value
	 */
	public void setLowerLayerWall(int pos, boolean bool) {
		lowerLayerWall[pos] = bool;
	}
	
	/**
	 * Returns if the tile at the given position of the LowerLayer is drawn as a wall
	 * 
	 * @param pos Position of the tile on the LowerLayer
	 * @return true if the tile at the given position of the LowerLayer is drawn as a wall
	 */
	public boolean isLowerLayerWall(int pos) {
		return lowerLayerWall[pos];
	}
	
	/**
	 * Returns a boolean array with length 4, which represent the passability of the lowerLayer on Position pos.
	 * The first boolean indicates, if "down" is allowed, the secound indicates "left", the third "right" and the last one "up".
	 * 
	 * @param pos Position on the LowerLayer. Should be within 0 and 161.
	 * @return a boolean array with length 4, which represent the passability of the lowerLayer
	 */
	public boolean[] getLowerLayerPassability(int pos) {
		return new boolean[] { lowerLayerDown[pos], lowerLayerLeft[pos], lowerLayerRight[pos], lowerLayerUp[pos] };
	}
	
	/**
	 * Returns the TerrainID of the given Position
	 * 
	 * @param pos The Position, of which the TerrainID should be returned. Should be within 0 and 161.
	 * @return the TerrainID of the given Position
	 */
	public int getTerrainID(int pos) {
		return terrainIds[pos];
	}
	
	/**
	 * Sets the TerrainID at pos to id
	 * 
	 * @param pos Postion, where the TerrainID should be changed. Should be within 0 and 161.
	 * @param id New TerrainID
	 */
	public void setTerrainID(int pos, int id) {
		if (id < 1) {
			throw new IllegalArgumentException("TerrainID needs to be > 0!");
		}
		terrainIds[pos] = id;
	}
	
	/**
	 * Sets the passability of the lowerLayer on Position pos to passability
	 * 
	 * @param pos Position on the LowerLayer. Should be within 0 and 161.
	 * @param passability new Passability. This is a boolean array of length 4, where the first element indicates, if "down"
	 * is allowed, the secound indicates "left", the third "right" and the last "up".
	 */
	public void setLowerLayerPassability(int pos, boolean[] passability) {
		lowerLayerDown[pos] = passability[0];
		lowerLayerLeft[pos] = passability[1];
		lowerLayerRight[pos] = passability[2];
		lowerLayerUp[pos] = passability[3];
	}
	
	/**
	 * Returns the name of the chipset
	 * 
	 * @return the name of the chipset
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the chipset
	 * 
	 * @param name the new name of the chipset
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the chipsets graphic
	 * 
	 * @return the name of the chipsets graphic
	 */
	public String getGraphic() {
		return graphic;
	}

	/**
	 * Sets the name of the chipsets graphic
	 * 
	 * @param graphic the new name of the chipsets graphic
	 */
	public void setGraphic(String graphic) {
		//TODO: *könnte* überprüfen, ob die Grafik existiert, muss aber nicht
		this.graphic = graphic;
	}

	/**
	 * Constructs a new LuciferChipsetData
	 * 
	 * @param bytes byte-Array which represents the LuciferChipsetData
	 * @throws IOException thrown, if something is wrong with the bytes
	 */
	public LuciferChipsetData(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferChipsetData
	 * 
	 * @param sr StringReader which represents the LuciferChipsetData
	 * @throws IOException thrown, if something is wrong with the StringReader
	 */
	public LuciferChipsetData(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			switch(unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				graphic = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x03:
				for (int i = 0; i < 162; i++) {
					terrainIds[i] = unit.content[i * 2] + (unit.content[(i * 2) + 1] << 8);
				}
				break;
			case 0x04:
				for (int i = 0; i < unit.content.length; i++) {
					lowerLayerDown[i] = (DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer % 2 == 1);
					lowerLayerLeft[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x2) % 2 == 1);
					lowerLayerRight[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x4) % 2 == 1);
					lowerLayerUp[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x8) % 2 == 1);
					lowerLayerAbove[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x10) % 2 == 1);
					lowerLayerWall[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x20) % 2 == 1);
				}
				break;
			case 0x05:
				upperLayer = unit.content;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferChipsetData! ID: " + unit.id);
			}
			unit = sr.nextUnitReadID();
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
	     if (!(obj instanceof LuciferChipsetData)) {
	        return false; // different class
	     }
	     
	     LuciferChipsetData o = (LuciferChipsetData) obj;
	     
	     return name == o.name
	     		&& graphic == o.graphic
	     		&& lowerLayerDown == o.lowerLayerDown
	     		&& lowerLayerLeft == o.lowerLayerLeft
	     		&& lowerLayerRight == o.lowerLayerRight
	     		&& lowerLayerUp == o.lowerLayerUp
	     		&& lowerLayerWall == o.lowerLayerWall
	     		&& lowerLayerAbove == o.lowerLayerAbove
	     		&& upperLayer == o.upperLayer
	     		&& terrainIds == o.terrainIds;
	}
}
