package engine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author gRuFtY
 *
 * This Class models an Attribute of the Database of the RM2k.
 */
public class LuciferAttributeUnit {
	
	private String name					= "";
	private boolean classificationMagic	= false;
	private long rateA					= 300;
	private long rateB					= 200;
	private long rateC					= 100;
	private long rateD					= 50;
	private long rateE					= 0;
	
	/**
	 * Returns the Name of the Attribute
	 * 
	 * @return Attribute-Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Name of the Attribute
	 * 
	 * @param name new Name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns true, if Classification is Magic attrib.
	 * 
	 * @return true, if Classification is Magic attrib., otherwise false
	 */
	public boolean isClassificationMagic() {
		return classificationMagic;
	}
	
	/**
	 * Returns true, if Classification is Weapon attrib.
	 * 
	 * @return true, if Classification is Weapon attrib., otherwise false
	 */
	public boolean isClassificationWeapon() {
		return !classificationMagic;
	}
	
	/**
	 * Sets Classification to Magic attrib.
	 */
	public void setClassificationMagic() {
		classificationMagic = true;
	}
	
	/**
	 * Sets Classification to Magic attrib. if bool is true
	 * 
	 * @param bool if true, set Classification to Magic attrib., otherwise to Weapon attrib.
	 */
	public void setClassificationMagic(boolean bool) {
		classificationMagic = bool;
	}
	
	/**
	 * Sets Classification to Weapon attrib.
	 */
	public void setClassificationWeapon() {
		classificationMagic = false;
	}
	
	/**
	 * Sets Classification to Weapon attrib. if bool is true
	 * 
	 * @param bool if true, set Classification to Weapon attrib., otherwise to Magic attrib.
	 */
	public void setClassificationWeapon(boolean bool) {
		classificationMagic = !bool;
	}
	
	/**
	 * Returns Rate of Effect A
	 * 
	 * @return Rate of Effect A
	 */
	public long getRateA() {
		return rateA;
	}
	
	/**
	 * Sets Rate of Effect A
	 * 
	 * @param rateA new Rate of Effect A
	 */
	public void setRateA(long rateA) {
		if (rateA < 0) {
			throw new IllegalArgumentException("Rate may not be negativ!");
		}
		this.rateA = rateA;
	}
	
	/**
	 * Returns Rate of Effect B
	 * 
	 * @return Rate of Effect B
	 */
	public long getRateB() {
		return rateB;
	}
	
	/**
	 * Sets Rate of Effect B
	 * 
	 * @param rateB new Rate of Effect B
	 */
	public void setRateB(long rateB) {
		if (rateB < 0) {
			throw new IllegalArgumentException("Rate may not be negativ!");
		}
		this.rateB = rateB;
	}
	
	/**
	 * Returns Rate of Effect C
	 * 
	 * @return Rate of Effect C
	 */
	public long getRateC() {
		return rateC;
	}
	
	/**
	 * Sets Rate of Effect C
	 * 
	 * @param rateC new Rate of Effect C
	 */
	public void setRateC(long rateC) {
		if (rateC < 0) {
			throw new IllegalArgumentException("Rate may not be negativ!");
		}
		this.rateC = rateC;
	}
	
	/**
	 * Returns Rate of Effect D
	 * 
	 * @return Rate of Effect D
	 */
	public long getRateD() {
		return rateD;
	}
	
	/**
	 * Sets Rate of Effect D
	 * 
	 * @param rateD new Rate of Effect D
	 */
	public void setRateD(long rateD) {
		if (rateD < 0) {
			throw new IllegalArgumentException("Rate may not be negativ!");
		}
		this.rateD = rateD;
	}
	
	/**
	 * Returns Rate of Effect E
	 * 
	 * @return Rate of Effect E
	 */
	public long getRateE() {
		return rateE;
	}
	
	/**
	 * Sets Rate of Effect E
	 * 
	 * @param rateE new Rate of Effect E
	 */
	public void setRateE(long rateE) {
		if (rateE < 0) {
			throw new IllegalArgumentException("Rate may not be negativ!");
		}
		this.rateE = rateE;
	}
	
	/**
	 * Constructs a new LuciferAttributeUnit
	 * 
	 * @param bytes byte-Array which represents the LuciferAttributeUnit
	 * @throws IOException thrown, if something is wrong with the bytes
	 */
	public LuciferAttributeUnit(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferAttributeUnit
	 * 
	 * @param dr DataReader which represents the LuciferAttributeUnit
	 * @throws IOException thrown, if something is wrong with the DataReader
	 */
	public LuciferAttributeUnit(DataReader dr) throws IOException {
		init(dr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			switch(unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				classificationMagic = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x0B:
				rateA = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				rateB = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0D:
				rateC = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0E:
				rateD = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0F:
				rateE = DataReader.rpgintToInt(unit.content).integer;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferAttributeUnit! ID: " + unit.id);
			}
			unit = sr.nextUnit();
		}
	}
	
	/**
	 * Returns the byte-representation of this AttributeUnit
	 * 
	 * @return byte-representation
	 */
	public byte[] write() {
		try {
			return Helper.concatAll(new LuciferBaseUnit(0x01, name.getBytes(Encoder.ENCODING)).write(new byte[0]),
					new LuciferBaseUnit(0x02, DataReader.intToRPGint(classificationMagic ? 1 : 0)).write(new byte[]{0}),
					new LuciferBaseUnit(0x0B, DataReader.intToRPGint(rateA)).write(DataReader.intToRPGint(300)),
					new LuciferBaseUnit(0x0C, DataReader.intToRPGint(rateB)).write(DataReader.intToRPGint(200)),
					new LuciferBaseUnit(0x0D, DataReader.intToRPGint(rateC)).write(DataReader.intToRPGint(100)),
					new LuciferBaseUnit(0x0E, DataReader.intToRPGint(rateD)).write(DataReader.intToRPGint(50)),
					new LuciferBaseUnit(0x0F, DataReader.intToRPGint(rateE)).write(new byte[]{0}),
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
				* result + (classificationMagic ? 1231
						: 1237);
		result = prime
				* result + ((name == null) ? 0
						: name.hashCode());
		result = prime
				* result + (int) (rateA ^ (rateA >>> 32));
		result = prime
				* result + (int) (rateB ^ (rateB >>> 32));
		result = prime
				* result + (int) (rateC ^ (rateC >>> 32));
		result = prime
				* result + (int) (rateD ^ (rateD >>> 32));
		result = prime
				* result + (int) (rateE ^ (rateE >>> 32));
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
		if (!(obj instanceof LuciferAttributeUnit)) {
			return false;
		}
		LuciferAttributeUnit other = (LuciferAttributeUnit) obj;
		if (classificationMagic != other.classificationMagic) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (rateA != other.rateA) {
			return false;
		}
		if (rateB != other.rateB) {
			return false;
		}
		if (rateC != other.rateC) {
			return false;
		}
		if (rateD != other.rateD) {
			return false;
		}
		if (rateE != other.rateE) {
			return false;
		}
		return true;
	}
}
