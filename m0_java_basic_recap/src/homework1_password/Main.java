package homework1_password;

public class Main {
    /**
     * Das ist die main-Methode. Hier müssen Sie für die Aufgabe nichts hin programmieren, Sie sollten allerdings das
     * Vorhandene benutzen und ggf. ergänzen um Ihre Programmierung zu testen. Die Aufgabenstellungen (es gibt zwei
     * unabhängig voneinander lösbare Aufgaben) finden Sie in der Datei Passwort.java.
     * Bevor Sie mit den Aufgaben anfangen: Starten Sie das Programm, es sollte lauffähig sein. Sie werden
     * Ausgaben sehen, die von dieser Main hier erzeugt werden. Diese Ausgaben können Ihnen helfen Ihre Programmierung
     * zu testen.
     * Probieren Sie hier verschiedene Dinge aus, das hier gezeigte sind nur BEISPIELE. Versuchen Sie hier Ihre
     * Programmierung bestmöglich zu testen! Die Main.java ist nicht Bestandteil der Abgabe. Hier können Sie
     * alles ausprobieren, was Sie für nötig halten, um Ihre Programmierung zu testen.
     */
    public static void main(String[] args) {

        System.out.println("Teilaufgabe 1 testen:");
        // das ist eine Liste von möglichen Passwörten. Diese (und ggf. weitere, die Ihnen einfallen) sollen auf die
        // Einhaltung einer Passwortrichtline (lang genug, Großbuchstaben, Sonderzeichen, Ziffern, etc.) geprüft werden.
        String[] pws = {
                "zukurz", // hier fehlen Großbuchstaben, Sonderzeichen und/oder Zahlen und das Passwort ist zu kurz
                "Sicher123!", // hier ist alles gemäß Richtlinie enthalten, also OK
                "nichtsicher", // lang genug, aber kein Großbuchstabe, keine Ziffer, kein Sonderzeichen
                "ImMeRnochNICHTsicher", // lang genug, aber keine Ziffern oder Sonderzeichen
                "JetztAberSicher!", // das ist OK
                "4hoch4istNICHT16" // das ist auch OK
        };

        // Die Schleife iteriert über alle Einträge in pws.
        for (int i = 0; i < pws.length; i++) {
            // hier wird das i-te Passwort auf Einhaltung der Richtline geprüft. Den Inhalt der Methode istSicheresPasswort
            // müssen Sie programmieren, das ist Teilaufgabe 1. Die Aufgabenstellung finden Sie in Passwort.java
            // direkt oberhalb der Methodendefinition.
            // Mit gedrückter STRG-Taste und einem Klick auf den Methodennamen gelangen Sie direkt dorthin.
            boolean ergebnisPruefung = Passwort.istSicheresPasswort(pws[i]);

            // Eine Textausgabe zum Ergebnis:
            System.out.print("Das Passwort:" + pws[i] + " ist ");
            if (!ergebnisPruefung){
                // wenn die Prüfung negativ=false ist, wird in den Ausgabetext ein NICHT eingefügt.
                System.out.print("NICHT ");
            }
            System.out.println("sicher (im Sinne der Passwortrichtlinie).");
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\n\nTeilaufgabe 2 testen:");
        // Hier ein neues Passwort erzeugen. Es wird automatisch ein zufälliges Passwort erzeugt:
        Passwort pw = new Passwort();

        /* wer möchte, kann hier ein eigenes Passwort setzen, um die Programmierung zu testen. Dazu einfach die zwei
           Zeilen unterhalb einkommentieren. Wichtig: Ihre Lösung muss auch mit den zufällig generierten Passwörtern
           funktionieren. */
        //String meinTestPasswort = "Geheim!";
        //pw.setMeinPasswort(meinTestPasswort);

        // Hier die "passwortErraten" Programmierung testen
        String erraten = pw.passwortErraten();

        System.out.println("Testpasswort richtig? " + pw.passwortPruefen(erraten));

    }
}
