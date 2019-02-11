import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per bucket before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per bucket before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0;

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {

        int key_hash = key.hashCode();
        int bucket_num = key_hash % buckets.length;
        return buckets[bucket_num];

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {

        // Create iterator for linked list
        LinkedList<Entry> key_list = chooseBucket(key);
        ListIterator<Entry> list_iter = key_list.listIterator(0);

        // If the key is anywhere in the list, return true.
        while (list_iter.hasNext()) {
            if (list_iter.next().getKey() == key) return true;
        }

        // If no matching key is found, return false
        return false;

    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {

        for (int i = 0; i < buckets.length; i++) {

            // Create iterator for linked list
            LinkedList<Entry> each_list = buckets[i];
            ListIterator<Entry> list_iter = each_list.listIterator(0);

            // If the value is anywhere in the list, return true.
            while (list_iter.hasNext()) {
                if (list_iter.next().getValue() == value) return true;
            }

        }

        // If no matching value is found, return false
        return false;

    }

    @Override
    public V get(Object key) {

        // Create iterator for linked list
        LinkedList<Entry> key_list = chooseBucket(key);
        ListIterator<Entry> list_iter = key_list.listIterator(0);

        // If the key is anywhere in the list, return corresponding value.
        while (list_iter.hasNext()) {

            Entry item = list_iter.next();
            if (item.getKey().equals(key)) return item.value;
        }

        // If no matching key is found, return null
        return null;

    }

    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {

        // Create iterator for linked list
        LinkedList<Entry> key_list = chooseBucket(key);
        ListIterator<Entry> list_iter = key_list.listIterator(0);

        // If the key is anywhere in the list, return corresponding value.
        int i = 0;
        while (list_iter.hasNext()) {

            Entry item = list_iter.next();
            if (item.getKey() == key) {
                key_list.set(i, new Entry(key, value));

                return item.value;
            }
            i++;
        }

        // If no matching key is found, return null
        key_list.addLast(new Entry(key, value));
        size++;

        if ((1.0*size)/buckets.length >= ALPHA) {
            // Rehash if too many items per bucket
            rehash(GROWTH_FACTOR);
        }

        return null;

    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {

        LinkedList<Entry> key_list = chooseBucket(key);
        ListIterator<Entry> list_iter = key_list.listIterator(0);

        int i = 0;
        while (list_iter.hasNext()) {

            // If item matches key, return it's value and remove it from list.
            Entry item = list_iter.next();
            if (item.getKey().equals(key)) {

                key_list.remove(i);
                size--;

                if (((1.0*size)/buckets.length <= BETA) && (buckets.length >= MIN_BUCKETS/SHRINK_FACTOR)) {
                    // Rehash if too few items per bucket
                    rehash(SHRINK_FACTOR);
                }

                return item.value;
            }
            i++;
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {

        LinkedList<Entry> old_entries = new LinkedList<Entry>();

        // Add all old entries to new list
        for (int i = 0; i < buckets.length; i++) {

            LinkedList<Entry> this_bucket = buckets[i];
            ListIterator<Entry> bucket_iter = this_bucket.listIterator(0);

            // Move entries over
            while (bucket_iter.hasNext()) {
                old_entries.addLast(bucket_iter.next());
            }
        }

        // Clear list and change size
        clear();
        initBuckets((int) (buckets.length*growthFactor));

        // Repopulate
        ListIterator<Entry> old_entries_iter = old_entries.listIterator(0);
        while (old_entries_iter.hasNext()) {

            Entry next_entry = old_entries_iter.next();
            put(next_entry.getKey(), next_entry.getValue());

        }

   }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}
