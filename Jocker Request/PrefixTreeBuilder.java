import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class PrefixTreeBuilder {

    private PrefixTreeBuilder() {
    }

    public static PrefixTree prefixBuilder(List<String> files) {

        PrefixTree tree = new PrefixTree();

        for (String fileName : files) {
            try {

                Parser parser = new Parser(fileName);
                List<String> words = parser.getWordList();
                for (String word : words) {
                    tree.addWord(word.toLowerCase());
                }

            }  catch (IOException e){
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