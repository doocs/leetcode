class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int j = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 1) {
                if (j != -1 && i - j - 1 < k) {
                    return false;
                }
                j = i;
            }
        }
        return true;
    }
}