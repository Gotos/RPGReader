package engine;

import java.io.IOException;

public class LuciferMonsterUnit {
	
	public String name				= "";
	public String graphic			= "";
	public long changeFellowColour	= 0;
	public long maxHP				= 10;
	public long maxMP				= 10;
	public long attack				= 10;
	public long defence			= 10;
	public long mind				= 10;
	public long agility			= 10;
	public long experience			= 0;
	public long money				= 0;
	public long item				= 0;
	public long itemChance			= 0;
	public long criticalHitChance	= 30;
	private long nrConditions		= 0;
	private long nrAttributes		= 0;
	private long[] conditions;
	private long[] attributes;
	public boolean transparent		= false;
	public boolean criticalHit		= false;
	public boolean usuallyMiss		= false;
	public boolean air				= false;
	public LuciferMonsterAction[] actions;

	public LuciferMonsterUnit(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferMonsterUnit(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		DataReader tmp;
		while (unit.id != 0) {
			switch (unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				graphic = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x03:
				changeFellowColour = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x04:
				maxHP = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x05:
				maxMP = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x06:
				attack = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x07:
				defence = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x08:
				mind = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x09:
				agility = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0A:
				transparent = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0B:
				experience = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				money = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				item = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0E:
				itemChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x15:
				criticalHit = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x16:
				criticalHitChance = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x1A:
				usuallyMiss = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1C:
				air = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x1F:
				nrConditions = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x20:
				tmp = new DataReader(unit.content);
				conditions = new long[(int) nrConditions + 1];
				for (int i = 1; i <= nrConditions; i++) {
					conditions[i] = tmp.nextInt();
				}
				break;
			case 0x21:
				nrAttributes = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x22:
				tmp = new DataReader(unit.content);
				attributes = new long[(int) nrAttributes + 1];
				for (int i = 1; i <= nrAttributes; i++) {
					attributes[i] = tmp.nextInt();
				}
				break;
			case 0x2A:
				tmp = new DataReader(unit.content);
				actions = new LuciferMonsterAction[(int) tmp.nextInt() + 1];
				for (int i = 1; i < actions.length; i++) {
					tmp.nextInt(); //read id
					actions[i] = new LuciferMonsterAction(tmp);
				}
				break;
			}
			unit = sr.nextUnit();
		}
	}
}