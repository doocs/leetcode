class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> t = new ArrayList<>(counter.entrySet());
        Collections.sort(t, Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> e : t) {
            int v = e.getKey();
            int cnt = e.getValue();
            if (k >= cnt) {
                k -= cnt;
                counter.remove(v);
            } else {
                break;
            }
        }
        return counter.size();
    }
}