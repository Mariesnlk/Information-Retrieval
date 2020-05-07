
import java.util.Collection;
import java.util.TreeSet;


public class TermPosting implements Comparable<TermPosting> {

    private int termID;
    private TreeSet<Integer> termPosting;

    public TermPosting(int termID, Collection<Integer> postingsCollection) {
        this.termID = termID;
        this.termPosting = new TreeSet<>();
        this.termPosting.addAll(postingsCollection);
    }

    public void addPostingsList(int posting) {
        termPosting.add(posting);
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public TreeSet<Integer> getTermPosting() {
        return termPosting;
    }

    public void setTermPosting(Collection<Integer> termPosting) {
        this.termPosting.addAll(termPosting);
    }

    @Override
    public int compareTo(TermPosting o) {
        return Integer.compare(termID, o.termID);
    }
}