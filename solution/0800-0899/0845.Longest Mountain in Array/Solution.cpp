class Solution {
public:
    int longestMountain(vector<int>& arr) {
        int n = arr.size();
        int f[n];
        int g[n];
        fill(f, f + n, 1);
        fill(g, g + n, 1);
        for (int i = 1; i < n; ++i) {
            if (arr[i] > arr[i - 1]) {
                f[i] = f[i - 1] + 1;
            }
        }
        int ans = 0;
        for (int i = n - 2; ~i; --i) {
            if (arr[i] > arr[i + 1]) {
                g[i] = g[i + 1] + 1;
                if (f[i] > 1) {
                    ans = max(ans, f[i] + g[i] - 1);
                }
            }
        }
        return ans;
    }
};