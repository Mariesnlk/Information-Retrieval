import java.util.LinkedList;
import java.util.List;

public class SuffixTree extends PrefixTree {

    @Override
    public void addWord(String word) {
        super.addWord(reverse(word));
    }

    public List<String> findWordsWithSuffix(String suffix) {

        suffix = reverse(suffix);

        List<String> words = new LinkedList<>();
        for (String word : super.returnWordsWithPrefix(suffix)) {
            words.add(reverse(word));
        }

        return words;
    }

    @Override
    public boolean search(String word) {
        return super.search(word);
    }

    private String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

}