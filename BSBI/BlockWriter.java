
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class BlockWriter {

    private static final String BOOK_PATH = "C://SYNE//Information Retrieval//";

    public BlockWriter() {
    }

    public void writeBlock(HashMap<Integer, TermPosting> termPostingsLists, int blockNumber) throws IOException {
        File file = new File(BOOK_PATH + "Block" + blockNumber + ".txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        Set<Integer> termsIDs = termPostingsLists.keySet();
        for (Integer termID : termsIDs) {
            List<Integer> postingsList = new ArrayList<>(termPostingsLists.get(termID).getTermPosting());

            StringBuilder line = new StringBuilder();
            line.append(termID).append(":");

            for (int documentID : postingsList) {
                line.append(documentID).append(",");
            }

            bufferedWriter.write(line.toString());
            bufferedWriter.newLine();

        }
        bufferedWriter.close();

    }

}