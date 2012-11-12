package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.util.ArrayList;

public class LuciferSystemData {
	
	private String ship						= "";
	private String boat						= "";
	private String airship					= "";
	private String titleGraphic				= "";
	private String goGraphic				= "";
	private String system					= "";
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
			case 0x0C:
				boat = new String(unit.content, Encoder.ENCODING);
			case 0x0D:
				airship = new String(unit.content, Encoder.ENCODING);
			case 0x0E:
				shipIndex = (short) DataReader.rpgintToInt(unit.content).integer;
			case 0x0F:
				boatIndex = (short) DataReader.rpgintToInt(unit.content).integer;
			case 0x10:
				airshipIndex = (short) DataReader.rpgintToInt(unit.content).integer;
			case 0x11:
				titleGraphic = new String(unit.content, Encoder.ENCODING);
			case 0x12:
				goGraphic = new String(unit.content, Encoder.ENCODING);
			case 0x13:
				system = new String(unit.content, Encoder.ENCODING);
			case 0x15:
				nrHeroes = (short) DataReader.rpgintToInt(unit.content).integer;
			case 0x16:
				heroes = new ArrayList<Long>(nrHeroes);
				DataReader subDr = new DataReader(unit.content);
				for (int i = 0; i < nrHeroes; i++) {
					heroes.add(subDr.nextInt());
				}
				case 0x1F:
					titleMusic = new LuciferMusicUnit(unit.content);
				case 0x20:
					battleMusic = new LuciferMusicUnit(unit.content);
				case 0x21:
					battleEndMusic = new LuciferMusicUnit(unit.content);
				case 0x22:
					innMusic = new LuciferMusicUnit(unit.content);
				case 0x23:
					boatMusic = new LuciferMusicUnit(unit.content);
				case 0x24:
					shipMusic = new LuciferMusicUnit(unit.content);
				case 0x25:
					airshipMusic = new LuciferMusicUnit(unit.content);
				case 0x26:
					gameOverMusic = new LuciferMusicUnit(unit.content);
				case 0x29:
					cursorSE = new LuciferSoundUnit(unit.content);
				case 0x2A:
					decisionSE = new LuciferSoundUnit(unit.content);
				case 0x2B:
					cancelSE = new LuciferSoundUnit(unit.content);
				case 0x2C:
					buzzerSE = new LuciferSoundUnit(unit.content);
				case 0x2D:
					startBattleSE = new LuciferSoundUnit(unit.content);
				case 0x2E:
					escapeSE = new LuciferSoundUnit(unit.content);
				case 0x2F:
					enemyAttackSE = new LuciferSoundUnit(unit.content);
				case 0x30:
					enemysDamageSE = new LuciferSoundUnit(unit.content);
				case 0x31:
					friendsDamageSE = new LuciferSoundUnit(unit.content);
				case 0x32:
					evasionSE = new LuciferSoundUnit(unit.content);
				case 0x33:
					enemyDieSE = new LuciferSoundUnit(unit.content);
				case 0x34:
					itemUseSE = new LuciferSoundUnit(unit.content);
				case 0x3D:
					teleportErase = (short) DataReader.rpgintToInt(unit.content).integer;
				case 0x3E:
					teleportShow = (short) DataReader.rpgintToInt(unit.content).integer;
				case 0x3F:
					battleErase = (short) DataReader.rpgintToInt(unit.content).integer;
				case 0x40:
					battleShow = (short) DataReader.rpgintToInt(unit.content).integer;
				case 0x41:
					battleEndErase = (short) DataReader.rpgintToInt(unit.content).integer;
				case 0x42:
					battleEndShow = (short) DataReader.rpgintToInt(unit.content).integer;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferSystemData! ID: " + unit.id);
			}
		}
	}

}
