class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        pair<int, int> ans = {0, 0};
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            unordered_map<string, vector<int>> cnt;
            vector<int> dup;
            for (int j = i + 1; j < n; ++j) {
                int dx = points[j][0] - x1, dy = points[j][1] - y1;
                if (dx == 0 && dy == 0) {
                    dup.push_back(j);
                    continue;
                }
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                string k = to_string(dx) + "." + to_string(dy);
                cnt[k].push_back(j);
            }
            auto consider = [&](int b, int c) {
                if (c > mx || (c == mx && ans > pair<int, int>{i, b})) {
                    mx = c;
                    ans = {i, b};
                }
            };
            if (cnt.empty()) {
                if (!dup.empty()) {
                    consider(dup[0], (int) dup.size() + 1);
                }
                continue;
            }
            for (auto& e : cnt) {
                int b = e.second[0];
                if (!dup.empty()) {
                    b = min(b, dup[0]);
                }
                consider(b, (int) e.second.size() + (int) dup.size() + 1);
            }
        }
        return vector<int>{ans.first, ans.second};
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
