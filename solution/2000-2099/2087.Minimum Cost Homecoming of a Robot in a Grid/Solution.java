class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int i = startPos[0], j = startPos[1];
        int x = homePos[0], y = homePos[1];
        int ans = 0;
        if (i < x) {
            for (int k = i + 1; k <= x; ++k) {
                ans += rowCosts[k];
            }
        } else {
            for (int k = x; k < i; ++k) {
                ans += rowCosts[k];
            }
        }
        if (j < y) {
            for (int k = j + 1; k <= y; ++k) {
                ans += colCosts[k];
            }
        } else {
            for (int k = y; k < j; ++k) {
                ans += colCosts[k];
            }
        }
        return ans;
    }
}