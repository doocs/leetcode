class FirstUnique {
    private Map<Integer, Integer> counter;
    private Set<Integer> uniqueNums;

    public FirstUnique(int[] nums) {
        counter = new LinkedHashMap<>();
        uniqueNums = new LinkedHashSet<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueNums.add(entry.getKey());
            }
        }
    }
    
    public int showFirstUnique() {
        return uniqueNums.isEmpty() ? -1 : uniqueNums.iterator().next();
    }
    
    public void add(int value) {
        if (!counter.containsKey(value)) {
            counter.put(value, 1);
            uniqueNums.add(value);
        } else {
            counter.put(value, counter.get(value) + 1);
            uniqueNums.remove(value);
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */