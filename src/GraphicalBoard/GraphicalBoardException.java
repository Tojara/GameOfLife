package GraphicalBoard;

import java.util.ArrayList;
import java.util.List;

import Tools.Tuple;

public class GraphicalBoardException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final int __listSize = 16;
	private static final List<Tuple<Integer, Integer>> __resolutionList = new ArrayList<Tuple<Integer, Integer>>(__listSize);

	public GraphicalBoardException(final String parMessage) {
		System.err.println(parMessage);
	}

	public static final boolean isValidResolutions(final int parWidth, final int parHeight) {
		final Tuple<Integer, Integer> locTupleToCompare = new Tuple<Integer, Integer>(parWidth, parHeight);

		if (__resolutionList.isEmpty()) {
			__resolutionList.add(new Tuple<Integer, Integer>(640, 480));
			__resolutionList.add(new Tuple<Integer, Integer>(800, 600));
			__resolutionList.add(new Tuple<Integer, Integer>(1152, 864));
			__resolutionList.add(new Tuple<Integer, Integer>(1280, 600));
			__resolutionList.add(new Tuple<Integer, Integer>(1280, 720));
			__resolutionList.add(new Tuple<Integer, Integer>(1280, 768));
			__resolutionList.add(new Tuple<Integer, Integer>(1280, 960));
			__resolutionList.add(new Tuple<Integer, Integer>(1280, 1024));
			__resolutionList.add(new Tuple<Integer, Integer>(1400, 1050));
			__resolutionList.add(new Tuple<Integer, Integer>(1600, 900));
			__resolutionList.add(new Tuple<Integer, Integer>(1600, 1200));
			__resolutionList.add(new Tuple<Integer, Integer>(1856, 1392));
			__resolutionList.add(new Tuple<Integer, Integer>(1920, 1080));
			__resolutionList.add(new Tuple<Integer, Integer>(1920, 1200));
			__resolutionList.add(new Tuple<Integer, Integer>(1920, 1440));
			__resolutionList.add(new Tuple<Integer, Integer>(2048, 1536));
		}

		return __resolutionList.contains(locTupleToCompare);
	}

	public static final GraphicalBoardException resolutionException(final String parMessage, final int parWindowWidth, final int parWindowHeight) {
		final StringBuilder locStrBld = new StringBuilder(parMessage);

		locStrBld.append("\n");
		if (!isValidResolutions(parWindowWidth, parWindowHeight)) {
			locStrBld.append("La largeur est actuellement de : " + parWindowWidth + ".\n");
			locStrBld.append("La hauteur est actuellement de : " + parWindowHeight + ".\n");
		}

		locStrBld.append("Voici la liste des résolutions disponibles :\n");
		for (final Tuple<Integer, Integer> locCurrentTuple : __resolutionList) {
			locStrBld.append("    =>" + locCurrentTuple.toString() + ".\n");
		}
		
		return new GraphicalBoardException(locStrBld.toString());
	}
	
	public static final boolean isValidCellSize(final int parCellWidth, final int parCellHeight) {
		return (parCellWidth > 0 || parCellHeight > 0);
	}
	
	public static final GraphicalBoardException cellSizeExceptions(final String parMessage, final int parCellWidth, final int parCellHeight) {
		final StringBuilder locStrBld = new StringBuilder(parMessage);

		locStrBld.append("\n");
		if (!isValidCellSize(parCellWidth, parCellHeight)) {
			locStrBld.append("La largeur de la cellule est actuellement de : " + parCellWidth + ".\n");
			locStrBld.append("La hauteur de la cellule est actuellement de : " + parCellHeight + ".\n");
		}
		
		return new GraphicalBoardException(locStrBld.toString());
	}
}
