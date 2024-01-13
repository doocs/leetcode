class Solution {
public:
    int findMaxValueOfEquation(vector<vector<int>>& points, int k) {
        int ans = -(1 << 30);
        deque<pair<int, int>> q;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            while (!q.empty() && x - q.front().first > k) {
                q.pop_front();
            }
            if (!q.empty()) {
                ans = max(ans, x + y + q.front().second - q.front().first);
            }
            while (!q.empty() && y - x >= q.back().second - q.back().first) {
                q.pop_back();
            }
            q.emplace_back(x, y);
        }
        return ans;
    }
};