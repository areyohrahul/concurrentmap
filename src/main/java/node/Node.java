package node;

import lombok.Data;

@Data
public final class Node<K, V> {

    private K key;
    private V value;
    private int hash;
    private Node<K, V> next;

    public Node(K key, V value, int hash, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.hash = hash;
        this.next = next;
    }
}
