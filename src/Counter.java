import java.util.ArrayList;
import java.util.List;

public class Counter {
    private String userInput;
    private String[] words;
    private int lineCount = 0;
    private int wordCount = 0;
    private int charCount = 0;
    private ArrayList<String> longestWords = new ArrayList<>(List.of("you haven't written any words..."));
    private int longestWordChars = 0;

    public Counter() {
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public ArrayList<String> getLongestWords() {
        return longestWords;
    }

    public boolean countUntilStop(String input) {
        this.userInput = input;

        if (!(userInput.toUpperCase().contains("STOP"))) { //Om texten INTE innehåller ordet STOP.
            count();
            findLongestWord();
            return false;
        } else if (userInput.equalsIgnoreCase("STOP")) { //Om texten ENBART består av ordet STOP.
            return true;
        } else {
            removeStopBeforeCounting();
            count();
            findLongestWord();
            return true;
        }
    }

    public void removeStopBeforeCounting() {
        while (userInput.toUpperCase().contains("STOP")) { //Så länge texten innehåller ordet STOP.
            if (userInput.toUpperCase().endsWith("STOP")) {
                userInput = userInput.substring(0, userInput.length() - 4); //Ta bort ordet STOP innan beräkningar görs.
            } else {
                userInput = userInput.substring(0, userInput.length() - 1); //Ta bort tecken som finns efter STOP, t.ex. "STOPP" eller "stop!".
            }
            if (userInput.equalsIgnoreCase("STOP")) { //Om texten ENBART består av ordet STOP, efter att extra tecken har tagits bort.
                userInput = "";
                lineCount--;
            }
        }
    }

    public void count() {
        lineCount++; //Raden räknas även om den är tom.

        if (!userInput.isEmpty()) { //Kontrollera att textraden INTE är tom innan resterande beräkningar görs.
            charCount += userInput.length();
            words = userInput.split(" "); //Dela upp textraden i separata ord.
            wordCount += words.length;

            for (String word : words) { //Anta att det kan finnas extra mellanslag som resulterar i tomma ord: "".
                if (word.isEmpty()) {
                    wordCount--;
                }
            }
        }
    }

    public void findLongestWord() {
        if (words != null) { //Kontrollera först att denna array inte är tom.
            for (int i = 0; i < words.length; i++) {
                if (!words[i].isEmpty()) { //Om ordet inte är tomt: "".
                    boolean wordWithNoLetters = false;
                    int charNotLetterCount = 0;

                    for (int j = 0; j < words[i].length(); j++) { //Kontrollera om ordet INTE innehåller några bokstäver, t.ex. ":-)"
                        if (Character.isLetter(words[i].charAt(j))) {
                            break;
                        } else {
                            charNotLetterCount++;
                        }
                        if (charNotLetterCount == words[i].length()) {
                            wordWithNoLetters = true;
                        }
                    }
                    //Anta att det kan finnas skiljetecken i slutet (som inte ska räknas med i ordets längd).
                    while ((!Character.isLetter(words[i].charAt(words[i].length() - 1))) && (!wordWithNoLetters)) { //Fortsätt tills sista tecknet i ordet är en bokstav.
                        words[i] = words[i].substring(0, words[i].length() - 1); //Ta bort det sista tecknet i ordet om det inte är en bokstav.
                    }
                }
            }
            for (String word : words) {
                if (word.length() >= longestWordChars) { //Om ordet är längre eller lika långt som tidigare sparat ord.
                    if (word.length() > longestWordChars) { //Om ordet är längre än tidigare sparat ord eller om inget längsta ord har sparats än.
                        longestWords = new ArrayList<>(List.of(word.toLowerCase()));
                    } else { //Annars (om ordet är lika långt som tidigare sparat ord).
                        boolean wordAlreadySaved = false;

                        for (String longestWord : longestWords) {
                            if (word.equalsIgnoreCase(longestWord)) { //Om ordet redan finns sparat.
                                wordAlreadySaved = true;
                                break;
                            }
                        }
                        if (!wordAlreadySaved) { //Om ordet INTE redan finns sparat.
                            longestWords.add(word.toLowerCase());
                        }
                    }
                    longestWordChars = word.length();
                }
            }
        }
    }
}