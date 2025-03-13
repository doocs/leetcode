class Solution {
public:
    int minimumSum(int n, int k) {
        int s = 0, i = 1;
        bool vis[n + k + 1];
        memset(vis, false, sizeof(vis));
        while (n--) {
            while (vis[i]) {
                ++i;
            }
            if (k >= i) {
                vis[k - i] = true;
            }
            s += i++;
        }
        return s;
    }
};
