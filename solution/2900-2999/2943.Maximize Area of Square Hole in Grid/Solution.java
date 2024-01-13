class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int x = Math.min(f(hBars), f(vBars));
        return x * x;
    }

    private int f(int[] nums) {
        Arrays.sort(nums);
        int ans = 1, cnt = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1] + 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans + 1;
    }
}