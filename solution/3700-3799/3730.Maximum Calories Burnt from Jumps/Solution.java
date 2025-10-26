class Solution {
    public long maxCaloriesBurnt(int[] heights) {
        Arrays.sort(heights);
        long ans = 0;
        int pre = 0;
        int r = heights.length - 1;
        for (int l = 0; l < r; ++l, --r) {
            ans += 1L * (heights[r] - pre) * (heights[r] - pre);
            ans += 1L * (heights[l] - heights[r]) * (heights[l] - heights[r]);
            pre = heights[l];
        }
        ans += 1L * (heights[r] - pre) * (heights[r] - pre);
        return ans;
    }
}
