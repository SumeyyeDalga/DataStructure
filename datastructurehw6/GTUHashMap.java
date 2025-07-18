public class GTUHashMap <K,V> {
    private Entry<K, V>[] table;
    private int size;
    private static final int INITIAL_CAPACITY = 1001;

    @SuppressWarnings("unchecked")
    public GTUHashMap() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private int find(K key){
        int index= key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }

        int i=1;
        while (table[index] != null && !table[index].isDeleted && !table[index].key.equals(key)) {
            index = (index + i * i) % table.length; // Quadratic probing
            i++;
        }
        return index;

    }

    public V get(K key){
        int index = find(key);
        if (table[index] != null && !table[index].isDeleted) {
            return table[index].value;
        }
        return null; 
    }

    public void put(K key, V value){
        if (size > table.length * 0.75) {
            resize();
        }
        int index = find(key);

        if(table[index] == null || table[index].isDeleted) {
            table[index] = new Entry<>(key, value);
            size++;
            
        } 
        else{
            table[index].value = value; 
        }

    }

    public void remove(K key) {
        int index = find(key);
        if (table[index] != null && !table[index].isDeleted && table[index].key.equals(key)) {
            table[index].isDeleted = true;
            size--;
        }
    }
    
    public boolean containsKey(K key) {
        int index = find(key);
        return table[index] != null && !table[index].isDeleted;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[getNextPrime(oldTable.length * 2)]; //next prime after double
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                put(entry.key, entry.value);
            }
        }
    }
    private int getNextPrime(int number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number++;
        }
    }
    private boolean isPrime(int n){
        if (n <= 1)
            return false;
            for (int i = 2; i <= Math.sqrt(n); i++) { 
                if (n % i == 0) {
                    return false;
                }
            }
        return true;
    }
}
