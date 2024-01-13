class Solution {
public:
    int numberOfWays(int numPeople) {
        const int mod = 1e9 + 7;
        int f[numPeople + 1];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i < 2) {
                return 1;
            }
            if (f[i]) {
                return f[i];
            }
            for (int l = 0; l < i; l += 2) {
                int r = i - l - 2;
                f[i] = (f[i] + 1LL * dfs(l) * dfs(r) % mod) % mod;
            }
            return f[i];
        };
        return dfs(numPeople);
    }
};