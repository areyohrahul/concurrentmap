package map;

public class ConcurrentMap<K, V> extends AbstractMap<K, V> {

    private final Segment[] segments = new Segment[32];

    public ConcurrentMap(int capacity, float loadFactor) {
        super(capacity, loadFactor);
        for (int i=0; i<segments.length; i++) {
            segments[i] = new Segment();
        }
    }

    private static final class Segment {

        private int count;

        Segment() {
            this.count = 0;
        }

        public synchronized void incrementCount() {
            this.count += 1;
        }

        public int getCount() {
            return this.count;
        }
    }

    public int size() {
        int size = 0;
        for (int i=0; i<this.segments.length; i++) {
            size += segments[i].getCount();
        }
        return size;
    }

    public V put(K key, V value) {
        synchronized (getSegment(key)) {
            V mapValue = super.put(key, value);
            if (mapValue == null) {
                getSegment(key).incrementCount();
            }
            return value;
        }
    }

    public V get(K key) {
        return super.get(key);
    }

    public void remove(K key) {
        synchronized (getSegment(key)) {
            super.remove(key);
        }
    }

    public boolean contains(K key) {
        return super.contains(key);
    }

    private Segment getSegment(K key) {
        return segments[(hash(key) & 0x1F)];
    }
}
