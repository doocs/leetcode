class Solution {
public:
    vector<int> bestCoordinate(vector<vector<int>>& towers, int radius) {
        int mx = 0;
        vector<int> ans = {0, 0};
        for (int i = 0; i < 51; ++i) {
            for (int j = 0; j < 51; ++j) {
                int t = 0;
                for (auto& e : towers) {
                    double d = sqrt((i - e[0]) * (i - e[0]) + (j - e[1]) * (j - e[1]));
                    if (d <= radius) {
                        t += floor(e[2] / (1 + d));
                    }
                }
                if (mx < t) {
                    mx = t;
                    ans = {i, j};
                }
            }
        }
        return ans;
    }
};