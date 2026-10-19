package Homeworks.hw1;

public class Main {

    public static void main(String[] args) {
//      1. Przepisz dane wejściowe dokładnie tak, ze spacjami:
        String rawShop = "  kurs market  ";
        String rawCustomer = "  anna   nowak  ";
        String rawEmail = " Anna.Nowak@Example.COM ";
        String rawCode1 = "  spoz-0599  ";
        String rawCode2 = "spoz-0429";
        String rawCode3 = "  kawa-3999";
        char register = 'A';
        int receiptNumber = 0;


//      2. Zrób porządek w kodach: bez spacji, wielkimi literami.
//      Z każdego kodu wyciągnij CENĘ — to liczba po myślniku, w groszach.
//      Pozycji myślnika nie wpisuj z palca, znajdź ją w tekście.
//      Tekst na liczbę zamienia Integer.parseInt

        String formattedShop = formatText(rawShop);
        String formattedCustomer = formatText(rawCustomer);
        String formattedEmail = formatText(rawEmail);
        String formattedCode1 = formatText(rawCode1);
        String formattedCode2 = formatText(rawCode2);
        String formattedCode3 = formatText(rawCode3);


        int price1 = getPriceFromString(formattedCode1);
        int price2 = getPriceFromString(formattedCode2);
        int price3 = getPriceFromString(formattedCode3);

//      3. Dopisz nazwy i ilości:
//      "Chleb razowy"   x2
//      "Mleko 3,2%"     x3
//      "Kawa ziarnista" x1

        String product1 = "Chleb razowy";
        int quantity1 = 2;
        String product2 = "Mleko 3,2%";
        int quantity2 = 3;
        String product3 = "Kawa ziarnista";
        int quantity3 = 1;
//
//      4.Zbuduj dane klienta:
//      customerName  "Anna Nowak"   (jedna spacja, każde słowo z wielkiej litery)
//      loyaltyCard   "AN-0599"      (inicjały + "-" + numer z pierwszego kodu)
//      email         bez spacji, małymi literami
//      maskedEmail   "an***@example.com"

        String[] names = formattedCustomer.split(" ");

        String customerName = names[0].substring(0, 1) + names[0].substring(1).toLowerCase() +
                " " + names[1].substring(0, 1) + names[1].substring(1).toLowerCase();
        String loyaltyCard = names[0].substring(0, 1) + names[1].substring(0, 1) + "-" + String.format("%04d", price1);
        String email = formattedEmail.toLowerCase();

        String username = email.substring(0, email.indexOf('@'));
        String domain = email.substring(email.indexOf('@'));
        String maskedEmail = username.substring(0, 2) + "***" + domain;

//      5. Wypisz nagłówek paragonu — dwie linie:
//      KURS MARKET · kasa A · paragon 1
//      Anna Nowak · karta AN-0599 · an***@example.com
//      Nazwa sklepu ma być zrobiona z rawShop, a numer paragonu ma wzrosnąć
//      PRZED wypisaniem, inkrementacją w tej samej linii.
        System.out.printf("%s · kasa %c · paragon %d%n",
                formattedShop, register, ++receiptNumber);

        System.out.printf("%s · karta %s · %s%n",
                customerName, loyaltyCard, maskedEmail);


//      %s -> String
//      %d -> int / long
//      %f -> float / double
//      %c -> char
//      %b -> bolean
//      %n -> new line
//      %% -> %
//      %10s  -> String min. width 10, aligned to right
//      %-10s  -> String min. width 10, aligned to left
//      %05d  -> fill with '0' min. width 5 aligned to right
//      %.2f -> two decimal places
//
//
//
//      6.Wypisz wiersze paragonu przez printf — nazwa, kod, ilość, cena, wartość:
//      Chleb razowy     SPOZ-0599   2 x    5,99 =     11,98 zł

        int itemCounter = 0;
        System.out.printf("%-17s %-12s %2d x %7.2f = %8.2f zł%n",
                product1, formattedCode1, quantity1, price1 / 100.0, (price1 * quantity1) / 100.0);
        itemCounter++;
        System.out.printf("%-17s %-12s %2d x %7.2f = %8.2f zł%n",
                product2, formattedCode2, quantity2, price2 / 100.0, (price2 * quantity2) / 100.0);
        itemCounter++;
        System.out.printf("%-17s %-12s %2d x %7.2f = %8.2f zł%n",
                product3, formattedCode3, quantity3, price3 / 100.0, (price3 * quantity3) / 100.0);
        itemCounter++;

//      7. Policz i wypisz, każde w osobnej linii i w jednej kolumnie:
//      RAZEM NETTO    suma wartości pozycji
//      VAT 23%        od sumy netto, przez Math.round
//      RAZEM BRUTTO
//      RABAT 7%       od sumy brutto, przez Math.round
//      DO ZAPŁATY

        int totalNetto = price1 * quantity1 + price2 * quantity2 + price3 * quantity3;
        int vat = (int) Math.round(totalNetto * 0.23);
        int totalBrutto = totalNetto + vat;
        int discount = (int) Math.round(totalBrutto * 0.07);
        int toPay = totalBrutto - discount;


        System.out.printf("%-41s %12s%n", "RAZEM NETTO", formatToZl(totalNetto));
        System.out.printf("%-41s %12s%n", "VAT 23%", formatToZl(vat));
        System.out.printf("%-41s %12s%n", "RAZEM BRUTTO", formatToZl(totalBrutto));
        System.out.printf("%-41s %12s%n", "RABAT 7%", formatToZl(discount));
        System.out.printf("%-41s %12s%n", "DO ZAPŁATY", formatToZl(toPay));
//        8. Płatność: int paidInCents = 10000;
//        Wypisz GOTÓWKA i RESZTA, a potem iloma monetami 5-złotowymi da się
//        wydać resztę i ile groszy zostanie poza nimi.

        int paidInCents = 10000;
        int change = paidInCents - toPay;
        int coins5zl = change / 500;
        int remainingCents = change % 500;

        System.out.printf("%-41s %12s%n", "GOTÓWKA", formatToZl(paidInCents));
        System.out.printf("%-41s %12s%n", "RESZTA", formatToZl(change));

        System.out.printf("monet 5 zł: %d, drobne poza nimi: %d gr%n",
                coins5zl, remainingCents);

//        9. Stopka:
//        - licznik pozycji zwiększany inkrementacją, raz na produkt
//        - zbuduj przez String.format tekst:
//        KURS MARKET · 3 pozycji · do zapłaty 74,17 zł
//                - wypisz pożegnanie z samym imieniem klienta i literą następnej kasy:
//        Dziękujemy, Anna · następna kasa B



        String footer = String.format(
                "%s · %d pozycji · do zapłaty %s",
                formattedShop,
                itemCounter,
                formatToZl(toPay)
        );

        System.out.println(footer);
        char nextRegister = (char) (register + 1);

        System.out.printf("Dziękujemy, %s · następna kasa %c%n",
                names[0].substring(0, 1) + names[0].substring(1).toLowerCase(), nextRegister);


    }


    public static String formatText(String str) {
        return str.trim().toUpperCase().replaceAll("\\s+", " ");
    }

    public static int getPriceFromString(String str) {
        return Integer.parseInt(str.substring(str.indexOf("-") + 1));
    }

    static String formatToZl(int cents) {
        return String.format(
                "%,.2f zł",
                cents / 100.0
        );
    }

}

