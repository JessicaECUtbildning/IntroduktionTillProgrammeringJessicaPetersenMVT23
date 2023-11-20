import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRunner {
    //Minst tre Junit-testfall ska skrivas
    //Testfallen skall skilja sig märkbart åt och testa olika delar av programmet
    @Test
    public void testStopEntered() {
        //Arrange
        Counter counter = new Counter();
        boolean expected = true;
        //Act
        boolean actual = counter.countUntilStop("stop");
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testStopAtEndOfLine() {
        Counter counter = new Counter();
        boolean expected = true;
        boolean actual = counter.countUntilStop("This is my last sentence. stop");
        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleStopEntered() {
        Counter counter = new Counter();
        int expected = 0;
        counter.countUntilStop("STOP, stop, Stop!");
        int actual = counter.getCharCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testCountChars() {
        Counter counter = new Counter();
        int expected = 29;
        counter.countUntilStop("Let's count these characters.");
        int actual = counter.getCharCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testCountWords() {
        Counter counter = new Counter();
        int expected = 4;
        counter.countUntilStop("Let's count these words.");
        int actual = counter.getWordCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testCountCharsExcludingStop() {
        Counter counter = new Counter();
        int expected = 29;
        counter.countUntilStop("Let's count these characters.STOP!");
        int actual = counter.getCharCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testCountWordsExcludingStop() {
        Counter counter = new Counter();
        int expected = 6;
        counter.countUntilStop("Let's count these words until I stop...");
        int actual = counter.getWordCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testFindLongestWord() {
        Counter counter = new Counter();
        String expected = "[longest]";
        counter.countUntilStop("Let's find the longest word.");
        String actual = counter.getLongestWords().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleLongestWord() {
        Counter counter = new Counter();
        String expected = "[longest, biggest, coolest]";
        counter.countUntilStop("Let's find the longest and the biggest and the coolest words.");
        String actual = counter.getLongestWords().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testIdenticalLongestWord() {
        Counter counter = new Counter();
        String expected = "[longest, coolest]";
        counter.countUntilStop("Let's find the LONGEST, COOLEST and the longest and coolest words.");
        String actual = counter.getLongestWords().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testExcludePunctuationMark() {
        Counter counter = new Counter();
        String expected = "[longest]";
        counter.countUntilStop("This word is the longest!?! :-)");
        String actual = counter.getLongestWords().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testIgnoreExtraWhitespaces() {
        Counter counter = new Counter();
        int expected = 11;
        counter.countUntilStop("Let's find the   longest   and the coolest  and the  longest words.");
        int actual = counter.getWordCount();
        assertEquals(expected, actual);
    }
}