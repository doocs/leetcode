class RandomizedSet {
    private final Map<Integer, Integer> m;
    private final List<Integer> a;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.m = new HashMap<>();
        this.a = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (this.m.containsKey(val)) {
            return false;
        }
        this.m.put(val, this.a.size());
        this.a.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (this.m.containsKey(val)) {
            int idx = this.m.get(val), last = this.a.size() - 1;
            Collections.swap(this.a, idx, last);
            this.m.put(this.a.get(idx), idx);
            this.a.remove(last);
            this.m.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return this.a.get(ThreadLocalRandom.current().nextInt(this.a.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
