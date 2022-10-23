class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int mi = min(nums), mx = max(nums);
        int i = -1, j = -1;
        for (int k = 0; k < n; ++k) {
            if (nums[k] == mi && i == -1) {
                i = k;
            }
            if (nums[k] == mx) {
                j = k;
            }
        }
        if (i == j) {
            return 0;
        }
        return i < j ? i + n - 1 - j : i + n - 2 - j;
    }

    private int max(int[] nums) {
        int v = 0;
        for (int x : nums) {
            v = Math.max(v, x);
        }
        return v;
    }

    private int min(int[] nums) {
        int v = nums[0];
        for (int x : nums) {
            v = Math.min(v, x);
        }
        return v;
    }
}