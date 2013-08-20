
public class Main {

	public static void main(String[] args) {
		final Board locBoard = new Board(10, 10);
		
		locBoard.initBoard();
		
		for (int locIdx = 0; locIdx < 10; ++locIdx) {
			locBoard.drawBoard();
			locBoard.nextTurn();
		}
		locBoard.drawBoard();
	}
}
