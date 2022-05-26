class Solution {
public:
    vector<int> countPoints(vector<vector<int>>& points, vector<vector<int>>& queries) {
        vector<int> ans;
        for (auto& query : queries) {
            int x0 = query[0], y0 = query[1], r = query[2];
            int count = 0;
            for (auto& point : points) {
                int x = point[0], y = point[1];
                int dx = x - x0, dy = y - y0;
                if (dx * dx + dy * dy <= r * r) {
                    ++count;
                }
            }
            ans.push_back(count);
        }
        return ans;
    }
};