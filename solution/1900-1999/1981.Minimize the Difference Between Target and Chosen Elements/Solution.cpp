class Solution {
public:
    int minimizeTheDifference(vector<vector<int>>& mat, int target) {
        vector<int> f = {1};
        for (auto& row : mat) {
            int mx = *max_element(row.begin(), row.end());
            vector<int> g(f.size() + mx);
            for (int x : row) {
                for (int j = x; j < f.size() + x; ++j) {
                    g[j] |= f[j - x];
                }
            }
            f = move(g);
        }
        int ans = 1 << 30;
        for (int j = 0; j < f.size(); ++j) {
            if (f[j]) {
                ans = min(ans, abs(j - target));
            }
        }
        return ans;
    }
};