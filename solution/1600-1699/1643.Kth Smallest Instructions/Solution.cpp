class Solution {
public:
    string kthSmallestPath(vector<int>& destination, int k) {
        int v = destination[0], h = destination[1];
        int n = v + h;
        int c[n + 1][h + 1];
        memset(c, 0, sizeof(c));
        c[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= h; ++j) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            if (h == 0) {
                ans.push_back('V');
            } else {
                int x = c[v + h - 1][h - 1];
                if (k > x) {
                    ans.push_back('V');
                    --v;
                    k -= x;
                } else {
                    ans.push_back('H');
                    --h;
                }
            }
        }
        return ans;
    }
};