package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a MapUnit of the RPG-Maker-Game.
 */
public class LuciferMapUnit {
	
	private long chipset					= 1;
	private long width						= 20;
	private long height	 					= 15;
	private long loop			 			= 0;
	private long horizontalScrollSpeed		= 0;
	private long verticalScrollSpeed		= 0;
	private long timesSaved					= 0;
	private ArrayList<Integer> lowerLayer;
	private ArrayList<Integer> upperLayer;
	private boolean usePanorama				= false;
	private boolean horizontalPan			= false;
	private boolean verticalPan				= false;
	private boolean horizontalAutoScroll	= false;
	private boolean verticalAutoScroll		= false;
	private String panorama					= "";
	private ArrayList<LuciferMapEvent> events;
	
	/**
	 * Constructs a new LuciferMapUnit
	 * 
	 * @param bytes byte-Array which represents the LuciferMapUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMapUnit
	 * 
	 * @param dr DataReader which represents the LuciferMapUnit
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapUnit(DataReader dr) throws IOException {
		init(dr);
	}
	
	/**
	 * Constructs a new LuciferMapUnit
	 */
	public LuciferMapUnit() { }

	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			DataReader tmp;
			switch (unit.id) {
			case 0x01:
				chipset = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x02:
				width = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				height = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				loop = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x1F:
				usePanorama = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x20:
				panorama = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x21:
				horizontalPan = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x22:
				verticalPan = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x23:
				horizontalAutoScroll = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x24:
				horizontalScrollSpeed = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x25:
				verticalAutoScroll = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x26:
				verticalScrollSpeed = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x47:
				tmp = new DataReader(unit.content);
				lowerLayer = new ArrayList<Integer>(unit.content.length / 2);
				for (int i = 0; i < unit.content.length / 2; i++) {
					lowerLayer.add((int) tmp.next16bitle()); //TODO check if correct
				}
				break;
			case 0x48:
				tmp = new DataReader(unit.content);
				upperLayer = new ArrayList<Integer>(unit.content.length / 2);
				for (int i = 0; i < unit.content.length / 2; i++) {
					upperLayer.add((int) tmp.next16bitle() - 10000);
				}
				break;
			case 0x51:
				tmp = new DataReader(unit.content);
				int nrEvents = (int) tmp.nextInt();
				events = new ArrayList<LuciferMapEvent>(nrEvents);
				for (int i = 0; i < nrEvents; i++) {
					long id = tmp.nextInt();
					events.set((int) id, new LuciferMapEvent(tmp, id));
				}
				
				break;
			case 0x5B:
				timesSaved = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMapUnit! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the chipset
	 * 
	 * @return the chipset
	 */
	public long getChipset() {
		return chipset;
	}

	/**
	 * Sets the chipset
	 * 
	 * @param chipset the new chipset
	 */
	public void setChipset(
			long chipset) {
		this.chipset = chipset;
	}

	/**
	 * Returns the width
	 * 
	 * @return the width
	 */
	public long getWidth() {
		return width;
	}

	/**
	 * Sets the width
	 * 
	 * @param width the new width
	 */
	public void setWidth(
			long width) {
		this.width = width;
	}

	/**
	 * Returns the height
	 * 
	 * @return the height
	 */
	public long getHeight() {
		return height;
	}

	/**
	 * Sets the height
	 * 
	 * @param height the new height
	 */
	public void setHeight(
			long height) {
		this.height = height;
	}

	/**
	 * Returns the loop
	 * 
	 * @return the loop
	 */
	public long getLoop() {
		return loop;
	}

	/**
	 * Sets the loop
	 * 
	 * @param loop the new loop
	 */
	public void setLoop(
			long loop) {
		this.loop = loop;
	}

	/**
	 * Returns the horizontalScrollSpeed
	 * 
	 * @return the horizontalScrollSpeed
	 */
	public long getHorizontalScrollSpeed() {
		return horizontalScrollSpeed;
	}

	/**
	 * Sets the horizontalScrollSpeed
	 * 
	 * @param horizontalScrollSpeed the new horizontalScrollSpeed
	 */
	public void setHorizontalScrollSpeed(
			long horizontalScrollSpeed) {
		this.horizontalScrollSpeed = horizontalScrollSpeed;
	}

	/**
	 * Returns the verticalScrollSpeed
	 * 
	 * @return the verticalScrollSpeed
	 */
	public long getVerticalScrollSpeed() {
		return verticalScrollSpeed;
	}

	/**
	 * Sets the verticalScrollSpeed
	 * 
	 * @param verticalScrollSpeed the new verticalScrollSpeed
	 */
	public void setVerticalScrollSpeed(
			long verticalScrollSpeed) {
		this.verticalScrollSpeed = verticalScrollSpeed;
	}

	/**
	 * Returns the timesSaved
	 * 
	 * @return the timesSaved
	 */
	public long getTimesSaved() {
		return timesSaved;
	}

	/**
	 * Sets the timesSaved
	 * 
	 * @param timesSaved the new timesSaved
	 */
	public void setTimesSaved(
			long timesSaved) {
		this.timesSaved = timesSaved;
	}

	/**
	 * Returns the lowerLayer
	 * 
	 * @return the lowerLayer
	 */
	public ArrayList<Integer> getLowerLayer() {
		return lowerLayer;
	}

	/**
	 * Sets the lowerLayer
	 * 
	 * @param lowerLayer the new lowerLayer
	 */
	public void setLowerLayer(
			ArrayList<Integer> lowerLayer) {
		this.lowerLayer = lowerLayer;
	}

	/**
	 * Returns the upperLayer
	 * 
	 * @return the upperLayer
	 */
	public ArrayList<Integer> getUpperLayer() {
		return upperLayer;
	}

	/**
	 * Sets the upperLayer
	 * 
	 * @param upperLayer the new upperLayer
	 */
	public void setUpperLayer(
			ArrayList<Integer> upperLayer) {
		this.upperLayer = upperLayer;
	}

	/**
	 * Returns the usePanorama
	 * 
	 * @return the usePanorama
	 */
	public boolean isUsePanorama() {
		return usePanorama;
	}

	/**
	 * Sets the usePanorama
	 * 
	 * @param usePanorama the new usePanorama
	 */
	public void setUsePanorama(
			boolean usePanorama) {
		this.usePanorama = usePanorama;
	}

	/**
	 * Returns the horizontalPan
	 * 
	 * @return the horizontalPan
	 */
	public boolean isHorizontalPan() {
		return horizontalPan;
	}

	/**
	 * Sets the horizontalPan
	 * 
	 * @param horizontalPan the new horizontalPan
	 */
	public void setHorizontalPan(
			boolean horizontalPan) {
		this.horizontalPan = horizontalPan;
	}

	/**
	 * Returns the verticalPan
	 * 
	 * @return the verticalPan
	 */
	public boolean isVerticalPan() {
		return verticalPan;
	}

	/**
	 * Sets the verticalPan
	 * 
	 * @param verticalPan the new verticalPan
	 */
	public void setVerticalPan(
			boolean verticalPan) {
		this.verticalPan = verticalPan;
	}

	/**
	 * Returns the horizontalAutoScroll
	 * 
	 * @return the horizontalAutoScroll
	 */
	public boolean isHorizontalAutoScroll() {
		return horizontalAutoScroll;
	}

	/**
	 * Sets the horizontalAutoScroll
	 * 
	 * @param horizontalAutoScroll the new horizontalAutoScroll
	 */
	public void setHorizontalAutoScroll(
			boolean horizontalAutoScroll) {
		this.horizontalAutoScroll = horizontalAutoScroll;
	}

	/**
	 * Returns the verticalAutoScroll
	 * 
	 * @return the verticalAutoScroll
	 */
	public boolean isVerticalAutoScroll() {
		return verticalAutoScroll;
	}

	/**
	 * Sets the verticalAutoScroll
	 * 
	 * @param verticalAutoScroll the new verticalAutoScroll
	 */
	public void setVerticalAutoScroll(
			boolean verticalAutoScroll) {
		this.verticalAutoScroll = verticalAutoScroll;
	}

	/**
	 * Returns the panorama
	 * 
	 * @return the panorama
	 */
	public String getPanorama() {
		return panorama;
	}

	/**
	 * Sets the panorama
	 * 
	 * @param panorama the new panorama
	 */
	public void setPanorama(
			String panorama) {
		this.panorama = panorama;
	}

	/**
	 * Returns the events
	 * 
	 * @return the events
	 */
	public ArrayList<LuciferMapEvent> getEvents() {
		return events;
	}

	/**
	 * Sets the events
	 * 
	 * @param events the new events
	 */
	public void setEvents(
			ArrayList<LuciferMapEvent> events) {
		this.events = events;
	}

	/**
	 * Returns the byte-representation of this MapUnit
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			byte[] lowerLayerWrite = new byte[0];
			for (long tile : lowerLayer) {
				lowerLayerWrite = Helper.concatAll(lowerLayerWrite, DataReader.to16bitle(tile));
			}
			byte[] upperLayerWrite = new byte[0];
			for (long tile : upperLayer) {
				upperLayerWrite = Helper.concatAll(upperLayerWrite, DataReader.to16bitle(tile));
			}
			byte[] eventlist = new byte[0];
			long nrEvents = events.size();
			for (int i = 0; i < events.size(); i++) {
				if (events.get(i) != null) {
					eventlist = Helper.concatAll(eventlist,
							DataReader.intToRPGint(i),
							events.get(i).write());
				}
			}
			eventlist = Helper.concatAll(DataReader.intToRPGint(nrEvents), eventlist);
			return Helper.concatAll(new LuciferBaseUnit(0x01, "LcfMapUnit".getBytes(Encoder.ENCODING)).write(false), 
					new LuciferBaseUnit(0x01, DataReader.intToRPGint(chipset)).write(new byte[]{1}),
					new LuciferBaseUnit(0x02, DataReader.intToRPGint(width)).write(new byte[]{20}),
					new LuciferBaseUnit(0x03, DataReader.intToRPGint(height)).write(new byte[]{15}),
					new LuciferBaseUnit(0x0B, DataReader.intToRPGint(loop)).write(),
					new LuciferBaseUnit(0x1F, DataReader.intToRPGint(usePanorama ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x20, panorama.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x21, DataReader.intToRPGint(horizontalPan ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x22, DataReader.intToRPGint(verticalPan ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x23, DataReader.intToRPGint(horizontalAutoScroll ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x24, DataReader.intToRPGint(horizontalScrollSpeed)).write(new byte[]{0}),
					new LuciferBaseUnit(0x25, DataReader.intToRPGint(verticalAutoScroll ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x26, DataReader.intToRPGint(verticalScrollSpeed)).write(new byte[]{0}),
					new LuciferBaseUnit(0x47, lowerLayerWrite).write(),
					new LuciferBaseUnit(0x48, upperLayerWrite).write(),
					new LuciferBaseUnit(0x51, eventlist).write(new byte[0]),
					new LuciferBaseUnit(0x5B, DataReader.intToRPGint(timesSaved)).write(),
					new byte[]{0}
					);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new byte[0];
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
				* result + (int) (chipset ^ (chipset >>> 32));
		result = prime
				* result + ((events == null) ? 0
						: events.hashCode());
		result = prime
				* result + (int) (height ^ (height >>> 32));
		result = prime
				* result + (horizontalAutoScroll ? 1231
						: 1237);
		result = prime
				* result + (horizontalPan ? 1231
						: 1237);
		result = prime
				* result + (int) (horizontalScrollSpeed ^ (horizontalScrollSpeed >>> 32));
		result = prime
				* result + (int) (loop ^ (loop >>> 32));
		result = prime
				* result + ((lowerLayer == null) ? 0
						: lowerLayer.hashCode());
		result = prime
				* result + ((panorama == null) ? 0
						: panorama.hashCode());
		result = prime
				* result + (int) (timesSaved ^ (timesSaved >>> 32));
		result = prime
				* result + ((upperLayer == null) ? 0
						: upperLayer.hashCode());
		result = prime
				* result + (usePanorama ? 1231
						: 1237);
		result = prime
				* result + (verticalAutoScroll ? 1231
						: 1237);
		result = prime
				* result + (verticalPan ? 1231
						: 1237);
		result = prime
				* result + (int) (verticalScrollSpeed ^ (verticalScrollSpeed >>> 32));
		result = prime
				* result + (int) (width ^ (width >>> 32));
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
		if (!(obj instanceof LuciferMapUnit)) {
			return false;
		}
		LuciferMapUnit other = (LuciferMapUnit) obj;
		if (chipset != other.chipset) {
			return false;
		}
		if (events == null) {
			if (other.events != null) {
				return false;
			}
		} else if (!events.equals(other.events)) {
			return false;
		}
		if (height != other.height) {
			return false;
		}
		if (horizontalAutoScroll != other.horizontalAutoScroll) {
			return false;
		}
		if (horizontalPan != other.horizontalPan) {
			return false;
		}
		if (horizontalScrollSpeed != other.horizontalScrollSpeed) {
			return false;
		}
		if (loop != other.loop) {
			return false;
		}
		if (lowerLayer == null) {
			if (other.lowerLayer != null) {
				return false;
			}
		} else if (!lowerLayer.equals(other.lowerLayer)) {
			return false;
		}
		if (panorama == null) {
			if (other.panorama != null) {
				return false;
			}
		} else if (!panorama.equals(other.panorama)) {
			return false;
		}
		if (timesSaved != other.timesSaved) {
			return false;
		}
		if (upperLayer == null) {
			if (other.upperLayer != null) {
				return false;
			}
		} else if (!upperLayer.equals(other.upperLayer)) {
			return false;
		}
		if (usePanorama != other.usePanorama) {
			return false;
		}
		if (verticalAutoScroll != other.verticalAutoScroll) {
			return false;
		}
		if (verticalPan != other.verticalPan) {
			return false;
		}
		if (verticalScrollSpeed != other.verticalScrollSpeed) {
			return false;
		}
		if (width != other.width) {
			return false;
		}
		return true;
	}
}