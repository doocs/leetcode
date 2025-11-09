class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                int k = j - i + 1;
                cnt.merge(nums[j], 1, Integer::sum);
                if (cnt.getOrDefault(target, 0) > k / 2) {
                    ++ans;
                }
            }
            cnt.clear();
        }
        return ans;
    }
}
