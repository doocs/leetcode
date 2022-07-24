class NumberContainers {
    private Map<Integer, Integer> mp = new HashMap<>();
    private Map<Integer, TreeSet<Integer>> t = new HashMap<>();

    public NumberContainers() {

    }
    
    public void change(int index, int number) {
        if (mp.containsKey(index)) {
            int v = mp.get(index);
            t.get(v).remove(index);
            if (t.get(v).isEmpty()) {
                t.remove(v);
            }
        }
        mp.put(index, number);
        t.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }
    
    public int find(int number) {
        return t.containsKey(number) ? t.get(number).first() : -1;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */