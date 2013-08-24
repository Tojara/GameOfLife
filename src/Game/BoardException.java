package Game;

public class BoardException extends Exception {
	private static final long serialVersionUID = 1L;

	public BoardException(final String parMessage) {
		System.err.println(parMessage);
	}

	public static final boolean isValidBoard(final int parParameterToCheck) {
		return !(parParameterToCheck < 10 || parParameterToCheck > 100);
	}

	public static final BoardException boardException(final String parMessage, final int parWidth, final int parHeight) {
		final StringBuilder locStrBld = new StringBuilder(parMessage);

		locStrBld.append("\n");
		if (!isValidBoard(parWidth)) {
			locStrBld.append("La largeur du plateau doit être compris entre 10 et 100.\n");
			locStrBld.append("La largeur est actuellement de : " + parWidth + ".\n");
		}

		if (!isValidBoard(parHeight)) {
			locStrBld.append("La hauteur du plateau doit être compris entre 10 et 100.\n");
			locStrBld.append("La hauteur est actuellement de : " + parHeight + ".\n");
		}

		return new BoardException(locStrBld.toString());
	}
}