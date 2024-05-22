class Solution {
public:
    vector<vector<int>> findWinners(vector<vector<int>>& matches) {
        map<int, int> cnt;
        for (auto& e : matches) {
            if (!cnt.contains(e[0])) {
                cnt[e[0]] = 0;
            }
            ++cnt[e[1]];
        }
        vector<vector<int>> ans(2);
        for (auto& [x, v] : cnt) {
            if (v < 2) {
                ans[v].push_back(x);
            }
        }
        return ans;
    }
};