import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Tester {
    private static final String BOOK_PATH = "C://SYNE//Information Retrieval//";

    public static void main(String[] args) {

        StopWatch stopwatch = new StopWatch();
        CollectionBuilder collectionBuilder = new CollectionBuilder();
        Collection collection = collectionBuilder.createCollection(Arrays.asList(BOOK_PATH + "Game of Thrones .fb2",
                BOOK_PATH + "Game of Thrones .fb2", BOOK_PATH + "Emma.fb2", BOOK_PATH + "The Jungle Book.fb2",
                BOOK_PATH + "Dracula.fb2", BOOK_PATH + "Pride_and_Prejudice.fb2",
                BOOK_PATH + "The_Picture_of_Dorian_Gray.fb2",
                BOOK_PATH + "The-Iliad.fb2", BOOK_PATH + "The-Unveiling.fb2", BOOK_PATH + "Collected-Works-of-Poe.fb2"));

        System.out.println("File size: " + collection.getTotalSizeKB() + " KB");
        System.out.println("Total word count: " + collection.getTotalWordCount());
        System.out.println("Dictionary word count: " + collection.getDictionaryWordCount());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_PATH + "Dictionary collection.txt"))) {
            for (int i = 0; i < collection.getDictionary().getWordsCount(); i++) {
                writer.write(collection.getDictionary().getDictionaryWords()[i] + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        double time = stopwatch.elapsedTime();
        System.out.println("Spent time " + time);
    }
}
