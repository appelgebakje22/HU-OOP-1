package src;

public class Practicum4A {

	public static void main(String[] args)
	{
		Huis h1 = new Huis("Nijenoord 1", 1970);
		Persoon p1 = new Persoon("Ronald Plasterk", 52);
		h1.setHuisbaas(p1);
		System.out.println(h1);
		System.out.println();

		Huis h2 = new Huis("Vredenburg", 1991);
		Persoon p2 = new Persoon("Annie Brouwers", 59);
		h2.setHuisbaas(p2);
		System.out.println(h2);
		System.out.println();
		System.out.println("Huisbaas 1: " + h1.getHuisbaas());
		System.out.println("Huisbaas 2: " + h2.getHuisbaas());
	}

	private static class Huis {
		private String adres;
		private int bouwjaar;
		private Persoon huisbaas;

		public Huis(String adr, int bwjr)
		{
			this.adres = adr;
			this.bouwjaar = bwjr;
		}

		public void setHuisbaas(Persoon hb)
		{
			this.huisbaas = hb;
		}

		public Persoon getHuisbaas()
		{
			return huisbaas;
		}

		@Override
		public String toString()
		{
			return String.format("Huis %s is gebouwd in %s\nen heeft huisbaas %s", this.adres, this.bouwjaar, this.huisbaas);
		}
	}

	private static class Persoon {

		private String naam;
		private int    leeftijd;

		public Persoon(String nm, int lft)
		{
			this.naam = nm;
			this.leeftijd = lft;
		}

		@Override
		public String toString()
		{
			return String.format("%s; Leeftijd %s jaar", this.naam, this.leeftijd);
		}
	}
}
