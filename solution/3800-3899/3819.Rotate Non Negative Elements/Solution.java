class Solution {
    public int[] rotateElements(int[] nums, int k) {
        int m = 0;
        for (int x : nums) {
            if (x >= 0) {
                m++;
            }
        }
        int[] t = new int[m];
        int p = 0;
        for (int x : nums) {
            if (x >= 0) {
                t[p++] = x;
            }
        }
        int[] d = new int[m];
        for (int i = 0; i < m; i++) {
            d[((i - k) % m + m) % m] = t[i];
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                nums[i] = d[j++];
            }
        }
        return nums;
    }
}
