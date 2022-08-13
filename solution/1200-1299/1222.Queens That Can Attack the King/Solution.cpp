class Solution {
public:
    vector<vector<int>> queensAttacktheKing(vector<vector<int>>& queens, vector<int>& king) {
        unordered_set<int> s;
        int n = 8;
        for (auto& queen : queens) s.insert(queen[0] * n + queen[1]);
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        vector<vector<int>> ans;
        for (auto& dir : dirs) {
            int x = king[0], y = king[1];
            int a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < n && y + b >= 0 && y + b < n) {
                x += a;
                y += b;
                if (s.count(x * n + y)) {
                    ans.push_back({x, y});
                    break;
                }
            }
        }
        return ans;
    }
};