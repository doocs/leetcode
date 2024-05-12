class Solution {
public:
    int maxPointsInsideSquare(vector<vector<int>>& points, string s) {
        map<int, vector<int>> g;
        for (int i = 0; i < points.size(); ++i) {
            auto& p = points[i];
            int key = max(abs(p[0]), abs(p[1]));
            g[key].push_back(i);
        }
        bool vis[26]{};
        int ans = 0;
        for (auto& [_, idx] : g) {
            for (int i : idx) {
                int j = s[i] - 'a';
                if (vis[j]) {
                    return ans;
                }
                vis[j] = true;
            }
            ans += idx.size();
        }
        return ans;
    }
};