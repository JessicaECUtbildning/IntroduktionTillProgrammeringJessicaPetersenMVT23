import java.util.Scanner;

public class Main {
    /*
    Skriv ett program som läser in text ifrån kommandoraden rad för rad tills användaren skriver ordet stop.
    När användaren är klar skriver programmet ut antal tecken, hur många rader som användaren har skrivit
    (exklusive raden med ordet stop), antal ord (separerade med blanksteg) samt det längsta ordet.

    Programmet skall bestå av två klasser:
     ❖ En klass som läser in text och skriver ut resultatet till användaren
     ❖ En annan klass som:
          ❖ Räknar raderna, antal tecken och antal ord
          ❖ Har koll på det längsta ordet
          ❖ Har koll på om användaren har skrivit ordet stop eller inte
    */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Counter counter = new Counter();
        boolean stopEntered = false;

        System.out.println("Write any text and I will count the number of " +
                "characters/words/lines and find the longest word for you. " +
                "Write STOP to finish and see the result.");
        while (!stopEntered) { //Så länge användaren INTE har skrivit ordet STOP
            stopEntered = counter.countUntilStop(scan.nextLine());
        }
        System.out.println("\nYour text, up to the word STOP, contains:");
        System.out.println("Characters: " + counter.getCharCount());
        System.out.println("Words: " + counter.getWordCount());
        System.out.println("Lines: " + counter.getLineCount());
        System.out.println("Longest word/words: " + counter.getLongestWords().toString());
    }
}