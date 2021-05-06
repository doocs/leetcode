class RandomizedSet {
    private Map<Integer, Integer> m;
    private List<Integer> l;
    private Random rnd;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        m = new HashMap<>();
        l = new ArrayList<>();
        rnd = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (m.containsKey(val)) {
            return false;
        }
        m.put(val, l.size());
        l.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!m.containsKey(val)) {
            return false;
        }
        int idx = m.get(val);
        int lastIdx = l.size() - 1;
        m.put(l.get(lastIdx), idx);
        m.remove(val);
        l.set(idx, l.get(lastIdx));
        l.remove(lastIdx);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rnd.nextInt(l.size());
        return l.get(idx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */