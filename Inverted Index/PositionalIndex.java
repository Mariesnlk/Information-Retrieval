import java.util.*;

public class PositionalIndex {

    private InvertedIndex invertedIndex;

    public PositionalIndex(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    public Set<Integer> findPhrase(String phrase) {

        String[] queryWords = parsePhrase(phrase);

        Set<Integer> result = new TreeSet<>();
        if (!checkIfIndexConsistPhrase(queryWords)) {
            return result;
        }

        String word1 = queryWords[0];
        Map<String, List<Integer>> documentsWhereWord1Present = invertedIndex.getInvertedIndex().get(word1);

        for (int i = 1; i <= queryWords.length - 1; i++) {

            String word2 = queryWords[i];

            Map<String, List<Integer>> documentsWhereWord2Present = invertedIndex.getInvertedIndex().get(word2);

            for (String document : documentsWhereWord2Present.keySet()) {
                if (documentsWhereWord1Present.containsKey(document)) {
                    addCoordinates(result, word1, word2, document, i);
                }
            }
        }


        return result;
    }

    private String[] parsePhrase(String phrase) {
        String[] queryWords = phrase.split(" ");
        for (int i = 0; i < queryWords.length; i++) {
            queryWords[i] = queryWords[i].toLowerCase();
        }
        return queryWords;
    }

    private boolean checkIfIndexConsistPhrase(String[] split) {
        return invertedIndex.getInvertedIndex().keySet().containsAll(Arrays.asList(split));
    }

    private void addCoordinates(Set<Integer> result, String word1, String word2, String document, int gap) {
        List<Integer> coordinatesWord1 = invertedIndex.getInvertedIndex().get(word1).get(document);
        List<Integer> coordinatesWord2 = invertedIndex.getInvertedIndex().get(word2).get(document);

        for (Integer coordinate : coordinatesWord2) {
            if (coordinatesWord1.contains(coordinate - gap)) {
                result.add(invertedIndex.getFiles().indexOf(document));
                break;
            } else if (!coordinatesWord1.contains(coordinate - gap)) {
                result.remove(invertedIndex.getFiles().indexOf(document));
            }
        }
    }
}