public class Solution {
    public long MaximumSubarraySum(int[] nums, int k) {
        int n = nums.Length;
        Dictionary<int, int> cnt = new Dictionary<int, int>(k);
        long s = 0;
        
        for (int i = 0; i < k; ++i) {
            if (!cnt.ContainsKey(nums[i])) {
                cnt[nums[i]] = 1;
            }
            else {
                cnt[nums[i]]++;
            }
            s += nums[i];
        }
        
        long ans = cnt.Count == k ? s : 0;
        
        for (int i = k; i < n; ++i) {
            if (!cnt.ContainsKey(nums[i])) {
                cnt[nums[i]] = 1;
            }
            else {
                cnt[nums[i]]++;
            }
            if (--cnt[nums[i - k]] == 0) {
                cnt.Remove(nums[i - k]);
            }
            
            s += nums[i] - nums[i - k];
            
            if (cnt.Count == k) {
                ans = Math.Max(ans, s);
            }
        }
        
        return ans;
    }
}