class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        pair<int, int> ans = {0, 0};
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            unordered_map<string, vector<pair<int, int>>> cnt;
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                int g = gcd(dx, dy);
                string k = to_string(dx / g) + "." + to_string(dy / g);
                cnt[k].push_back({i, j});
                if (mx < cnt[k].size() || (mx == cnt[k].size() && ans > cnt[k][0])) {
                    mx = cnt[k].size();
                    ans = cnt[k][0];
                }
            }
        }
        return vector<int>{ans.first, ans.second};
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};