class Solution {
public:
    vector<int> f;

    int minSteps(int n) {
        f.assign(n + 1, -1);
        return dfs(n);
    }

    int dfs(int n) {
        if (n == 1) return 0;
        if (f[n] != -1) return f[n];
        int ans = n;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                ans = min(ans, dfs(n / i) + i);
            }
        }
        f[n] = ans;
        return ans;
    }
};