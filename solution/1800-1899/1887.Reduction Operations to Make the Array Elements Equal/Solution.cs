public class Solution {
    public int ReductionOperations(int[] nums) {
        Array.Sort(nums);
        int ans = 0, cnt = 0;
        for (int i = 1; i < nums.Length; i++) {
            if (nums[i] != nums[i - 1]) {
                ++cnt;
            }
            ans += cnt;
        }
        return ans;
    }
}
