class Solution {
public:
    int minAreaRect(vector<vector<int>>& points) {
        map<int, vector<int>> d;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            d[x].emplace_back(y);
        }
        unordered_map<int, int> pos;
        int ans = 1 << 30;
        for (auto& [x, ys] : d) {
            sort(ys.begin(), ys.end());
            int n = ys.size();
            for (int i = 0; i < n; ++i) {
                int y1 = ys[i];
                for (int j = i + 1; j < n; ++j) {
                    int y2 = ys[j];
                    int p = y1 * 40001 + y2;
                    if (pos.count(p)) {
                        ans = min(ans, (x - pos[p]) * (y2 - y1));
                    }
                    pos[p] = x;
                }
            }
        }
        return ans == 1 << 30 ? 0 : ans;
    }
};