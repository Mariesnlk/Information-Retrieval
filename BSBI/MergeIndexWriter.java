
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class MergeIndexWriter {

    private final Queue<TermPosting> listToMerge;
    private BufferedWriter writer;
    private final BSBI bsbi;

    public MergeIndexWriter(BSBI bsbi, Queue<TermPosting> list, File file) throws IOException {
        this.bsbi = bsbi;
        this.listToMerge = list;
        writer = new BufferedWriter(new FileWriter(file));
    }

    public void mergeAndWrite() throws IOException {
        ArrayList<TermPosting> termPostingsListsToMerge = new ArrayList<>();
        generateTermPostingsList(termPostingsListsToMerge);
    }

    private void generateTermPostingsList(ArrayList<TermPosting> termPostingsListsToMerge) throws IOException {

        boolean readerFinished = false;
        while (!readerFinished) {

            try {
                TermPosting termPostingsList = listToMerge.poll();
                if (termPostingsList != null) {
                    if (!termPostingsListsToMerge.isEmpty()) {
                        TermPosting previous = termPostingsListsToMerge.get(termPostingsListsToMerge.size() - 1);
                        if (termPostingsList.getTermID() != previous.getTermID()) {
                            TermPosting tpl = mergeTermPostingsLists(termPostingsListsToMerge);
                            writeTermPostingsList(tpl);
                            termPostingsListsToMerge.clear();
                            termPostingsListsToMerge.add(termPostingsList);
                        } else {
                            termPostingsListsToMerge.add(termPostingsList);
                        }
                    } else {
                        termPostingsListsToMerge.add(termPostingsList);
                    }
                } else {
                    throw new InterruptedException();
                }
            } catch (InterruptedException e) {

                if (bsbi.getBlocksFinished()) {
                    writer.close();
                    readerFinished = true;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    private TermPosting mergeTermPostingsLists(ArrayList<TermPosting> termPostingsLists) {

        int termID = termPostingsLists.get(0).getTermID();

        Queue<Integer> docIDs = new PriorityQueue<>();
        for (TermPosting termPostingsList : termPostingsLists) {
            docIDs.addAll(termPostingsList.getTermPosting());
        }

        List<Integer> postingsList = new ArrayList<>(docIDs.size());
        while (!docIDs.isEmpty()) {
            postingsList.add(docIDs.remove());
        }

        return new TermPosting(termID, postingsList);
    }

    private void writeTermPostingsList(TermPosting termPostingsList) throws IOException {

        List<Integer> postingsList = new ArrayList<>(termPostingsList.getTermPosting());
        StringBuilder line = new StringBuilder();
        line.append(termPostingsList.getTermID()).append(":");

        for (int docId : postingsList) {
            line.append(docId).append(",");
        }

        writer.write(line.toString());
        writer.newLine();

    }
}