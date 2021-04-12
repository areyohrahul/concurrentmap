import map.ConcurrentMap;

public class Driver {

    public static void main(String[] args) {
        ConcurrentMap<Integer, Integer> map = new ConcurrentMap<>(32, 0.75f);

        map.put(1, 1);
        map.put(2, 2);

        System.out.println(map.size());
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.contains(1));
        System.out.println(map.contains(5));
        System.out.println(map.get(5));
    }
}
