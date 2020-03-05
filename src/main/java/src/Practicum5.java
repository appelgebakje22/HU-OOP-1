package src;

import java.util.ArrayList;

public class Practicum5 {

    public static void main(String[] arg) {
        Klas k = new Klas("V1Z");
        Leerling l = new Leerling("Hans");
        k.voegLeerlingToe(l);
        l = new Leerling("Jan");
        k.voegLeerlingToe(l);
        l = new Leerling("Wim");
        k.voegLeerlingToe(l);
        System.out.println(k);
        k.wijzigCijfer(new String("Hans"), 7.6);
        k.wijzigCijfer("Klaas", 7.6);
        System.out.println(k.toString());
        System.out.println("Aantal leerlingen: " + k.aantalLeerlingen());
    }

    private static class Leerling {

        private final String naam;
        private double cijfer;

        public Leerling(String nm) {
            this.naam = nm;
        }

        public String getNaam() {
            return naam;
        }

        public double getCijfer() {
            return cijfer;
        }

        public void setCijfer(double c) {
            this.cijfer = c;
        }

        @Override
        public String toString() {
            return String.format("%s heeft cijfer: %s", this.naam, this.cijfer);
        }
    }

    private static class Klas {

        private final ArrayList<Leerling> deLeerlingen = new ArrayList<>();
        private final String klasCode;

        public Klas(String kC) {
            this.klasCode = kC;
        }

        public void voegLeerlingToe(Leerling l) {
            this.deLeerlingen.add(l);
        }

        public void wijzigCijfer(final String nm, final double nweCijfer) {
            this.deLeerlingen.stream().filter(l -> l.naam.equals(nm)).forEach(l -> l.setCijfer(nweCijfer));
        }

        public ArrayList<Leerling> getLeerlingen() {
            return deLeerlingen;
        }

        public int aantalLeerlingen() {
            return this.deLeerlingen.size();
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder(String.format("In klas %s zitten de volgende leerlingen:", this.klasCode)).append('\n');
            this.deLeerlingen.forEach(l -> b.append(l).append('\n'));
            return b.toString();
        }
    }
}