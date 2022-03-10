class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        List<Integer> t = new ArrayList<>();
        for (int cnt : counter.values()) {
            t.add(cnt);
        }
        Collections.sort(t, Collections.reverseOrder());
        int ans = 0;
        int n = 0;
        for (int cnt : t) {
            n += cnt;
            ++ans;
            if (n * 2 >= arr.length) {
                break;
            }
        }
        return ans;
    }
}