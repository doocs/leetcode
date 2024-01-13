class Solution {
public:
    vector<long long> countBlackBlocks(int m, int n, vector<vector<int>>& coordinates) {
        unordered_map<long long, int> cnt;
        int dirs[5] = {0, 0, -1, -1, 0};
        for (auto& e : coordinates) {
            int x = e[0], y = e[1];
            for (int k = 0; k < 4; ++k) {
                int i = x + dirs[k], j = y + dirs[k + 1];
                if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                    ++cnt[1LL * i * n + j];
                }
            }
        }
        vector<long long> ans(5);
        ans[0] = (m - 1LL) * (n - 1);
        for (auto& [_, x] : cnt) {
            ++ans[x];
            --ans[0];
        }
        return ans;
    }
};