import org.junit.Before;
import org.junit.Test;
import src.Practicum6A;
import src.Practicum6A.Persoon;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersoonTest {
    private int ditJaar;
    private Persoon koper;
    private Persoon koperArm;
    private Persoon verkoper;
    private Practicum6A.Game game1;
    private Practicum6A.Game game2;
    private String toStringTekstGame1;
    private String toStringTekstGame2;
    private String toStringTekstKoper0Games;
    private String toStringTekstKoperHeeftGame1;
    private String toStringTekstKoperHeeftGame2;
    private String toStringTekstKoper2Games;
    private String toStringTekstKoperArm;
    private String toStringTekstKoperArm1Game;
    private String toStringTekstVerkoper0Games;
    private String toStringTekstVerkoperHeeftGame1;
    private String toStringTekstVerkoperHeeftGame2;

    @Before
    public void init() {
        ditJaar = LocalDate.now().getYear();
        koper = new Persoon("Eric de Koper", 200.0);
        koperArm = new Persoon("Hans", 36.0);
        verkoper = new Persoon("Jos de Verkoper", 60.0);
        toStringTekstKoper0Games = "Eric de Koper heeft een budget van $200,00 en bezit de volgende games:";
        //game 1 is nieuw
        game1 = new Practicum6A.Game("Mario Kart", ditJaar, 50.0);
        toStringTekstGame1 = "Mario Kart, uitgegeven in " + ditJaar + "; nieuwprijs: $50,00 nu voor: $50,00";
        toStringTekstKoperHeeftGame1 = "Eric de Koper heeft een budget van $150,00 en bezit de volgende games:";
        //game2 is 1 jaar oud
        game2 = new Practicum6A.Game("Zelda", ditJaar - 1, 50.0);
        toStringTekstGame2 = "Zelda, uitgegeven in " + (ditJaar - 1) + "; nieuwprijs: $50,00 nu voor: $35,00";
        toStringTekstKoperHeeftGame2 = "Eric de Koper heeft een budget van $165,00 en bezit de volgende games:";
        toStringTekstKoper2Games = "Eric de Koper heeft een budget van $115,00 en bezit de volgende games:";
        toStringTekstKoperArm = "Hans heeft een budget van $36,00 en bezit de volgende games:";
        toStringTekstKoperArm1Game = "Hans heeft een budget van $1,00 en bezit de volgende games:";
        toStringTekstVerkoper0Games = "Jos de Verkoper heeft een budget van $60,00 en bezit de volgende games:";
        toStringTekstVerkoperHeeftGame1 = "Jos de Verkoper heeft een budget van $10,00 en bezit de volgende games:";
        toStringTekstVerkoperHeeftGame2 = "Jos de Verkoper heeft een budget van $25,00 en bezit de volgende games:";
    }


    //region tests toString()
    @Test
    public void testToStringGeenGames() {
        assertEquals(
                "toString() levert niet de juiste string op.",
                toStringTekstKoper0Games,
                koper.toString());
    }

    @Test
    public void testToString1Game() {
        koper.koop(game1);
        assertEquals("toString() levert niet de juiste string op.",
                toStringTekstKoperHeeftGame1 +
                        "\n" + toStringTekstGame1,
                koper.toString()
        );
    }

    @Test
    public void testToString2Games() {
        koper.koop(game1);
        koper.koop(game2);
        assertEquals("toString() levert niet de juiste string op.",
                toStringTekstKoper2Games +
                        "\n" + toStringTekstGame1 +
                        "\n" + toStringTekstGame2,
                koper.toString()
        );
    }
    //endregion

    //region tests koop()
    @Test
    public void testResultKoopGameNieuwVoldoendeBudget() {
        boolean gelukt = koper.koop(game1);
        assertEquals("koop() geeft ten onrechte terug dat de koop niet gelukt is.", true, gelukt);
    }

    @Test
    public void testBudgetKoopGameNieuwVoldoendeBudget() {
        boolean gelukt = koper.koop(game1);
        assertEquals("koop() levert niet het goede budget op.", 150.0, Math.round(koper.getBudget() * 100) / 100d);
    }

    @Test
    public void testGameListKoopGameNieuwVoldoendeBudget() {
        boolean gelukt = koper.koop(game1);
        assertEquals("koop() moet gelukt zijn, maar levert een fout op in, waarschijnlijk, de lijst met Games.",
                toStringTekstKoperHeeftGame1 + "\n" + toStringTekstGame1, koper.toString()
        );
    }

    @Test
    public void testResultKoopGameNieuwOnvoldoendeBudget() {
        boolean gelukt = koperArm.koop(game1);
        assertEquals("koop() geeft ten onrechte terug dat de koop gelukt is.", false, gelukt);
    }

    @Test
    public void testBudgetKoopGameNieuwOnvoldoendeBudget() {
        boolean gelukt = koperArm.koop(game1);
        assertEquals("koop() levert niet het goede budget op.", 36.0, Math.round(koperArm.getBudget() * 100) / 100d);
    }

    @Test
    public void testGameListKoopGameNieuwOnvoldoendeBudget() {
        boolean gelukt = koperArm.koop(game1);
        assertEquals("koop() mag niet gelukt zijn, maar levert een fout op in, waarschijnlijk, de lijst met Games.",
                toStringTekstKoperArm, koperArm.toString()
        );
    }

    @Test
    public void testResultKoopDubbeleGameVoldoendeBudget() {
        boolean gelukt = koper.koop(game1);
        gelukt = koper.koop(game1);
        assertEquals("Game is al in bezit, maar koop() geeft ten onrechte terug dat de koop gelukt is.", false, gelukt);
    }

    @Test
    public void testBudgetKoopDubbeleGameVoldoendeBudget() {
        boolean gelukt = koper.koop(game1);
        gelukt = koper.koop(game1);
        assertEquals("koop() levert niet het goede budget op (game is al in bezit)", 150.0, Math.round(koper.getBudget() * 100) / 100d);
    }

    @Test
    public void testGameListKoopDubbeleGameVoldoendeBudget() {
        boolean gelukt = koper.koop(game1);
        gelukt = koper.koop(game1);
        assertEquals("koop() mag niet gelukt zijn (game is al in bezit), maar levert een fout op in, waarschijnlijk, de lijst met Games.",
                toStringTekstKoperHeeftGame1 + "\n" + toStringTekstGame1, koper.toString());
    }

    @Test
    public void testResultKoopDubbeleGameOnvoldoendeBudget() {
        boolean gelukt = koperArm.koop(game2);
        gelukt = koperArm.koop(game2);
        assertEquals("Game is al in bezit en er is te weinig budget, maar koop() geeft ten onrechte terug dat de koop gelukt is.", false, gelukt);
    }

    @Test
    public void testBudgetKoopDubbeleGameOnvoldoendeBudget() {
        boolean gelukt = koperArm.koop(game2);
        gelukt = koperArm.koop(game2);
        assertEquals("koop() levert niet het goede budget op (game is al in bezit).", 1.00, Math.round(koperArm.getBudget() * 100) / 100d);
    }

    @Test
    public void testGameListKoopDubbeleGameOnvoldoendeBudget() {
        boolean gelukt = koperArm.koop(game2);
        gelukt = koperArm.koop(game2);
        assertEquals("koop() mag niet gelukt zijn, maar levert een fout op in, waarschijnlijk, de lijst met Games.",
                toStringTekstKoperArm1Game + "\n" + toStringTekstGame2, koperArm.toString()
        );
    }
    //endregion

    //region tests verkoop()
    @Test
    public void testResultVerkoopGeenBezitVerkoper() {
        boolean gelukt = verkoper.verkoop(game1, koper);
        assertFalse("verkoop() geeft ten onrechte terug dat verkoop gelukt is.", gelukt);
    }

    @Test
    public void testBudgetVerkoopGeenBezitVerkoper() {
        boolean gelukt = verkoper.verkoop(game1, koper);
        assertEquals("verkoop() levert niet het goede budget van de verkoper op (Game is niet in bezit verkoper).", 60, Math.round(verkoper.getBudget() * 100) / 100d);
        assertEquals("verkoop() levert niet het goede budget van de koper op (Game is niet in bezit verkoper).", 200, Math.round(koper.getBudget() * 100) / 100d);
    }

    @Test
    public void testGameListVerkoopGeenBezitVerkoper() {
        boolean gelukt = verkoper.verkoop(game1, koper);
        assertEquals("verkoop() mag niet gelukt zijn (Game is niet in bezit verkoper), maar levert een fout op in, waarschijnlijk, de lijst met Games (verkoper).",
                toStringTekstVerkoper0Games, verkoper.toString());
        assertEquals("verkoop() mag niet gelukt zijn (Game is niet in bezit verkoper), maar levert een fout op in, waarschijnlijk, de lijst met Games (koper).",
                toStringTekstKoper0Games, koper.toString());
    }

    @Test
    public void testResultVerkoopKoperOnvoldoendeBudget() {
        verkoper.koop(game1);
        boolean gelukt = verkoper.verkoop(game1, koperArm);
        assertFalse("verkoop() geeft ten onrechte terug dat verkoop gelukt is.", gelukt);
    }

    @Test
    public void testBudgetVerkoopKoperOnvoldoendeBudget() {
        verkoper.koop(game1);
        boolean gelukt = verkoper.verkoop(game1, koperArm);
        assertEquals("verkoop() levert niet het goede budget van de verkoper op (game is niet verkocht).", 10, Math.round(verkoper.getBudget() * 100) / 100d);
        assertEquals("verkoop() levert niet het goede budget van de koper op (game is niet verkocht).", 36, Math.round(koperArm.getBudget() * 100) / 100d);
    }

    @Test
    public void testGameListVerkoopKoperOnvoldoendeBudget() {
        verkoper.koop(game1);
        boolean gelukt = verkoper.verkoop(game1, koperArm);
        assertEquals("verkoop() mag niet gelukt zijn (koper onvoldoende budget), maar levert een fout op in, waarschijnlijk, de lijst met Games (verkoper).",
                toStringTekstVerkoperHeeftGame1 + "\n" + toStringTekstGame1, verkoper.toString());
        assertEquals("verkoop() mag niet gelukt zijn (koper onvoldoende budget), maar levert een fout op in, waarschijnlijk, de lijst met Games (koper).",
                toStringTekstKoper0Games, koper.toString());
    }

    @Test
    public void testResultVerkoopKoperDubbeleGame() {
        verkoper.koop(game2);
        koper.koop(game2);
        boolean gelukt = verkoper.verkoop(game2, koper);
        assertFalse("verkoop() geeft ten onrechte terug dat verkoop gelukt is.", gelukt);
    }

    @Test
    public void testBudgetVerkoopKoperDubbeleGame() {
        verkoper.koop(game2);
        koper.koop(game2);
        assertEquals("verkoop() levert niet het goede budget van de verkoper op (game is niet verkocht).", 25, Math.round(verkoper.getBudget() * 100) / 100d);
        assertEquals("verkoop() levert niet het goede budget van de koper op (game is niet verkocht).", 165, Math.round(koper.getBudget() * 100) / 100d);
    }

    @Test
    public void testGameListVerkoopKoperDubbeleGame() {
        verkoper.koop(game2);
        koper.koop(game2);
        assertEquals("verkoop() mag niet gelukt zijn (koper heeft game al), maar levert een fout op in, waarschijnlijk, de lijst met Games (verkoper).",
                toStringTekstVerkoperHeeftGame2 + "\n" + toStringTekstGame2, verkoper.toString());
        assertEquals("verkoop() mag niet gelukt zijn (koper heeft game al), maar levert een fout op in, waarschijnlijk, de lijst met Games (koper).",
                toStringTekstKoperHeeftGame2 + "\n" + toStringTekstGame2, koper.toString());
    }

    @Test
    public void testResultVerkoopVoorwaardenOK() {
        verkoper.koop(game2);
        boolean gelukt = verkoper.verkoop(game2, koper);
        assertTrue("verkoop() geeft ten onrechte terug dat verkoop niet gelukt is.", gelukt);
    }

    @Test
    public void testBudgetVerkoopVoorwaardenOK() {
        verkoper.koop(game2);
        boolean gelukt = verkoper.verkoop(game2, koper);
        assertEquals("verkoop() levert niet het goede budget van de verkoper op (game is verkocht).", 60, Math.round(verkoper.getBudget() * 100) / 100d, 0);
        assertEquals("verkoop() levert niet het goede budget van de koper op (game is verkocht).", 165, Math.round(koper.getBudget() * 100) / 100d, 0);
    }

    @Test
    public void testGameListVerkoopVoorwaardenOK() {
        verkoper.koop(game2);
        boolean gelukt = verkoper.verkoop(game2, koper);
        assertEquals("verkoop() moet gelukt zijn, maar levert een fout op in, waarschijnlijk, de lijst met Games (verkoper).",
                toStringTekstVerkoper0Games, verkoper.toString());
        assertEquals("verkoop() moet gelukt zijn, maar levert een fout op in, waarschijnlijk, de lijst met Games (koper).",
                toStringTekstKoperHeeftGame2 + "\n" + toStringTekstGame2, koper.toString());
    }
    //endregion
}
