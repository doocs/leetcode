class Solution {
public:
    int findBlackPixel(vector<vector<char>>& picture, int target) {
        int m = picture.size();
        int n = picture[0].size();
        vector<int> g[n];
        vector<int> rows(m);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    g[j].push_back(i);
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < n; ++j) {
            if (g[j].empty() || (rows[g[j][0]] != target)) {
                continue;
            }
            int i1 = g[j][0];
            int ok = 0;
            if (g[j].size() == rows[i1]) {
                ok = target;
                for (int i2 : g[j]) {
                    if (picture[i1] != picture[i2]) {
                        ok = 0;
                        break;
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
};