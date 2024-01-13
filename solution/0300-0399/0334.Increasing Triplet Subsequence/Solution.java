class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] lmi = new int[n];
        int[] rmx = new int[n];
        lmi[0] = Integer.MAX_VALUE;
        rmx[n - 1] = Integer.MIN_VALUE;
        for (int i = 1; i < n; ++i) {
            lmi[i] = Math.min(lmi[i - 1], nums[i - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            rmx[i] = Math.max(rmx[i + 1], nums[i + 1]);
        }
        for (int i = 0; i < n; ++i) {
            if (lmi[i] < nums[i] && nums[i] < rmx[i]) {
                return true;
            }
        }
        return false;
    }
}