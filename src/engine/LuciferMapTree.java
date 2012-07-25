package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a MapTree of the RPG-Maker-Game.
 */
public class LuciferMapTree {
	
	private ArrayList<LuciferMapTreeMap> maps;
	private ArrayList<Long> mapOrder;
	private long selectedMap		= 0;
	private long partyStartMap		= 0;
	private long partyStartX		= 0;
	private long partyStartY		= 0;
	private long shipStartMap		= 0;
	private long shipStartX			= 0;
	private long shipStartY			= 0;
	private long boatStartMap		= 0;
	private long boatStartX			= 0;
	private long boatStartY			= 0;
	private long airshipStartMap	= 0;
	private long airshipStartX		= 0;
	private long airshipStartY		= 0;
	
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
		maps.add(null);
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
		int nrMaps = maps.size();
		for (int i = 1; i <= maps.size(); i++) {
			if (maps.get(i) != null) {
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
	
	/**
	 * Returns the maps
	 * 
	 * @return the maps
	 */
	public ArrayList<LuciferMapTreeMap> getMaps() {
		return maps;
	}

	/**
	 * Sets the maps. It should always fit the mapOrder.
	 * 
	 * @param maps the new maps
	 */
	public void setMaps(
			ArrayList<LuciferMapTreeMap> maps) {
		this.maps = maps;
	}

	/**
	 * Returns the mapOrder
	 * 
	 * @return the mapOrder
	 */
	public ArrayList<Long> getMapOrder() {
		return mapOrder;
	}

	/**
	 * Sets the mapOrder. It should always fit the maps.
	 * 
	 * @param mapOrder the new mapOrder
	 */
	public void setMapOrder(
			ArrayList<Long> mapOrder) {
		this.mapOrder = mapOrder;
	}

	/**
	 * Returns the selectedMap, the map which will open when opening the rpg-maker.
	 * 
	 * @return the selectedMap
	 */
	public long getSelectedMap() {
		return selectedMap;
	}

	/**
	 * Sets the selectedMap, the map which will open when opening the rpg-maker.
	 * 
	 * @param selectedMap the new selectedMap
	 */
	public void setSelectedMap(
			long selectedMap) {
		this.selectedMap = selectedMap;
	}

	/**
	 * Returns the partyStartMap
	 * 
	 * @return the partyStartMap
	 */
	public long getPartyStartMap() {
		return partyStartMap;
	}

	/**
	 * Sets the partyStartMap
	 * 
	 * @param partyStartMap the new partyStartMap
	 */
	public void setPartyStartMap(
			long partyStartMap) {
		this.partyStartMap = partyStartMap;
	}

	/**
	 * Returns the partyStartX
	 * 
	 * @return the partyStartX
	 */
	public long getPartyStartX() {
		return partyStartX;
	}

	/**
	 * Sets the partyStartX
	 * 
	 * @param partyStartX the new partyStartX
	 */
	public void setPartyStartX(
			long partyStartX) {
		this.partyStartX = partyStartX;
	}

	/**
	 * Returns the partyStartY
	 * 
	 * @return the partyStartY
	 */
	public long getPartyStartY() {
		return partyStartY;
	}

	/**
	 * Sets the partyStartY
	 * 
	 * @param partyStartY the new partyStartY
	 */
	public void setPartyStartY(
			long partyStartY) {
		this.partyStartY = partyStartY;
	}

	/**
	 * Returns the shipStartMap
	 * 
	 * @return the shipStartMap
	 */
	public long getShipStartMap() {
		return shipStartMap;
	}

	/**
	 * Sets the shipStartMap
	 * 
	 * @param shipStartMap the new shipStartMap
	 */
	public void setShipStartMap(
			long shipStartMap) {
		this.shipStartMap = shipStartMap;
	}

	/**
	 * Returns the shipStartX
	 * 
	 * @return the shipStartX
	 */
	public long getShipStartX() {
		return shipStartX;
	}

	/**
	 * Sets the shipStartX
	 * 
	 * @param shipStartX the new shipStartX
	 */
	public void setShipStartX(
			long shipStartX) {
		this.shipStartX = shipStartX;
	}

	/**
	 * Returns the shipStartY
	 * 
	 * @return the shipStartY
	 */
	public long getShipStartY() {
		return shipStartY;
	}

	/**
	 * Sets the shipStartY
	 * 
	 * @param shipStartY the new shipStartY
	 */
	public void setShipStartY(
			long shipStartY) {
		this.shipStartY = shipStartY;
	}

	/**
	 * Returns the boatStartMap
	 * 
	 * @return the boatStartMap
	 */
	public long getBoatStartMap() {
		return boatStartMap;
	}

	/**
	 * Sets the boatStartMap
	 * 
	 * @param boatStartMap the new boatStartMap
	 */
	public void setBoatStartMap(
			long boatStartMap) {
		this.boatStartMap = boatStartMap;
	}

	/**
	 * Returns the boatStartX
	 * 
	 * @return the boatStartX
	 */
	public long getBoatStartX() {
		return boatStartX;
	}

	/**
	 * Sets the boatStartX
	 * 
	 * @param boatStartX the new boatStartX
	 */
	public void setBoatStartX(
			long boatStartX) {
		this.boatStartX = boatStartX;
	}

	/**
	 * Returns the boatStartY
	 * 
	 * @return the boatStartY
	 */
	public long getBoatStartY() {
		return boatStartY;
	}

	/**
	 * Sets the boatStartY
	 * 
	 * @param boatStartY the new boatStartY
	 */
	public void setBoatStartY(
			long boatStartY) {
		this.boatStartY = boatStartY;
	}

	/**
	 * Returns the airshipStartMap
	 * 
	 * @return the airshipStartMap
	 */
	public long getAirshipStartMap() {
		return airshipStartMap;
	}

	/**
	 * Sets the airshipStartMap
	 * 
	 * @param airshipStartMap the new airshipStartMap
	 */
	public void setAirshipStartMap(
			long airshipStartMap) {
		this.airshipStartMap = airshipStartMap;
	}

	/**
	 * Returns the airshipStartX
	 * 
	 * @return the airshipStartX
	 */
	public long getAirshipStartX() {
		return airshipStartX;
	}

	/**
	 * Sets the airshipStartX
	 * 
	 * @param airshipStartX the new airshipStartX
	 */
	public void setAirshipStartX(
			long airshipStartX) {
		this.airshipStartX = airshipStartX;
	}

	/**
	 * Returns the airshipStartY
	 * 
	 * @return the airshipStartY
	 */
	public long getAirshipStartY() {
		return airshipStartY;
	}

	/**
	 * Sets the airshipStartY
	 * 
	 * @param airshipStartY the new airshipStartY
	 */
	public void setAirshipStartY(
			long airshipStartY) {
		this.airshipStartY = airshipStartY;
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