package de.grufty.rpgreader.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LuciferRoute {
	
	private long commandLength				= 0;
	public ArrayList<LuciferMoveCommand> commands	= new ArrayList<LuciferMoveCommand>();
	public boolean repeat					= true;
	public boolean ignoreImpossible		= false;
	
	public LuciferRoute(byte[] str) throws IOException {
		init(new DataReader(str));
	}
	
	public LuciferRoute(DataReader sr) throws IOException {
		init(sr);
	}
	
	public LuciferRoute() {	}

	private void init(DataReader sr) throws IOException {
		LuciferBaseUnit unit = sr.nextUnit();
		while (unit.id != 0) {
			DataReader tmp;
			switch (unit.id) {
			case 0x0B:
				commandLength = DataReader.rpgintToInt(unit.content).integer;
				break;
			case 0x0C:
				tmp = new DataReader(unit.content);
				commands = new ArrayList<LuciferMoveCommand>((int) commandLength);
				RPGInt move;
				long[] data;
				long length;
				RPGInt anotherTemp;
				RPGInt thirdTemp;
				for (int i = 0; i < commandLength; i++) {
					move = tmp.rpgintToInt();
					//System.out.println(move.integer);
					if ((move.integer == 0x20) || (move.integer == 0x21)) {
						data = new long[1];
						anotherTemp = tmp.rpgintToInt();
						data[0] = anotherTemp.integer;
						commands.add(i, new LuciferMoveCommand(move.integer, data));
						commandLength -= anotherTemp.length + move.length - 1;
					} else if ((move.integer == 0x22)) {
						data = new long[1];
						anotherTemp = tmp.rpgintToInt();
						length = anotherTemp.integer;
						byte[] filename = tmp.read(length, true);
						data[0] = tmp.nextInt();
						commands.add(i, new LuciferMoveCommand(move.integer, data, filename));
						System.out.println("22:" + (length + anotherTemp.length + move.length + 1));
						commandLength -= length + anotherTemp.length + move.length;
					} else if ((move.integer == 0x23)) {
						data = new long[3];
						anotherTemp = tmp.rpgintToInt();
						length = anotherTemp.integer;
						byte[] filename = tmp.read(length, true);
						for (int j = 0; j < 3; j++) {
							thirdTemp = tmp.rpgintToInt();
							data[j] = thirdTemp.integer;
							length += thirdTemp.length;
						}
						commands.add(i, new LuciferMoveCommand(move.integer, data, filename));
						System.out.println("23:" + (length + move.length + anotherTemp.length));
						commandLength -= length + move.length + anotherTemp.length - 1;
					} else {
						commands.add(i, new LuciferMoveCommand(move.integer));
					}
				}
				//commands = Helper.slice(commands, 0, (int) commandLength);
				/*try{
					System.out.println(commands.length);
					System.out.println("LastMove:"+commands[3].data[2]);
				} catch(Exception e){}*/
				break;
			case 0x15:
				repeat = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			case 0x16:
				ignoreImpossible = (DataReader.rpgintToInt(unit.content).integer == 1);
				break;
			}
			unit = sr.nextUnit();
		}
	}
	
	public byte[] write() {
		byte[] moves = new byte[0];
		for (int i = 0; i < commands.size(); i++) {
			moves = Helper.concatAll(moves, commands.get(i).write());
		}
		return Helper.concatAll(new LuciferBaseUnit(0x0B, DataReader.intToRPGint(moves.length)).write(new byte[]{0}),
				new LuciferBaseUnit(0x0C, moves).write(),
				new LuciferBaseUnit(0x15, DataReader.intToRPGint((repeat) ? 1 : 0)).write(new byte[]{1}),
				new LuciferBaseUnit(0x16, DataReader.intToRPGint((ignoreImpossible) ? 1 : 0)).write(new byte[]{0}),
				new byte[]{0}
				);
	}
	
	@Override
	public boolean equals(Object obj) {
	     if (this == obj) {
	        return true;
	     }
	     if (obj == null) {
	        return false;
	     }
	     if (!(obj instanceof LuciferRoute)) {
	        return false; // different class
	     }
	     
	     LuciferRoute o = (LuciferRoute) obj;
	     
	     return commandLength == o.commandLength
	     		&& ignoreImpossible == o.ignoreImpossible
	     		&& repeat == o.repeat
	     		&& commands.equals(o.commands);
	}
	
}