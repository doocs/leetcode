class Solution {
public:
    int change(int amount, vector<int>& coins) {
        int n = amount;
        unsigned f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int x : coins) {
            for (int j = x; j <= n; ++j) {
                f[j] += f[j - x];
            }
        }
        return f[n];
    }
};