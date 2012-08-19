package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author alina
 *
 * This class represents a MapEventPage of the RPG-Maker-Game.
 */
public class LuciferMapEventPage {
	
	private LuciferMapEventPageCondition conditions		= new LuciferMapEventPageCondition();
	private String charset								= "";
	private long graphic								= 0;
	private long facing									= -1;
	private long stepping								= 1;
	private long movementType							= 0;
	private long movementFrequency						= 3;
	private long eventStartCondition					= 0;
	private long position								= 0;
	private long animationType							= 0;
	private long movementSpeed							= 2;
	private long scriptLength							= 0;
	private LuciferRoute route							= new LuciferRoute();
	private ArrayList<LuciferEventCommand> commands		= new ArrayList<LuciferEventCommand>();
	private boolean transparent							= false;
	private boolean preventEventOverlap					= false;
	
	/**
	 * Returns the conditions
	 * 
	 * @return the conditions
	 */
	public LuciferMapEventPageCondition getConditions() {
		return conditions;
	}

	/**
	 * Sets the conditions
	 * 
	 * @param conditions the new conditions
	 */
	public void setConditions(
			LuciferMapEventPageCondition conditions) {
		this.conditions = conditions;
	}

	/**
	 * Returns the charset
	 * 
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * Sets the charset
	 * 
	 * @param charset the new charset
	 */
	public void setCharset(
			String charset) {
		this.charset = charset;
	}

	/**
	 * Returns the graphic
	 * 
	 * @return the graphic
	 */
	public long getGraphic() {
		return graphic;
	}

	/**
	 * Sets the graphic
	 * 
	 * @param graphic the new graphic
	 */
	public void setGraphic(
			long graphic) {
		this.graphic = graphic;
	}

	/**
	 * Returns the facing
	 * 
	 * @return the facing
	 */
	public long getFacing() {
		return facing;
	}

	/**
	 * Sets the facing
	 * 
	 * @param facing the new facing
	 */
	public void setFacing(
			long facing) {
		this.facing = facing;
	}

	/**
	 * Returns the stepping
	 * 
	 * @return the stepping
	 */
	public long getStepping() {
		return stepping;
	}

	/**
	 * Sets the stepping
	 * 
	 * @param stepping the new stepping
	 */
	public void setStepping(
			long stepping) {
		this.stepping = stepping;
	}

	/**
	 * Returns the movementType
	 * 
	 * @return the movementType
	 */
	public long getMovementType() {
		return movementType;
	}

	/**
	 * Sets the movementType
	 * 
	 * @param movementType the new movementType
	 */
	public void setMovementType(
			long movementType) {
		this.movementType = movementType;
	}

	/**
	 * Returns the movementFrequency
	 * 
	 * @return the movementFrequency
	 */
	public long getMovementFrequency() {
		return movementFrequency;
	}

	/**
	 * Sets the movementFrequency
	 * 
	 * @param movementFrequency the new movementFrequency
	 */
	public void setMovementFrequency(
			long movementFrequency) {
		this.movementFrequency = movementFrequency;
	}

	/**
	 * Returns the eventStartCondition
	 * 
	 * @return the eventStartCondition
	 */
	public long getEventStartCondition() {
		return eventStartCondition;
	}

	/**
	 * Sets the eventStartCondition
	 * 
	 * @param eventStartCondition the new eventStartCondition
	 */
	public void setEventStartCondition(
			long eventStartCondition) {
		this.eventStartCondition = eventStartCondition;
	}

	/**
	 * Returns the position
	 * 
	 * @return the position
	 */
	public long getPosition() {
		return position;
	}

	/**
	 * Sets the position
	 * 
	 * @param position the new position
	 */
	public void setPosition(
			long position) {
		this.position = position;
	}

	/**
	 * Returns the animationType
	 * 
	 * @return the animationType
	 */
	public long getAnimationType() {
		return animationType;
	}

	/**
	 * Sets the animationType
	 * 
	 * @param animationType the new animationType
	 */
	public void setAnimationType(
			long animationType) {
		this.animationType = animationType;
	}

	/**
	 * Returns the movementSpeed
	 * 
	 * @return the movementSpeed
	 */
	public long getMovementSpeed() {
		return movementSpeed;
	}

	/**
	 * Sets the movementSpeed
	 * 
	 * @param movementSpeed the new movementSpeed
	 */
	public void setMovementSpeed(
			long movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	/**
	 * Returns the route
	 * 
	 * @return the route
	 */
	public LuciferRoute getRoute() {
		return route;
	}

	/**
	 * Sets the route
	 * 
	 * @param route the new route
	 */
	public void setRoute(
			LuciferRoute route) {
		this.route = route;
	}

	/**
	 * Returns the commands
	 * 
	 * @return the commands
	 */
	public ArrayList<LuciferEventCommand> getCommands() {
		return commands;
	}

	/**
	 * Sets the commands
	 * 
	 * @param commands the new commands
	 */
	public void setCommands(
			ArrayList<LuciferEventCommand> commands) {
		this.commands = commands;
	}

	/**
	 * Returns the transparent
	 * 
	 * @return the transparent
	 */
	public boolean isTransparent() {
		return transparent;
	}

	/**
	 * Sets the transparent
	 * 
	 * @param transparent the new transparent
	 */
	public void setTransparent(
			boolean transparent) {
		this.transparent = transparent;
	}

	/**
	 * Returns the preventEventOverlap
	 * 
	 * @return the preventEventOverlap
	 */
	public boolean isPreventEventOverlap() {
		return preventEventOverlap;
	}

	/**
	 * Sets the preventEventOverlap
	 * 
	 * @param preventEventOverlap the new preventEventOverlap
	 */
	public void setPreventEventOverlap(
			boolean preventEventOverlap) {
		this.preventEventOverlap = preventEventOverlap;
	}

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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result + (int) (animationType ^ (animationType >>> 32));
		result = prime
				* result + ((charset == null) ? 0
						: charset.hashCode());
		result = prime
				* result + ((commands == null) ? 0
						: commands.hashCode());
		result = prime
				* result + ((conditions == null) ? 0
						: conditions.hashCode());
		result = prime
				* result + (int) (eventStartCondition ^ (eventStartCondition >>> 32));
		result = prime
				* result + (int) (facing ^ (facing >>> 32));
		result = prime
				* result + (int) (graphic ^ (graphic >>> 32));
		result = prime
				* result + (int) (movementFrequency ^ (movementFrequency >>> 32));
		result = prime
				* result + (int) (movementSpeed ^ (movementSpeed >>> 32));
		result = prime
				* result + (int) (movementType ^ (movementType >>> 32));
		result = prime
				* result + (int) (position ^ (position >>> 32));
		result = prime
				* result + (preventEventOverlap ? 1231
						: 1237);
		result = prime
				* result + ((route == null) ? 0
						: route.hashCode());
		result = prime
				* result + (int) (stepping ^ (stepping >>> 32));
		result = prime
				* result + (transparent ? 1231
						: 1237);
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
		if (!(obj instanceof LuciferMapEventPage)) {
			return false;
		}
		LuciferMapEventPage other = (LuciferMapEventPage) obj;
		if (animationType != other.animationType) {
			return false;
		}
		if (charset == null) {
			if (other.charset != null) {
				return false;
			}
		} else if (!charset.equals(other.charset)) {
			return false;
		}
		if (commands == null) {
			if (other.commands != null) {
				return false;
			}
		} else if (!commands.equals(other.commands)) {
			return false;
		}
		if (conditions == null) {
			if (other.conditions != null) {
				return false;
			}
		} else if (!conditions.equals(other.conditions)) {
			return false;
		}
		if (eventStartCondition != other.eventStartCondition) {
			return false;
		}
		if (facing != other.facing) {
			return false;
		}
		if (graphic != other.graphic) {
			return false;
		}
		if (movementFrequency != other.movementFrequency) {
			return false;
		}
		if (movementSpeed != other.movementSpeed) {
			return false;
		}
		if (movementType != other.movementType) {
			return false;
		}
		if (position != other.position) {
			return false;
		}
		if (preventEventOverlap != other.preventEventOverlap) {
			return false;
		}
		if (route == null) {
			if (other.route != null) {
				return false;
			}
		} else if (!route.equals(other.route)) {
			return false;
		}
		if (stepping != other.stepping) {
			return false;
		}
		if (transparent != other.transparent) {
			return false;
		}
		return true;
	}
}