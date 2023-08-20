class Solution {
public:
    int minimumSum(int n, int k) {
        int s = 0, i = 1;
        bool vis[k + n * n + 1];
        memset(vis, false, sizeof(vis));
        while (n--) {
            while (vis[i]) {
                ++i;
            }
            vis[i] = true;
            if (k >= i) {
                vis[k - i] = true;
            }
            s += i;
        }
        return s;
    }
};