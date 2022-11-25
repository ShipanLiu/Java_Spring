package homework1_password;

import java.util.concurrent.ThreadLocalRandom;

public class Passwort extends PasswortGeheim {

    public Passwort() {
    }

    public Passwort(long seed) {
        super(seed);
    }

    final static String erlaubteZeichen = kleinbuchstabe + grossbuchstabe + ziffer + sonderzeichen;

    /**
     * Bonusaufgabe 1 im Wintersemester 2022/23 (insgesamt 10 Punkte möglich)
     *
     * Teilaufgabe 1 (max. 5 Punkte)
     *
     * Bitte prüfen Sie hier mit aus der Vorlesung bekannten Programmiermitteln, ob das Passwort den Richtlinien entspricht.
     * Folgende Kriterien soll Ihr Passwortprüfer berücksichtigen:
     * Das Passwort
     * - muss mindestens 8 Zeichen lang sein,
     * - muss mindestens einen Großbuchstaben (A-Z) enthalten,
     * - muss mindestens zwei Kleinbuchstaben (a-z) enthalten,
     * - muss ENTWEDER mindestens zwei Ziffern (0-9) enthalten
     * - ODER mindestens eins dieser Sonderzeichen !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ enthalten,
     * - darf nicht Ihre Matrikelnummer (als aufeinanderfolgende Zeichen) enthalten.
     * Darüber hinaus darf das Passwort beliebige weitere Zeichen enthalten.
     *
     * Für die Lösung der Aufgabe können Sie die vorgegebenen Variablen kleinbuchstabe, großbuchstabe, ziffer und
     * sonderzeichen gerne verwenden. Diese sind wie folgt definiert und können einfach durch den Variablennamen
     * aufgerufen werden:
     * String kleinbuchstabe = "abcdefghijklmnopqrstuvwxyz";
     * String großbuchstabe = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     * String ziffer = "0123456789";
     * String sonderzeichen = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
     *
     * Das Schlüsselwort static hat keine Auswirkungen auf Ihre Umsetzung der Aufgabe und braucht nicht weiter beachtet
     * zu werden.
     */

    static boolean istSicheresPasswort(String passwort) {
        /////////////////////////////
        // Ab hier Ihr Code ....
        boolean ergebnis = false;
        boolean containsOneUppercase = false;
        boolean containsOneLowercase = false;
        boolean containsSpecialCharacter = false;
        int digitalSize = 0;

        // my own matrikelnummer in the HSD
        String matrikelnummer = "hsd101";

        // check if the password contains Matrikelnummer
        if(passwort.contains(matrikelnummer)) {
            System.out.println("Matrikelnummer included!!!");
            ergebnis = false;
            return ergebnis;
        }

        // check if the password is too long
        if(passwort.length() < 8) {
            System.out.println("password too long");
            ergebnis = false;
            return ergebnis;
        }

        // now check uppercase + lowercase + special characters
        int passwordLength = passwort.length();
        int i = 0;
        while(i < passwordLength) {
            char ch = passwort.charAt(i);
            if(ziffer.indexOf(ch) != -1) {
                digitalSize++;
            }
            if(grossbuchstabe.indexOf(ch) != -1) {
                containsOneUppercase = true;
            }
            if(kleinbuchstabe.indexOf(ch) != -1) {
                containsOneLowercase = true;
            }
            if(sonderzeichen.indexOf(ch) != -1) {
                containsSpecialCharacter = true;
            }
            if(containsOneLowercase && containsOneUppercase && (containsSpecialCharacter || digitalSize >= 2)) {
                ergebnis = true;
                break;
            }
            i++;
        }
        return ergebnis;
    }

    /**
     * Bonusaufgabe 1 im Wintersemester 2022/23 (insgesamt 10 Punkte möglich)
     *
     * Teilaufgabe 2 (max. 5 Punkte)
     *
     * Erweitern Sie die Methode "passwortErraten" so, dass diese ein Passwort durch Ausprobieren ermittelt (errät).
     * Nutzen Sie dafür die vorgegebene Methode ueberinstimmendeZeichen. Diese Methode liefert als Rückgabe ein Array
     * von boolean Werten. Die Länge dieses Arrays verrät Ihnen, wie lang das zu erratende Passwort sein muss.
     * Wenn ein Element des Arrays den Wert true hat, signalisiert dies, dass Sie an dieser Position schon das richtige
     * Zeichen des Passworts erraten haben.
     * Wenn alle Elemente true sind, haben Sie das korrekte Passwort gefunden.
     *
     * Nutzen Sie gerne die Variable erlaubteZeichen. Diese enthält alle für ein Passwort infrage kommenden Zeichen!
     */
    String passwortErraten() {
        /////////////////////////////
        // Ab hier Ihr Code ....
        // alles ab hier, bis "// bis hier Ihr Code" kann durch Sie verändert werden.

        // Das folgende Codebeispiel dient nur der Unterstützung und zum Verständnis und kann durch Sie verändert oder
        // entfernt werden. Es ist auch nicht unbedingt ein Ansatz für die Lösung.
        String fertigErratenesPasswort = "** das Passwort wurde noch nicht erraten **";
        String rateversuch = "IstEsDasHier" + erlaubteZeichen.charAt(82); // Ein mögliches Passwort (das Zeichen mit dem Index 82 in erlaubteZeichen ist das Fragezeichen.
                                                                            //"IstEsDasHier?"
        boolean[] woRichtig = this.ueberinstimmendeZeichen(rateversuch); // im Array "woRichtig" stehen jetzt Booleans
        int anzahlDerZeichenImPasswort = woRichtig.length; // password length(a random number, will change)
        int anzahlDerRichtigenZeichen = 0; // Zählvariable
        int erlaubteZeichenLength = erlaubteZeichen.length();

        System.out.println("woRichtig length: " + anzahlDerZeichenImPasswort);

        for (int i=0; i<anzahlDerZeichenImPasswort; i++){
            if (woRichtig[i]){
                anzahlDerRichtigenZeichen++; // Zählen, wenn die i-te Stelle richtig ist
            }else{
                StringBuilder sb = new StringBuilder(rateversuch);
                for(int j = 0; j < erlaubteZeichenLength; j++) {
                    sb.setCharAt(i, erlaubteZeichen.charAt(j));
                    // convert back to string
                    rateversuch = sb.toString();
                    // and try again
                    woRichtig = this.ueberinstimmendeZeichen(rateversuch);
                    if(woRichtig[i]) {
                        System.out.println("one character is right");
                        System.out.println("this character is: " + rateversuch.charAt(i));
                        anzahlDerRichtigenZeichen++;
                        break;
                    }
                }
                System.out.println("anzahlDerRichtigenZeichen: " + anzahlDerRichtigenZeichen);
                // das i-te Element stimmt nicht, an dieser Position muss also ein anderes element aus "erlaubteZeichen"
                // ausprobiert werden.

            }
        }

        // wenn die anzahl der richtigen Zeichen im Passwort mit der gesamtzahl der Zeichen im Passwort übereinstimmt,
        // wurde offensichtlich das richtige Passwort erraten.
        if (anzahlDerRichtigenZeichen == anzahlDerZeichenImPasswort){
            fertigErratenesPasswort = rateversuch;
            System.out.println("the fertigErratenesPasswort is: " + fertigErratenesPasswort);
        }

        // Hinweis: Wenn der Rateversuch erfolgreich war, kann hier das erratene Passwort über return zurückgegeben werden.
        return fertigErratenesPasswort;
        // bis hier Ihr Code
        ////////////////////////////////////////////////////////////////////////////////////

    }
}
