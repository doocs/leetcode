public class Solution {
    public int SortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.Length; i++) {
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        return Math.Max(ans, 0);
    }
}
