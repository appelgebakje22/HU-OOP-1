package src.Practicum11;

public class Utils {

	public static String transformLine(String input, double exchange) {
		String[] parts = input.split(" : ");
		return parts[0] + " : " + convertExchange(parts[1], exchange);
	}

	private static String convertExchange(String dollarString, double exchange) {
		try {
			double dollar = Double.parseDouble(dollarString);
			double euro = dollar * exchange / 100.0;
			return String.format("%.2f", euro);
		} catch (NumberFormatException e) {
			return "<<ERROR>>";
		}
	}
}
