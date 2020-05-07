import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefixTree {

    class PrefixTreeNode {
        private Map<Character, PrefixTreeNode> links = new HashMap<>();
        private char character;
        private boolean word;
        private boolean leaf;

        public PrefixTreeNode() {
        }

        public PrefixTreeNode(char character) {
            this.character = character;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public boolean isWord() {
            return word;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public void setWord(boolean word) {
            this.word = word;
        }

        public Map<Character, PrefixTreeNode> getLinks() {
            return links;
        }

        public char getNodeCharacter() {
            return character;
        }
    }
    private PrefixTreeNode root;

    public PrefixTree() {
        root = new PrefixTreeNode();
    }

    public void addWord(String word) {
        Map<Character, PrefixTreeNode> links = root.getLinks();

        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            PrefixTreeNode node;
            if (links.containsKey(character)) {
                node = links.get(character);
            } else {
                node = new PrefixTreeNode(character);
                links.put(character, node);
            }

            links = node.getLinks();

            if (i == word.length() - 1 && links.isEmpty()) {
                node.setLeaf(true);
            }
            if (i == word.length() - 1) {
                node.setWord(true);
            }
        }
    }

    private PrefixTreeNode searchNode(String word) {
        Map<Character, PrefixTreeNode> links = root.getLinks();
        PrefixTreeNode node = null;
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            if (links.containsKey(character)) {
                node = links.get(character);
                links = node.getLinks();
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        PrefixTreeNode node = searchNode(word);
        return node != null && node.isWord();
    }


    public List<String> returnWordsWithPrefix(String prefix) {
        if (!(searchNode(prefix) != null)) {
            return null;
        }
        List<String> words = new ArrayList<>();
        PrefixTreeNode node = searchNode(prefix);
        StringBuilder prefixString = new StringBuilder(prefix);
        getWords(node, prefixString.deleteCharAt(prefixString.length() - 1), words);
        return words;
    }

    private void getWords(PrefixTreeNode node, StringBuilder charString, List<String> words) {

        charString.append(node.getNodeCharacter());
        if (node.isWord()) {
            words.add(charString.toString());
        }
        if (node.isLeaf()) {
            return;
        }
        for (Map.Entry<Character, PrefixTreeNode> pair : node.getLinks().entrySet()) {
            getWords(pair.getValue(), charString, words);
            charString.deleteCharAt(charString.length() - 1);
        }

    }

}