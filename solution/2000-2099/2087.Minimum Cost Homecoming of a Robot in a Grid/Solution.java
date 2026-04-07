class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int x0 = startPos[0], y0 = startPos[1];
        int x1 = homePos[0], y1 = homePos[1];
        int dx = x0 < x1 ? calc(rowCosts, x0 + 1, x1) : calc(rowCosts, x1, x0 - 1);
        int dy = y0 < y1 ? calc(colCosts, y0 + 1, y1) : calc(colCosts, y1, y0 - 1);
        return dx + dy;
    }

    private int calc(int[] nums, int i, int j) {
        int res = 0;
        for (int k = i; k <= j; ++k) {
            res += nums[k];
        }
        return res;
    }
}
