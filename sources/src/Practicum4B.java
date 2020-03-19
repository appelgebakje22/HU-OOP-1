package src;

public class Practicum4B {

	public static void main(String[] args) {
		AutoHuur ah1 = new AutoHuur();
		System.out.println("Eerste autohuur:\n" + ah1 + "\n");

		Klant k = new Klant("Mijnheer de Vries");
		k.setKorting(10.0);
		ah1.setHuurder(k);
		System.out.println("Eerste autohuur:\n" + ah1 + "\n");

		Auto a1 = new Auto("Peugeot 207", 50);
		ah1.setGehuurdeAuto(a1);
		ah1.setAantalDagen(4);
		System.out.println("Eerste autohuur:\n" + ah1 + "\n");

		AutoHuur ah2 = new AutoHuur();
		Auto a2 = new Auto("Ferrari", 3500);
		ah2.setGehuurdeAuto(a2);
		ah2.setHuurder(k);
		ah2.setAantalDagen(1);
		System.out.println("Tweede autohuur:\n" + ah2 + "\n");

		System.out.println("Gehuurd: " + ah1.getGehuurdeAuto());
		System.out.println("Gehuurd: " + ah2.getGehuurdeAuto());
	}

	public static class Klant {

		private final String naam;
		private double kortingsPercentage;

		public Klant(String nm) {
			this.naam = nm;
		}

		public void setKorting(double kP) {
			this.kortingsPercentage = kP;
		}

		public double getKorting() {
			return kortingsPercentage;
		}

		@Override
		public String toString() {
			return String.format("%s (korting: %s%%)", this.naam, this.kortingsPercentage);
		}
	}

	public static class Auto {

		private final String type;
		private double prijsPerDag;

		public Auto(String tp, double prPd) {
			this.type = tp;
			this.prijsPerDag = prPd;
		}

		public void setPrijsPerDag(double prPd) {
			this.prijsPerDag = prPd;
		}

		public double getPrijsPerDag() {
			return prijsPerDag;
		}

		@Override
		public String toString() {
			return String.format("%s met prijs per dag: %s", this.type, this.prijsPerDag);
		}
	}

	public static class AutoHuur {

		private Klant huurder;
		private Auto gehuurdeAuto;
		private int aantalDagen;

		public Klant getHuurder() {
			return huurder;
		}

		public void setHuurder(Klant k) {
			this.huurder = k;
		}

		public Auto getGehuurdeAuto() {
			return gehuurdeAuto;
		}

		public void setGehuurdeAuto(Auto gA) {
			this.gehuurdeAuto = gA;
		}

		public int getAantalDagen() {
			return aantalDagen;
		}

		public void setAantalDagen(int aD) {
			this.aantalDagen = aD;
		}

		public double totaalPrijs() {
			if (this.gehuurdeAuto == null)
				return 0;
			double result = this.gehuurdeAuto.prijsPerDag * this.aantalDagen;
			if (this.huurder != null) {
				double percentage = result * (this.huurder.kortingsPercentage / 100.0);
				result -= percentage;
			}
			return result;
		}

		@Override
		public String toString() {
			String autoString = this.gehuurdeAuto != null ? "autotype: " + this.gehuurdeAuto.toString() : "er is geen auto bekend";
			String huurderString = this.huurder != null ? "op naam van: " + this.huurder.toString() : "er is geen huurder bekend";
			return String.format("\t%s\n\t%s\n\tAantal dagen: %s en dat kost %s", autoString, huurderString, this.aantalDagen, this.totaalPrijs());
		}
	}
}
