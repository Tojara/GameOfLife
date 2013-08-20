package Math;

 public class MathFunctions {
	 final public static int random(final int parLower, final int parHigher) {
		 return (int)(Math.random() * (parHigher - parLower)) + parLower;
		}
}
