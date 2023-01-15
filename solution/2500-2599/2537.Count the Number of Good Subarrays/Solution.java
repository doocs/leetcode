class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        long ans = 0, cur = 0;
        int i = 0;
        for (int x : nums) {
            cur += cnt.getOrDefault(x, 0);
            cnt.merge(x, 1, Integer::sum);
            while (cur - cnt.get(nums[i]) + 1 >= k) {
                cur -= cnt.merge(nums[i++], -1, Integer::sum);
            }
            if (cur >= k) {
                ans += i + 1;
            }
        }
        return ans;
    }
}