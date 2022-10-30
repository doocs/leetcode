class Solution {
    public int destroyTargets(int[] nums, int space) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            v %= space;
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        int ans = 0, mx = 0;
        for (int v : nums) {
            int t = cnt.get(v % space);
            if (t > mx || (t == mx && v < ans)) {
                ans = v;
                mx = t;
            }
        }
        return ans;
    }
}