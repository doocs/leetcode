class Solution {
public:
    vector<int> countBits(int n) {
        vector<int> f(n + 1);
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i & (i - 1)] + 1;
        }
        return f;
    }
};