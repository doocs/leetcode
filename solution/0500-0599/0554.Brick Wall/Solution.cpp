class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        unordered_map<int, int> cnt;
        for (const auto& row : wall) {
            int s = 0;
            for (int i = 0; i + 1 < row.size(); ++i) {
                s += row[i];
                cnt[s]++;
            }
        }
        int mx = 0;
        for (const auto& [_, x] : cnt) {
            mx = max(mx, x);
        }
        return wall.size() - mx;
    }
};
