import static src.Practicum4B.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Practicum4B {

	private Klant huurder;
	private Auto auto;
	private AutoHuur huur;

	@Before
	public void init() {
		this.huurder = new Klant("DeKlant");
		this.auto = new Auto("DeAuto", 100);
		this.huur = new AutoHuur();
	}

	@Test
	public void testPrijsZonderKorting() {
		this.huur.setAantalDagen(10);
		this.huur.setHuurder(this.huurder);
		this.huur.setGehuurdeAuto(this.auto);
		assertEquals(this.huur.totaalPrijs(), 1000, 0);
	}

	@Test
	public void testPrijsMetKorting() {
		this.huurder.setKorting(10.0);
		this.huur.setAantalDagen(10);
		this.huur.setHuurder(this.huurder);
		this.huur.setGehuurdeAuto(this.auto);
		assertEquals(this.huur.totaalPrijs(), 900, 0);
	}

	@Test
	public void testGeenHuurderGeenAuto() {
		this.huur.setAantalDagen(10);
		assertEquals(this.huur.toString(), "\ter is geen auto bekend\n\ter is geen huurder bekend\n\tAantal dagen: 10 en dat kost 0.0");
		assertNull(this.huur.getHuurder());
		assertNull(this.huur.getGehuurdeAuto());
	}

	@Test
	public void testWelHuurderGeenAuto() {
		this.huur.setHuurder(this.huurder);
		this.huur.setAantalDagen(10);
		assertEquals(this.huur.toString(), "\ter is geen auto bekend\n\top naam van: DeKlant (korting: 0.0%)\n\tAantal dagen: 10 en dat kost 0.0");
		assertNotNull(this.huur.getHuurder());
		assertNull(this.huur.getGehuurdeAuto());
	}

	@Test
	public void testGeenHuurderWelAuto() {
		this.huur.setGehuurdeAuto(this.auto);
		this.huur.setAantalDagen(10);
		assertEquals(this.huur.toString(), "\tautotype: DeAuto met prijs per dag: 100.0\n\ter is geen huurder bekend\n\tAantal dagen: 10 en dat kost 1000.0");
		assertNull(this.huur.getHuurder());
		assertNotNull(this.huur.getGehuurdeAuto());
	}
}