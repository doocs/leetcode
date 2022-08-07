class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            nums[i] = i - nums[i];
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        long ans = 0;
        for (int v : cnt.values()) {
            ans += v * (n - v);
        }
        return ans >> 1;
    }
}