import java.util.*;

public class ThreeGrammarIndex {
    private List<String> files;
    private Map<String, List<String>> threeGrammarIndex = new HashMap<>();

    public ThreeGrammarIndex(List<String> files) {
        this.files = new ArrayList<>(files);
    }

    public void addWord(String word) {

        StringBuilder stringBuilder = new StringBuilder("$" + word + "$");

        for (int i = 0; i < stringBuilder.length() - 2; i++) {

            if (threeGrammarIndex.containsKey(stringBuilder.substring(i, i + 3))) {
                threeGrammarIndex.get(stringBuilder.substring(i, i + 3)).add(word);
            } else {
                threeGrammarIndex.put(stringBuilder.substring(i, i + 3), new LinkedList<>());
                threeGrammarIndex.get(stringBuilder.substring(i, i + 3)).add(word);
            }
        }
    }
    public Map<String, List<String>> getThreeGrammarIndex() {
        return threeGrammarIndex;
    }
}
