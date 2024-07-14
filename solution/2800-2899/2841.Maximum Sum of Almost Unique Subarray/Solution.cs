public class Solution {
    public long MaxSum(IList<int> nums, int m, int k) {
        Dictionary<int, int> cnt = new Dictionary<int, int>();
        int n = nums.Count;
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
        
        long ans = cnt.Count >= m ? s : 0;
        
        for (int i = k; i < n; ++i) {
            if (!cnt.ContainsKey(nums[i])) {
                cnt[nums[i]] = 1;
            }
            else {
                cnt[nums[i]]++;
            }
            if (cnt.ContainsKey(nums[i - k])) {
                cnt[nums[i - k]]--;
                if (cnt[nums[i - k]] == 0) {
                    cnt.Remove(nums[i - k]);
                }
            }
            
            s += nums[i] - nums[i - k];
            
            if (cnt.Count >= m) {
                ans = Math.Max(ans, s);
            }
        }
        
        return ans;
    }
}