class Solution {
    public long countBadPairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int x = i - nums[i];
            ans += i - cnt.merge(x, 1, Integer::sum) + 1;
        }
        return ans;
    }
}