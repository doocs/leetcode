public class Solution {
    public bool ContainsNearbyDuplicate(int[] nums, int k) {
        var mp = new Dictionary<int, int>();
        for (int i = 0; i < nums.Length; ++i)
        {
            if (mp.ContainsKey(nums[i]) && i - mp[nums[i]] <= k)
            {
                return true;
            }
            mp[nums[i]] = i;
        }
        return false;
    }
}