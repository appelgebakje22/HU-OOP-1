package src.Practicum9A;

public class Utils {

	public static String euroBedrag(double bedrag) {
		return Utils.euroBedrag(bedrag, 2);
	}

	public static String euroBedrag(double bedrag, int precisie) {
		return String.format("€%." + precisie + "f", bedrag);
	}
}