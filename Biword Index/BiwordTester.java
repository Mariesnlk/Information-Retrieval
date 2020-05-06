
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class BiwordTester {

    private static final String BOOK_PATH = "C://SYNE//Information Retrieval//";

    public static void main(String[] args) {
        BiwordIndex biwordIndex = BiwordIndexReader.createBiwordIndex(Arrays.asList(BOOK_PATH + "Emma.fb2",
                BOOK_PATH + "Game of Thrones .fb2", BOOK_PATH + "Dracula.fb2"));

        BiwordIndexWriter.writeBiwordIndex(biwordIndex, BOOK_PATH + "BiWordResult.txt");

        for (int i = 0; i < biwordIndex.getFiles().size(); i++) {
            System.out.println((i + " = " + new File(biwordIndex.getFiles().get(i)).getName()));
        }

        BiwordPhraseSearch biwordPhraseSearch = new BiwordPhraseSearch(biwordIndex);
        Scanner in = new Scanner(System.in);
        Scanner exit = new Scanner(System.in);
        int toExit = 0;

        do {
            System.out.println("Insert search: ");
            String input = in.nextLine();
            System.out.println(biwordPhraseSearch.findPhrase(input));
            System.out.println("Do you want to continue? 1-yes, 0-no");
            toExit = exit.nextInt();
        } while (toExit != 0);

        //DONE C://SYNE//Information Retrieval//Emma.fb2
        //DONE C://SYNE//Information Retrieval//Game of Thrones .fb2
        //DONE C://SYNE//Information Retrieval//Dracula.fb2
        //0 = Emma.fb2
        //1 = Game of Thrones .fb2
        //2 = Dracula.fb2
        //Insert search:
        //hello world
        //[]
        //"Do you want to continue? 1-yes, 0-no"
        //1
        //Insert search:
        //"Do you want to continue? 1-yes, 0-no"
        //1
        //Insert search:
        //was so
        //[0, 1, 2]
        //"Do you want to continue? 1-yes, 0-no"
        //1
        //Insert search:
        //i am
        //[0, 1, 2]
        //"Do you want to continue? 1-yes, 0-no"
        //1
        //Insert search:
        //emma was
        //[0]
        //"Do you want to continue? 1-yes, 0-no"
        //1
        //Insert search:
        //was lived
        //[]
        //"Do you want to continue? 1-yes, 0-no"
        //0
    }

}
