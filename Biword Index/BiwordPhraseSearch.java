import java.util.LinkedList;
import java.util.List;

public class BiwordPhraseSearch {

    private BiwordIndex biwordIndex;

    public BiwordPhraseSearch(BiwordIndex biwordIndex) {
        this.biwordIndex = biwordIndex;
    }

    public List<Integer> findPhrase(String phrase) {
        List<Integer> result = new LinkedList<>();

        String[] split = phrase.split(" ");
        String[] pairs = new String[split.length - 1];


        if (split.length > 1) {
            for (int i = 0; i < pairs.length; i++) {
                pairs[i] = split[i] + " " + split[i + 1];
            }
        }

        for (String document : biwordIndex.getBiwordIndex().keySet()) {
            boolean containsPhrase = true;
            for (String pair : pairs) {
                if (!biwordIndex.getBiwordIndex().get(document).contains(pair)) {
                    containsPhrase = false;

                }
            }
            if (containsPhrase){
                result.add(biwordIndex.getFiles().indexOf(document));
            }
        }
        return result;
    }
}