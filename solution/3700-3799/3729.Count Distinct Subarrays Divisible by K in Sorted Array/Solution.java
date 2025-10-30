class Solution {
    public long numGoodSubarrays(int[] nums, int k) {
        long ans = 0;
        int s = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for (int x : nums) {
            s = (s + x) % k;
            ans += cnt.getOrDefault(s, 0);
            cnt.merge(s, 1, Integer::sum);
        }
        int n = nums.length;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) {
                ++j;
            }
            int m = j - i;
            for (int h = 1; h <= m; ++h) {
                if (1L * nums[i] * h % k == 0) {
                    ans -= (m - h);
                }
            }
            i = j;
        }
        return ans;
    }
}
