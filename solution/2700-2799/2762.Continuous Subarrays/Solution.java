class Solution {
    public long continuousSubarrays(int[] nums) {
        long ans = 0;
        int i = 0, n = nums.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int j = 0; j < n; ++j) {
            tm.merge(nums[j], 1, Integer::sum);
            while (tm.lastEntry().getKey() - tm.firstEntry().getKey() > 2) {
                tm.merge(nums[i], -1, Integer::sum);
                if (tm.get(nums[i]) == 0) {
                    tm.remove(nums[i]);
                }
                ++i;
            }
            ans += j - i + 1;
        }
        return ans;
    }
}