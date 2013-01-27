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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LuciferSystemData other = (LuciferSystemData) obj;
		if (airship == null) {
			if (other.airship != null)
				return false;
		} else if (!airship.equals(other.airship))
			return false;
		if (airshipIndex != other.airshipIndex)
			return false;
		if (airshipMusic == null) {
			if (other.airshipMusic != null)
				return false;
		} else if (!airshipMusic.equals(other.airshipMusic))
			return false;
		if (bATestVictim != other.bATestVictim)
			return false;
		if (bAUseGrid != other.bAUseGrid)
			return false;
		if (battleEndErase != other.battleEndErase)
			return false;
		if (battleEndMusic == null) {
			if (other.battleEndMusic != null)
				return false;
		} else if (!battleEndMusic.equals(other.battleEndMusic))
			return false;
		if (battleEndShow != other.battleEndShow)
			return false;
		if (battleErase != other.battleErase)
			return false;
		if (battleMusic == null) {
			if (other.battleMusic != null)
				return false;
		} else if (!battleMusic.equals(other.battleMusic))
			return false;
		if (battleShow != other.battleShow)
			return false;
		if (battleTestBG == null) {
			if (other.battleTestBG != null)
				return false;
		} else if (!battleTestBG.equals(other.battleTestBG))
			return false;
		if (boat == null) {
			if (other.boat != null)
				return false;
		} else if (!boat.equals(other.boat))
			return false;
		if (boatIndex != other.boatIndex)
			return false;
		if (boatMusic == null) {
			if (other.boatMusic != null)
				return false;
		} else if (!boatMusic.equals(other.boatMusic))
			return false;
		if (buzzerSE == null) {
			if (other.buzzerSE != null)
				return false;
		} else if (!buzzerSE.equals(other.buzzerSE))
			return false;
		if (cancelSE == null) {
			if (other.cancelSE != null)
				return false;
		} else if (!cancelSE.equals(other.cancelSE))
			return false;
		if (cursorSE == null) {
			if (other.cursorSE != null)
				return false;
		} else if (!cursorSE.equals(other.cursorSE))
			return false;
		if (decisionSE == null) {
			if (other.decisionSE != null)
				return false;
		} else if (!decisionSE.equals(other.decisionSE))
			return false;
		if (enemyAttackSE == null) {
			if (other.enemyAttackSE != null)
				return false;
		} else if (!enemyAttackSE.equals(other.enemyAttackSE))
			return false;
		if (enemyDieSE == null) {
			if (other.enemyDieSE != null)
				return false;
		} else if (!enemyDieSE.equals(other.enemyDieSE))
			return false;
		if (enemysDamageSE == null) {
			if (other.enemysDamageSE != null)
				return false;
		} else if (!enemysDamageSE.equals(other.enemysDamageSE))
			return false;
		if (escapeSE == null) {
			if (other.escapeSE != null)
				return false;
		} else if (!escapeSE.equals(other.escapeSE))
			return false;
		if (evasionSE == null) {
			if (other.evasionSE != null)
				return false;
		} else if (!evasionSE.equals(other.evasionSE))
			return false;
		if (friendsDamageSE == null) {
			if (other.friendsDamageSE != null)
				return false;
		} else if (!friendsDamageSE.equals(other.friendsDamageSE))
			return false;
		if (gameOverMusic == null) {
			if (other.gameOverMusic != null)
				return false;
		} else if (!gameOverMusic.equals(other.gameOverMusic))
			return false;
		if (goGraphic == null) {
			if (other.goGraphic != null)
				return false;
		} else if (!goGraphic.equals(other.goGraphic))
			return false;
		if (heroes == null) {
			if (other.heroes != null)
				return false;
		} else if (!heroes.equals(other.heroes))
			return false;
		if (innMusic == null) {
			if (other.innMusic != null)
				return false;
		} else if (!innMusic.equals(other.innMusic))
			return false;
		if (itemUseSE == null) {
			if (other.itemUseSE != null)
				return false;
		} else if (!itemUseSE.equals(other.itemUseSE))
			return false;
		if (nrHeroes != other.nrHeroes)
			return false;
		if (numberOfSaves != other.numberOfSaves)
			return false;
		if (ship == null) {
			if (other.ship != null)
				return false;
		} else if (!ship.equals(other.ship))
			return false;
		if (shipIndex != other.shipIndex)
			return false;
		if (shipMusic == null) {
			if (other.shipMusic != null)
				return false;
		} else if (!shipMusic.equals(other.shipMusic))
			return false;
		if (startBattleSE == null) {
			if (other.startBattleSE != null)
				return false;
		} else if (!startBattleSE.equals(other.startBattleSE))
			return false;
		if (system == null) {
			if (other.system != null)
				return false;
		} else if (!system.equals(other.system))
			return false;
		if (teleportErase != other.teleportErase)
			return false;
		if (teleportShow != other.teleportShow)
			return false;
		if (titleGraphic == null) {
			if (other.titleGraphic != null)
				return false;
		} else if (!titleGraphic.equals(other.titleGraphic))
			return false;
		if (titleMusic == null) {
			if (other.titleMusic != null)
				return false;
		} else if (!titleMusic.equals(other.titleMusic))
			return false;
		return true;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getBoat() {
		return boat;
	}

	public void setBoat(String boat) {
		this.boat = boat;
	}

	public String getAirship() {
		return airship;
	}

	public void setAirship(String airship) {
		this.airship = airship;
	}

	public String getTitleGraphic() {
		return titleGraphic;
	}

	public void setTitleGraphic(String titleGraphic) {
		this.titleGraphic = titleGraphic;
	}

	public String getGoGraphic() {
		return goGraphic;
	}

	public void setGoGraphic(String goGraphic) {
		this.goGraphic = goGraphic;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getBattleTestBG() {
		return battleTestBG;
	}

	public void setBattleTestBG(String battleTestBG) {
		this.battleTestBG = battleTestBG;
	}

	public short getShipIndex() {
		return shipIndex;
	}

	public void setShipIndex(short shipIndex) {
		this.shipIndex = shipIndex;
	}

	public short getBoatIndex() {
		return boatIndex;
	}

	public void setBoatIndex(short boatIndex) {
		this.boatIndex = boatIndex;
	}

	public short getAirshipIndex() {
		return airshipIndex;
	}

	public void setAirshipIndex(short airshipIndex) {
		this.airshipIndex = airshipIndex;
	}

	public short getNrHeroes() {
		return nrHeroes;
	}

	public void setNrHeroes(short nrHeroes) {
		this.nrHeroes = nrHeroes;
	}

	public short getTeleportErase() {
		return teleportErase;
	}

	public void setTeleportErase(short teleportErase) {
		this.teleportErase = teleportErase;
	}

	public short getTeleportShow() {
		return teleportShow;
	}

	public void setTeleportShow(short teleportShow) {
		this.teleportShow = teleportShow;
	}

	public short getBattleErase() {
		return battleErase;
	}

	public void setBattleErase(short battleErase) {
		this.battleErase = battleErase;
	}

	public short getBattleShow() {
		return battleShow;
	}

	public void setBattleShow(short battleShow) {
		this.battleShow = battleShow;
	}

	public short getBattleEndErase() {
		return battleEndErase;
	}

	public void setBattleEndErase(short battleEndErase) {
		this.battleEndErase = battleEndErase;
	}

	public short getBattleEndShow() {
		return battleEndShow;
	}

	public void setBattleEndShow(short battleEndShow) {
		this.battleEndShow = battleEndShow;
	}

	public long getNumberOfSaves() {
		return numberOfSaves;
	}

	public void setNumberOfSaves(long numberOfSaves) {
		this.numberOfSaves = numberOfSaves;
	}

	public long getbATestVictim() {
		return bATestVictim;
	}

	public void setbATestVictim(long bATestVictim) {
		this.bATestVictim = bATestVictim;
	}

	public boolean isbAUseGrid() {
		return bAUseGrid;
	}

	public void setbAUseGrid(boolean bAUseGrid) {
		this.bAUseGrid = bAUseGrid;
	}

	public LuciferMusicUnit getTitleMusic() {
		return titleMusic;
	}

	public void setTitleMusic(LuciferMusicUnit titleMusic) {
		this.titleMusic = titleMusic;
	}

	public LuciferMusicUnit getBattleMusic() {
		return battleMusic;
	}

	public void setBattleMusic(LuciferMusicUnit battleMusic) {
		this.battleMusic = battleMusic;
	}

	public LuciferMusicUnit getBattleEndMusic() {
		return battleEndMusic;
	}

	public void setBattleEndMusic(LuciferMusicUnit battleEndMusic) {
		this.battleEndMusic = battleEndMusic;
	}

	public LuciferMusicUnit getInnMusic() {
		return innMusic;
	}

	public void setInnMusic(LuciferMusicUnit innMusic) {
		this.innMusic = innMusic;
	}

	public LuciferMusicUnit getBoatMusic() {
		return boatMusic;
	}

	public void setBoatMusic(LuciferMusicUnit boatMusic) {
		this.boatMusic = boatMusic;
	}

	public LuciferMusicUnit getShipMusic() {
		return shipMusic;
	}

	public void setShipMusic(LuciferMusicUnit shipMusic) {
		this.shipMusic = shipMusic;
	}

	public LuciferMusicUnit getAirshipMusic() {
		return airshipMusic;
	}

	public void setAirshipMusic(LuciferMusicUnit airshipMusic) {
		this.airshipMusic = airshipMusic;
	}

	public LuciferMusicUnit getGameOverMusic() {
		return gameOverMusic;
	}

	public void setGameOverMusic(LuciferMusicUnit gameOverMusic) {
		this.gameOverMusic = gameOverMusic;
	}

	public LuciferSoundUnit getCursorSE() {
		return cursorSE;
	}

	public void setCursorSE(LuciferSoundUnit cursorSE) {
		this.cursorSE = cursorSE;
	}

	public LuciferSoundUnit getDecisionSE() {
		return decisionSE;
	}

	public void setDecisionSE(LuciferSoundUnit decisionSE) {
		this.decisionSE = decisionSE;
	}

	public LuciferSoundUnit getCancelSE() {
		return cancelSE;
	}

	public void setCancelSE(LuciferSoundUnit cancelSE) {
		this.cancelSE = cancelSE;
	}

	public LuciferSoundUnit getBuzzerSE() {
		return buzzerSE;
	}

	public void setBuzzerSE(LuciferSoundUnit buzzerSE) {
		this.buzzerSE = buzzerSE;
	}

	public LuciferSoundUnit getStartBattleSE() {
		return startBattleSE;
	}

	public void setStartBattleSE(LuciferSoundUnit startBattleSE) {
		this.startBattleSE = startBattleSE;
	}

	public LuciferSoundUnit getEscapeSE() {
		return escapeSE;
	}

	public void setEscapeSE(LuciferSoundUnit escapeSE) {
		this.escapeSE = escapeSE;
	}

	public LuciferSoundUnit getEnemyAttackSE() {
		return enemyAttackSE;
	}

	public void setEnemyAttackSE(LuciferSoundUnit enemyAttackSE) {
		this.enemyAttackSE = enemyAttackSE;
	}

	public LuciferSoundUnit getEnemysDamageSE() {
		return enemysDamageSE;
	}

	public void setEnemysDamageSE(LuciferSoundUnit enemysDamageSE) {
		this.enemysDamageSE = enemysDamageSE;
	}

	public LuciferSoundUnit getFriendsDamageSE() {
		return friendsDamageSE;
	}

	public void setFriendsDamageSE(LuciferSoundUnit friendsDamageSE) {
		this.friendsDamageSE = friendsDamageSE;
	}

	public LuciferSoundUnit getEvasionSE() {
		return evasionSE;
	}

	public void setEvasionSE(LuciferSoundUnit evasionSE) {
		this.evasionSE = evasionSE;
	}

	public LuciferSoundUnit getEnemyDieSE() {
		return enemyDieSE;
	}

	public void setEnemyDieSE(LuciferSoundUnit enemyDieSE) {
		this.enemyDieSE = enemyDieSE;
	}

	public LuciferSoundUnit getItemUseSE() {
		return itemUseSE;
	}

	public void setItemUseSE(LuciferSoundUnit itemUseSE) {
		this.itemUseSE = itemUseSE;
	}

	public ArrayList<Long> getHeroes() {
		return heroes;
	}

	public void setHeroes(ArrayList<Long> heroes) {
		this.heroes = heroes;
	}

}
