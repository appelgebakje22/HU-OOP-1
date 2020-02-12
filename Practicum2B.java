public class Practicum2B {

	public static void main(String[] args) {
		Voetbalclub ajx = new Voetbalclub("Ajax      ");
		Voetbalclub feij = new Voetbalclub("Feijenoord");

		feij.verwerkResultaat('w');
		feij.verwerkResultaat('w');
		feij.verwerkResultaat('w');
		feij.verwerkResultaat('g');

		System.out.println("Feijenoord punten: " + feij.aantalPunten());
		System.out.println("Ajax gespeeld: " + ajx.aantalGespeeld());
		System.out.println();

		System.out.println(ajx);
		System.out.println(feij);
	}

	private static class Voetbalclub {

		private String naam;
		private int aantalGewonnen, aantalGelijk, aantalVerloren;

		public Voetbalclub(String naam) {
			this.naam = naam;
		}

		public void verwerkResultaat(char ch) {
			/*
			if (ch == 'w')
				this.aantalGewonnen = this.aantalGewonnen + 1;
			if (ch == 'g')
				this.aantalGelijk = this.aantalGelijk + 1;
			if (ch == 'v')
				this.aantalVerloren = this.aantalVerloren + 1;
			*/
			switch(ch) {
				case 'w':
					++this.aantalGewonnen;
					return;
				case 'g':
					++this.aantalGelijk;
					return;
				case 'v':
					++this.aantalVerloren;
					return;
				default:
					return;
			}
		}

		public int aantalGespeeld() {
			return this.aantalGewonnen + this.aantalGelijk + this.aantalVerloren;
		}

		public int aantalPunten() {
			return this.aantalGewonnen * 3 + this.aantalGelijk;
		}

		@Override
		public String toString() {
			return String.format(
				"%s   %s %s %s %s %s",
				this.naam,
				this.aantalGespeeld(),
				this.aantalGewonnen,
				this.aantalGelijk,
				this.aantalVerloren,
				this.aantalPunten()
			);
		}
	}
}
