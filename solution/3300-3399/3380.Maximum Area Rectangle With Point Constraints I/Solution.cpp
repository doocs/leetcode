class Solution {
public:
    int maxRectangleArea(vector<vector<int>>& points) {
        auto check = [&](int x1, int y1, int x2, int y2) -> bool {
            int cnt = 0;
            for (const auto& point : points) {
                int x = point[0];
                int y = point[1];
                if (x < x1 || x > x2 || y < y1 || y > y2) {
                    continue;
                }
                if ((x == x1 || x == x2) && (y == y1 || y == y2)) {
                    cnt++;
                    continue;
                }
                return false;
            }
            return cnt == 4;
        };

        int ans = -1;
        for (int i = 0; i < points.size(); i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < i; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                int x3 = min(x1, x2), y3 = min(y1, y2);
                int x4 = max(x1, x2), y4 = max(y1, y2);
                if (check(x3, y3, x4, y4)) {
                    ans = max(ans, (x4 - x3) * (y4 - y3));
                }
            }
        }
        return ans;
    }
};
