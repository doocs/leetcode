class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& grid) {
        int n = grid.size();
        int f = 0, g = 0, fp = -1;
        const int inf = 1 << 30;
        for (auto& row : grid) {
            int ff = inf, gg = inf;
            int ffp = -1;
            for (int j = 0; j < n; ++j) {
                int s = (fp != j ? f : g) + row[j];
                if (s < ff) {
                    gg = ff;
                    ff = s;
                    ffp = j;
                } else if (s < gg) {
                    gg = s;
                }
            }
            f = ff;
            g = gg;
            fp = ffp;
        }
        return f;
    }
};
