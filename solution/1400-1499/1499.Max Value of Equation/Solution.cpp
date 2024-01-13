class Solution {
public:
    int findMaxValueOfEquation(vector<vector<int>>& points, int k) {
        int ans = -(1 << 30);
        priority_queue<pair<int, int>> pq;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            while (pq.size() && x - pq.top().second > k) {
                pq.pop();
            }
            if (pq.size()) {
                ans = max(ans, x + y + pq.top().first);
            }
            pq.emplace(y - x, x);
        }
        return ans;
    }
};