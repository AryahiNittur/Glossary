import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class GlossaryTest {

    // definition strings test

    @Test
    public void definitionStrings_onlyKeys() {
        // Initialize the map with test data
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("key1", "word1 word2");
        termsMap.add("word1", "definition for word1");
        termsMap.add("word2", "definition for word2");

        // Call the method
        String[] result = Glossary.definitionStrings("key1", termsMap);

        String[] expected = { "<a href=\"word1.html\">word1</a>",
                "<a href=\"word2.html\">word2</a>" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    @Test
    public void definitionStrings_regular() {
        // Initialize the map with test data
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("key1", "word1 and word2 are great");
        termsMap.add("word1", "definition for word1");
        termsMap.add("word2", "definition for word2");

        // Call the method
        String[] result = Glossary.definitionStrings("key1", termsMap);

        String[] expected = { "<a href=\"word1.html\">word1</a>", "and",
                "<a href=\"word2.html\">word2</a>", "are", "great" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    @Test
    public void definitionStrings_singleWord() {
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("key1", "word1");
        termsMap.add("word1", "definition for word1");

        String[] result = Glossary.definitionStrings("key1", termsMap);
        String[] expected = { "<a href=\"word1.html\">word1</a>" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    @Test
    public void definitionStrings_withPunctuation() {
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("key1", "word1, word2!");
        termsMap.add("word1", "definition for word1");
        termsMap.add("word2", "definition for word2");

        String[] result = Glossary.definitionStrings("key1", termsMap);
        String[] expected = { "<a href=\"word1.html\">word1</a>,",
                "<a href=\"word2.html\">word2</a>!" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    // alphabetizedKeys

    @Test
    public void testAlphabetizedKeys_basic() {
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("banana", "a fruit");
        termsMap.add("apple", "another fruit");
        termsMap.add("cherry", "yet another fruit");

        String[] result = Glossary.alphabetizedKeys(termsMap);
        String[] expected = { "apple", "banana", "cherry" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    @Test
    public void testAlphabetizedKeys_emptyMap() {
        Map<String, String> termsMap = new Map1L<>();

        String[] result = Glossary.alphabetizedKeys(termsMap);
        String[] expected = {};

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    @Test
    public void testAlphabetizedKeys_singleEntry() {
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("key1", "value1");

        String[] result = Glossary.alphabetizedKeys(termsMap);
        String[] expected = { "key1" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    @Test
    public void testAlphabetizedKeys_alreadySorted() {
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("apple", "a fruit");
        termsMap.add("banana", "a fruit");
        termsMap.add("cherry", "a fruit");

        String[] result = Glossary.alphabetizedKeys(termsMap);
        String[] expected = { "apple", "banana", "cherry" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    @Test
    public void testAlphabetizedKeys_mixedCase() {
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("Banana", "a fruit");
        termsMap.add("apple", "another fruit");
        termsMap.add("Cherry", "yet another fruit");

        String[] result = Glossary.alphabetizedKeys(termsMap);
        String[] expected = { "Banana", "Cherry", "apple" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    @Test
    public void testAlphabetizedKeys_specialCharacters() {
        Map<String, String> termsMap = new Map1L<>();
        termsMap.add("apple", "a fruit");
        termsMap.add("banana-2", "another fruit");
        termsMap.add("cherry_1", "yet another fruit");

        String[] result = Glossary.alphabetizedKeys(termsMap);
        String[] expected = { "apple", "banana-2", "cherry_1" };

        boolean allGood = true;
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                allGood = false;
            }
        }

        assertEquals(true, allGood);
    }

    // tests for terms processer

    @Test
    public void testTermsProcessor_multipleTermsSingleLine() {
        SimpleReader input = new SimpleReader1L("data/testTerms1.txt");

        Map<String, String> result = Glossary.termsProcesser(input);

        assertEquals(2, result.size());
        assertEquals("A sweet red fruit", result.value("apple"));
        assertEquals("A yellow tropical fruit", result.value("banana"));
    }

    @Test
    public void testTermsProcessor_singleTermMultiLineDefinition() {
        SimpleReader input = new SimpleReader1L("data/testTerms2.txt");

        Map<String, String> result = Glossary.termsProcesser(input);

        assertEquals(1, result.size());
        assertEquals("A sweet red fruit grown in orchards", result.value("apple"));
    }

    @Test
    public void testTermsProcessor_multipleTermsMultipleLines() {
        SimpleReader input = new SimpleReader1L("data/terms.txt");

        Map<String, String> result = Glossary.termsProcesser(input);

        // Assert that the size of the result map matches the number of terms
        assertEquals(7, result.size());

        // Validate each term and its corresponding definition
        assertEquals("something that one wishes to convey, especially by language",
                result.value("meaning"));
        assertEquals("a word whose definition is in a glossary", result.value("term"));
        assertEquals(
                "a string of characters in a language, which has at least one character",
                result.value("word"));
        assertEquals("a sequence of words that gives meaning to a term",
                result.value("definition"));
        assertEquals(
                "a list of difficult or specialized terms, with their definitions, usually near the end of a book",
                result.value("glossary"));
        assertEquals("a set of strings of characters, each of which has meaning",
                result.value("language"));
        assertEquals("a printed or written literary work", result.value("book"));

    }

    /**
     * Reads a file into a queue of lines.
     *
     * @param fileReader
     *            SimpleReader object to read the file
     * @return a Queue containing lines of the file
     */
    private static Queue<String> readFileToQueue(SimpleReader fileReader) {
        Queue<String> lines = new Queue1L<>();

        while (!fileReader.atEOS()) {
            String line = fileReader.nextLine();
            lines.enqueue(line); // Add each line to the queue
        }

        return lines;
    }
}
