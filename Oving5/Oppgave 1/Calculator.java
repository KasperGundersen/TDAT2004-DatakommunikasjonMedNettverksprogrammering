import java.text.ParseException;

public class Calculator {
    String regnestykke;

    public Calculator(String t) {
        regnestykke = t;
    }

    public int kalkuler() throws ParseException {
        try {
            String[] tab = regnestykke.split(" ");
            Integer tall1 = Integer.parseInt(tab[0]);
            Character operator = tab[1].charAt(0);
            Integer tall2 = Integer.parseInt(tab[2]);

            if (tall1 == null || operator == null || operator.equals("") || tall2 == null
                    || operator != '+' && operator != '-') {
                throw new ParseException("Ikke gyldig regneoperator", 1);
            }

            if (operator == '+') {
                return tall1 + tall2;
            }
            return tall1 - tall2;
        } catch (Exception e) {
            throw new ParseException("Ikke gyldig input", 1);
        }
    }

    public String toString() {
        try {
            return regnestykke + " = " + kalkuler();
        } catch (ParseException e) {
            return e.toString();
        }
    }
}