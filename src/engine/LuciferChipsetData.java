package engine;

import java.io.IOException;

/**
 * This class models an Chipset of the RM2k.
 * 
 * @author gRuFtY
 */
public class LuciferChipsetData {
	
	private String name					= "";
	private String graphic				= "";
	//lesereihenfolge? von links nach rechts, von oben nach unten?
	public int[] terrainIds				= new int[162]; //Sollten so stimmen; Am Index des Chips steht seine TerreinID
	public boolean[] lowerLayerDown		= new boolean[162];
	public boolean[] lowerLayerLeft		= new boolean[162];
	public boolean[] lowerLayerRight	= new boolean[162];
	public boolean[] lowerLayerUp		= new boolean[162];
	public byte[] upperLayer			= new byte[144]; //Flags?
	
	//TODO: missing terrainIds and both Layers
	
	/**
	 * Returns the name of the chipset
	 * 
	 * @return the name of the chipset
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the chipset
	 * 
	 * @param name the new name of the chipset
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the chipsets graphic
	 * 
	 * @return the name of the chipsets graphic
	 */
	public String getGraphic() {
		return graphic;
	}

	/**
	 * Sets the name of the chipsets graphic
	 * 
	 * @param graphic the new name of the chipsets graphic
	 */
	public void setGraphic(String graphic) {
		//TODO: *k�nnte* �berpr�fen, ob die Grafik existiert, muss aber nicht
		this.graphic = graphic;
	}

	/**
	 * Constructs a new LuciferChipsetData
	 * 
	 * @param bytes byte-Array which represents the LuciferChipsetData
	 * @throws IOException thrown, if something is wrong with the bytes
	 */
	public LuciferChipsetData(byte[] bytes) throws IOException {
		init(new DataReader(bytes));
	}
	
	/**
	 * Constructs a new LuciferChipsetData
	 * 
	 * @param sr StringReader which represents the LuciferChipsetData
	 * @throws IOException thrown, if something is wrong with the StringReader
	 */
	public LuciferChipsetData(DataReader sr) throws IOException {
		init(sr);
	}
	
	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnitReadID();
		while (unit.id != 0) {
			switch(unit.id) {
			case 0x01:
				name = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x02:
				graphic = new String(unit.content, Encoder.ENCODING);
				break;
			case 0x03:
				for (int i = 0; i < 162; i++) {
					terrainIds[i] = unit.content[i * 2] + (unit.content[(i * 2) + 1] << 8);
				}
				break;
			case 0x04:
				for (int i = 0; i < unit.content.length; i++) {
					lowerLayerDown[i] = (DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer % 2 == 1);
					lowerLayerLeft[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 2) % 2 == 1);
					lowerLayerRight[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 4) % 2 == 1);
					lowerLayerUp[i] = ((DataReader.rpgintToInt(new byte[] { unit.content[i] }).integer / 8) % 2 == 1);
				}
				break;
			case 0x05:
				upperLayer = unit.content;
				break;
			default:
				Helper.warn(3, "Unknown Unit-ID in LuciferChipsetData! ID: " + unit.id);
			}
			unit = sr.nextUnitReadID();
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
	     if (!(obj instanceof LuciferChipsetData)) {
	        return false; // different class
	     }
	     
	     LuciferChipsetData o = (LuciferChipsetData) obj;
	     
	     return name == o.name
	     		&& graphic == o.graphic
	     		&& lowerLayerDown == o.lowerLayerDown
	     		&& lowerLayerLeft == o.lowerLayerLeft
	     		&& lowerLayerRight == o.lowerLayerRight
	     		&& lowerLayerUp == o.lowerLayerUp
	     		&& upperLayer == o.upperLayer
	     		&& terrainIds == o.terrainIds;
	}
}
