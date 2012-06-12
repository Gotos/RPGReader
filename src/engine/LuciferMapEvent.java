package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class LuciferMapEvent {
	
	public long id;
	public long xPos	= 0;
	public long yPos	= 0;
	public String name	= "";
	public LuciferMapEventPage[] pages;
	
	public LuciferMapEvent(byte[] str, long gid) throws IOException {
		init(new DataReader(str), gid);
	}
	
	public LuciferMapEvent(DataReader sr, long gid) throws IOException {
		init(sr, gid);
	}
	
	private void init(DataReader sr, long gid) throws IOException {
		id = gid;
		DataReader tmp;
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				//System.out.println("Event " + id + ": " + name);
				break;
			case 0x02:
				xPos = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x03:
				yPos = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				tmp = new DataReader(unit.content);
				pages = new LuciferMapEventPage[(int) tmp.nextInt()];
				for (int i = 0; i < pages.length; i++) {
					tmp.nextInt(); //read pagenumber
					//System.out.println("Page:"+i);
					pages[i] = new LuciferMapEventPage(tmp);
				}
				break;
			}
			unit = sr.nextUnit();
		}
		
	}
	
	public byte[] write() {
		try {
			byte[] pagelist = DataReader.intToRPGint(pages.length);
			for (int i = 0; i < pages.length; i++) {
				pagelist = Helper.concatAll(pagelist, DataReader.intToRPGint(i + 1), pages[i].write());
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
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferMapEvent)) {
	        return false; // different class
	     }
	     
	     LuciferMapEvent o = (LuciferMapEvent) obj;
	     
	     return id == o.id
	     		&& xPos == o.xPos
	     		&& yPos == o.yPos
	     		&& name.equals(o.name)
	     		&& Arrays.equals(pages, o.pages);
	}
	
}