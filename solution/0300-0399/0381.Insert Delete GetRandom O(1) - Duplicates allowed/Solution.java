class RandomizedCollection {
    private Map<Integer, Set<Integer>> m;
    private List<Integer> l;
    private Random rnd;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        m = new HashMap<>();
        l = new ArrayList<>();
        rnd = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        m.computeIfAbsent(val, k -> new HashSet<>()).add(l.size());
        l.add(val);
        return m.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!m.containsKey(val)) {
            return false;
        }
        Set<Integer> idxSet = m.get(val);
        int idx = idxSet.iterator().next();
        int lastIdx = l.size() - 1;
        l.set(idx, l.get(lastIdx));
        idxSet.remove(idx);

        Set<Integer> lastIdxSet = m.get(l.get(lastIdx));
        lastIdxSet.remove(lastIdx);
        if (idx < lastIdx) {
            lastIdxSet.add(idx);
        }
        if (idxSet.isEmpty()) {
            m.remove(val);
        }
        l.remove(lastIdx);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int size = l.size();
        return size == 0 ? -1 : l.get(rnd.nextInt(size));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */