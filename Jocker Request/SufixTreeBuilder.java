import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class SufixTreeBuilder {

    private SufixTreeBuilder() {
    }

    public static SuffixTree createSuffixTree(List<String> files) {

        SuffixTree tree = new SuffixTree();

        for (String fileName : files) {
            try {

                Parser parser = new Parser(fileName);
                List<String> words = parser.getWordList();
                for (String word : words) {
                    tree.addWord(word.toLowerCase());
                }

            } catch (IOException e){
                e.printStackTrace();
            } catch (SAXException e){
                e.printStackTrace();
            } catch (ParserConfigurationException e){
                e.printStackTrace();
            }

        }

        return tree;


    }
}