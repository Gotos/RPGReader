package com.github.gotos.rpgreader.engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
	
	public LuciferSystemData(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
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

}
