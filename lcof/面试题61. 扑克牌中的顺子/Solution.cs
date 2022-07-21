public class Solution {
    public bool IsStraight(int[] nums) {
        bool[] t = new bool[14];
        int max_val = 0, min_val = 14;
        foreach(var num in nums) {
            if (num == 0) {
                continue;
            }
            if (t[num]) {
                return false;
            }
            t[num] = true;
            max_val = Math.Max(max_val, num);
            min_val = Math.Min(min_val, num);
        }
        return max_val - min_val <= 4;
    }
}