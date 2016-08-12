/**
 * Represents a strategy for playing the Pile game.
 *
 */
public interface PileStrategy
{
	/**
	 * Gives the strategy's name.
	 * @return the name of the strategy
	 */
	String getName();
	
	/**
	 * Given the current pile setup returns a valid move.
	 * A move is valid if it selects one of the existing 
	 * piles with positive size, and selects a number of stones 
	 * that is positive but not greater than the chosen pile's size.
	 * 
	 * Any changes made to the input argument piles will be ignored (i.e., no need to
	 * apply your move to the given array as PileGame will do that for you).
	 * 
	 * @param piles the current pile sizes 
	 * @return the move the strategy wishes to make
	 */
	Move getMove(int[] piles);
}
