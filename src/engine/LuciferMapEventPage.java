package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LuciferMapEventPage {
	
	public LuciferMapEventPageCondition conditions		= new LuciferMapEventPageCondition();
	public String charset								= "";
	public long graphic								= 0;
	public long facing									= -1;
	public long stepping								= 1;
	public long movementType							= 0;
	public long movementFrequency						= 3;
	public long eventStartCondition					= 0;
	public long position								= 0;
	public long animationType							= 0;
	public long movementSpeed							= 2;
	private long scriptLength							= 0;
	public LuciferRoute route							= new LuciferRoute();
	public ArrayList<LuciferEventCommand> commands		= new ArrayList<LuciferEventCommand>();
	public boolean transparent							= false;
	public boolean preventEventOverlap					= false;
	
	/**
	 * Constructs a new LuciferMapEventPage
	 * 
	 * @param bytes byte-Array which represents the LuciferMapEventPage
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapEventPage(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferMapEventPage
	 * 
	 * @param dr DataReader which represents the LuciferMapEventPage
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferMapEventPage(DataReader dr) throws IOException {
		init(dr);
	}
	
	/**
	 * Constructs a new LuciferMapEventPage
	 */
	public LuciferMapEventPage() { }

	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			DataReader tmp;
			switch (unit.id) {
			case 0x02:
				conditions = new LuciferMapEventPageCondition(unit.content);
				break;
			case 0x15:
				charset = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x16:
				graphic = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x17:
				facing = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x18:
				stepping = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x19:
				transparent = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1F:
				movementType = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x20:
				movementFrequency = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x21:
				eventStartCondition = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x22:
				position = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x23:
				preventEventOverlap = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x24:
				animationType = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x25:
				movementSpeed = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x29:
				route = new LuciferRoute(unit.content);
				break;
			case 0x33:
				scriptLength = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x34:
				tmp = new DataReader(unit.content);
				commands = new ArrayList<LuciferEventCommand>();
				int finalPos = tmp.getPos() + (int) scriptLength;
				while (tmp.getPos() < finalPos) {
					commands.add(new LuciferEventCommand(tmp));
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferMapEventPage! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this MapEventPage
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			byte[] commandlist = new byte[0];
			for (int i = 0; i < commands.size(); i++) {
				commandlist = Helper.concatAll(commandlist, commands.get(i).write());
			}
			return Helper.concatAll(new LuciferBaseUnit(0x02, conditions.write()).write(),
					new LuciferBaseUnit(0x15, charset.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x16, DataReader.intToRPGint(graphic)).write(new byte[]{0}),
					new LuciferBaseUnit(0x17, DataReader.intToRPGint(facing)).write(new byte[0]),
					new LuciferBaseUnit(0x18, DataReader.intToRPGint(stepping)).write(new byte[]{1}),
					new LuciferBaseUnit(0x19, DataReader.intToRPGint((transparent) ? 1 : 0)).write(),
					new LuciferBaseUnit(0x1F, DataReader.intToRPGint(movementType)).write(),
					new LuciferBaseUnit(0x20, DataReader.intToRPGint(movementFrequency)).write(new byte[]{3}),
					new LuciferBaseUnit(0x21, DataReader.intToRPGint(eventStartCondition)).write(),
					new LuciferBaseUnit(0x22, DataReader.intToRPGint(position)).write(),
					new LuciferBaseUnit(0x23, DataReader.intToRPGint((preventEventOverlap) ? 1 : 0)).write(),
					new LuciferBaseUnit(0x24, DataReader.intToRPGint(animationType)).write(),
					new LuciferBaseUnit(0x25, DataReader.intToRPGint(movementSpeed)).write(new byte[]{2}),
					new LuciferBaseUnit(0x29, route.write()).write(),
					new LuciferBaseUnit(0x33, DataReader.intToRPGint(commandlist.length)).write(),
					new LuciferBaseUnit(0x34, commandlist).write(),
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
	     if (!(obj instanceof LuciferMapEventPage)) {
	        return false; // different class
	     }
	     
	     LuciferMapEventPage o = (LuciferMapEventPage) obj;
	     
	     return graphic == o.graphic
	     		&& facing == o.facing
	     		&& stepping == o.stepping
	     		&& movementType == o.movementType
	     		&& movementFrequency == o.movementFrequency
	     		&& eventStartCondition == o.eventStartCondition
	     		&& position == o.position
	     		&& animationType == o.animationType
	     		&& movementSpeed == o.movementSpeed
	     		&& scriptLength == o.scriptLength
	     		&& transparent == o.transparent
	     		&& preventEventOverlap == o.preventEventOverlap
	     		&& charset.equals(o.charset)
	     		&& conditions.equals(o.conditions)
	     		&& route.equals(o.route)
	     		&& commands.equals(o.commands);
	}
}