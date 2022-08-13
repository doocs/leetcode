class Solution {
public:
    int findMaxValueOfEquation(vector<vector<int>>& points, int k) {
        deque<vector<int>> q;
        int ans = INT_MIN;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            while (!q.empty() && x - q.front()[0] > k) q.pop_front();
            if (!q.empty()) ans = max(ans, y + x + q.front()[1] - q.front()[0]);
            while (!q.empty() && y - x > q.back()[1] - q.back()[0]) q.pop_back();
            q.push_back(p);
        }
        return ans;
    }
};