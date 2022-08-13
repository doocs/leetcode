class Solution {
public:
    vector<vector<int>> findWinners(vector<vector<int>>& matches) {
        unordered_map<int, int> cnt;
        for (auto& m : matches) {
            int a = m[0], b = m[1];
            if (!cnt.count(a)) cnt[a] = 0;
            ++cnt[b];
        }
        vector<vector<int>> ans(2);
        for (auto& [u, v] : cnt) {
            if (v < 2) ans[v].push_back(u);
        }
        sort(ans[0].begin(), ans[0].end());
        sort(ans[1].begin(), ans[1].end());
        return ans;
    }
};