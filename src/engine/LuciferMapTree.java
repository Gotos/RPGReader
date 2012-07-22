package engine;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a MapTree of the RPG-Maker-Game.
 */
public class LuciferMapTree implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8982844369928596911L;
	
	public ArrayList<LuciferMapTreeMap> maps;
	public ArrayList<Long> mapOrder;
	public long selectedMap		= 0;
	public long partyStartMap		= -1;
	public long partyStartX		= -1;
	public long partyStartY		= -1;
	public long shipStartMap		= -1;
	public long shipStartX			= -1;
	public long shipStartY			= -1;
	public long boatStartMap		= -1;
	public long boatStartX			= -1;
	public long boatStartY			= -1;
	public long airshipStartMap	= -1;
	public long airshipStartX		= -1;
	public long airshipStartY		= -1;
	
	/**
	 * Constructs a new LuciferMapTree
	 */
	public LuciferMapTree() { }
	
	/**
	 * Constructs a new LuciferMapTree
	 * 
	 * @param bytes byte-Array which represents the LuciferMapTree
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapTree(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMapTree
	 * 
	 * @param dr DataReader which represents the LuciferMapTree
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapTree(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		int nrMaps = (int) sr.nextInt();
		maps = new ArrayList<LuciferMapTreeMap>(nrMaps);
		for (int i = 0; i < nrMaps; i++) {
			long id = sr.nextInt();
			maps.set((int) id, new LuciferMapTreeMap(sr, id));
		}
		sr.nextInt(); //nrMaps is repeated here. Because we already know nrMaps, we wont save it, but we still need to read it.
		mapOrder = new ArrayList<Long>(nrMaps);
		for (int i = 0; i < nrMaps; i++) {
			mapOrder.add(sr.nextInt());
		}
		selectedMap = sr.nextInt();
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				partyStartMap = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x02:
				partyStartX = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				partyStartY = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0B:
				shipStartMap = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				shipStartX = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				shipStartY = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				boatStartMap = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x16:
				boatStartX = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x17:
				boatStartY = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x1F:
				airshipStartMap = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x20:
				airshipStartX = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x21:
				airshipStartY = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMapTree! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this MapTree
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		byte[] mapbytes = new byte[0];
		int nrMaps = 0;
		for (int i = 0; i < maps.size(); i++) {
			if (maps.get(i) != null) {
				nrMaps++;
				mapbytes = Helper.concatAll(mapbytes, DataReader.intToRPGint(i), maps.get(i).write());
			}
		}
		byte[] maporderbytes = new byte[0];
		for (long map:mapOrder) {
			maporderbytes = Helper.concatAll(maporderbytes, DataReader.intToRPGint(map));
		}
		try {
			return Helper.concatAll(new LuciferBaseUnit(0x01, "LcfMapTree".getBytes(Encoder.ENCODING)).write(false),
					DataReader.intToRPGint(nrMaps),
					mapbytes,
					DataReader.intToRPGint(nrMaps),
					maporderbytes,
					DataReader.intToRPGint(selectedMap),
					new LuciferBaseUnit(0x01, DataReader.intToRPGint(partyStartMap)).write(new byte[0]),
					new LuciferBaseUnit(0x02, DataReader.intToRPGint(partyStartX)).write(new byte[0]),
					new LuciferBaseUnit(0x03, DataReader.intToRPGint(partyStartY)).write(new byte[0]),
					new LuciferBaseUnit(0x0b, DataReader.intToRPGint(shipStartMap)).write(new byte[0]),
					new LuciferBaseUnit(0x0c, DataReader.intToRPGint(shipStartX)).write(new byte[0]),
					new LuciferBaseUnit(0x0d, DataReader.intToRPGint(shipStartY)).write(new byte[0]),
					new LuciferBaseUnit(0x15, DataReader.intToRPGint(boatStartMap)).write(new byte[0]),
					new LuciferBaseUnit(0x16, DataReader.intToRPGint(boatStartX)).write(new byte[0]),
					new LuciferBaseUnit(0x17, DataReader.intToRPGint(boatStartY)).write(new byte[0]),
					new LuciferBaseUnit(0x1F, DataReader.intToRPGint(airshipStartMap)).write(new byte[0]),
					new LuciferBaseUnit(0x20, DataReader.intToRPGint(airshipStartX)).write(new byte[0]),
					new LuciferBaseUnit(0x21, DataReader.intToRPGint(airshipStartY)).write(new byte[0]),
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
				* result + (int) (airshipStartMap ^ (airshipStartMap >>> 32));
		result = prime
				* result + (int) (airshipStartX ^ (airshipStartX >>> 32));
		result = prime
				* result + (int) (airshipStartY ^ (airshipStartY >>> 32));
		result = prime
				* result + (int) (boatStartMap ^ (boatStartMap >>> 32));
		result = prime
				* result + (int) (boatStartX ^ (boatStartX >>> 32));
		result = prime
				* result + (int) (boatStartY ^ (boatStartY >>> 32));
		result = prime
				* result + ((mapOrder == null) ? 0
						: mapOrder.hashCode());
		result = prime
				* result + ((maps == null) ? 0
						: maps.hashCode());
		result = prime
				* result + (int) (partyStartMap ^ (partyStartMap >>> 32));
		result = prime
				* result + (int) (partyStartX ^ (partyStartX >>> 32));
		result = prime
				* result + (int) (partyStartY ^ (partyStartY >>> 32));
		result = prime
				* result + (int) (selectedMap ^ (selectedMap >>> 32));
		result = prime
				* result + (int) (shipStartMap ^ (shipStartMap >>> 32));
		result = prime
				* result + (int) (shipStartX ^ (shipStartX >>> 32));
		result = prime
				* result + (int) (shipStartY ^ (shipStartY >>> 32));
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
		if (!(obj instanceof LuciferMapTree)) {
			return false;
		}
		LuciferMapTree other = (LuciferMapTree) obj;
		if (airshipStartMap != other.airshipStartMap) {
			return false;
		}
		if (airshipStartX != other.airshipStartX) {
			return false;
		}
		if (airshipStartY != other.airshipStartY) {
			return false;
		}
		if (boatStartMap != other.boatStartMap) {
			return false;
		}
		if (boatStartX != other.boatStartX) {
			return false;
		}
		if (boatStartY != other.boatStartY) {
			return false;
		}
		if (mapOrder == null) {
			if (other.mapOrder != null) {
				return false;
			}
		} else if (!mapOrder.equals(other.mapOrder)) {
			return false;
		}
		if (maps == null) {
			if (other.maps != null) {
				return false;
			}
		} else if (!maps.equals(other.maps)) {
			return false;
		}
		if (partyStartMap != other.partyStartMap) {
			return false;
		}
		if (partyStartX != other.partyStartX) {
			return false;
		}
		if (partyStartY != other.partyStartY) {
			return false;
		}
		if (selectedMap != other.selectedMap) {
			return false;
		}
		if (shipStartMap != other.shipStartMap) {
			return false;
		}
		if (shipStartX != other.shipStartX) {
			return false;
		}
		if (shipStartY != other.shipStartY) {
			return false;
		}
		return true;
	}
}