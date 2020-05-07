
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class BlockReader {

    private File[] blocksFiles;
    private BlockingQueue<String[]> queue;

    public BlockReader(File[] blocksFiles, BlockingQueue<String[]> queue) {
        this.queue = queue;
        this.blocksFiles = blocksFiles;
    }

    public void readBlocks() {

        for (File textFile : blocksFiles) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
                Parser parser = new Parser(textFile.getPath());
                List<String> wordList = parser.getWordList();
                String text = "";

                StringBuilder textBuilder = new StringBuilder(text);
                for (String word : wordList) {
                    textBuilder.append(" ").append(word);
                }

                text = textBuilder.toString();

                String[] document = new String[2];
                document[0] = textFile.getName();
                document[1] = text;

                queue.add(document);
                bufferedReader.close();


            } catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        }

    }
}