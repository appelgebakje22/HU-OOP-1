package src.Practicum11;

public class Utils {

	public static String transformLine(String input, double exchange) {
		String[] parts = input.split(" : ");
		return parts[0] + " : " + dollarToEuro(parts[1], exchange);
	}

	private static String dollarToEuro(String dollarString, double exchange) {
		try {
			double dollar = Double.parseDouble(dollarString);
			double euro = dollar * 100.0 / exchange;
			return String.format("%.2f", euro);
		} catch (NumberFormatException e) {
			return "<<ERROR>>";
		}
	}
}
