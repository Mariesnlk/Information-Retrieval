
import java.io.File;
import java.io.IOException;
import java.util.*;
public class BSBIIndex {

    private Queue<String[]> queue;
    private BSBI bsbi;
    private Map<String, Integer> termList;
    private HashMap<String, Integer> blockDocumentList;
    private HashMap<Integer, TermPosting> termPostingsLists;
    private List<File> documents;
    private static final String BOOK_PATH = "C://SYNE//Information Retrieval//";

    public BSBIIndex(BSBI bsbi, Queue<String[]> queue, HashMap<String, Integer> termList, HashMap<String, Integer> blockDocumentList, File[] documents) {
        this.bsbi = bsbi;
        this.queue = queue;
        this.termList = termList;
        this.blockDocumentList = blockDocumentList;
        this.termPostingsLists = new HashMap<>();
        this.documents = new LinkedList<>(Arrays.asList(documents));
    }


    public void createIndex(int blockNumber, int filesPerBlock) {
        int filesDone = 0;
        int termID = termList.size();
        boolean isIndexingFinished = false;


        while (!isIndexingFinished) {

            String[] document = queue.poll();
            if (document != null) {
                String documentTitle = document[0];
                String documentBody = document[1];

                blockDocumentList.put(documentTitle, blockNumber);


                String[] terms = documentBody.split(" ");

                for (String term : terms) {
                    term = term.toLowerCase();
                    int id;
                    if (!termList.containsKey(term)) {
                        termList.put(term, termID);
                        id = termID;
                        termID++;
                    } else {
                        id = termList.get(term);
                    }

                    if (!termPostingsLists.containsKey(id)) {
                        TermPosting termPostings = new TermPosting(id, new ArrayList<>());
                        termPostings.addPostingsList(documents.indexOf(new File(BOOK_PATH + documentTitle)));
                        termPostingsLists.put(id, termPostings);
                    } else {
                        termPostingsLists.get(id).addPostingsList(documents.indexOf(new File(BOOK_PATH + documentTitle)));
                    }
                }

                filesDone++;
            }

            if (filesDone == filesPerBlock) {
                writeBlockIntoFile(blockNumber);
                isIndexingFinished = true;
            }
        }
    }

    private void writeBlockIntoFile(int documentID) {
        BlockWriter blockWriter = new BlockWriter();
        try {
            blockWriter.writeBlock(termPostingsLists, documentID);
            bsbi.getList().putAll(termPostingsLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String, Integer> getTermList() {
        return termList;
    }

}