class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                return count(nums, l, r, m);
            }
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return 0;
    }

    private int count(int[] nums, int l, int r, int m) {
        int cnt = 0;
        for (int i = m; i >= l; --i) {
            if (nums[i] == nums[m]) {
                ++cnt;
            } else if (nums[i] < nums[m]) {
                break;
            }
        }
        for (int i = m + 1; i <= r; ++i) {
            if (nums[i] == nums[m]) {
                ++cnt;
            } else if (nums[i] > nums[m]) {
                break;
            }
        }
        return cnt;
    }
}