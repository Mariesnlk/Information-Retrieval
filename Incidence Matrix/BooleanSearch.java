import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BooleanSearch {

    private IncidenceMatrix incidenceMatrix;

    public BooleanSearch(IncidenceMatrix incidenceMatrix) {

        this.incidenceMatrix = incidenceMatrix;
    }

    public List<Integer> operators(String input) {

        String[] splitQueryResult = input.split(" ");
        ArrayDeque<String> queue = new ArrayDeque<>(Arrays.asList(splitQueryResult));

        String word1 = queue.pop();
        List<Integer> result = findWord(word1);

        while (!queue.isEmpty()) {
            switch (queue.pop().toLowerCase()) {
                case "And":
                    result = OneWordInAnother(result, findWord(queue.pop()));
                    break;
                case "Or":
                    result = OneWordOrAnother(result, findWord(queue.pop()));
                    break;
                case "Not":
                    result = OneWordWithoutAnother(result, findWord(queue.pop()));
                    break;
            }
        }
        return result;
    }

    public List<Integer> findWord(String word) {

        List<Boolean> list = incidenceMatrix.getMatrix().get(word);
        if (list == null) {
            list = new LinkedList<>();
        }
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)) {
                result.add(i);
            }
        }
        return result;
    }


    public List<Integer> OneWordInAnother(List<Integer> wordList1, List<Integer> wordList2) {
        List<Integer> result = new LinkedList<>();
        if (wordList1.size() > wordList2.size()) {
            for (Integer wordList1Element : wordList1) {
                if (wordList2.contains(wordList1Element)) {
                    result.add(wordList1Element);
                }
            }
        } else {
            for (Integer wordList2Element : wordList2) {
                if (wordList1.contains(wordList2Element)) {
                    result.add(wordList2Element);
                }
            }
        }
        return result;
    }

    public List<Integer> OneWordOrAnother(List<Integer> wordList1, List<Integer> wordList2) {
        for (Integer value : wordList2) {
            if (!wordList1.contains(value))
                wordList1.add(value);
        }
        return wordList1;
    }

    public List<Integer> OneWordWithoutAnother(List<Integer> saveWordToList, List<Integer> toExcludeWord) {

        for (Integer value : toExcludeWord) {
            saveWordToList.remove(value);
        }
        return saveWordToList;
    }
}