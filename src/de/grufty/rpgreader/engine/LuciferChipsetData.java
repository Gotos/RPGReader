package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * This class models an Chipset of the RM2k.
 * 
 * @author gRuFtY
 */
public class LuciferChipsetData implements UnitInterface {
	
	private String name					= "";
	private String graphic				= "";
	private int[] terrainIds			= newFilledIntArray(162, 1);
	private boolean[] lowerLayerDown	= newFilledBooleanArray(162, true);
	private boolean[] lowerLayerLeft	= newFilledBooleanArray(162, true); //TODO: Check these defaults!
	private boolean[] lowerLayerRight	= newFilledBooleanArray(162, true);
	private boolean[] lowerLayerUp		= newFilledBooleanArray(162, true);
	private boolean[] lowerLayerAbove	= newFilledBooleanArray(162, false);
	private boolean[] lowerLayerWall	= newFilledBooleanArray(162, false);
	private boolean[] upperLayerDown	= newFilledBooleanArray(144, true);
	private boolean[] upperLayerLeft	= newFilledBooleanArray(144, true);
	private boolean[] upperLayerRight	= newFilledBooleanArray(144, true);
	private boolean[] upperLayerUp		= newFilledBooleanArray(144, true);
	private boolean[] upperLayerAbove	= newFilledBooleanArray(144, false);
	private boolean[] upperLayerCounter	= newFilledBooleanArray(144, false);
	private boolean wateranimation		= false;
	private boolean waterspeed			= false;

	//TODO: more getter/setter for Layers?
	//TODO: can upperLayers use wall/lowerlayers use counter?
	
	/**
	 * Sets the wateranimation to 1-2-3, if set is true, else to 1-2-3-2
	 * 
	 * @param set decides, which animation to use
	 */
	public void setWateranimation123(boolean set) {
		wateranimation = true;
	}
	
	/**
	 * Returns, if wateranimation is set to 1-2-3
	 * 
	 * @return true, if wateranimation is set to 1-2-3. false, if wateranimation is 1-2-3-2
	 */
	public boolean isWateranimation123() {
		return wateranimation;
	}
	
	/**
	 * Sets the wateranimationspeed to high, if set is true, else to low
	 * 
	 * @param set decides, which speed to use
	 */
	public void setWaterspeed(boolean set) {
		waterspeed = set;
	}
	
	/**
	 * Returns if wateranimation-speed is set to high
	 * 
	 * @return true, if wateranimation-speed is high
	 */
	public boolean isWaterspeedHigh() {
		return waterspeed;
	}
	
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
	 * Sets if the tile at the given position of the UpperLayer is drawn above hero
	 * 
	 * @param pos Position of the tile on the UpperLayer
	 * @param bool new above-value
	 */
	public void setUpperLayerAbove(int pos, boolean bool) {
		upperLayerAbove[pos] = bool;
	}
	
	/**
	 * Returns if the tile at the given position of the upperLayer is drawn above hero
	 * 
	 * @param pos Position of the tile on the upperLayer
	 * @return true if the tile at the given position of the upperLayer is drawn above hero
	 */
	public boolean isUpperLayerAbove(int pos) {
		return upperLayerAbove[pos];
	}
	
	/**
	 * Sets if the tile at the given position of the upperLayer is a counter
	 * 
	 * @param pos Position of the tile on the upperLayer
	 * @param bool new counter-value
	 */
	public void setUpperLayerCounter(int pos, boolean bool) {
		upperLayerCounter[pos] = bool;
	}
	
	/**
	 * Returns if the tile at the given position of the upperLayer is a counter
	 * 
	 * @param pos Position of the tile on the upperLayer
	 * @return true if the tile at the given position of the upperLayer is a counter
	 */
	public boolean isUpperLayerCounter(int pos) {
		return upperLayerCounter[pos];
	}
	
	/**
	 * Returns a boolean array with length 4, which represent the passability of the upperLayer on Position pos.
	 * The first boolean indicates, if "down" is allowed, the secound indicates "left", the third "right" and the last one "up".
	 * 
	 * @param pos Position on the upperLayer. Should be within 0 and 143.
	 * @return a boolean array with length 4, which represent the passability of the lowerLayer
	 */
	public boolean[] getUpperLayerPassability(int pos) {
		return new boolean[] { upperLayerDown[pos], upperLayerLeft[pos], upperLayerRight[pos], upperLayerUp[pos] };
	}
	
	/**
	 * Sets the passability of the upperLayer on Position pos to passability
	 * 
	 * @param pos Position on the upperLayer. Should be within 0 and 143.
	 * @param passability new Passability. This is a boolean array of length 4, where the first element indicates, if "down"
	 * is allowed, the secound indicates "left", the third "right" and the last "up".
	 */
	public void setUpperLayerPassability(int pos, boolean[] passability) {
		upperLayerDown[pos] = passability[0];
		upperLayerLeft[pos] = passability[1];
		upperLayerRight[pos] = passability[2];
		upperLayerUp[pos] = passability[3];
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
	 * @param dr DataReader which represents the LuciferChipsetData
	 * @throws IOException thrown, if something is wrong with the DataReader
	 */
	public LuciferChipsetData(DataReader dr) throws IOException {
		init(dr);
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
				for (int i = 0; i < unit.content.length; i++) {
					upperLayerDown[i] = (DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer % 2 == 1);
					upperLayerLeft[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x2) % 2 == 1);
					upperLayerRight[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x4) % 2 == 1);
					upperLayerUp[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x8) % 2 == 1);
					upperLayerAbove[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x10) % 2 == 1);
					upperLayerCounter[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 0x40) % 2 == 1);
				}
				break;
			case 0x0B:
				wateranimation = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0C:
				waterspeed = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferChipsetData! ID: " + unit.id);
			}
			unit = sr.nextUnitReadID();
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
				* result + ((graphic == null) ? 0
						: graphic.hashCode());
		result = prime
				* result + Arrays.hashCode(lowerLayerAbove);
		result = prime
				* result + Arrays.hashCode(lowerLayerDown);
		result = prime
				* result + Arrays.hashCode(lowerLayerLeft);
		result = prime
				* result + Arrays.hashCode(lowerLayerRight);
		result = prime
				* result + Arrays.hashCode(lowerLayerUp);
		result = prime
				* result + Arrays.hashCode(lowerLayerWall);
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + Arrays.hashCode(terrainIds);
		result = prime
				* result + Arrays.hashCode(upperLayerAbove);
		result = prime
				* result + Arrays.hashCode(upperLayerCounter);
		result = prime
				* result + Arrays.hashCode(upperLayerDown);
		result = prime
				* result + Arrays.hashCode(upperLayerLeft);
		result = prime
				* result + Arrays.hashCode(upperLayerRight);
		result = prime
				* result + Arrays.hashCode(upperLayerUp);
		result = prime
				* result + (wateranimation ? 1231
						: 1237);
		result = prime
				* result + (waterspeed ? 1231
						: 1237);
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
		if (!(obj instanceof LuciferChipsetData)) {
			return false;
		}
		LuciferChipsetData other = (LuciferChipsetData) obj;
		if (graphic == null) {
			if (other.graphic != null) {
				return false;
			}
		} else if (!graphic.equals(other.graphic)) {
			return false;
		}
		if (!Arrays.equals(
				lowerLayerAbove, other.lowerLayerAbove)) {
			return false;
		}
		if (!Arrays.equals(
				lowerLayerDown, other.lowerLayerDown)) {
			return false;
		}
		if (!Arrays.equals(
				lowerLayerLeft, other.lowerLayerLeft)) {
			return false;
		}
		if (!Arrays.equals(
				lowerLayerRight, other.lowerLayerRight)) {
			return false;
		}
		if (!Arrays.equals(
				lowerLayerUp, other.lowerLayerUp)) {
			return false;
		}
		if (!Arrays.equals(
				lowerLayerWall, other.lowerLayerWall)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (!Arrays.equals(
				terrainIds, other.terrainIds)) {
			return false;
		}
		if (!Arrays.equals(
				upperLayerAbove, other.upperLayerAbove)) {
			return false;
		}
		if (!Arrays.equals(
				upperLayerCounter, other.upperLayerCounter)) {
			return false;
		}
		if (!Arrays.equals(
				upperLayerDown, other.upperLayerDown)) {
			return false;
		}
		if (!Arrays.equals(
				upperLayerLeft, other.upperLayerLeft)) {
			return false;
		}
		if (!Arrays.equals(
				upperLayerRight, other.upperLayerRight)) {
			return false;
		}
		if (!Arrays.equals(
				upperLayerUp, other.upperLayerUp)) {
			return false;
		}
		if (wateranimation != other.wateranimation) {
			return false;
		}
		if (waterspeed != other.waterspeed) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the byte-representation of this ChipsetUnit
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			byte[] terrain = new byte[324];
			for (int i = 0; i < terrainIds.length; i++) {
				byte[] thisTile = DataReader.to16bitle(terrainIds[i]);
				terrain[i * 2] = thisTile[0];
				terrain[i * 2 + 1] = thisTile[1];
			}
			byte[] defaultTerrain = new byte[324];
			for (int i = 0; i < terrainIds.length; i++) {
				defaultTerrain[i * 2] = 1;
				defaultTerrain[i * 2 + 1] = 0;
			}
			byte[] lowerLayer = new byte[162];
			for (int i = 0; i < 162; i++) {
				lowerLayer[i] = (byte) ((lowerLayerDown[i] ? 1 : 0)
								+ (lowerLayerLeft[i] ? 2 : 0)
								+ (lowerLayerRight[i] ? 4 : 0)
								+ (lowerLayerUp[i] ? 8 : 0)
								+ (lowerLayerAbove[i] ? 16 : 0)
								+ (lowerLayerWall[i] ? 32 : 0));
			}
			byte[] defaultLowerLayer = new byte[162];
			Arrays.fill(defaultLowerLayer, (byte) 15);
			byte[] upperLayer = new byte[14];
			for (int i = 0; i < 144; i++) {
				upperLayer[i] = (byte) ((upperLayerDown[i] ? 1 : 0)
								+ (upperLayerLeft[i] ? 2 : 0)
								+ (upperLayerRight[i] ? 4 : 0)
								+ (upperLayerUp[i] ? 8 : 0)
								+ (upperLayerAbove[i] ? 16 : 0)
								+ (upperLayerCounter[i] ? 32 : 0));
			}
			byte[] defaultUpperLayer = new byte[144];
			Arrays.fill(defaultUpperLayer, (byte) 15);
			defaultUpperLayer[0] += 10;
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x02, graphic.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x03, terrain).write(defaultTerrain),
					new LuciferBaseUnit(0x04, lowerLayer).write(defaultLowerLayer),
					new LuciferBaseUnit(0x05, upperLayer).write(defaultUpperLayer),
					new LuciferBaseUnit(0x0B, DataReader.intToRPGint(wateranimation ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x0C, DataReader.intToRPGint(waterspeed ? 1 : 0)).write(new byte[]{0}),
					new byte[]{0}
					);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}
