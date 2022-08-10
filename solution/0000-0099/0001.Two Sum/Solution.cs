public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        var m = new Dictionary<int, int>();
        for (var i = 0; i < nums.Length; ++i)
        {
            int j;
            int v = nums[i];
            int x = target - v;
            if (m.TryGetValue(x, out j))
            {
                return new [] {j, i};
            }
            if (!m.ContainsKey(v))
            {
                m.Add(v, i);
            }
            
        }
        return null;
    }
}