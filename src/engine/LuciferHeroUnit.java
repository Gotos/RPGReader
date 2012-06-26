package engine;

import java.io.IOException;
import java.util.ArrayList;

public class LuciferHeroUnit {
	
	public String name							= "";
	public String degree						= "";
	public String graphicFile					= "";
	public String faceGraphicFile				= "";
	public String skillName						= "";
	public long graphicIndex					= 0;
	public long minLevel						= 1;
	public long maxLevel						= 50;
	public long chanceOnCritical				= 30;
	public long faceGraphicIndex				= 0;
	public long expBase						= 30;
	public long expAdditional					= 30;
	public long expCorrection					= 30;
	public long weapon							= 0;
	public long shild							= 0;
	//public long secondWeapon					= shild;
	public long armor							= 0;
	public long helmed							= 0;
	public long other							= 0;
	public long unarmedAnimation				= 0;
	private long nrConditions					= 0;
	private long nrAttributes					= 0;
	public long[] hp							= new long[51];
	public long[] mp							= new long[51];
	public long[] attack						= new long[51];
	public long[] defense						= new long[51];
	public long[] mind							= new long[51];
	public long[] agility						= new long[51];
	public ArrayList<Long> conditions			= new ArrayList<Long>();
	public ArrayList<Long> attributes			= new ArrayList<Long>();
	public ArrayList<LuciferHeroSpell> skills	= new ArrayList<LuciferHeroSpell>();
	public boolean graphicTransparent			= false;
	public boolean allowCritical				= true;
	public boolean dualWield					= false;
	public boolean fixedEquipment				= false;
	public boolean aiControl					= false;
	public boolean strongDefense				= false;
	public boolean renameSkill					= false;
	
	public LuciferHeroUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferHeroUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		DataReader tmp;
		while (unit.id != 0) {
			switch(unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				degree = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x03:
				graphicFile = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x04:
				graphicIndex = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				graphicTransparent = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x07:
				minLevel = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x08:
				maxLevel = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x09:
				allowCritical = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0A:
				//Percentage is a mistranslation; proper format is 1 in X
				chanceOnCritical = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0F:
				faceGraphicFile = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x10:
				faceGraphicIndex = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				dualWield = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x16:
				fixedEquipment = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x17:
				aiControl = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x18:
				strongDefense = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1F:
				tmp = new DataReader(unit.content);
				for (int i = 1; i < 51; i++) {
					hp[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					mp[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					attack[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					defense[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					mind[i] = tmp.next16bitle();
				}
				for (int i = 1; i < 51; i++) {
					agility[i] = tmp.next16bitle();
				}
				break;
			case 0x29:
				expBase = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2A:
				expAdditional = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x2B:
				expCorrection = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x33:
				tmp = new DataReader(unit.content);
				weapon = tmp.next16bitle();
				shild = tmp.next16bitle();
				armor = tmp.next16bitle();
				helmed = tmp.next16bitle();
				other = tmp.next16bitle();
				break;
			case 0x38:
				unarmedAnimation = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x3F:
				tmp = new DataReader(unit.content);
				int nr = (int) tmp.nextInt();
				for (int i = 0; i < nr; i++) {
					tmp.nextInt(); //read id
					skills.add(new LuciferHeroSpell(tmp));
				}
				break;
			case 0x42:
				renameSkill = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x43:
				skillName = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x47:
				nrConditions = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x48:
				tmp = new DataReader(unit.content);
				for (int i = 1; i <= nrConditions; i++) {
					conditions.add(tmp.nextInt());
				}
				break;
			case 0x49:
				nrAttributes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x4A:
				tmp = new DataReader(unit.content);
				for (int i = 1; i <= nrAttributes; i++) {
					attributes.add(tmp.nextInt());
				}
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferHeroUnit! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
}