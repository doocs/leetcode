class Solution {
public:
    int winningPlayerCount(int n, vector<vector<int>>& pick) {
        int cnt[10][11]{};
        unordered_set<int> s;
        for (const auto& p : pick) {
            int x = p[0], y = p[1];
            if (++cnt[x][y] > x) {
                s.insert(x);
            }
        }
        return s.size();
    }
};
