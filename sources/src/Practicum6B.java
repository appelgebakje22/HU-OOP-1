package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Practicum6B {

    public static void main(String[] args) {
        int releaseJaar1 = LocalDate.now().getYear() - 1; // 1 jaar geleden

        Game g1 = new Game("Just Cause 3", releaseJaar1, 49.98);
        Game g2 = new Game("Need for Speed: Rivals", releaseJaar1, 45.99);
        Game g3 = new Game("Need for Speed: Rivals", releaseJaar1, 45.99);

        Persoon p1 = new Persoon("Eric", 200);
        Persoon p2 = new Persoon("Hans", 55);

        System.out.println("p1 koopt g1:" + (p1.koop(g1) ? "" : " niet") + " gelukt");
        System.out.println("p1 koopt g2:" + (p1.koop(g2) ? "" : " niet") + " gelukt");
        System.out.println("p1 koopt g3:" + (p1.koop(g3) ? "" : " niet") + " gelukt");
        System.out.println("\np1: " + p1 + "\n\np2: " + p2 + "\n");

        System.out.println("p1 verkoopt g2 aan p2:" + (p1.verkoop(g2, p2) ? "" : " niet") + " gelukt");
        System.out.println("p1 verkoopt g1 aan p2:" + (p1.verkoop(g1, p2) ? "" : " niet") + " gelukt");
        System.out.println("\np1: " + p1 + "\n\np2: " + p2 + "\n");

        ArrayList<Game> teKoop = new ArrayList<>();
        teKoop.add(g1);
        teKoop.add(new Game("Mario Kart 8", 2019, 35.00));
        ArrayList<Game> nogNietInBezit = p1.bepaalGamesNietInBezit(teKoop);
        System.out.println("p1 heeft de volgende games nog niet: " + nogNietInBezit.toString());

        Game game1 = p1.zoekGameOpNaam("Just Cause 3");
        System.out.println("p1 heeft Just Cause 3 " + (game1 != null ? "wel!" : "niet!"));
        Game game2 = p1.zoekGameOpNaam("Just Cause 4");
        System.out.println("p1 heeft Just Cause 4 " + (game2 != null ? "wel!" : "niet!"));
    }

    public static class Game {

        private final String naam;
        private final int releaseJaar;
        private final double nieuwPrijs;

        public Game(String nm, int rJ, double nwPr) {
            this.naam = nm;
            this.releaseJaar = rJ;
            this.nieuwPrijs = nwPr;
        }

        public String getNaam() {
            return naam;
        }

        public double huidigeWaarde() {
            return this.nieuwPrijs * Math.pow(0.7, LocalDate.now().getYear() - this.releaseJaar);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Game game = (Game) o;
            return releaseJaar == game.releaseJaar &&
                    naam.equals(game.naam);
        }

        @Override
        public int hashCode() {
            return Objects.hash(naam, releaseJaar);
        }

        @Override
        public String toString() {
            //Mario Kart, uitgegeven in 2019; nieuwprijs: $50,00 nu voor: $35,00
            //Mario Kart, uitgegeven in 2019; nieuwprijs: $50,00 nu voor: $35,00
            return String.format(Locale.GERMAN, "%s, uitgegeven in %s; nieuwprijs: $%.2f nu voor: $%.2f", this.naam, this.releaseJaar, this.nieuwPrijs, this.huidigeWaarde());
        }
    }

    public static class Persoon {

        private final ArrayList<Game> mijnGames = new ArrayList<>();
        private final String naam;
        private double budget;

        public Persoon(String nm, double bud) {
            this.naam = nm;
            this.budget = bud;
        }

        public double getBudget() {
            return budget;
        }

        public boolean koop(Game g) {
            if (this.mijnGames.contains(g) || this.getBudget() < g.huidigeWaarde())
                return false;
            this.budget -= g.huidigeWaarde();
            return this.mijnGames.add(g);
        }

        public boolean verkoop(Game g, Persoon koper) {
            if (!this.mijnGames.contains(g) || !koper.koop(g))
                return false;
            this.budget += g.huidigeWaarde();
            return this.mijnGames.remove(g);
        }

        public ArrayList<Game> bepaalGamesNietInBezit(ArrayList<Game> checkLijst)
        {
            ArrayList<Game> resultaat = new ArrayList<>(checkLijst);
            resultaat.removeAll(mijnGames);
            return resultaat;
        }

        public Game zoekGameOpNaam(String naam)
        {
            if (naam == null)
                return null;
            return mijnGames.stream().filter(game -> game.naam.equals(naam)).findFirst().orElse(null);
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder(String.format(Locale.GERMAN, "%s heeft een budget van %.2f en bezit de volgende games:", this.naam, this.budget)).append('\n');
            this.mijnGames.forEach(g -> b.append(g).append('\n'));
            return b.toString();
        }
    }
}