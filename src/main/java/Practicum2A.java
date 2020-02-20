public class Practicum2A {

	public static void main(String[] args)
	{
		Zwembad z1 = new Zwembad(2.0, 5.5, 1.5);
		System.out.println("BREEDTE: " + z1.getBreedte());
		System.out.println("LENGTE: " + z1.getLengte());
		System.out.println("DIEPTE: " + z1.getDiepte());
		System.out.println("BEREKENDE INHOUD: " + z1.inhoud());
		System.out.println();

		Zwembad z2 = new Zwembad( );
		z2.setBreedte(2.5);
		z2.setLengte(100.0);
		z2.setDiepte(2.0);
		double inh = z2.inhoud();
		System.out.println("GEGEVENS ZWEMBAD: " + z2.toString());
		System.out.println("BEREKENDE INHOUD: " + z2.inhoud());
	}

	private static class Zwembad {

		private double breedte, lengte, diepte;

		public Zwembad(double breedte, double lengte, double diepte) {
			this.breedte = breedte;
			this.lengte = lengte;
			this.diepte = diepte;
		}

		public Zwembad() {
		}

		public void setBreedte(double breedte) {
			this.breedte = breedte;
		}

		public void setLengte(double lengte) {
			this.lengte = lengte;
		}

		public void setDiepte(double diepte) {
			this.diepte = diepte;
		}

		public double getBreedte() {
			return this.breedte;
		}

		public double getLengte() {
			return this.lengte;
		}

		public double getDiepte() {
			return this.diepte;
		}

		public double inhoud() {
			return this.breedte * this.lengte * this.diepte;
		}

		@Override
		public String toString() {
			return String.format("Dit zwembad is %s meter breed, %s meter lang, en %s meter diep", this.breedte, this.lengte, this.diepte);
		}
	}
}
