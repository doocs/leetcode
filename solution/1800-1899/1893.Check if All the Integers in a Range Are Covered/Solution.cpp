class Solution {
public:
    bool isCovered(vector<vector<int>>& ranges, int left, int right) {
        int diff[52]{};
        for (auto& range : ranges) {
            int l = range[0], r = range[1];
            ++diff[l];
            --diff[r + 1];
        }
        int cur = 0;
        for (int i = 0; i < 52; ++i) {
            cur += diff[i];
            if (i >= left && i <= right && cur <= 0) {
                return false;
            }
        }
        return true;
    }
};