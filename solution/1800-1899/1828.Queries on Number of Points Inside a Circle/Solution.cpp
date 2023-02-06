class Solution {
public:
    vector<int> countPoints(vector<vector<int>>& points, vector<vector<int>>& queries) {
        vector<int> ans;
        for (auto& q : queries) {
            int x = q[0], y = q[1], r = q[2];
            int cnt = 0;
            for (auto& p : points) {
                int i = p[0], j = p[1];
                int dx = i - x, dy = j - y;
                cnt += dx * dx + dy * dy <= r * r;
            }
            ans.emplace_back(cnt);
        }
        return ans;
    }
};