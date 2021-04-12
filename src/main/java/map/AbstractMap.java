package map;

import lombok.Data;
import node.Node;

@Data
public class AbstractMap<K, V> {

    private transient Node<K, V>[] table;

    private int threshold;
    private float loadFactor;

    public AbstractMap(int capacity, float loadFactor) {
        this.threshold = ((int)(capacity * this.loadFactor / 32.0F) + 1);
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    public V put(K key, V value) {
        int hashVal = hash(key);
        int index = hashVal & 0x1F;

        Node<K, V> first = table[index];
        for (Node<K, V> e = first; e != null; e = e.getNext()) {
            if ((e.getHash() == hashVal) && (eq(key, e.getKey()))) {
                V oldValue = e.getValue();
                e.setValue(value);
                return oldValue;
            }
        }

        Node<K, V> newEntry = new Node<>(key, value, hashVal, first);
        table[index] = newEntry;
        return null;
    }

    private boolean eq(K key1, K key2) {
        return key1 == key2;
    }

    public V get(K key) {
        int hashVal = hash(key);
        int index = hashVal & 0x1F;

        Node<K, V> first = table[index];
        for (Node<K, V> e = first; e != null; e = e.getNext()) {
            if ((e.getHash() == hashVal) && (eq(key, e.getKey()))) {
                return e.getValue();
            }
        }

        return null;
    }

    public void remove(K key) {
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public int hash(K key) {
        int h = key.hashCode();
        return (h << 7) - h + (h >>> 9) + (h >>> 17);
    }
}
