package com.github.gotos.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * This class represents the content system-tab of the RPG-Maker-Game.
 * 
 * @author alina
 *
 */
public class LuciferSystemData implements UnitInterface {
	
	private String ship						= "";
	private String boat						= "";
	private String airship					= "";
	private String titleGraphic				= "";
	private String goGraphic				= "";
	private String system					= "";
	private String battleTestBG				= "";
	private short shipIndex					= 0;
	private short boatIndex					= 0;
	private short airshipIndex				= 0;
	private short nrHeroes					= 1;
	private short teleportErase;
	private short teleportShow;
	private short battleErase;
	private short battleShow;
	private short battleEndErase;
	private short battleEndShow;
	private long numberOfSaves				= 0;
	private long bATestVictim				= 0;
	private boolean bAUseGrid				= false;
	private LuciferMusicUnit titleMusic;
	private LuciferMusicUnit battleMusic;
	private LuciferMusicUnit battleEndMusic;
	private LuciferMusicUnit innMusic;
	private LuciferMusicUnit boatMusic;
	private LuciferMusicUnit shipMusic;
	private LuciferMusicUnit airshipMusic;
	private LuciferMusicUnit gameOverMusic;
	private LuciferSoundUnit cursorSE;
	private LuciferSoundUnit decisionSE;
	private LuciferSoundUnit cancelSE;
	private LuciferSoundUnit buzzerSE;
	private LuciferSoundUnit startBattleSE;
	private LuciferSoundUnit escapeSE;
	private LuciferSoundUnit enemyAttackSE;
	private LuciferSoundUnit enemysDamageSE;
	private LuciferSoundUnit friendsDamageSE;
	private LuciferSoundUnit evasionSE;
	private LuciferSoundUnit enemyDieSE;
	private LuciferSoundUnit itemUseSE;
	private ArrayList<Long> heroes;
	
	/**
	 * Constructs a new LuciferSystemData
	 * 
	 * @param bytes byte-Array which represents the LuciferSystemData
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferSystemData(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferRoute
	 * 
	 * @param dr DataReader which represents the LuciferRoute
	 * @throws IOException is thrown on any parsing-error
	 */
	public LuciferSystemData(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader dr) throws IOException {
		LuciferBaseUnit unit = dr.nextUnitReadID();
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x0B:
				ship = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x0C:
				boat = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x0D:
				airship = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x0E:
				shipIndex = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0F:
				boatIndex = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x10:
				airshipIndex = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x11:
				titleGraphic = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x12:
				goGraphic = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x13:
				system = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x15:
				nrHeroes = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x16:
				heroes = new ArrayList<Long>(nrHeroes);
				DataReader subDr = new DataReader(unit.content);
				for (int i = 0; i < nrHeroes; i++) {
					heroes.add(subDr.nextInt());
				}
				break;
			case 0x1F:
				titleMusic = new LuciferMusicUnit(unit.content);
				break;
			case 0x20:
				battleMusic = new LuciferMusicUnit(unit.content);
				break;
			case 0x21:
				battleEndMusic = new LuciferMusicUnit(unit.content);
				break;
			case 0x22:
				innMusic = new LuciferMusicUnit(unit.content);
				break;
			case 0x23:
				boatMusic = new LuciferMusicUnit(unit.content);
				break;
			case 0x24:
				shipMusic = new LuciferMusicUnit(unit.content);
				break;
			case 0x25:
				airshipMusic = new LuciferMusicUnit(unit.content);
				break;
			case 0x26:
				gameOverMusic = new LuciferMusicUnit(unit.content);
				break;
			case 0x29:
				cursorSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x2A:
				decisionSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x2B:
				cancelSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x2C:
				buzzerSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x2D:
				startBattleSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x2E:
				escapeSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x2F:
				enemyAttackSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x30:
				enemysDamageSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x31:
				friendsDamageSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x32:
				evasionSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x33:
				enemyDieSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x34:
				itemUseSE = new LuciferSoundUnit(unit.content);
				break;
			case 0x3D:
				teleportErase = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x3E:
				teleportShow = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x3F:
				battleErase = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x40:
				battleShow = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x41:
				battleEndErase = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x42:
				battleEndShow = (short) DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x47:
				System.out.println("0x47: " + DataReader.rpgintToInt(unit.content).integer);
				break;
			case 0x48:
				System.out.println("0x48: " + DataReader.rpgintToInt(unit.content).integer);
				break;
			case 0x51:
				bATestVictim = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x52:
				bAUseGrid = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x54:
				battleTestBG = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x5B:
				numberOfSaves = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferSystemData! ID: " + unit.id + " " + new String(unit.content));
			}
			unit = dr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this Monster
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			byte[] heroArray = DataReader.intToRPGint(heroes.size());
			for (int i = 0; i < heroes.size(); i++) {
				if (heroes.get(i) != null) {
					heroArray = Helper.concatAll(heroArray, DataReader.intToRPGint(i), DataReader.intToRPGint(heroes.get(i)));
				}
			}
			
			return Helper.concatAll(new LuciferBaseUnit(0x0B, ship.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x0C, boat.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x0D, airship.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x0E, DataReader.intToRPGint(shipIndex)).write(new byte[]{0}),
					new LuciferBaseUnit(0x0F, DataReader.intToRPGint(boatIndex)).write(new byte[]{0}),
					new LuciferBaseUnit(0x10, DataReader.intToRPGint(airshipIndex)).write(new byte[]{0}),
					new LuciferBaseUnit(0x11, titleGraphic.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x12, goGraphic.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x13, system.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x15, DataReader.intToRPGint(heroes.size())).write(new byte[]{0}),
					new LuciferBaseUnit(0x16, heroArray).write(new byte[0]),
					new LuciferBaseUnit(0x1F, titleMusic.write()).write(new byte[0]),
					new LuciferBaseUnit(0x20, battleMusic.write()).write(new byte[0]),
					new LuciferBaseUnit(0x21, battleEndMusic.write()).write(new byte[0]),
					new LuciferBaseUnit(0x22, innMusic.write()).write(new byte[0]),
					new LuciferBaseUnit(0x23, boatMusic.write()).write(new byte[0]),
					new LuciferBaseUnit(0x24, shipMusic.write()).write(new byte[0]),
					new LuciferBaseUnit(0x25, airshipMusic.write()).write(new byte[0]),
					new LuciferBaseUnit(0x26, gameOverMusic.write()).write(new byte[0]),
					new LuciferBaseUnit(0x29, cursorSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x2A, decisionSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x2B, cancelSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x2C, buzzerSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x2D, startBattleSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x2E, escapeSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x2F, enemyAttackSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x30, enemysDamageSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x31, friendsDamageSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x32, evasionSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x33, enemyDieSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x34, itemUseSE.write()).write(new byte[0]),
					new LuciferBaseUnit(0x3D, DataReader.intToRPGint(teleportErase)).write(),
					new LuciferBaseUnit(0x3E, DataReader.intToRPGint(teleportShow)).write(),
					new LuciferBaseUnit(0x3F, DataReader.intToRPGint(battleErase)).write(),
					new LuciferBaseUnit(0x40, DataReader.intToRPGint(battleShow)).write(),
					new LuciferBaseUnit(0x41, DataReader.intToRPGint(battleEndErase)).write(),
					new LuciferBaseUnit(0x42, DataReader.intToRPGint(battleEndShow)).write(),
					//TODO: ID 47
					//TODO: ID 48
					new LuciferBaseUnit(0x51, DataReader.intToRPGint(bATestVictim)).write(new byte[]{0}),
					new LuciferBaseUnit(0x52, DataReader.intToRPGint(bAUseGrid ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x54, battleTestBG.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x5B, DataReader.intToRPGint(numberOfSaves)).write(new byte[]{0}),
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
		result = prime * result + ((airship == null) ? 0 : airship.hashCode());
		result = prime * result + airshipIndex;
		result = prime * result
				+ ((airshipMusic == null) ? 0 : airshipMusic.hashCode());
		result = prime * result + (int) (bATestVictim ^ (bATestVictim >>> 32));
		result = prime * result + (bAUseGrid ? 1231 : 1237);
		result = prime * result + battleEndErase;
		result = prime * result
				+ ((battleEndMusic == null) ? 0 : battleEndMusic.hashCode());
		result = prime * result + battleEndShow;
		result = prime * result + battleErase;
		result = prime * result
				+ ((battleMusic == null) ? 0 : battleMusic.hashCode());
		result = prime * result + battleShow;
		result = prime * result
				+ ((battleTestBG == null) ? 0 : battleTestBG.hashCode());
		result = prime * result + ((boat == null) ? 0 : boat.hashCode());
		result = prime * result + boatIndex;
		result = prime * result
				+ ((boatMusic == null) ? 0 : boatMusic.hashCode());
		result = prime * result
				+ ((buzzerSE == null) ? 0 : buzzerSE.hashCode());
		result = prime * result
				+ ((cancelSE == null) ? 0 : cancelSE.hashCode());
		result = prime * result
				+ ((cursorSE == null) ? 0 : cursorSE.hashCode());
		result = prime * result
				+ ((decisionSE == null) ? 0 : decisionSE.hashCode());
		result = prime * result
				+ ((enemyAttackSE == null) ? 0 : enemyAttackSE.hashCode());
		result = prime * result
				+ ((enemyDieSE == null) ? 0 : enemyDieSE.hashCode());
		result = prime * result
				+ ((enemysDamageSE == null) ? 0 : enemysDamageSE.hashCode());
		result = prime * result
				+ ((escapeSE == null) ? 0 : escapeSE.hashCode());
		result = prime * result
				+ ((evasionSE == null) ? 0 : evasionSE.hashCode());
		result = prime * result
				+ ((friendsDamageSE == null) ? 0 : friendsDamageSE.hashCode());
		result = prime * result
				+ ((gameOverMusic == null) ? 0 : gameOverMusic.hashCode());
		result = prime * result
				+ ((goGraphic == null) ? 0 : goGraphic.hashCode());
		result = prime * result + ((heroes == null) ? 0 : heroes.hashCode());
		result = prime * result
				+ ((innMusic == null) ? 0 : innMusic.hashCode());
		result = prime * result
				+ ((itemUseSE == null) ? 0 : itemUseSE.hashCode());
		result = prime * result + nrHeroes;
		result = prime * result
				+ (int) (numberOfSaves ^ (numberOfSaves >>> 32));
		result = prime * result + ((ship == null) ? 0 : ship.hashCode());
		result = prime * result + shipIndex;
		result = prime * result
				+ ((shipMusic == null) ? 0 : shipMusic.hashCode());
		result = prime * result
				+ ((startBattleSE == null) ? 0 : startBattleSE.hashCode());
		result = prime * result + ((system == null) ? 0 : system.hashCode());
		result = prime * result + teleportErase;
		result = prime * result + teleportShow;
		result = prime * result
				+ ((titleGraphic == null) ? 0 : titleGraphic.hashCode());
		result = prime * result
				+ ((titleMusic == null) ? 0 : titleMusic.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LuciferSystemData)) {
			return false;
		}
		LuciferSystemData other = (LuciferSystemData) obj;
		if (airship == null) {
			if (other.airship != null) {
				return false;
			}
		} else if (!airship.equals(other.airship)) {
			return false;
		}
		if (airshipIndex != other.airshipIndex) {
			return false;
		}
		if (airshipMusic == null) {
			if (other.airshipMusic != null) {
				return false;
			}
		} else if (!airshipMusic.equals(other.airshipMusic)) {
			return false;
		}
		if (bATestVictim != other.bATestVictim) {
			return false;
		}
		if (bAUseGrid != other.bAUseGrid) {
			return false;
		}
		if (battleEndErase != other.battleEndErase) {
			return false;
		}
		if (battleEndMusic == null) {
			if (other.battleEndMusic != null) {
				return false;
			}
		} else if (!battleEndMusic.equals(other.battleEndMusic)) {
			return false;
		}
		if (battleEndShow != other.battleEndShow) {
			return false;
		}
		if (battleErase != other.battleErase) {
			return false;
		}
		if (battleMusic == null) {
			if (other.battleMusic != null) {
				return false;
			}
		} else if (!battleMusic.equals(other.battleMusic)) {
			return false;
		}
		if (battleShow != other.battleShow) {
			return false;
		}
		if (battleTestBG == null) {
			if (other.battleTestBG != null) {
				return false;
			}
		} else if (!battleTestBG.equals(other.battleTestBG)) {
			return false;
		}
		if (boat == null) {
			if (other.boat != null) {
				return false;
			}
		} else if (!boat.equals(other.boat)) {
			return false;
		}
		if (boatIndex != other.boatIndex) {
			return false;
		}
		if (boatMusic == null) {
			if (other.boatMusic != null) {
				return false;
			}
		} else if (!boatMusic.equals(other.boatMusic)) {
			return false;
		}
		if (buzzerSE == null) {
			if (other.buzzerSE != null) {
				return false;
			}
		} else if (!buzzerSE.equals(other.buzzerSE)) {
			return false;
		}
		if (cancelSE == null) {
			if (other.cancelSE != null) {
				return false;
			}
		} else if (!cancelSE.equals(other.cancelSE)) {
			return false;
		}
		if (cursorSE == null) {
			if (other.cursorSE != null) {
				return false;
			}
		} else if (!cursorSE.equals(other.cursorSE)) {
			return false;
		}
		if (decisionSE == null) {
			if (other.decisionSE != null) {
				return false;
			}
		} else if (!decisionSE.equals(other.decisionSE)) {
			return false;
		}
		if (enemyAttackSE == null) {
			if (other.enemyAttackSE != null) {
				return false;
			}
		} else if (!enemyAttackSE.equals(other.enemyAttackSE)) {
			return false;
		}
		if (enemyDieSE == null) {
			if (other.enemyDieSE != null) {
				return false;
			}
		} else if (!enemyDieSE.equals(other.enemyDieSE)) {
			return false;
		}
		if (enemysDamageSE == null) {
			if (other.enemysDamageSE != null) {
				return false;
			}
		} else if (!enemysDamageSE.equals(other.enemysDamageSE)) {
			return false;
		}
		if (escapeSE == null) {
			if (other.escapeSE != null) {
				return false;
			}
		} else if (!escapeSE.equals(other.escapeSE)) {
			return false;
		}
		if (evasionSE == null) {
			if (other.evasionSE != null) {
				return false;
			}
		} else if (!evasionSE.equals(other.evasionSE)) {
			return false;
		}
		if (friendsDamageSE == null) {
			if (other.friendsDamageSE != null) {
				return false;
			}
		} else if (!friendsDamageSE.equals(other.friendsDamageSE)) {
			return false;
		}
		if (gameOverMusic == null) {
			if (other.gameOverMusic != null) {
				return false;
			}
		} else if (!gameOverMusic.equals(other.gameOverMusic)) {
			return false;
		}
		if (goGraphic == null) {
			if (other.goGraphic != null) {
				return false;
			}
		} else if (!goGraphic.equals(other.goGraphic)) {
			return false;
		}
		if (heroes == null) {
			if (other.heroes != null) {
				return false;
			}
		} else if (!heroes.equals(other.heroes)) {
			return false;
		}
		if (innMusic == null) {
			if (other.innMusic != null) {
				return false;
			}
		} else if (!innMusic.equals(other.innMusic)) {
			return false;
		}
		if (itemUseSE == null) {
			if (other.itemUseSE != null) {
				return false;
			}
		} else if (!itemUseSE.equals(other.itemUseSE)) {
			return false;
		}
		if (nrHeroes != other.nrHeroes) {
			return false;
		}
		if (numberOfSaves != other.numberOfSaves) {
			return false;
		}
		if (ship == null) {
			if (other.ship != null) {
				return false;
			}
		} else if (!ship.equals(other.ship)) {
			return false;
		}
		if (shipIndex != other.shipIndex) {
			return false;
		}
		if (shipMusic == null) {
			if (other.shipMusic != null) {
				return false;
			}
		} else if (!shipMusic.equals(other.shipMusic)) {
			return false;
		}
		if (startBattleSE == null) {
			if (other.startBattleSE != null) {
				return false;
			}
		} else if (!startBattleSE.equals(other.startBattleSE)) {
			return false;
		}
		if (system == null) {
			if (other.system != null) {
				return false;
			}
		} else if (!system.equals(other.system)) {
			return false;
		}
		if (teleportErase != other.teleportErase) {
			return false;
		}
		if (teleportShow != other.teleportShow) {
			return false;
		}
		if (titleGraphic == null) {
			if (other.titleGraphic != null) {
				return false;
			}
		} else if (!titleGraphic.equals(other.titleGraphic)) {
			return false;
		}
		if (titleMusic == null) {
			if (other.titleMusic != null) {
				return false;
			}
		} else if (!titleMusic.equals(other.titleMusic)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the ship
	 * 
	 * @return the ship
	 */
	public String getShip() {
		return ship;
	}

	/**
	 * Sets the ship
	 * 
	 * @param ship the new ship
	 */
	public void setShip(String ship) {
		this.ship = ship;
	}

	/**
	 * Returns the boat
	 * 
	 * @return the boat
	 */
	public String getBoat() {
		return boat;
	}

	/**
	 * Sets the boat
	 * 
	 * @param boat the new boat
	 */
	public void setBoat(String boat) {
		this.boat = boat;
	}

	/**
	 * Returns the airship
	 * 
	 * @return the airship
	 */
	public String getAirship() {
		return airship;
	}

	/**
	 * Sets the airship
	 * 
	 * @param airship the new airship
	 */
	public void setAirship(String airship) {
		this.airship = airship;
	}

	/**
	 * Returns the titleGraphic
	 * 
	 * @return the titleGraphic
	 */
	public String getTitleGraphic() {
		return titleGraphic;
	}

	/**
	 * Sets the titleGraphic
	 * 
	 * @param titleGraphic the new titleGraphic
	 */
	public void setTitleGraphic(String titleGraphic) {
		this.titleGraphic = titleGraphic;
	}

	/**
	 * Returns the goGraphic
	 * 
	 * @return the goGraphic
	 */
	public String getGoGraphic() {
		return goGraphic;
	}

	/**
	 * Sets the goGraphic
	 * 
	 * @param goGraphic the new goGraphic
	 */
	public void setGoGraphic(String goGraphic) {
		this.goGraphic = goGraphic;
	}

	/**
	 * Returns the system
	 * 
	 * @return the system
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * Sets the system
	 * 
	 * @param system the new system
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * Returns the battleTestBG
	 * 
	 * @return the battleTestBG
	 */
	public String getBattleTestBG() {
		return battleTestBG;
	}

	/**
	 * Sets the battleTestBG
	 * 
	 * @param battleTestBG the new battleTestBG
	 */
	public void setBattleTestBG(String battleTestBG) {
		this.battleTestBG = battleTestBG;
	}

	/**
	 * Returns the shipIndex
	 * 
	 * @return the shipIndex
	 */
	public short getShipIndex() {
		return shipIndex;
	}

	/**
	 * Sets the shipIndex
	 * 
	 * @param shipIndex the new shipIndex
	 */
	public void setShipIndex(short shipIndex) {
		this.shipIndex = shipIndex;
	}

	/**
	 * Returns the boatIndex
	 * 
	 * @return the boatIndex
	 */
	public short getBoatIndex() {
		return boatIndex;
	}

	/**
	 * Sets the boatIndex
	 * 
	 * @param boatIndex the new boatIndex
	 */
	public void setBoatIndex(short boatIndex) {
		this.boatIndex = boatIndex;
	}

	/**
	 * Returns the airshipIndex
	 * 
	 * @return the airshipIndex
	 */
	public short getAirshipIndex() {
		return airshipIndex;
	}

	/**
	 * Sets the airshipIndex
	 * 
	 * @param airshipIndex the new airshipIndex
	 */
	public void setAirshipIndex(short airshipIndex) {
		this.airshipIndex = airshipIndex;
	}

	/**
	 * Returns the nrHeroes
	 * 
	 * @return the nrHeroes
	 */
	public short getNrHeroes() {
		return nrHeroes;
	}

	/**
	 * Sets the nrHeroes
	 * 
	 * @param nrHeroes the new nrHeroes
	 */
	public void setNrHeroes(short nrHeroes) {
		this.nrHeroes = nrHeroes;
	}

	/**
	 * Returns the teleportErase
	 * 
	 * @return the teleportErase
	 */
	public short getTeleportErase() {
		return teleportErase;
	}

	/**
	 * Sets the teleportErase
	 * 
	 * @param teleportErase the new teleportErase
	 */
	public void setTeleportErase(short teleportErase) {
		this.teleportErase = teleportErase;
	}

	/**
	 * Returns the teleportShow
	 * 
	 * @return the teleportShow
	 */
	public short getTeleportShow() {
		return teleportShow;
	}

	/**
	 * Sets the teleportShow
	 * 
	 * @param teleportShow the new teleportShow
	 */
	public void setTeleportShow(short teleportShow) {
		this.teleportShow = teleportShow;
	}

	/**
	 * Returns the battleErase
	 * 
	 * @return the battleErase
	 */
	public short getBattleErase() {
		return battleErase;
	}

	/**
	 * Sets the battleErase
	 * 
	 * @param battleErase the new battleErase
	 */
	public void setBattleErase(short battleErase) {
		this.battleErase = battleErase;
	}

	/**
	 * Returns the battleShow
	 * 
	 * @return the battleShow
	 */
	public short getBattleShow() {
		return battleShow;
	}

	/**
	 * Sets the battleShow
	 * 
	 * @param battleShow the new battleShow
	 */
	public void setBattleShow(short battleShow) {
		this.battleShow = battleShow;
	}

	/**
	 * Returns the battleEndErase
	 * 
	 * @return the battleEndErase
	 */
	public short getBattleEndErase() {
		return battleEndErase;
	}

	/**
	 * Sets the battleEndErase
	 * 
	 * @param battleEndErase the new battleEndErase
	 */
	public void setBattleEndErase(short battleEndErase) {
		this.battleEndErase = battleEndErase;
	}

	/**
	 * Returns the battleEndShow
	 * 
	 * @return the battleEndShow
	 */
	public short getBattleEndShow() {
		return battleEndShow;
	}

	/**
	 * Sets the battleEndShow
	 * 
	 * @param battleEndShow the new battleEndShow
	 */
	public void setBattleEndShow(short battleEndShow) {
		this.battleEndShow = battleEndShow;
	}

	/**
	 * Returns the numberOfSaves
	 * 
	 * @return the numberOfSaves
	 */
	public long getNumberOfSaves() {
		return numberOfSaves;
	}

	/**
	 * Sets the numberOfSaves
	 * 
	 * @param numberOfSaves the new numberOfSaves
	 */
	public void setNumberOfSaves(long numberOfSaves) {
		this.numberOfSaves = numberOfSaves;
	}

	/**
	 * Returns the bATestVictim
	 * 
	 * @return the bATestVictim
	 */
	public long getbATestVictim() {
		return bATestVictim;
	}

	/**
	 * Sets the bATestVictim
	 * 
	 * @param bATestVictim the new bATestVictim
	 */
	public void setbATestVictim(long bATestVictim) {
		this.bATestVictim = bATestVictim;
	}

	/**
	 * Returns the bAUseGrid
	 * 
	 * @return the bAUseGrid
	 */
	public boolean isbAUseGrid() {
		return bAUseGrid;
	}

	/**
	 * Sets the bAUseGrid
	 * 
	 * @param bAUseGrid the new bAUseGrid
	 */
	public void setbAUseGrid(boolean bAUseGrid) {
		this.bAUseGrid = bAUseGrid;
	}

	/**
	 * Returns the titleMusic
	 * 
	 * @return the titleMusic
	 */
	public LuciferMusicUnit getTitleMusic() {
		return titleMusic;
	}

	/**
	 * Sets the titleMusic
	 * 
	 * @param titleMusic the new titleMusic
	 */
	public void setTitleMusic(LuciferMusicUnit titleMusic) {
		this.titleMusic = titleMusic;
	}

	/**
	 * Returns the battleMusic
	 * 
	 * @return the battleMusic
	 */
	public LuciferMusicUnit getBattleMusic() {
		return battleMusic;
	}

	/**
	 * Sets the battleMusic
	 * 
	 * @param battleMusic the new battleMusic
	 */
	public void setBattleMusic(LuciferMusicUnit battleMusic) {
		this.battleMusic = battleMusic;
	}

	/**
	 * Returns the battleEndMusic
	 * 
	 * @return the battleEndMusic
	 */
	public LuciferMusicUnit getBattleEndMusic() {
		return battleEndMusic;
	}

	/**
	 * Sets the battleEndMusic
	 * 
	 * @param battleEndMusic the new battleEndMusic
	 */
	public void setBattleEndMusic(LuciferMusicUnit battleEndMusic) {
		this.battleEndMusic = battleEndMusic;
	}

	/**
	 * Returns the innMusic
	 * 
	 * @return the innMusic
	 */
	public LuciferMusicUnit getInnMusic() {
		return innMusic;
	}

	/**
	 * Sets the innMusic
	 * 
	 * @param innMusic the new innMusic
	 */
	public void setInnMusic(LuciferMusicUnit innMusic) {
		this.innMusic = innMusic;
	}

	/**
	 * Returns the boatMusic
	 * 
	 * @return the boatMusic
	 */
	public LuciferMusicUnit getBoatMusic() {
		return boatMusic;
	}

	/**
	 * Sets the boatMusic
	 * 
	 * @param boatMusic the new boatMusic
	 */
	public void setBoatMusic(LuciferMusicUnit boatMusic) {
		this.boatMusic = boatMusic;
	}

	/**
	 * Returns the shipMusic
	 * 
	 * @return the shipMusic
	 */
	public LuciferMusicUnit getShipMusic() {
		return shipMusic;
	}

	/**
	 * Sets the shipMusic
	 * 
	 * @param shipMusic the new shipMusic
	 */
	public void setShipMusic(LuciferMusicUnit shipMusic) {
		this.shipMusic = shipMusic;
	}

	/**
	 * Returns the airshipMusic
	 * 
	 * @return the airshipMusic
	 */
	public LuciferMusicUnit getAirshipMusic() {
		return airshipMusic;
	}

	/**
	 * Sets the airshipMusic
	 * 
	 * @param airshipMusic the new airshipMusic
	 */
	public void setAirshipMusic(LuciferMusicUnit airshipMusic) {
		this.airshipMusic = airshipMusic;
	}

	/**
	 * Returns the gameOverMusic
	 * 
	 * @return the gameOverMusic
	 */
	public LuciferMusicUnit getGameOverMusic() {
		return gameOverMusic;
	}

	/**
	 * Sets the gameOverMusic
	 * 
	 * @param gameOverMusic the new gameOverMusic
	 */
	public void setGameOverMusic(LuciferMusicUnit gameOverMusic) {
		this.gameOverMusic = gameOverMusic;
	}

	/**
	 * Returns the cursorSE
	 * 
	 * @return the cursorSE
	 */
	public LuciferSoundUnit getCursorSE() {
		return cursorSE;
	}

	/**
	 * Sets the cursorSE
	 * 
	 * @param cursorSE the new cursorSE
	 */
	public void setCursorSE(LuciferSoundUnit cursorSE) {
		this.cursorSE = cursorSE;
	}

	/**
	 * Returns the decisionSE
	 * 
	 * @return the decisionSE
	 */
	public LuciferSoundUnit getDecisionSE() {
		return decisionSE;
	}

	/**
	 * Sets the decisionSE
	 * 
	 * @param decisionSE the new decisionSE
	 */
	public void setDecisionSE(LuciferSoundUnit decisionSE) {
		this.decisionSE = decisionSE;
	}

	/**
	 * Returns the cancelSE
	 * 
	 * @return the cancelSE
	 */
	public LuciferSoundUnit getCancelSE() {
		return cancelSE;
	}

	/**
	 * Sets the cancelSE
	 * 
	 * @param cancelSE the new cancelSE
	 */
	public void setCancelSE(LuciferSoundUnit cancelSE) {
		this.cancelSE = cancelSE;
	}

	/**
	 * Returns the buzzerSE
	 * 
	 * @return the buzzerSE
	 */
	public LuciferSoundUnit getBuzzerSE() {
		return buzzerSE;
	}

	/**
	 * Sets the buzzerSE
	 * 
	 * @param buzzerSE the new buzzerSE
	 */
	public void setBuzzerSE(LuciferSoundUnit buzzerSE) {
		this.buzzerSE = buzzerSE;
	}

	/**
	 * Returns the startBattleSE
	 * 
	 * @return the startBattleSE
	 */
	public LuciferSoundUnit getStartBattleSE() {
		return startBattleSE;
	}

	/**
	 * Sets the startBattleSE
	 * 
	 * @param startBattleSE the new startBattleSE
	 */
	public void setStartBattleSE(LuciferSoundUnit startBattleSE) {
		this.startBattleSE = startBattleSE;
	}

	/**
	 * Returns the escapeSE
	 * 
	 * @return the escapeSE
	 */
	public LuciferSoundUnit getEscapeSE() {
		return escapeSE;
	}

	/**
	 * Sets the escapeSE
	 * 
	 * @param escapeSE the new escapeSE
	 */
	public void setEscapeSE(LuciferSoundUnit escapeSE) {
		this.escapeSE = escapeSE;
	}

	/**
	 * Returns the enemyAttackSE
	 * 
	 * @return the enemyAttackSE
	 */
	public LuciferSoundUnit getEnemyAttackSE() {
		return enemyAttackSE;
	}

	/**
	 * Sets the enemyAttackSE
	 * 
	 * @param enemyAttackSE the new enemyAttackSE
	 */
	public void setEnemyAttackSE(LuciferSoundUnit enemyAttackSE) {
		this.enemyAttackSE = enemyAttackSE;
	}

	/**
	 * Returns the enemysDamageSE
	 * 
	 * @return the enemysDamageSE
	 */
	public LuciferSoundUnit getEnemysDamageSE() {
		return enemysDamageSE;
	}

	/**
	 * Sets the enemysDamageSE
	 * 
	 * @param enemysDamageSE the new enemysDamageSE
	 */
	public void setEnemysDamageSE(LuciferSoundUnit enemysDamageSE) {
		this.enemysDamageSE = enemysDamageSE;
	}

	/**
	 * Returns the friendsDamageSE
	 * 
	 * @return the friendsDamageSE
	 */
	public LuciferSoundUnit getFriendsDamageSE() {
		return friendsDamageSE;
	}

	/**
	 * Sets the friendsDamageSE
	 * 
	 * @param friendsDamageSE the new friendsDamageSE
	 */
	public void setFriendsDamageSE(LuciferSoundUnit friendsDamageSE) {
		this.friendsDamageSE = friendsDamageSE;
	}

	/**
	 * Returns the evasionSE
	 * 
	 * @return the evasionSE
	 */
	public LuciferSoundUnit getEvasionSE() {
		return evasionSE;
	}

	/**
	 * Sets the evasionSE
	 * 
	 * @param evasionSE the new evasionSE
	 */
	public void setEvasionSE(LuciferSoundUnit evasionSE) {
		this.evasionSE = evasionSE;
	}

	/**
	 * Returns the enemyDieSE
	 * 
	 * @return the enemyDieSE
	 */
	public LuciferSoundUnit getEnemyDieSE() {
		return enemyDieSE;
	}

	/**
	 * Sets the enemyDieSE
	 * 
	 * @param enemyDieSE the new enemyDieSE
	 */
	public void setEnemyDieSE(LuciferSoundUnit enemyDieSE) {
		this.enemyDieSE = enemyDieSE;
	}

	/**
	 * Returns the itemUseSE
	 * 
	 * @return the itemUseSE
	 */
	public LuciferSoundUnit getItemUseSE() {
		return itemUseSE;
	}

	/**
	 * Sets the itemUseSE
	 * 
	 * @param itemUseSE the new itemUseSE
	 */
	public void setItemUseSE(LuciferSoundUnit itemUseSE) {
		this.itemUseSE = itemUseSE;
	}

	/**
	 * Returns the heroes
	 * 
	 * @return the heroes
	 */
	public ArrayList<Long> getHeroes() {
		return heroes;
	}

	/**
	 * Sets the heroes
	 * 
	 * @param heroes the new heroes
	 */
	public void setHeroes(ArrayList<Long> heroes) {
		this.heroes = heroes;
	}

}
