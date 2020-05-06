import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public class BiwordIndexReader{

    private BiwordIndexReader() {
    }

    public static BiwordIndex createBiwordIndex(List<String> files) {

        BiwordIndex biwordIndex = new BiwordIndex(files);

        for (String fileName : files) {
            try {
                Parser parser = new Parser(fileName);
                List<String> wordList = parser.getWordList();

                if (wordList.size() > 1) {
                    int i = 0;
                    do {
                        String phrase = wordList.get(i).toLowerCase() + " " + wordList.get(++i).toLowerCase();
                        biwordIndex.addPhrase(fileName, phrase);
                    } while (i < wordList.size() - 1);
                } else {
                    biwordIndex.addPhrase(fileName, wordList.get(0));
                }
                System.out.println("DONE " + fileName);

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }

        return biwordIndex;
    }

}