package ecgen;

import com.github.gotos.rpgreader.engine.LuciferEventCommand;


/**
 * @author gRuFtY
 *
 * This class models a "Change Variable" Event Command
 */
public final class ChangeVariable {
	private ChangeVariable() { }
	
	/**
	 * Creates a new "Change Variable" Command, that adds constant to Variable[varID].
	 * 
	 * @param treeDepth depth of the command
	 * @param varID ID of the changing variable
	 * @param constant constant, that is added to Variable[varID]
	 * @return Change Variable[varID] += constant
	 */
	LuciferEventCommand addConstantToSingleVariable(long treeDepth, long varID, long constant) {
		return new LuciferEventCommand(LuciferEventCommand.CHANGE_VARIABLE, treeDepth, "", new long[] {0, varID, varID, 1, 0, constant, 0});
	}
	
	/**
	 * Creates a new "Change Variable" Command, that sets Variable[varID] to constant.
	 * 
	 * @param treeDepth depth of the command
	 * @param varID ID of the changing variable
	 * @param constant constant, that Variable[varID] is set to
	 * @return Change Variable[varID] = constant
	 */
	LuciferEventCommand setSingleVariableToConstant(long treeDepth, long varID, long constant) {
		return new LuciferEventCommand(LuciferEventCommand.CHANGE_VARIABLE, treeDepth, "", new long[] {0, varID, varID, 0, 0, constant, 0});
	}
}
