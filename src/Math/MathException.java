package Math;

public class MathException extends Exception {
	private static final long serialVersionUID = 1L;

	public MathException(final String parMessage) {
		System.err.println(parMessage + "\n");
	}
	
	public static final MathException divideByZero(final String parMessage) {
		return new MathException(parMessage);
	}
}
