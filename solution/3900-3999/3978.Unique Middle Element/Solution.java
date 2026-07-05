class Solution {
    public boolean isMiddleElementUnique(int[] nums) {
        int cnt = 0;
        for (int x : nums) {
            if (x == nums[nums.length / 2]) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}