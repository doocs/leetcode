class Solution {
public:
    int countCornerRectangles(vector<vector<int>>& grid) {
        int n = grid[0].size();
        int ans = 0;
        map<pair<int, int>, int> cnt;
        for (auto& row : grid) {
            for (int i = 0; i < n; ++i) {
                if (row[i]) {
                    for (int j = i + 1; j < n; ++j) {
                        if (row[j]) {
                            ans += cnt[{i, j}];
                            ++cnt[{i, j}];
                        }
                    }
                }
            }
        }
        return ans;
    }
};