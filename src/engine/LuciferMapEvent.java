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
	
	public long id;
	public long xPos	= 0;
	public long yPos	= 0;
	public String name	= "";
	public ArrayList<LuciferMapEventPage> pages;
	
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