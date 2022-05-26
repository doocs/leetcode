class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int e : arr) {
            counter.put(e, counter.getOrDefault(e, 0) + 1);
        }
        Set<Integer> s = new HashSet<>();
        for (int num : counter.values()) {
            if (s.contains(num)) {
                return false;
            }
            s.add(num);
        }
        return true;
    }
}