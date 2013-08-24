package Tools;

public class Tuple<X, Y> {
	final X __x;
	final Y __y;
	
	public Tuple (final X parX, final Y parY) {
		__x = parX;
		__y = parY;
	}
	
	public final X getX() {
		return __x;
	}
	
	public final Y getY() {
		return __y;
	}
	
	@Override
	public String toString() {
		return "(" + __x + ", " + __y + ")";
	}
	
	@Override
	public boolean equals(final Object parTupleToCompare) {
		if (parTupleToCompare == null) {
			return false;
		} else if (parTupleToCompare == this) {
			return false;
		} else if (!(parTupleToCompare instanceof Tuple)) {
			return false;
		}
		
		@SuppressWarnings("unchecked")
		final Tuple<X, Y> locTupleToCompare = (Tuple<X, Y>) parTupleToCompare;
		
		return (locTupleToCompare.getX().equals(__x) && locTupleToCompare.getY().equals(__y));
	}
	
	@Override
	public int hashCode() {
		assert (false);
		return 0;
		
	}
}
