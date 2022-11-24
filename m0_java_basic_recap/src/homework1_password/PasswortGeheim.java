package homework1_password;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
// import java.util.concurrent.ThreadLocalRandom;

/**
 * Schauen Sie sich hier gerne um. Es ist nicht notwendig, dass hier etwas geändert wird. Falls Sie etwas ändern:
 * stellen Sie sicher, dass Ihre Lösung in Passwort.java noch mit dem Original funktioniert. Wir werden die von Ihnen
 * abgegebenen Lösungscodes testen und die muss mit der Originalversion dieser Datei lauffähig sein.
 */
public class PasswortGeheim {

    final static String kleinbuchstabe = "abcdefghijklmnopqrstuvwxyz";
    final static String grossbuchstabe = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static String ziffer = "0123456789";
    final static String sonderzeichen = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    Random meinRand;

    ValueWatcher<String> meinPasswort;

    /**
     * Mit jedem neuen Objekt der Klasse, wird ein neues zufälliges Passwort erzeugt.
     */
    public PasswortGeheim() {
        this(System.currentTimeMillis());
    }

    public PasswortGeheim(long seed){
        this.meinRand = new Random(seed);
        int laenge = meinRand.nextInt(8, 17);
        this.meinPasswort = new ValueWatcher<>(this.sicheresPasswortAusdenken(laenge));
    }

    /**
     * Würfelt ein neues, zufälliges Passwort, das den Richtlinien entspricht.
     * @param laenge Länge des zufälligen Passworts (muss mindestens 8 sein)
     * @return das zufällige Passwort
     */
    private String sicheresPasswortAusdenken(int laenge){
        // int laenge = ThreadLocalRandom.current().nextInt(8, 17);
        if (laenge < 8){
            throw new RuntimeException("Ein sicheres Passwort muss mindestens 8 Zeichen haben!");
        }
        List<Integer> order = new ArrayList<>();
        for (int i=0; i<laenge; i++)
            order.add(i);
        java.util.Collections.shuffle(order);

        char[] pw = new char[laenge];
        int currentIndex = 0;
        pw[order.get(currentIndex++)] =(char)meinRand.nextInt('A', 'Z' + 1); // ein Großbuchstabe
        pw[order.get(currentIndex++)] =(char)meinRand.nextInt('a', 'z' + 1); // zwei Kleinbuchstaben
        pw[order.get(currentIndex++)] =(char)meinRand.nextInt('a', 'z' + 1); // zwei Kleinbuchstaben

        if (meinRand.nextDouble() <.5){
            // entweder zwei Ziffern
            pw[order.get(currentIndex++)] =(char)meinRand.nextInt('0', '9' + 1);
            pw[order.get(currentIndex++)] =(char)meinRand.nextInt('0', '9' + 1);
        }else{
            // oder ein Sonderzeichen
            pw[order.get(currentIndex++)] = sonderzeichen.charAt(meinRand.nextInt(0, sonderzeichen.length() ));
        }

        while (currentIndex < laenge){
            if (meinRand.nextDouble() < 0.45){
                pw[order.get(currentIndex++)] = (char)meinRand.nextInt('A', 'Z'+1); // ein Großbuchstabe
                continue;
            }
            if (meinRand.nextDouble() < 0.90){
                pw[order.get(currentIndex++)] = (char)meinRand.nextInt('A', 'Z'+1); // ein Kleinbuchstabe
                continue;
            }
            pw[order.get(currentIndex++)] = sonderzeichen.charAt(meinRand.nextInt(0, sonderzeichen.length())); // ein Sonderzeichen
        }
        return new String(pw);
    }

    /**
     *  Vergleicht zeichenweise einen String mit dem "geheimen" Passwort.
     * @param pw ein String, der mit dem "geheimen" Passwort verglichen werden soll.
     * @return ein Array von boolean, das genauso lang ist, wie das "geheime" Passwort. Stimmt ein Zeichen von pw mit
     * dem "geheimen" Passwort überein, wird die entsprechende Stelle im Rückgabearray auf true gesetzt.
     */
    public final boolean[] ueberinstimmendeZeichen(String pw){
        String meinPw = meinPasswort.getValue();
        boolean[] res = new boolean[meinPw.length()];

        for (int i=0; i<Math.min(pw.length(),res.length); i++){
            res[i] = pw.charAt(i) == meinPw.charAt(i);
        }
        return res;
    }

    /**
     * Prüft, ob ein Passwort mit dem "geheimen" Passwort übereinstimmt.
     * @param pw das zu prüfende Passwort.
     * @return true, wenn das zu prüfende Passwort mit dem "geheimen" Passwort übereinstimmt.
     */
    public final boolean passwortPruefen(String pw){
        return this.meinPasswort.getValue().equals(pw);
    }

    @Override
    public String toString() {
        return "<geheim>";
    }

    /**
     * Gibt das aktuelle Passwort zurück und setzt es direkt neu.
     * @return
     */
    public String passwortVerraten() {
        String bisherPasswort = meinPasswort.getValue();
        // Wieder neues Kennwort ausdenken
        int laenge = meinRand.nextInt(8, 17);
        this.meinPasswort = new ValueWatcher<String>(this.sicheresPasswortAusdenken(laenge));
        return bisherPasswort;
    }

    /**
     * Setzt ein neues Kennwort, um z.B. für Testzwecke das Kennwort
     * Entschlüsseln mit einem bekannten Kennwort auszuprobieren.
     * @param meinPasswort Das neue Kennwort
     */
    public void setMeinPasswort(String meinPasswort) {
        this.meinPasswort = new ValueWatcher<String>(meinPasswort);
    }

    /**
     * @return Liefert die Anzahl der bisherigen Rateversuche für das aktuelle Passwort.
     */
    public long anzahlRateversuche(){
        return meinPasswort.getCount();
    }
}

/**
 * Diese Klasse überwacht die Lesezugriffe auf das Passwort. Damit kann im Nachhinein ermittelt werden, wie oft auf
 * das Passwort zugeriffen werden musste, um es zu erraten.
 * @param <T>
 */
class ValueWatcher<T>{
    private T value;
    private long count;

    public ValueWatcher(T value){
        count = 0;
        this.value=value;
    }
    public T getValue(){
        count++;
        return value;
    }
    public long getCount(){
        return count;
    }
    public long setValueGetCount(T value){
        long currentCount = this.count;
        this.value = value;
        this.count = 0;
        return currentCount;
    }
}