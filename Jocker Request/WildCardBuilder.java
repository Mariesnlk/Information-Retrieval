import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class WildCardBuilder {

    public static WildCard createCollection(List<String> files) {

        WildCard wildCard = new WildCard(files);

        for (String fileName : files) {
            try {
                Parser parser = new Parser(fileName);
                List<String> wordList = parser.getWordList();
                for (String word : wordList) {
                    wildCard.generateRotations(word.toLowerCase());
                }
            } catch (IOException e){
                e.printStackTrace();
            } catch (SAXException e){
                e.printStackTrace();
            } catch (ParserConfigurationException e){
                e.printStackTrace();
            }
        }
        return wildCard;
    }

}