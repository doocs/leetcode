class Solution {
    public boolean canSortArray(int[] nums) {
        int preMx = 0;
        int i = 0, n = nums.length;
        while (i < n) {
            int cnt = Integer.bitCount(nums[i]);
            int j = i + 1;
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