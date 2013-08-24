package Game;
import Math.MathFunctions;

public class Board {
	private final int __width;
	private final int __height;
	private final int[][] __board;
	private boolean __finalState;

	public Board(final int parWidth, final int parHeight) {
		__width = parWidth;
		__height = parHeight;
		__board = new int[__width][__height];
		__finalState = false;
	}

	public final int getWidth() {
		return __width;
	}

	public final int getHeight() {
		return __height;
	}

	public final int[][] getBoard() {
		return __board;
	}

	public final boolean isGameFinished() {
		return __finalState;
	}

	final public void initBoard() throws BoardException {
		if (!BoardException.isValidBoard(__width) || !BoardException.isValidBoard(__height)) {
			throw BoardException.boardException("La taille du plateau n'est pas valide.", __width, __height);
		} else {
			for (int locHeightIdx = 0; locHeightIdx < __height; ++locHeightIdx) {
				for (int locWidthIdx = 0; locWidthIdx < __width; ++locWidthIdx) {
					__board[locWidthIdx][locHeightIdx] = MathFunctions.random(0, 2);
				}
			}
		}
	}

	final public void nextTurn() throws BoardException {
		if (!BoardException.isValidBoard(__width) || !BoardException.isValidBoard(__height)) {
			throw BoardException.boardException("La taille du plateau n'est pas valide.", __width, __height);
		}

		final int[][] locNewBoard = new int[__width][__height];

		createNewBoard(locNewBoard);
		checkEndGame(locNewBoard);
		for (int locHeightIdx = 0; locHeightIdx < __height; ++locHeightIdx) {
			for (int locWidthIdx = 0; locWidthIdx < __width; ++locWidthIdx) {
				__board[locWidthIdx][locHeightIdx] = locNewBoard[locWidthIdx][locHeightIdx];
			}
		}
	}

	final private void createNewBoard(final int[][] parNewBoard) throws BoardException {
		if (!BoardException.isValidBoard(__width) || !BoardException.isValidBoard(__height)) {
			throw BoardException.boardException("La taille du plateau n'est pas valide.", __width, __height);
		}

		for (int locHeightIdx = 0; locHeightIdx < __height; ++locHeightIdx) {
			for (int locWidthIdx = 0; locWidthIdx < __width; ++locWidthIdx) {
				parNewBoard[locWidthIdx][locHeightIdx] = (checkCell(locWidthIdx, locHeightIdx) == true) ? 1 : 0;
			}
		}
	}
	
	final private void checkEndGame(final int[][] parNewBoard) {
		for (int locIdxHeight = 0; locIdxHeight < __height; ++locIdxHeight) {
			for (int locIdxWidth = 0; locIdxWidth < __width; ++locIdxWidth) {
				if (__board[locIdxWidth][locIdxHeight] != parNewBoard[locIdxWidth][locIdxHeight]) {
					return;
				}
			}
		}
		__finalState = true;
	}

	/*
	 * Retourne True si la cellule doit être vivante sinon retourne Faux
	 */
	final private boolean checkCell(final int parX, final int parY) {
		if (parX == 0) {
			if (parY == 0) {
				return checkCornerLeftTop();
			} else if (parY == __height - 1) {
				return checkCornerLeftDown();
			} else {
				return checkLeftSide(parY);
			}
		} else if (parX == __width - 1) {
			if (parY == 0) {
				return checkCornerRightTop();
			} else if (parY == __height - 1) {
				return checkCornerRightDown();
			} else {
				return checkRightSide(parY);
			}
		} else if (parY == 0) {
			return checkTopSide(parX);
		} else if (parY == __height - 1) {
			return checkDownSide(parX);
		} else {
			return checkOthersCell(parX, parY);
		}
	}

	final private boolean checkCornerLeftTop() {
		final boolean locIsAlreadyAlive = (__board[0][0] == 1) ? true : false;
		final int locNumNextCell = __board[0][0] + __board[0][1] + __board[1][0];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkCornerRightTop() {
		final boolean locIsAlreadyAlive = (__board[__width - 1][0] == 1) ? true : false;
		final int locNumNextCell = __board[__width - 2][0] + __board[__width - 2][1] + __board[__width - 1][1];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkCornerLeftDown() {
		final boolean locIsAlreadyAlive = (__board[0][__height - 1] == 1) ? true : false;
		final int locNumNextCell = __board[0][__height - 2] + __board[0][__height - 2] + __board[1][__height - 1];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkCornerRightDown() {
		final boolean locIsAlreadyAlive = (__board[__width - 1][__height - 1] == 1) ? true : false;
		final int locNumNextCell = __board[__width - 2][__height - 1] + __board[__width - 2][__height - 2] + __board[__width - 1][__height - 2];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkTopSide(final int parX) {
		final boolean locIsAlreadyAlive = (__board[parX][0] == 1) ? true : false;
		final int locNumNextCell = __board[parX - 1][0] + __board[parX - 1][1] + __board[parX][1] + __board[parX + 1][1] + __board[parX + 1][0];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkDownSide(final int parX) {
		final boolean locIsAlreadyAlive = (__board[parX][__height - 1] == 1) ? true : false;
		final int locNumNextCell = __board[parX - 1][__height - 1] + __board[parX - 1][__height - 2] + __board[parX][__height - 2] + __board[parX + 1][__height - 2] + __board[parX + 1][__height - 1];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkLeftSide(final int parY) {
		final boolean locIsAlreadyAlive = (__board[0][parY] == 1) ? true : false;
		final int locNumNextCell = __board[0][parY - 1] + __board[1][parY - 1] + __board[1][parY] + __board[1][parY + 1] + __board[0][parY + 1];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkRightSide(final int parY) {
		final boolean locIsAlreadyAlive = (__board[__width - 1][parY] == 1) ? true : false;
		final int locNumNextCell = __board[__width - 1][parY - 1] + __board[__width - 2][parY - 1] + __board[__width - 2][parY] + __board[__width - 2][parY + 1] + __board[__width - 1][parY + 1];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	/*
	 * On lance une vérification sur toutes les cellules à l'exception de celles situées sur les extrémités
	 */
	final private boolean checkOthersCell(final int parX, final int parY) {
		final boolean locIsAlreadyAlive = (__board[parX][parY] == 1) ? true : false;
		final int locNumNextCell = __board[parX - 1][parY] + __board[parX - 1][parY - 1] + __board[parX][parY - 1] + __board[parX + 1][parY - 1] + __board[parX + 1][parY] + __board[parX + 1][parY + 1] + __board[parX][parY + 1];

		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkNextCellStep(final boolean parIsAlreadyAlive, final int parNumNextCell) {
		if (parIsAlreadyAlive) {
			return (parNumNextCell == 2 || parNumNextCell == 3);
		}
		return (parNumNextCell == 3);
	}
}