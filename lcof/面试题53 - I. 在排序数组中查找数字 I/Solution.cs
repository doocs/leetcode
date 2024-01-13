public class Solution {
    public int Search(int[] nums, int target) {
        int l = search(nums, target);
        int r = search(nums, target + 1);
        return r - l;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.Length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
