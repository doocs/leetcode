class Solution {
    public boolean canSortArray(int[] nums) {
        int preMx = -300;
        int i = 0, n = nums.length;
        while (i < n) {
            int j = i + 1;
            int cnt = Integer.bitCount(nums[i]);
            int mi = nums[i], mx = nums[i];
            while (j < n && Integer.bitCount(nums[j]) == cnt) {
                mi = Math.min(mi, nums[j]);
                mx = Math.max(mx, nums[j]);
                j++;
            }
            if (preMx > mi) {
                return false;
            }
            preMx = mx;
            i = j;
        }
        return true;
    }
}