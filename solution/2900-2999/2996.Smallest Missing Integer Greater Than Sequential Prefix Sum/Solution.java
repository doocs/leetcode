class Solution {
    public int missingInteger(int[] nums) {
        int s = nums[0];
        for (int j = 1; j < nums.length && nums[j] == nums[j - 1] + 1; ++j) {
            s += nums[j];
        }
        final int m = 51;
        boolean[] st = new boolean[m];
        for (int x : nums) {
            st[x] = true;
        }
        while (s < m && st[s]) {
            ++s;
        }
        return s;
    }
}