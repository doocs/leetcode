class Solution {
public:
    int maxSumAfterPartitioning(vector<int>& arr, int k) {
        int n = arr.size();
        int f[n + 1];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) {
            int mx = 0;
            for (int j = i; j >= max(0, i - k + 1); --j) {
                mx = max(mx, arr[j]);
                int t = mx * (i - j + 1) + f[j];
                f[i + 1] = max(f[i + 1], t);
            }
        }
        return f[n];
    }
};