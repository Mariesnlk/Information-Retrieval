import java.util.LinkedList;
import java.util.List;
public class Collection {

    private List<CollectionItem> collectionItems = new LinkedList<>();
    private Dictionary dictionary = new Dictionary();

    public long getTotalSizeKB() {
        return collectionItems.stream().mapToLong(CollectionItem::getFileSize).sum() /  1024 ;
    }

    public long getTotalWordCount() {
        return collectionItems.stream().mapToLong(CollectionItem::getWordsCount).sum();
    }

    public long getDictionaryWordCount() {
        return dictionary.getWordsCount();
    }

    public List<CollectionItem> getCollectionItems() {
        return collectionItems;
    }

    public void setCollectionItems(List<CollectionItem> collectionItems) {
        this.collectionItems = collectionItems;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}