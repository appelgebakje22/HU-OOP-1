import org.junit.Before;
import org.junit.Test;

import static src.Practicum6A.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class GameTest {

    private Game game1JrOud;
    private int ditJaar;

    @Before
    public void init() {
        ditJaar = LocalDate.now().getYear();
        game1JrOud = new Game("Mario Kart", ditJaar - 1, 50.0);
    }

    //region Tests met huidigeWaarde()
    @Test
    public void testHuidigeWaardeNwPrijsNa0Jr() {
        Game game0JrOud = new Game("Mario Kart", ditJaar, 50.0);
        assertEquals("Huidige waarde na 0 jr niet correct.", 50.0, Math.round(game0JrOud.huidigeWaarde() * 100) / 100d, 0);
    }

    @Test
    public void testHuidigeWaardeNwPrijsNa1Jr() {
        assertEquals("Huidige waarde na 1 jr niet correct.", 35.0, Math.round(game1JrOud.huidigeWaarde() * 100) / 100d, 0);
    }

    @Test
    public void testHuidigeWaardeNwPrijsNa5Jr() {
        Game game5JrOud = new Game("Mario Kart", ditJaar - 5, 50.0);
        assertEquals("Huidige waarde na 5 jr niet correct.", 8.4, Math.round(game5JrOud.huidigeWaarde() * 100) / 100d, 0);
    }

    @Test
    public void testHuidigeWaardeGratisGameNa0Jr() {
        Game gratisGame0JrOud = new Game("Mario Kart Free", ditJaar, 0.0);
        assertEquals("Huidige waarde gratis game na 0 jr niet correct.", 0.0, Math.round(gratisGame0JrOud.huidigeWaarde() * 100) / 100d, 0);
    }

    @Test
    public void testHuidigeWaardeGratisGameNa5Jr() {
        Game gratisGame5JrOud = new Game("Mario Kart Free", ditJaar - 5, 0.0);
        assertEquals("Huidige waarde gratis game na 5 jr niet correct.", 0.0, Math.round(gratisGame5JrOud.huidigeWaarde() * 100) / 100d, 0);
    }

    //endregion

    //region Tests met equals()
    @Test
    public void testGameEqualsZelfdeGame() {
        Game zelfdeGame1JrOud = new Game("Mario Kart", ditJaar - 1, 50.0);
        assertEquals("equals() geeft false bij vgl. met zelfde game", game1JrOud, zelfdeGame1JrOud);
    }

    @Test
    public void testGameEqualsSelf() {
        assertEquals("equals() geeft false bij vgl. met zichzelf", game1JrOud, game1JrOud);
    }

    @Test
    public void testGameNotEqualsString() {
        assertNotEquals("equals() geeft true bij vgl. tussen game en String", "testString", game1JrOud);
    }

    @Test
    public void testGameNotEqualsGameAndereNaam() {
        Game otherGame1JrOud = new Game("Zelda", ditJaar - 1, 35.0);
        assertNotEquals("equals() geeft true bij vgl. met game met andere naam", game1JrOud, otherGame1JrOud);
    }

    @Test
    public void testGameNotEqualsGameAnderJaar() {
        Game game5JrOud = new Game("Mario Kart", ditJaar - 5, 50.0);
        assertNotEquals("equals() geeft true bij vgl. met game met ander releaseJaar", game1JrOud, game5JrOud);
    }

    @Test
    public void testGameEqualsGameAndereNwPrijs() {
        Game duurdereGame1JrOud = new Game("Mario Kart", ditJaar - 1, 59.95);
        assertEquals("equals() geeft false bij vgl. met zelfde game met andere nieuwprijs", game1JrOud, duurdereGame1JrOud);
    }

    @Test
    public void testGameNotEqualsGameHeelAndereGame() {
        Game heelAndereGame = new Game("Zelda", ditJaar - 2, 41.95);
        assertNotEquals("equals() geeft true bij vgl. met heel andere game", game1JrOud, heelAndereGame);
    }
    //endregion

    @Test
    public void testToString() {
        assertEquals("toString() geeft niet de juiste tekst terug.",
                "Mario Kart, uitgegeven in " + (ditJaar - 1) + "; nieuwprijs: $50,00 nu voor: $35,00",
                game1JrOud.toString());
    }

}