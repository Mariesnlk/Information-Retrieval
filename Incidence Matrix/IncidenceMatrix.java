import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class IncidenceMatrix {

    private List<String> files;
    private Map<String, List<Boolean>> matrix = new HashMap<>();

    public IncidenceMatrix(List<String> files) {
        this.files = new ArrayList<>(files);
    }

    public IncidenceMatrix() {}

    public Map<String, List<Boolean>> getMatrix() {
        return matrix;
    }

    public List<String> getFiles() {
        return files;
    }

    public void add(String word, String fileName) {
        Objects.requireNonNull(word);
        Objects.requireNonNull(fileName);

        List<Boolean> column = matrix.get(word);
        if (column == null) {

            column = initializeMatrix();
            matrix.put(word, column);
        }

        column.set(files.indexOf(fileName), true);
    }

    private List<Boolean> initializeMatrix() {
        List<Boolean> matrixRow = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            matrixRow.add(false);
        }
        return matrixRow;
    }

    public static IncidenceMatrix createCollection(List<String> files) {

        IncidenceMatrix matrix = new IncidenceMatrix(files);

        for (String fileName : files) {
            try {
                Parser parser = new Parser(fileName);
                List<String> wordList = parser.getWordList();
                for (String word : wordList) {
                    matrix.add(word.toLowerCase(), fileName);
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        }
        return matrix;
    }

    public static void writeMatrix(IncidenceMatrix matrix, String newFileName) {

        Set<String> treeSet = new TreeSet<>(matrix.getMatrix().keySet());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            for (int i = 0; i < matrix.getFiles().size(); i++) {
                writer.write(i + " = " + new File(matrix.getFiles().get(i)).getName() + System.lineSeparator());
            }

            for (String name : treeSet) {
                writer.write(name + " : ");
                List<Boolean> values = matrix.getMatrix().get(name);

                for (int i = 0; i < values.size(); i++) {
                    writer.write(i + ":" + checkBool(values.get(i)) + ", ");
                }
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int checkBool(Boolean boolResult) {
        if (boolResult) {
            return 1;
        } else if (boolResult) {
            return 0;
        } else
            return -1;
    }

    public IncidenceMatrix createDictionary(List<String> files) {

        IncidenceMatrix matrix = new IncidenceMatrix(files);

        for (String fileName : files) {
            try {
                Parser parser = new Parser(fileName);
                List<String> wordList = parser.getWordList();
                for (String word : wordList) {
                    matrix.add(word.toLowerCase(), fileName);
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return matrix;
    }

}