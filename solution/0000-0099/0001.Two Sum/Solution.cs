public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        var m = new Dictionary<int, int>();
        for (int i = 0, j; ; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.TryGetValue(y, out j)) {
                return new [] {j, i};
            }
            if (!m.ContainsKey(x)) {
                m.Add(x, i);
            }
        }
    }
}