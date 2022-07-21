public class Solution {
    public int MissingNumber(int[] nums) {
        int l = 0, r = nums.Length - 1;
        if (r == 0 || nums[0] == 1) {
            return nums[0] ^ 1;
        }
        if (nums[r] == r) {
            return r + 1;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == mid) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}