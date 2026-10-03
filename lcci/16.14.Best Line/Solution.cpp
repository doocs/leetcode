class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        vector<int> ans = {0, 1};
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    continue;
                }
                int cnt = 0;
                int a = -1, b = -1;
                for (int k = 0; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    long c1 = (long) (y2 - y1) * (x3 - x1);
                    long c2 = (long) (y3 - y1) * (x2 - x1);
                    if (c1 == c2) {
                        ++cnt;
                        if (a < 0) {
                            a = k;
                        } else if (b < 0) {
                            b = k;
                        }
                    }
                }
                if (cnt > mx || (cnt == mx && (a < ans[0] || (a == ans[0] && b < ans[1])))) {
                    mx = cnt;
                    ans[0] = a;
                    ans[1] = b;
                }
            }
        }
        return ans;
    }
};
