package src;

import java.util.ArrayList;

public class Practicum7 {

	public static void main(String[] args) {
		StringProcessor processor = new StringProcessor();
		processor.voegProcesToe(new VervangProces("hij", "hij/zij"));
		processor.voegProcesToe(new HoofdletterProces());
		String inputString = "Een student loopt meestal in het derde jaar stage. Dan moet hij zelf een stageplek vinden.";
		String result = processor.verwerk(inputString);
		System.out.println(result);
		//Eigen proces
		processor.voegProcesToe(new ReverseProces());
		String result2 = processor.verwerk(inputString);
		System.out.println(result2);
	}

	private interface OpmaakProces {

		String maakOp(String input);
	}

	private static class StringProcessor {

		private final ArrayList<OpmaakProces> processen = new ArrayList<>();

		public void voegProcesToe(OpmaakProces proces) {
			this.processen.add(proces);
		}

		public String verwerk(String input) {
			for (OpmaakProces proces : this.processen)
				input = proces.maakOp(input);
			return input;
		}
	}

	private static class VervangProces implements OpmaakProces {

		private final String oud, nieuw;

		public VervangProces(String oud, String nieuw) {
			this.oud = oud;
			this.nieuw = nieuw;
		}

		@Override
		public String maakOp(String input) {
			return input.replaceAll(this.oud, this.nieuw);
		}
	}

	private static class HoofdletterProces implements OpmaakProces {

		@Override
		public String maakOp(String input) {
			return input.toUpperCase();
		}
	}

	private static class ReverseProces implements OpmaakProces {

		@Override
		public String maakOp(String input) {
			StringBuilder builder = new StringBuilder();
			for (int i = input.length() - 1; i >= 0; --i)
				builder.append(input.charAt(i));
			return builder.toString();
		}
	}
}