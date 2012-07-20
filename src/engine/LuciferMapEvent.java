package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a MapEvent of the RPG-Maker-Game.
 */
public class LuciferMapEvent {
	
	private long id;
	private long xPos	= 0;
	private long yPos	= 0;
	private String name	= "";
	private ArrayList<LuciferMapEventPage> pages;
	
	/**
	 * Returns the event-id
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the event-id. You should update the list of events on your map, too, or strange things may happen.
	 * 
	 * @param id the new id
	 */
	public void setId(
			long id) {
		this.id = id;
	}

	/**
	 * Returns the xPos
	 * 
	 * @return the xPos
	 */
	public long getxPos() {
		return xPos;
	}

	/**
	 * Sets the xPos
	 * 
	 * @param xPos the new xPos
	 */
	public void setxPos(
			long xPos) {
		this.xPos = xPos;
	}

	/**
	 * Returns the yPos
	 * 
	 * @return the yPos
	 */
	public long getyPos() {
		return yPos;
	}

	/**
	 * Sets the yPos
	 * 
	 * @param yPos the new yPos
	 */
	public void setyPos(
			long yPos) {
		this.yPos = yPos;
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
	 * Returns the event-pages
	 * 
	 * @return the pages
	 */
	public ArrayList<LuciferMapEventPage> getPages() {
		return pages;
	}

	/**
	 * Sets the event-pages
	 * 
	 * @param pages the new pages
	 */
	public void setPages(
			ArrayList<LuciferMapEventPage> pages) {
		this.pages = pages;
	}

	/**
	 * Constructs a new LuciferMapEvent
	 * 
	 * @param bytes byte-Array which represents the LuciferMapEvent
	 * @param id EventID of this Event
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapEvent(byte[] bytes, long id) throws IOException {
		init(new DataReader(bytes), id);
	}
	
	/**
	 * Constructs a new LuciferMapEvent
	 * 
	 * @param dr DataReader which represents the LuciferMapEvent
	 * @param id EventID of this Event
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapEvent(DataReader dr, long id) throws IOException {
		init(dr, id);
	}
	
	private void init(DataReader sr, long gid) throws IOException {
		id = gid;
		DataReader tmp;
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				xPos = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				yPos = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				tmp = new DataReader(unit.content);
				pages = new ArrayList<LuciferMapEventPage>((int) tmp.nextInt());
				for (int i = 0; i < pages.size(); i++) {
					tmp.nextInt(); //read pagenumber
					pages.add(new LuciferMapEventPage(tmp));
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMapUnit! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
		
	}
	
	/**
	 * Returns the byte-representation of this MapEvent
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			byte[] pagelist = DataReader.intToRPGint(pages.size());
			for (int i = 0; i < pages.size(); i++) {
				pagelist = Helper.concatAll(pagelist, DataReader.intToRPGint(i + 1), pages.get(i).write());
			}
			pagelist = Helper.concatAll(pagelist);
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(),
					new LuciferBaseUnit(0x02, DataReader.intToRPGint(xPos)).write(new byte[]{0}),
					new LuciferBaseUnit(0x03, DataReader.intToRPGint(yPos)).write(new byte[]{0}),
					new LuciferBaseUnit(0x05, pagelist).write(),
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
				* result + (int) (id ^ (id >>> 32));
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + ((pages == null) ? 0
						: pages.hashCode());
		result = prime
				* result + (int) (xPos ^ (xPos >>> 32));
		result = prime
				* result + (int) (yPos ^ (yPos >>> 32));
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
		if (!(obj instanceof LuciferMapEvent)) {
			return false;
		}
		LuciferMapEvent other = (LuciferMapEvent) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pages == null) {
			if (other.pages != null) {
				return false;
			}
		} else if (!pages.equals(other.pages)) {
			return false;
		}
		if (xPos != other.xPos) {
			return false;
		}
		if (yPos != other.yPos) {
			return false;
		}
		return true;
	}
	
}