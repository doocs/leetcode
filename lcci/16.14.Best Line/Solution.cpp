class Solution {
public:
    vector<int> bestLine(vector<vector<int>>& points) {
        int n = points.size();
        int mx = 0;
        vector<int> ans(2);
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int cnt = 2;
                for (int k = j + 1; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    long a = (long) (y2 - y1) * (x3 - x1);
                    long b = (long) (y3 - y1) * (x2 - x1);
                    cnt += a == b;
                }
                if (mx < cnt) {
                    mx = cnt;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        return ans;
    }
};