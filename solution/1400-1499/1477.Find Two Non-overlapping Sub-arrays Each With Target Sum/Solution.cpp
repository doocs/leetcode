class Solution {
public:
    int minSumOfLengths(vector<int>& arr, int target) {
        unordered_map<int, int> d;
        d[0] = 0;
        int s = 0, n = arr.size();
        int f[n + 1];
        const int inf = 1 << 30;
        f[0] = inf;
        int ans = inf;
        for (int i = 1; i <= n; ++i) {
            int v = arr[i - 1];
            s += v;
            f[i] = f[i - 1];
            if (d.count(s - target)) {
                int j = d[s - target];
                f[i] = min(f[i], i - j);
                ans = min(ans, f[j] + i - j);
            }
            d[s] = i;
        }
        return ans > n ? -1 : ans;
    }
};