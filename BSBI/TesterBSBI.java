//import java.io.File;
//import java.util.Arrays;
//import java.util.List;
//
//public class TesterBSBI {
//    private static final String BOOK_PATH = "C://SYNE//Information Retrieval//";
//
//    public static final File[] documents = new File[BOOK_PATH + "Game of Thrones .fb2",
//            BOOK_PATH + "Game of Thrones .fb2", BOOK_PATH + "Emma.fb2", BOOK_PATH + "The Jungle Book.fb2",
//            BOOK_PATH + "Dracula.fb2", BOOK_PATH + "Pride_and_Prejudice.fb2",
//            BOOK_PATH + "The_Picture_of_Dorian_Gray.fb2",
//            BOOK_PATH + "The-Iliad.fb2", BOOK_PATH + "The-Unveiling.fb2", BOOK_PATH + "Collected-Works-of-Poe.fb2"];
//
//    public static void main(String[] args) {
//
//        BSBI bsbi = new BSBI(documents);
//        bsbi.bsbi();
//    }
//}


import java.io.File;
import java.util.Arrays;
import java.util.List;


public class TesterBSBI {
    private static final String BOOK_PATH = "C://SYNE//Information Retrieval//";

    public static final List<File> FILES = Arrays.asList(new File(BOOK_PATH + "Game of Thrones .fb2"),
            new File(BOOK_PATH + "Dracula.fb2"), new File(BOOK_PATH + "Emma.fb2"),
            new File(BOOK_PATH + "Pride_and_Prejudice.fb2"), new File(BOOK_PATH +  "The Jungle Book.fb2")
    );

    public static void main(String[] args) {
        BSBI bsbi = new BSBI((File[]) FILES.toArray());
        bsbi.bsbi();
    }
}