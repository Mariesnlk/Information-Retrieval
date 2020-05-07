import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class ThreeGrammarIndexBuilder {

        public ThreeGrammarIndexBuilder() { }

        public static ThreeGrammarIndex createCollection(List<String> files) {

            ThreeGrammarIndex threeGramIndex = new ThreeGrammarIndex(files);

            for (String fileName : files) {
                try {
                    Parser parser = new Parser(fileName);
                    List<String> wordList = parser.getWordList();
                    for (String word : wordList) {
                        threeGramIndex.addWord(word.toLowerCase());
                    }

                } catch (ParserConfigurationException | SAXException | IOException e) {
                    e.printStackTrace();
                }

            }

            return threeGramIndex;
        }

    }