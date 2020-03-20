package src.Practicum9A;

public class Utils {

	private static int count;

	public static String euroBedrag(double bedrag) {
		return Utils.euroBedrag(bedrag, 2);
	}

	public static String euroBedrag(double bedrag, int precisie) {
		++Utils.count;
		return String.format("€%." + precisie + "f", bedrag);
	}
}