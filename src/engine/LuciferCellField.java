package engine;

import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * This class models an CellField of an FrameUnit of an BattleAnimation of the RM2k.
 * 
 * @author gRuFtY
 */
public class LuciferCellField {
	
	private long patternno		= 0;
	private long xPos			= 0;
	private long yPos			= 0;
	private long magnifiction	= 100;
	private long red			= 100;
	private long green			= 100;
	private long blue			= 100;
	private long chroma			= 100;
	private long transparency	= 0;
	private boolean empty		= false;
	
	/**
	 * Returns if the LuciferCellField is empty
	 * 
	 * @return is the LuciferCellField empty?
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * Sets this LuciferCellField to the given empty-value
	 * 
	 * @param empty if true, set this LuciferCellField empty, else set it not empty
	 */
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	/**
	 * Sets this LuciferCellField to be empty
	 */
	public void setEmpty() {
		this.empty = true;
	}
	
	/**
	 * Sets this LuciferCellField not to be empty
	 */
	public void unsetEmpty() {
		this.empty = false;
	}
	
	/**
	 * Returns the magnifiction
	 * 
	 * @return the magnifiction
	 */
	public long getMagnifiction() {
		return magnifiction;
	}

	/**
	 * Sets the magnifiction
	 * 
	 * @param magnifiction New magnifiction; needs to be >= 0
	 */
	public void setMagnifiction(long magnifiction) {
		if (magnifiction < 0) {
			throw new IllegalArgumentException("Magnifiction may not be negativ!");
		}
		this.magnifiction = magnifiction;
	}

	/**
	 * Returns the red-factor
	 * 
	 * @return the red-factor
	 */
	public long getRed() {
		return red;
	}

	/**
	 * Sets the red-factor
	 * 
	 * @param red new red-factor; needs to be >= 0
	 */
	public void setRed(long red) {
		if (red < 0) {
			throw new IllegalArgumentException("Color may not be negativ!");
		}
		this.red = red;
	}

	/**
	 * Returns the green-factor
	 * 
	 * @return the green-factor
	 */
	public long getGreen() {
		return green;
	}

	/**
	 * Sets the green-factor
	 * 
	 * @param green new green-factor; needs to be >= 0
	 */
	public void setGreen(long green) {
		if (green < 0) {
			throw new IllegalArgumentException("Color may not be negativ!");
		}
		this.green = green;
	}

	/**
	 * Returns the blue-factor
	 * 
	 * @return the blue-factor
	 */
	public long getBlue() {
		return blue;
	}

	/**
	 * Sets the blue-factor
	 * 
	 * @param blue new blue-factor; needs to be >= 0
	 */
	public void setBlue(long blue) {
		if (blue < 0) {
			throw new IllegalArgumentException("Color may not be negativ!");
		}
		this.blue = blue;
	}

	/**
	 * Returns chroma
	 * 
	 * @return chroma
	 */
	public long getChroma() {
		return chroma;
	}

	/**
	 * Sets the chroma
	 * 
	 * @param chroma new chroma; needs to be >= 0
	 */
	public void setChroma(long chroma) {
		if (chroma < 0) {
			throw new IllegalArgumentException("Color may not be negativ!");
		}
		this.chroma = chroma;
	}

	/**
	 * Returns transparency
	 * 
	 * @return transparency
	 */
	public long getTransparency() {
		return transparency;
	}

	/**
	 * Sets the transparency
	 * 
	 * @param transparency new transparency; needs to be >= 0
	 */
	public void setTransparency(long transparency) {
		if (transparency < 0) {
			throw new IllegalArgumentException("Transparency may not be negativ!");
		}
		this.transparency = transparency;
	}
	
	/**
	 * Returns the index on the pattern which is shown by the CellField.
	 * Begins with 0 for leftmost frame on the Pattern
	 * 
	 * @return PatternIndex
	 */
	public long getPatternIndex() {
		return patternno;
	}

	/**
	 * Sets a new PatternIndex. The PatternIndex is the location of the frame on the Animationpattern.
	 * Begins with 0 for the leftmost frame on the pattern.
	 * 
	 * @param index new PatternIndex
	 * @throws InvalidParameterException is thrown, if the new Index is not >= 0.
	 */
	public void setPatternIndex(long index) throws InvalidParameterException {
		if (index >= 0) {
			patternno = index;
		} else {
			throw new InvalidParameterException("PatternIndex must be >= 0!");
		}
	}
	
	/**
	 * Returns the X-Position of the Cell
	 * 
	 * @return X-Postion of the Cell
	 */
	public long getXPosition() {
		return xPos;
	}
	
	/**
	 * Sets the X-Position of the Cell. The Position is usually a multiple of 16.
	 * 
	 * @param xPos new x-Position of the Cell
	 */
	public void setXPostition(long xPos) {
		this.xPos = xPos;
	}
	
	/**
	 * Returns the Y-Position of the Cell
	 * 
	 * @return Y-Postion of the Cell
	 */
	public long getYPosition() {
		return yPos;
	}
	
	/**
	 * Sets the Y-Position of the Cell. The Position is usually a multiple of 16.
	 * 
	 * @param yPos new y-Position of the Cell
	 */
	public void setYPostition(long yPos) {
		this.yPos = yPos;
	}
	
	/**
	 * Constructs a new LuciferCellField
	 * 
	 * @param bytes byte-Array which represents the LuciferCellField
	 * @throws IOException thrown, if something is wrong with the bytes
	 */
	public LuciferCellField(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferCellField
	 * 
	 * @param sr StringReader which represents the LuciferCellField
	 * @throws IOException thrown, if something is wrong with the StringReader
	 */
	public LuciferCellField(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			switch(unit.id) {
			case 0x01:
				empty = (DataReader.rpgintToInt(unit.content).integer != 1);
			case 0x02:
				patternno = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				xPos = DataReader.rpgintToInt(unit.content).integer;
				if ((xPos & 0x00400000) != 0) {
					xPos |= 0xFF800000;
				}
				break;
			case 0x04:
				yPos = DataReader.rpgintToInt(unit.content).integer;
				if ((yPos & 0x00400000) != 0) {
					yPos |= 0xFF800000;
				}
				break;
			case 0x05:
				magnifiction = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				red = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x07:
				green = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x08:
				blue = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x09:
				chroma = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0A:
				transparency = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferCellField! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
		//sr.nextInt();
	}

	/**
	 * Returns the byte-representation of this CellField
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		return Helper.concatAll(new LuciferBaseUnit(0x01, DataReader.intToRPGint(empty ? 1 : 0)).write(new byte[]{1}),
				new LuciferBaseUnit(0x02, DataReader.intToRPGint(patternno)).write(new byte[]{0}),
				new LuciferBaseUnit(0x03, DataReader.intToRPGint(xPos)).write(new byte[]{0}),
				new LuciferBaseUnit(0x04, DataReader.intToRPGint(yPos)).write(new byte[]{0}),
				new LuciferBaseUnit(0x05, DataReader.intToRPGint(magnifiction)).write(new byte[]{100}),
				new LuciferBaseUnit(0x06, DataReader.intToRPGint(red)).write(new byte[]{100}),
				new LuciferBaseUnit(0x07, DataReader.intToRPGint(green)).write(new byte[]{100}),
				new LuciferBaseUnit(0x08, DataReader.intToRPGint(blue)).write(new byte[]{100}),
				new LuciferBaseUnit(0x09, DataReader.intToRPGint(chroma)).write(new byte[]{100}),
				new LuciferBaseUnit(0x0A, DataReader.intToRPGint(transparency)).write(new byte[]{0}),
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
	     if (!(obj instanceof LuciferCellField)) {
	        return false; // different class
	     }
	     
	     LuciferCellField o = (LuciferCellField) obj;
	     
	     return empty == o.empty
	     		&& patternno == o.patternno
	     		&& xPos == o.xPos
	     		&& yPos == o.yPos
	     		&& magnifiction == o.magnifiction
	     		&& red == o.red
	     		&& green == o.green
	     		&& blue == o.blue
	     		&& chroma == o.chroma
	     		&& transparency == o.transparency;
	}
}
