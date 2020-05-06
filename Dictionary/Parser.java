
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Parser {

    private SAXParserFactory saxParserFactory;
    private SAXParser saxParser;
    private FB2Handler fb2Handler;
    private List<String> words = new LinkedList<>();

    public Parser(String fileName) throws ParserConfigurationException, SAXException, IOException {
        Objects.requireNonNull(fileName, "File name must be not null");

        this.saxParserFactory = SAXParserFactory.newInstance();
        this.saxParser = saxParserFactory.newSAXParser();
        this.fb2Handler = new FB2Handler();
        saxParser.parse(fileName, fb2Handler);
    }

    public List<String> getWordList() {
        return words;
    }

    public class FB2Handler extends DefaultHandler {

        private String valueOfTheElement;

        @Override
        public void characters(char[] ch, int start, int length){
            valueOfTheElement = new String(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName){
            switch (qName) {
                case "p":
                    String[] splitWords = valueOfTheElement.split("\\W+");
                    words.addAll(Arrays.asList(splitWords));
                    break;
            }
        }
    }
}