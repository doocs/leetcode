class Solution {
    public int removeDuplicates(int[] nums) {
        int cnt = 0, cur = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) ++cnt;
            else cnt = 0;
            if (cnt < 2) nums[cur++] = nums[i];
        }
        return cur;
    }
}