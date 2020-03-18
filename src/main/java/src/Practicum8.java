package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Practicum8 {

	public static void main(String[] args) throws InterruptedException {
		BedrijfsInventaris inv = new BedrijfsInventaris("HU", 1.5E5);
		try {
			Computer c1 = new Computer("Apple Macbook Pro 17\"", "000-111-222", 8000, 2019);
			Computer c2 = new Computer("Apple Macbook Pro 17\"", "000-111-222", 8000, 2019);
			inv.schafAan(c1);
			System.out.println(inv);
			inv.schafAan(c2);
			System.out.println(inv);
		} catch (Exception e) {
			printError("Computer: " + e.getMessage());
		}
		try {
			Auto a1 = new Auto("Mercedes-Benz A-klasse A 180", 29995.00, 2019, "4-ZTV-94");
			Auto a2 = new Auto("Mercedes-Benz A-klasse A 180", 29995.00, 2019, "4-ZTV-94");
			inv.schafAan(a1);
			System.out.println(inv);
			inv.schafAan(a2);
			System.out.println(inv);
		} catch (Exception e) {
			printError("Auto: " + e.getMessage());
		}
		try {
			Fiets f1 = new Fiets("Gazelle A-klasse A 180", 295.00, 2019, 25);
			Fiets f2 = new Fiets("Gazelle A-klasse A 180", 295.00, 2019, 25);
			inv.schafAan(f1);
			System.out.println(inv);
			inv.schafAan(f2);
			System.out.println(inv);
		} catch (Exception e) {
			printError("Fiets: " + e.getMessage());
		}
		try {
			Computer c1 = new Computer("Gucci Smart Toilet", "111-222-333", Integer.MAX_VALUE, 2020);
			inv.schafAan(c1);
			System.out.println(inv);
		} catch (Exception e) {
			printError("Budget: " + e.getMessage());
		}
		System.out.println(inv);
	}

	private static void printError(Object o) {
		System.out.println("\u001b[31m" + o + "\u001b[0m");
	}

	private interface Goed {

		double huidigeWaarde();
	}

	private static class BedrijfsInventaris {

		private final ArrayList<Goed> alleGoederen = new ArrayList<>();
		private final String naam;
		private double bedrijfsBudget;

		public BedrijfsInventaris(String nm, double bud) {
			this.naam = nm;
			this.bedrijfsBudget = bud;
		}

		public void schafAan(Goed g) {
			if (g.huidigeWaarde() > this.bedrijfsBudget)
				throw new RuntimeException("Niet goeg geld!");
			if (this.alleGoederen.contains(g))
				throw new IllegalArgumentException("Dit goed is al aangeschaft!");
			this.alleGoederen.add(g);
			this.bedrijfsBudget -= g.huidigeWaarde();
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder("Bedrijf ").append(this.naam).append(" heeft de volgende goederen aangeschaft:");
			for (Goed g : this.alleGoederen)
				builder.append("\n\t").append(g);
			return builder.toString();
		}
	}

	private static abstract class Voertuig implements Goed {

		private final String type;
		protected double nieuwprijs;
		protected int bouwjaar;

		public Voertuig(String tp, double pr, int jr) {
			this.type = Objects.requireNonNull(tp);
			this.nieuwprijs = pr;
			this.bouwjaar = jr;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Voertuig voertuig = (Voertuig) o;
			return Double.compare(voertuig.nieuwprijs, this.nieuwprijs) == 0 &&
					this.bouwjaar == voertuig.bouwjaar &&
					this.type.equals(voertuig.type);
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.type, this.nieuwprijs, this.bouwjaar);
		}

		@Override
		public String toString() {
			return String.format("Voertuig: %s met bouwjaar %s heeft een waarde van: €%.2f", this.type, this.bouwjaar, this.huidigeWaarde());
		}
	}

	private static class Auto extends Voertuig {

		private final String kenteken;

		public Auto(String tp, double pr, int jr, String kt) {
			super(tp, pr, jr);
			this.kenteken = Objects.requireNonNull(kt);
		}

		@Override
		public double huidigeWaarde() {
			return this.nieuwprijs * Math.pow(0.7, LocalDate.now().getYear() - this.bouwjaar);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			if (!super.equals(o)) return false;
			Auto auto = (Auto) o;
			return this.kenteken.equals(auto.kenteken);
		}

		@Override
		public int hashCode() {
			return Objects.hash(super.hashCode(), this.kenteken);
		}
	}

	private static class Fiets extends Voertuig {

		private final int framenummer;

		public Fiets(String tp, double pr, int jr, int fnr) {
			super(tp, pr, jr);
			this.framenummer = fnr;
		}

		@Override
		public double huidigeWaarde() {
			return this.nieuwprijs * Math.pow(0.9, LocalDate.now().getYear() - this.bouwjaar);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			if (!super.equals(o)) return false;
			Fiets fiets = (Fiets) o;
			return this.framenummer == fiets.framenummer;
		}

		@Override
		public int hashCode() {
			return Objects.hash(super.hashCode(), this.framenummer);
		}
	}

	private static class Computer implements Goed {

		private final String type, macAdres;
		private final double aanschafPrijs;
		private final int productieJaar;

		public Computer(String tp, String adr, double pr, int jr) {
			this.type = Objects.requireNonNull(tp);
			this.macAdres = Objects.requireNonNull(adr);
			this.aanschafPrijs = pr;
			this.productieJaar = jr;
		}

		@Override
		public double huidigeWaarde() {
			return this.aanschafPrijs * Math.pow(0.6, LocalDate.now().getYear() - this.productieJaar);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Computer computer = (Computer) o;
			return Double.compare(computer.aanschafPrijs, this.aanschafPrijs) == 0 &&
					this.productieJaar == computer.productieJaar &&
					this.type.equals(computer.type) &&
					this.macAdres.equals(computer.macAdres);
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.type, this.macAdres, this.aanschafPrijs, this.productieJaar);
		}

		@Override
		public String toString() {
			return String.format("Computer: %s met productiejaar %s heeft een waarde van: €%.2f", this.type, this.productieJaar, this.huidigeWaarde());
		}
	}
}
