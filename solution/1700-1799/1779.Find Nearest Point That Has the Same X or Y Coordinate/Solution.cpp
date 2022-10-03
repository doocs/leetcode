class Solution {
public:
    int nearestValidPoint(int x, int y, vector<vector<int>>& points) {
        int ans = -1, mi = 1e6;
        for (int i = 0; i < points.size(); ++i) {
            int a = points[i][0], b = points[i][1];
            if (a == x || b == y) {
                int d = abs(a - x) + abs(b - y);
                if (d < mi) {
                    mi = d;
                    ans = i;
                }
            }
        }
        return ans;
    }
};