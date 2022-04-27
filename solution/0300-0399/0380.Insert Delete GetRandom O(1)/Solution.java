class RandomizedSet {
    private Map<Integer, Integer> m = new HashMap<>();
    private List<Integer> l = new ArrayList<>();
    private Random rnd = new Random();

    public RandomizedSet() {

    }
    
    public boolean insert(int val) {
        if (m.containsKey(val)) {
            return false;
        }
        m.put(val, l.size());
        l.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!m.containsKey(val)) {
            return false;
        }
        int idx = m.get(val);
        l.set(idx, l.get(l.size() - 1));
        m.put(l.get(l.size() - 1), idx);
        l.remove(l.size() - 1);
        m.remove(val);
        return true;
    }
    
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