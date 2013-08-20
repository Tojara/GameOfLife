import Math.MathFunctions;

public class Board {
	private final int __width;
	private final int __height;
	private final int[][] __board;
	
	public Board() {
		this.__width = 10;
		this.__height = 10;
		this.__board = new int[this.__width][this.__height];
	}
	
	public Board(final int parWidth, final int parHeight) {
		this.__width = parWidth;
		this.__height = parHeight;
		this.__board = new int[this.__width][this.__height];
	}
	
	public final int getWidth() {
		return this.__width;
	}
	
	public final int getHeight() {
		return this.__height;
	}
	
	public final int[][] getBoard() {
		return this.__board;
	}
	
	final public void initBoard() {
		if (this.__width <= 0 || this.__height <= 0) {
			System.err.print("Error, width and height must > 0");
		} else {
			for (int locHeightIdx = 0; locHeightIdx < this.__height; ++locHeightIdx) {
				for (int locWidthIdx = 0; locWidthIdx < this.__width; ++locWidthIdx) {
					this.__board[locWidthIdx][locHeightIdx] = MathFunctions.random(0, 2);
				}
			}
		}
	}
	
	final public void nextTurn() {
		final int[][] locNewBoard = new int[__width][__height];
		
		createNewBoard(locNewBoard);
		for (int locHeightIdx = 0; locHeightIdx < this.__height; ++locHeightIdx) {
			for (int locWidthIdx = 0; locWidthIdx < this.__width; ++locWidthIdx) {
				this.__board[locWidthIdx][locHeightIdx] = locNewBoard[locWidthIdx][locHeightIdx];
			}
		}
	}
	
	final public void drawBoard() {
		for (int locHeightIdx = 0; locHeightIdx < this.__height; ++locHeightIdx) {
			for (int locWidthIdx = 0; locWidthIdx < this.__width; ++locWidthIdx) {
				this.drawCell(this.__board[locWidthIdx][locHeightIdx]);
			}
			System.out.println();
		}
	}
	
	final private void drawCell(final int parCell) {
		final char locDeadCell = ' ';
		final char locLifeCell = 'x';
		
		if (parCell == 0) {
			System.out.print(locDeadCell);
		} else if (parCell == 1) {
			System.out.print(locLifeCell);
		} else {
			assert(false);
		}
	}
	
	final private void createNewBoard(final int[][] parNewBoard) {
		for (int locHeightIdx = 0; locHeightIdx < this.__height; ++locHeightIdx) {
			for (int locWidthIdx = 0; locWidthIdx < this.__width; ++locWidthIdx) {
				parNewBoard[locWidthIdx][locHeightIdx] = (checkCell(locWidthIdx, locHeightIdx) == true) ? 1 : 0;
			}
		}
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
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		
		final boolean locIsAlreadyAlive = (__board[0][0] == 1) ? true : false;
		final int locNumNextCell = __board[0][0] + __board[0][1] + __board[1][0];
		
		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}
	
	final private boolean checkCornerRightTop() {
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		
		final boolean locIsAlreadyAlive = (__board[__width - 1][0] == 1) ? true : false;
		final int locNumNextCell = __board[__width - 2][0] + __board[__width - 2][1] + __board[__width - 1][1];
		
		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkCornerLeftDown() {
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		
		final boolean locIsAlreadyAlive = (__board[0][__height - 1] == 1) ? true : false;
		final int locNumNextCell = __board[0][__height - 2] + __board[0][__height - 2] + __board[1][__height - 1];
		
		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkCornerRightDown() {
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		
		final boolean locIsAlreadyAlive = (__board[__width - 1][__height - 1] == 1) ? true : false;
		final int locNumNextCell = __board[__width - 2][__height - 1] + __board[__width - 2][__height - 2] + __board[__width - 1][__height - 2];
		
		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}
	
	final private boolean checkTopSide(final int parX) {
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		assert (parX > 0 && parX < __width - 1);
		
		final boolean locIsAlreadyAlive = (__board[parX][0] == 1) ? true : false;
		final int locNumNextCell = __board[parX - 1][0] + __board[parX - 1][1] + __board[parX][1] + __board[parX + 1][1] + __board[parX + 1][0];
		
		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}
	
	final private boolean checkDownSide(final int parX) {
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		assert (parX > 0 && parX < __width - 1);
	
		final boolean locIsAlreadyAlive = (__board[parX][__height - 1] == 1) ? true : false;
		final int locNumNextCell = __board[parX - 1][__height - 1] + __board[parX - 1][__height - 2] + __board[parX][__height - 2] + __board[parX + 1][__height - 2] + __board[parX + 1][__height - 1];
	
		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkLeftSide(final int parY) {
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		assert (parY > 0 && parY < __height - 1);
	
		final boolean locIsAlreadyAlive = (__board[0][parY] == 1) ? true : false;
		final int locNumNextCell = __board[0][parY - 1] + __board[1][parY - 1] + __board[1][parY] + __board[1][parY + 1] + __board[0][parY + 1];
	
		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}

	final private boolean checkRightSide(final int parY) {
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		assert (parY > 0 && parY < __height - 1);
	
		final boolean locIsAlreadyAlive = (__board[__width - 1][parY] == 1) ? true : false;
		final int locNumNextCell = __board[__width - 1][parY - 1] + __board[__width - 2][parY - 1] + __board[__width - 2][parY] + __board[__width - 2][parY + 1] + __board[__width - 1][parY + 1];
	
		return checkNextCellStep(locIsAlreadyAlive, locNumNextCell);
	}
	
	/*
	 * On lance une vérification sur toutes les cellules à l'exception de celles situées sur les extrémités
	 */
	final private boolean checkOthersCell(final int parX, final int parY) {
		assert (__width > 1) : "La largeur du plateau doit être supérieur à 1";
		assert (__height > 1) : "La hauteur du plateau doit être supérieur à 1";
		assert (parX > 0 && parX < __width - 1);
		assert (parY > 0 && parY < __height - 1);
		
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