class Solution {
    public int minGroupsForValidAssignment(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int k = nums.length;
        for (int v : cnt.values()) {
            k = Math.min(k, v);
        }
        for (;; --k) {
            int ans = 0;
            for (int v : cnt.values()) {
                if (v / k < v % k) {
                    ans = 0;
                    break;
                }
                ans += (v + k) / (k + 1);
            }
            if (ans > 0) {
                return ans;
            }
        }
    }
}