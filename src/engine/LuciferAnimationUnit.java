package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author gRuFtY
 *
 * This class models an BattleAnimation of the RM2k.
 */
public class LuciferAnimationUnit {
	
	private String name					= "";
	private String filename				= "";
	private boolean applyScopeAll		= false;
	private short yOrientationLine		= 2;
	private LuciferTimingUnit[] timing;
	private LuciferFrameUnit[] frames;
	
	//TODO: Timing und Frames gehören nicht zusammen; es können zu einem Frame mehrere Timing-Events gehören
	
	/**
	 * Returns the name of the animation
	 * 
	 * @return name of the animation
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the animation to the given one
	 * 
	 * @param name new name of the animation
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the filename of the graphic for the animation
	 * 
	 * @return filename of the animation
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Sets the filename to the graphic of the animation to the given one
	 * 
	 * @param filename filename of the new graphic of the animation
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	/**
	 * Returns, if the Apply Scope is All
	 * 
	 * @return true, if the Apply Scope is set to All, otherwise false
	 */
	public boolean isApplyScopeAll() {
		return applyScopeAll;
	}
	
	/**
	 * Returns, if the Apply Scope is Single
	 * 
	 * @return true, if the Apply Scope is set to Single, otherwise false
	 */
	public boolean isApplyScopeSingle() {
		return !applyScopeAll;
	}
	
	/**
	 * Sets the Apply Scope to All
	 */
	public void setApplyScopeAll() {
		applyScopeAll = true;
	}
	
	/**
	 * Sets the Apply Scope to False
	 */
	public void setApplyScopeSingle() {
		applyScopeAll = false;
	}
	
	/**
	 * Sets the Apply Scope to All, if bool is true, and to Single, if bool is false
	 * 
	 * @param bool if true, set Apply Scope to All, otherwise to Single
	 */
	public void setApplyScopeAll(boolean bool) {
		applyScopeAll = bool;
	}
	
	/**
	 * Sets the Apply Scope to Single, if bool is true, and to All, if bool is false
	 * 
	 * @param bool if true, set Apply Scope to Single, otherwise to All
	 */
	public void setApplyScopeSingle(boolean bool) {
		applyScopeAll = !bool;
	}
	
	/**
	 * Returns the Y coord. line. 0 means Head, 1 translates to Center and 2 is Feed.
	 * 
	 * @return Y coord. line
	 */
	public short getYCoordLine() {
		return yOrientationLine;
	}
	
	/**
	 * Sets the Y coord. line.
	 * 
	 * @param yCoordLine new Y coord. line where 0 means Head, 1 translates to Center and 2 is Feed.
	 * @throws IllegalArgumentException is thrown, if yCoordLine is not 0, 1 or 2
	 */
	public void setYCoordLine(short yCoordLine) throws IllegalArgumentException {
		if (yCoordLine < 3 && yCoordLine >= 0) {
			yOrientationLine = yCoordLine;
		} else {
			throw new IllegalArgumentException("yCoordLine must be within 0 (head) and 2 (feed)");
		}
	}
	
	/**
	 * Returns the Timing of the Animation as an LuciferTimingUnit-Array
	 * 
	 * @return Timing
	 */
	public LuciferTimingUnit[] getTiming() {
		return timing;
	}
	
	/**
	 * Return the LuciferTimingUnit of frame index
	 * 
	 * @param index frame of which the LuciferTimingUnit should be returned
	 * @return LuciferTimingUnit of frame index
	 * @throws ArrayIndexOutOfBoundsException thrown, if Array-Index is out of Bounds. Surprise, surprise ;-)
	 */
	public LuciferTimingUnit getTiming(int index) throws ArrayIndexOutOfBoundsException {
		return timing[index];
	}
	
	/**
	 * Sets this animations Timing to the given LuciferTimingUnit-Array
	 * 
	 * @param timing new Timing of the animation
	 */
	public void setTiming(LuciferTimingUnit[] timing) {
		this.timing = timing;
	}
	
	/**
	 * Sets the Timing at frame index to the giving Timing
	 * 
	 * @param index frame, of which the timing should be set
	 * @param timing new Timing
	 * @throws ArrayIndexOutOfBoundsException thrown, if the index is not in the Timing-Array
	 */
	public void setTiming(int index, LuciferTimingUnit timing) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= this.timing.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		this.timing[index] = timing;
	}
	
	/**
	 * Returns the Frames of this Animation as LuciferFrameUnit-Array
	 * 
	 * @return Frames of this Animation
	 */
	public LuciferFrameUnit[] getFrames() {
		return frames;
	}
	
	/**
	 * Returns the Frame at the given Index
	 * 
	 * @param index Index of the Frame
	 * @return frame at index
	 * @throws ArrayIndexOutOfBoundsException thrown, if Array-Index is out of Bounds. Surprise, surprise ;-)
	 */
	public LuciferFrameUnit getFrame(int index) throws ArrayIndexOutOfBoundsException {
		return frames[index];
	}
	
	/**
	 * Sets this animations Frames to the given LuciferFrameUnit-Array
	 * 
	 * @param frames new Frames
	 */
	public void setFrames(LuciferFrameUnit[] frames) {
		this.frames = frames;
	}
	
	/**
	 * Sets the Frame at index to the giving Frame
	 * 
	 * @param index frame that should be set
	 * @param frame new Frame
	 * @throws ArrayIndexOutOfBoundsException thrown, if the index is not in the Frame-Array
	 */
	public void setFrame(int index, LuciferFrameUnit frame) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= this.frames.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		this.frames[index] = frame;
	}
	
	/**
	 * Constructs a new LuciferAnimationUnit
	 * 
	 * @param bytes byte-Array which represents the LuciferAnimationUnit
	 * @throws IOException thrown, if something is wrong with the bytes
	 */
	public LuciferAnimationUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferAnimationUnit
	 * 
	 * @param sr StringReader which represents the LuciferAnimationUnit
	 * @throws IOException thrown, if something is wrong with the StringReader
	 */
	public LuciferAnimationUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		DataReader tmp;
		while (unit.id != 0) {
			switch(unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				filename = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x06:
				tmp = new DataReader(unit.content);
				timing = new LuciferTimingUnit[(int) tmp.nextInt()];
				for (int i = 0; i < timing.length; i++) {
					tmp.nextInt();
					timing[i] = new LuciferTimingUnit(tmp);
				}
				break;
			case 0x09:
				applyScopeAll = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0A:
				yOrientationLine = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				tmp = new DataReader(unit.content);
				frames = new LuciferFrameUnit[(int) tmp.nextInt()];
				for (int i = 0; i < frames.length; i++) {
					tmp.nextInt();
					frames[i] = new LuciferFrameUnit(tmp);
					tmp.nextInt();
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferAnimationUnit! ID: " + unit.id);
			}
		unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this AnimationUnit
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		byte[] timingblock = new byte[0];
		byte[] framesblock = new byte[0];
		for (int i = 0; i < timing.length; i++) {
			timingblock = Helper.concatAll(timingblock, timing[i].write());
			framesblock = Helper.concatAll(framesblock, frames[i].write());
		}
		try {
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x02, filename.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x06, timingblock).write(new byte[0]),
					new LuciferBaseUnit(0x09, DataReader.intToRPGint(applyScopeAll ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x0A, DataReader.intToRPGint(yOrientationLine)).write(new byte[]{2}),
					new LuciferBaseUnit(0x0C, framesblock).write(new byte[0]),
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
	     if (!(obj instanceof LuciferAnimationUnit)) {
	        return false; // different class
	     }
	     
	     LuciferAnimationUnit o = (LuciferAnimationUnit) obj;
	     
	     return name == o.name
	     		&& filename == o.filename
	     		&& applyScopeAll == o.applyScopeAll
	     		&& yOrientationLine == o.yOrientationLine
	     		&& frames.equals(o.frames)
	     		&& timing.equals(o.timing);
	}
}
