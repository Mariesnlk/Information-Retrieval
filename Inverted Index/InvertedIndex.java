import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InvertedIndex {

    private List<String> files;
    private Map<String, Map<String, List<Integer>>> invertedIndex = new TreeMap<>();

    public InvertedIndex(List<String> files) {
        this.files = new ArrayList<>(files);
    }

    public Map<String, Map<String, List<Integer>>> getInvertedIndex() {
        return invertedIndex;
    }

    public List<String> getFiles() { return files; }

    public void add(String word, String fileName, Integer wordPosition) {
        Objects.requireNonNull(word);
        Objects.requireNonNull(fileName);

        Map<String, List<Integer>> document = invertedIndex.get(word);

        if (document == null) {
            document = new TreeMap<>();
        }
        List<Integer> position = document.get(fileName);

        if (position == null) {
            position = new LinkedList<>();
        }

        position.add(wordPosition);
        document.put(fileName, position);

        invertedIndex.put(word, document);
    }

    public static void write(InvertedIndex invertedIndex, String newFileName) {

        Set<String> treeSet = new TreeSet<>(invertedIndex.getInvertedIndex().keySet());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            for (int i = 0; i < invertedIndex.getFiles().size(); i++) {
                writer.write(i + " = " + new File(invertedIndex.getFiles().get(i)).getName() + System.lineSeparator());
            }

            for (String name : treeSet) {
                writer.write(System.lineSeparator());
                writer.write(name + System.lineSeparator());

                for (int i = 0; i < invertedIndex.getFiles().size(); i++) {
                    List<Integer> values = invertedIndex.getInvertedIndex().get(name).get(invertedIndex.getFiles().get(i));
                    if (values != null) {

                        writer.write(i + ": ");
                        for (Integer value : values) {
                            writer.write(value + ", ");
                        }
                        writer.write(System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static InvertedIndex createCollection(List<String> files) {

        InvertedIndex invertedIndex = new InvertedIndex(files);

        for (String fileName : files) {
            int wordPosition = 1;
            try {
                Parser parser = new Parser(fileName);
                List<String> wordList = parser.getWordList();
                for (String word : wordList) {
                    invertedIndex.add(word.toLowerCase(), fileName, wordPosition++);
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        }
        return invertedIndex;
    }
}