package NumberGame;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CodePage {
    static int[] generateNumberArr = new int[4];

    public static void main(String[] args) {

        loginPage();
        generateNumbers();

    }

    public static void generateNumbers() {
        int test = 0;
        int min = 1000;
        int max = 9999;
        Random rnd = new Random();
        do {
            int number = rnd.nextInt(9999 - 1000) + 1000;
            for (int i = 3; i >= 0; i--) {
                generateNumberArr[i] = number % 10;//birlerbasamagını burada alıyorum. bu birler basamağının benim arrayimdeki 3. sayi olması gerekiyor o yüzden burayı 3ten balattım
                number /= 10;
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (generateNumberArr[i] == generateNumberArr[j]) {
                        test++; //4
                    }

                }

            }
            if (test == 4) {
                break;
            } else {
                test = 0;
            }

        } while (test < 4);
        System.out.println(Arrays.toString(generateNumberArr));
    }

    static Scanner scan = new Scanner(System.in);

    public static void loginPage() {
        System.out.println("Press 1 for explanation, press 2 for play.");
        char login = scan.nextLine().charAt(0); //tek bir karakter oldugu icin char ile alıyoruz., 0 ıncı indexi almak icin 0 girdik kullanici iki karakter girse bile sadece ilk karakteri alacağız

        switch (login) {
            case '1':
                giveExplanation();
                generateNumbers();
                numberControl();
                break;
            case '2':
                generateNumbers();
                numberControl();
                break;
            default:
                System.out.println("You entered the wrong character");
                loginPage();

        }

    }

    private static void numberControl() {
        System.out.println("THE GAME BEGINS");
        int[] enteredNumberArr = new int[4];
        int addt;
        int subst;
        int totalknown;
        int howManyTimes = 0;
        int test;


        do {
            addt = 0;
            subst = 0;
            totalknown = 0;
            test = 0;
            System.out.println("Please enter a different 4-digit number.");
            int enteredNumber = scan.nextInt();
            enteredNumberArr[3] = enteredNumber % 10;
            enteredNumberArr[2] = (enteredNumber / 10) % 10;
            enteredNumberArr[1] = (enteredNumber / 100) % 10;
            enteredNumberArr[0] = enteredNumber / 1000;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (enteredNumberArr[i] == enteredNumberArr[j]) {
                        test++;
                    }

                }

            }

            if (enteredNumber < 1000 || enteredNumber > 9999) {
                System.out.println("The entered number " + enteredNumber + " is not four digits. ");

            } else if (test > 4) {
                System.out.println(" The entered number " + enteredNumber + " contains the same digit.");
            } else {
                for (int i = 0; i < 4; i++) {//burada bilgisayarin random sayi ile kullanicinin girdigi sayilar arasında kac tane aynı rakamdan var.
                    for (int j = 0; j < 4; j++) {
                        if (enteredNumberArr[i] == generateNumberArr[j]) {
                            totalknown++;

                        }

                    }
                }
                    for (int j = 0; j < 4; j++) {// 1578 1698
                        if (enteredNumberArr[j] == generateNumberArr[j]) {//burada indexleri kontrol edeceğim.
                            addt++;


                        }

                    }
                    subst = totalknown - addt;
                    howManyTimes++;
                    if (addt == 4) {
                        System.out.println("your number: " + Arrays.toString(enteredNumberArr));
                        System.out.println("+ " + addt + " Congratulations,  " + howManyTimes + " .you found it in your try.");
                        System.out.println(" number held: " + Arrays.toString(generateNumberArr));

                    } else {
                        System.out.println("+ " + addt + " , - " + subst);
                    }

                }



            }
            while (addt < 4) ;
        }


        public static void giveExplanation () {
            int n;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("src/StudentProje_sayiBulmaOyunu/Explanation");
                while ((n = fis.read()) != -1) {
                    System.out.print((char) n);

                }
            } catch (FileNotFoundException e) {
                System.out.println("this file not found");

            } catch (IOException e) {
                System.out.println("Could not get information from file");

            }
            System.out.println(" ");
        }
    }



