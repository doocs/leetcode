class Solution {
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            if (v % 2 == 0) {
                cnt.put(v, cnt.getOrDefault(v, 0) + 1);
            }
        }
        int ans = -1, mx = 0;
        for (var e : cnt.entrySet()) {
            int v = e.getKey(), t = e.getValue();
            if (mx < t || (mx == t && ans > v)) {
                mx = t;
                ans = v;
            }
        }
        return ans;
    }
}