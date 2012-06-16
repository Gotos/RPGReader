package engine;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class LuciferMapTree implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8982844369928596911L;
	
	public ArrayList<LuciferMapTreeMap> maps;
	public long[] mapOrder;
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
	
	public LuciferMapTree() { }
	
	//TODO: byte-Konstruktor???
	
	public LuciferMapTree(DataReader sr) throws IOException {
		int nrMaps = (int) sr.nextInt();
		maps = new ArrayList<LuciferMapTreeMap>(nrMaps);
		for (int i = 0; i < nrMaps; i++) {
			long id = sr.nextInt();
			maps.set((int) id, new LuciferMapTreeMap(sr, id));
		}
		sr.nextInt(); //nrMaps is repeated here. Because we already know nrMaps, we wont save it, but we still need to read it.
		mapOrder = new long[nrMaps];
		for (int i = 0; i < nrMaps; i++) {
			mapOrder[i] = sr.nextInt();
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
			}
			//content[unit.id]=unit.content;
			unit = sr.nextUnit();
		}
	}
	
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
			// TODO Auto-generated catch block
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
	     if (!(obj instanceof LuciferMapTree)) {
	        return false; // different class
	     }
	     
	     LuciferMapTree o = (LuciferMapTree) obj;
	     
	     return selectedMap == o.selectedMap
	     		&& partyStartMap == o.partyStartMap
	     		&& partyStartX == o.partyStartX
	     		&& partyStartY == o.partyStartY
	     		&& shipStartMap == o.shipStartMap
	     		&& shipStartX == o.shipStartX
  				&& shipStartY == o.shipStartY
  				&& boatStartMap == o.boatStartMap
  				&& boatStartX == o.boatStartX
  				&& boatStartY == o.boatStartY
  				&& airshipStartMap == o.airshipStartMap
  				&& airshipStartX == o.airshipStartX
  				&& airshipStartY == o.airshipStartY
  				&& maps.equals(o.maps)
	     		&& Arrays.equals(mapOrder, o.mapOrder);
	}
}