package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LuciferMapUnit {
	
	public long chipset					= 1;
	public long width						= 20;
	public long height	 					= 15;
	public long loop			 			= 0;
	public long horizontalScrollSpeed		= 0;
	public long verticalScrollSpeed		= 0;
	public long timesSaved					= 0;
	public ArrayList<Long> lowerLayer;
	public ArrayList<Long> upperLayer;
	public boolean usePanorama				= false;
	public boolean horizontalPan			= false;
	public boolean verticalPan				= false;
	public boolean horizontalAutoScroll	= false;
	public boolean verticalAutoScroll		= false;
	public String panorama					= "";
	public ArrayList<LuciferMapEvent> events;
	
	public LuciferMapUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferMapUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	public LuciferMapUnit() {
		// TODO Auto-generated constructor stub
	}

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
				lowerLayer = new ArrayList<Long>(unit.content.length / 2);
				for (int i = 0; i < unit.content.length / 2; i++) {
					lowerLayer.add(tmp.next16bitle()); //TODO check if correct
				}
				break;
			case 0x48:
				tmp = new DataReader(unit.content);
				upperLayer = new ArrayList<Long>(unit.content.length / 2);
				for (int i = 0; i < unit.content.length / 2; i++) {
					upperLayer.add(tmp.next16bitle()); //TODO check if correct
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
			}
			unit = sr.nextUnit();
		}
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferMapUnit)) {
	        return false; // different class
	     }
	     
	     LuciferMapUnit o = (LuciferMapUnit) obj;
	     
	     //TODO: lowerLayer und upperLayer werden scheinbar inkorrekt geschrieben.
	     
	     return chipset == o.chipset
	     		&& width == o.width
	     		&& height == o.height
	     		&& loop == o.loop
	     		&& horizontalScrollSpeed == o.horizontalScrollSpeed
	     		&& verticalScrollSpeed == o.verticalScrollSpeed
	     		&& timesSaved == o.timesSaved
	     		&& lowerLayer.equals(o.lowerLayer)
	     		&& upperLayer.equals(o.upperLayer)
	     		&& usePanorama == o.usePanorama
	     		&& horizontalPan == o.horizontalPan
	     		&& horizontalAutoScroll == o.horizontalAutoScroll
	     		&& verticalPan == o.verticalPan
	     		&& verticalAutoScroll == o.verticalAutoScroll
	     		&& panorama.equals(o.panorama)
	     		&& events.equals(o.events);
	}
}