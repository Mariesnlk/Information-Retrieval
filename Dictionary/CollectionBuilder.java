import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CollectionBuilder {

    public Collection createCollection(List<String> files) {

        Collection collection = new Collection();

        Dictionary dictionary = new Dictionary();
        for (String fileName : files) {
            try {
                Parser bookParser = new Parser(fileName);
                List<String> wordList = bookParser.getWordList();
                for (String word : wordList) {
                    dictionary.addWordToTheDictionary(word);
                }
                File file = new File(fileName);
                collection.getCollectionItems().add(new CollectionItem(fileName, file.length(), wordList.size()));
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            catch (SAXException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        collection.setDictionary(dictionary);
        return collection;
    }
}