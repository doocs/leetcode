public class Solution {
    public int[] SearchRange(int[] nums, int target) {
        int l = Search(nums, target);
        int r = Search(nums, target + 1);
        return l == r ? new int[] {-1, -1} : new int[] {l, r - 1};
    }

    private int Search(int[] nums, int x) {
        int left = 0, right = nums.Length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}