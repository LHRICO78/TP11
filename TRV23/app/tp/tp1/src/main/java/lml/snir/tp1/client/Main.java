package lml.snir.tp1.client;

/**
 *
 * @author fanou
 */
public class Main {

    private String msg = "Bonjour je suis étudiant en BTS CIEL IR session 2025 !";

    public static void main(String[] args) {
        Main main = new Main();
        main.q6();
        System.out.println("20°C = " + main.celsiusToFahrenheit(20)+"°F");
    }

    private void q1(int count) {
        System.out.println(this.msg + count);
    }

    private void q3() {
        for (int i = 0; i < 10; i++) {
            this.q1(i);
        }
    }

    private void q5() {
        int i = 0;
        while (i < 10) {
            this.q1(i);
            i++;
        }
    }

    private void q6() {
        int nbCaracteres = this.msg.length();
        int nbLettres = 0;
        int nbVoyelles = 0;
        int nbConsonnes = 0;
        int nbChiffres = 0;
        int nbAutres = 0;
        System.out.println(this.msg);
        for (int i = 0; i < nbCaracteres; i++) {
            char ch = this.msg.charAt(i);

            if (Character.isAlphabetic(ch)) {
                nbLettres++;
                ch = Character.toLowerCase(ch);
                if (ch == 'a' | ch == 'à' | ch == 'e' | ch == 'i' | ch == 'o' | ch == 'u' | ch == 'y' | ch == 'é' | ch == 'è') {
                    nbVoyelles++;
                } else {
                   nbConsonnes++;
                }
            }

            if (Character.isDigit(ch)) {
                nbChiffres++;
            }

            if (!Character.isAlphabetic(ch) & !Character.isDigit(ch)) {
                nbAutres++;
            }

        }

        System.out.println("nbCaracteres " + nbCaracteres);
        System.out.println("nbLettres " + nbLettres);
        System.out.println("nbVoyelles " + nbVoyelles);
        System.out.println("nbConsonnes " + nbConsonnes);
        System.out.println("nbChiffres " + nbChiffres);
        System.out.println("nbAutres " + nbAutres);

    }
    
    private double celsiusToFahrenheit(double temperature) {
        return (9.0/5.0) * temperature + 32.0;
    }
}
