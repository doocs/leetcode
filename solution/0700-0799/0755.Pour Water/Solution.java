class Solution {
    public int[] pourWater(int[] heights, int volume, int k) {
        while (volume-- > 0) {
            boolean find = false;
            for (int d = -1; d < 2 && !find; d += 2) {
                int i = k, j = k;
                while (i + d >= 0 && i + d < heights.length && heights[i + d] <= heights[i]) {
                    if (heights[i + d] < heights[i]) {
                        j = i + d;
                    }
                    i += d;
                }
                if (j != k) {
                    find = true;
                    ++heights[j];
                }
            }
            if (!find) {
                ++heights[k];
            }
        }
        return heights;
    }
}