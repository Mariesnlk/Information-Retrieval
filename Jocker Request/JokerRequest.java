import java.util.LinkedList;
import java.util.List;
public class JokerRequest {

    private PrefixTree prefixTree;
    private SuffixTree suffixTree;
    private WildCard wildCard;
    private ThreeGrammarIndex threeGrammarIndex;

    public JokerRequest(PrefixTree prefixTree, SuffixTree suffixTree, WildCard wildCard) {
        this.prefixTree = prefixTree;
        this.suffixTree = suffixTree;
        this.wildCard = wildCard;
    }

    public JokerRequest(ThreeGrammarIndex threeGramIndex, WildCard wildCard) {
        this.threeGrammarIndex = threeGramIndex;
        this.wildCard = wildCard;
    }

    public List<String> doneRequest(String query) {

        List<String> words;

        if (query.charAt(query.length() - 1) == '*') {
            words = prefixTree.returnWordsWithPrefix(query.substring(0, query.length() - 1));
        } else if (query.charAt(0) == '*') {
            words = suffixTree.findWordsWithSuffix(query.substring(1, query.length()));
        } else {
            words = findMiddleJokerRequest(query);
        }
        return words;

    }

    public List<String> findMiddleJokerRequest(String query) {
        wildCard.generateRotations(query);
        StringBuilder stringBuilder = new StringBuilder(query + "$");
        List<String> result = new LinkedList<>();

        do {
            stringBuilder.append(stringBuilder.charAt(0));
            stringBuilder.deleteCharAt(0);
        } while (stringBuilder.charAt(stringBuilder.length() - 1) != '*');

        String word = stringBuilder.toString();
        String[] splitBySymbol = word.split("\\$");

        List<String> wordsWithPrefix = findWordsWithPrefix(splitBySymbol[1]);
        List<String> indexWords;

        indexWords = splitBySymbol[0].contains("*") ?
                getIndexWordsDoubleQuery(splitBySymbol[0].split("\\*")[0],
                        splitBySymbol[0].split("\\*")[1], wordsWithPrefix):
                getIndexWords(splitBySymbol[0], wordsWithPrefix);


        addWordsPresentInPermutermIndex(result, indexWords);

        System.out.println(result);
        return result;
    }

    private List<String> getIndexWords(String s, List<String> wordsWithPrefix) {
        List<String> indexWords = new LinkedList<>();
        for (String wordWithPrefix : wordsWithPrefix) {
            String possibleWord = wordWithPrefix.substring(0, wordWithPrefix.length() - s.length());
            indexWords.add(s + "$" + possibleWord);

        }
        return indexWords;
    }

    private List<String> getIndexWordsDoubleQuery(String start, String end, List<String> wordsWithPrefix) {
        List<String> indexWords = new LinkedList<>();
        for (String wordWithPrefix : wordsWithPrefix) {
            String possibleWord = wordWithPrefix.substring(0, wordWithPrefix.length() - end.length());
            if (possibleWord.contains(start)) {
                indexWords.add(end + "$" + possibleWord);
            }
        }
        return indexWords;
    }

    private List<String> findWordsWithPrefix(String prefix) {
        return prefixTree.returnWordsWithPrefix(prefix.substring(0, prefix.length() - 1));
    }

    private void addWordsPresentInPermutermIndex(List<String> result, List<String> indexWords) {
        for (String index : indexWords) {
            if (wildCard.getPermutermIndex().containsKey(index)) {
                result.add(wildCard.getPermutermIndex().get(index));
            }
        }
    }
}