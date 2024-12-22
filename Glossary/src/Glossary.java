import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into
 * multiple linked HTML output files organized as a glossary.
 *
 * @author Aryahi Nittur
 *
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * Creates index.html.
     *
     * @param sortedKeys
     *            the array of terms sorted alphabetically
     * @param out
     *            the SimpleWriter object to write the HTML content
     * @param folder
     *            the folder name that contains the files
     * @param termsMap
     *            the map containing term definitions (term as key, definition
     *            as value)
     * @requires sortedKeys is not null, out is open for writing, and termsMap
     *           is not null
     * @ensures an alphabetically organized HTML page listing terms
     *          alphabetically is written to out
     */
    public static void indexHTML(String[] sortedKeys, String folder, SimpleWriter out,
            Map<String, String> termsMap) {

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Glossary</title>");

        //css

        out.println("<style>");
        out.println("body {");
        out.println("    font-family: 'Arial', sans-serif;");
        out.println("    background-color: #f4f4f9;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("    line-height: 1.6;");
        out.println("    color: #333;");
        out.println("}");
        out.println("h2, h3 {");
        out.println("    text-align: center;");
        out.println("    color: #4CAF50;");
        out.println("}");
        out.println("h2 {");
        out.println("    font-size: 2.5rem;");
        out.println("    margin-top: 1rem;");
        out.println("}");
        out.println("h3 {");
        out.println("    font-size: 1.8rem;");
        out.println("    margin-top: 0.5rem;");
        out.println("}");
        out.println("hr {");
        out.println("    border: 0;");
        out.println("    height: 3px;");
        out.println("    background: linear-gradient(to right, #4CAF50, #00BFFF);");
        out.println("    margin: 20px auto;");
        out.println("    width: 80%;");
        out.println("}");
        out.println("main {");
        out.println("    max-width: 800px;");
        out.println("    background-color: #fff;");
        out.println("    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);");
        out.println("    padding: 20px;");
        out.println("    border-radius: 8px;");
        out.println("    margin: 0 auto;");
        out.println("}");
        out.println("ul {");
        out.println("    list-style: none;");
        out.println("    padding: 0;");
        out.println("    margin: 20px auto;");
        out.println("}");
        out.println("li {");
        out.println("    margin: 10px 0;");
        out.println("    text-align: center;");
        out.println("}");
        out.println("a {");
        out.println("    text-decoration: none;");
        out.println("    font-size: 1.2rem;");
        out.println("    font-weight: bold;");
        out.println("    color: #007BFF;");
        out.println("    transition: color 0.3s ease, text-shadow 0.3s ease;");
        out.println("}");
        out.println("a:hover {");
        out.println("    color: #0056b3;");
        out.println("    text-shadow: 0 0 5px #007BFF;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Sample Glossary</h2>");
        out.println("<hr>");
        out.println("<main>");
        out.println("<h3>Index</h3>");
        out.println("<ul>");

        for (int i = 0; i < sortedKeys.length; i++) {
            out.println("<li>");

            out.println(
                    "<a href=\"" + sortedKeys[i] + ".html\">" + sortedKeys[i] + "</a>");

            out.println("</li>");

            //create html page for the current term

            termHTML(sortedKeys[i], folder, termsMap);

        }

        out.println("</ul>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");

        out.close();

    }

    /**
     * Creates sortedKey.html.
     *
     * @param sortedKey
     *            source of terms, out (sortedKey.html) is the file it writes to
     * @param folder
     *            the folder name that contains the files
     * @param termsMap
     *            the map containing term definitions (term as key, definition
     *            as value)
     * @requires sortedKey is not an empty string
     * @ensures a html page with strings listed alphabetically
     *
     */
    public static void termHTML(String sortedKey, String folder,
            Map<String, String> termsMap) {

        SimpleWriter out = new SimpleWriter1L(folder + "/" + sortedKey + ".html");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + sortedKey + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>");
        out.println("<b>");
        out.println("<i>");
        out.println("<font color=\"red\">" + sortedKey + "</font>");
        out.println("</i>");
        out.println("</b>");
        out.println("</h2>");
        out.println("<blockquote>");

        // use word seperator take in string definition,
        // send out array of words to print (includes .html)

        String[] definitionStrings = definitionStrings(sortedKey, termsMap);

        for (int i = 0; i < definitionStrings.length; i++) {
            out.println(definitionStrings[i]);
        }

        out.println("</blockquote>");
        out.println("<hr>");
        out.println("<main>");
        out.println("<p>");
        out.println("Return to <a href=\"index.html\">index</a>.");
        out.println("</p>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");

        out.close();

    }

    /**
     * Creates array of strings to write to file easier.
     *
     * @param sortedKey
     *            source of terms
     * @param termsMap
     *            the map containing term definitions (term as key, definition
     *            as value)
     * @return void
     * @requires sortedKey is not an empty string
     * @ensures a array of strings to write to file easier.
     *
     */
    public static String[] definitionStrings(String sortedKey,
            Map<String, String> termsMap) {

        String definition = termsMap.value(sortedKey);

        Queue<String> storeStrings = new Queue1L<>();

        int positionTracker = 0;

        for (int i = 0; i < definition.length(); i++) {
            if (definition.charAt(i) == ' ') {

                storeStrings.enqueue(definition.substring(positionTracker, i));

                positionTracker = i + 1;
            }

        }

        storeStrings.enqueue(definition.substring(positionTracker, definition.length()));

        String[] definitionStrings = new String[storeStrings.length()];

        int indexOfArray = 0;

        while (storeStrings.length() > 0) {
            String wordDequeued = storeStrings.dequeue();
            // you have pool.

            String punctuation = "";
            String noPuncWord = wordDequeued;

            if (wordDequeued.charAt(wordDequeued.length() - 1) == ','
                    || wordDequeued.charAt(wordDequeued.length() - 1) == ';'
                    || wordDequeued.charAt(wordDequeued.length() - 1) == '!') {
                punctuation = wordDequeued.substring(wordDequeued.length() - 1);

                noPuncWord = wordDequeued.substring(0, wordDequeued.length() - 1);

            }

            // Initialize the string to be added to definitionStrings
            String addedString = "";

            // Check if the stripped word is in the map
            if (termsMap.hasKey(noPuncWord)) {
                // Link the stripped word
                String temp = "<a href=\"" + noPuncWord + ".html\">" + noPuncWord
                        + "</a>";

                // Append punctuation if present
                if (punctuation.length() != 0) {
                    addedString = temp.concat(punctuation);
                } else {
                    addedString = temp;
                }
            } else {
                // Otherwise, keep the word/punctuation as is
                addedString = wordDequeued;
            }

            // Add the output to the definitionStrings array
            definitionStrings[indexOfArray] = addedString;
            indexOfArray++;
        }

        return definitionStrings;
    }

    /**
     * Returns a map of terms and definitions from file.
     *
     * @param input
     *            source of strings, one per line
     * @return map of lines read from {@code input}
     * @requires input.is_open
     * @ensures <pre>
     * input.is_open  and  input.content = <>  and
     * map of lines from #input.content
     * </pre>
     */
    public static Map<String, String> termsProcesser(SimpleReader input) {

        Map<String, String> termsMap = new Map1L<>();

        // Process input file line by line
        // While not at the end of the file
        while (!input.atEOS()) {
            // Read a term
            String term = input.nextLine();

            // if term does not have a space in it
            if (!term.contains(" ")) {

                String word = term; // its a word

                Queue<String> definitions = new Queue1L<String>();
                // if we're not at the end of the stream
                boolean definitionStillThere = true;

                // while there is still a definition b/c some definitions
                // go on for multiple lines
                // and while its not at the end of the file
                while (definitionStillThere && !input.atEOS()) {

                    String possibleDef = input.nextLine().trim();

                    if (possibleDef.equals("") || !possibleDef.contains(" ")) {
                        // break while loop if there is a blank line
                        // if definition does not contain a space its a word
                        definitionStillThere = false;
                    } else {
                        definitions.enqueue(possibleDef);
                    }

                }

                String definition = "";

                // merge all the definition lines

                while (definitions.length() != 0) {
                    String fullDefinition = definition
                            .concat(definitions.dequeue() + " ");

                    definition = fullDefinition;
                }

                // Add the term and definition pair to the map
                termsMap.add(word, definition.trim());
            }
        }
        input.close();
        // Return the map containing terms and definitions
        return termsMap;

    }

    /**
     * Returns an array of alphabetized terms.
     *
     * @param termsMap
     *            source of strings, one per line
     * @return array of aplhabetized words
     * @requires termsMap is a Map<String, String>
     * @ensures map is in resores mode, an array of alphabetized terms.
     *
     */
    public static String[] alphabetizedKeys(Map<String, String> termsMap) {
        // slaps the map's terms into an array, then alphabetizes it using the .sort
        // then it spits out the terms in a sorted array

        Map<String, String> tempTermsMap = new Map1L<>();
        tempTermsMap.transferFrom(termsMap);

        // create a queue to store it

        Queue<String> key = new Queue1L<String>();

        //from the slides safe way to modify map

        while (tempTermsMap.size() > 0) {
            Map.Pair<String, String> p = tempTermsMap.removeAny();
            termsMap.add(p.key(), p.value());
            key.enqueue(p.key());
        }

        String[] alphabetizedTerms = new String[key.length()];

        int indexOfArray = 0;

        while (key.length() > 0) {
            alphabetizedTerms[indexOfArray] = key.dequeue();
            indexOfArray++;
        }

        java.util.Arrays.sort(alphabetizedTerms);

        return alphabetizedTerms;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // single point of control is here

        out.println("Enter your folder name: ");
        String folder = in.nextLine();

        out.println("Enter your filename: ");
        String line = folder + "/" + in.nextLine();

        SimpleReader inputFile = new SimpleReader1L(line);

        // creates main map
        Map<String, String> termsMap = termsProcesser(inputFile);

        SimpleWriter index = new SimpleWriter1L(folder + "/index.html");

        // creates html pages
        indexHTML(alphabetizedKeys(termsMap), folder, index, termsMap);

        // simple tests
        /*
         * Map<String, String> termsMap = termsProcesser(inputFile);
         *
         * for (Map.Pair<String, String> p : termsMap) { out.println(p.key() +
         * ": " + p.value()); }
         *
         * // alphabetizedKeys test
         *
         * String[] alphabetizedTerms = alphabetizedKeys(termsMap);
         *
         * for (int i = 0; i < alphabetizedTerms.length; i++) {
         * out.println(alphabetizedTerms[i]); }
         *
         * // indexHTML test
         *
         * SimpleWriter homepage = new SimpleWriter1L("index.html");
         *
         * indexHTML(alphabetizedTerms, homepage);
         *
         *
         */

        in.close();
        out.close();
    }

}
