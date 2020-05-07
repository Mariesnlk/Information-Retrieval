import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WildCard {


    private List<String> files;
    private Map<String, String> permutermIndex = new HashMap<>();

    public WildCard(List<String> files) {
        this.files = new ArrayList<>(files);
    }

    public void generateRotations(String word) {

        StringBuilder stringBuilder = new StringBuilder(word + "$");

        for (int i = 0; i < stringBuilder.length(); i++) {
            stringBuilder.append(stringBuilder.charAt(0));
            stringBuilder.deleteCharAt(0);
            permutermIndex.put(stringBuilder.toString(), word);
        }
    }

    public Map<String, String> getPermutermIndex() {
        return permutermIndex;
    }
}