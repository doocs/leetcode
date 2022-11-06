class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>(k);
        long s = 0;
        for (int i = 0; i < k; ++i) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            s += nums[i];
        }
        long ans = 0;
        for (int i = k; i < n; ++i) {
            if (cnt.size() == k) {
                ans = Math.max(ans, s);
            }
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            cnt.put(nums[i - k], cnt.getOrDefault(nums[i - k], 0) - 1);
            if (cnt.get(nums[i - k]) == 0) {
                cnt.remove(nums[i - k]);
            }
            s += nums[i];
            s -= nums[i - k];
        }
        if (cnt.size() == k) {
            ans = Math.max(ans, s);
        }
        return ans;
    }
}