class Solution {
    public int countSpecialIntegers(int[] nums) {
        int[] cnt = new int[101];
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                ++cnt[nums[i]];
            }
        }
        int ans = 0;
        for (int c : cnt) {
            if (c == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
