class Solution {
public:
    vector<int> pourWater(vector<int>& heights, int volume, int k) {
        while (volume--) {
            bool find = false;
            for (int d = -1; d < 2 && !find; d += 2) {
                int i = k, j = k;
                while (i + d >= 0 && i + d < heights.size() && heights[i + d] <= heights[i]) {
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
};