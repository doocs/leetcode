public class Solution {
    public bool CanBeIncreasing(int[] nums) {
        int n = nums.Length;
        bool check(int k) {
            int pre = 0;
            for (int i = 0; i < n; ++i) {
                if (i == k) {
                    continue;
                }
                if (pre >= nums[i]) {
                    return false;
                }
                pre = nums[i];
            }
            return true;
        }
        int i = 0;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        return check(i) || check(i + 1);
    }
}
