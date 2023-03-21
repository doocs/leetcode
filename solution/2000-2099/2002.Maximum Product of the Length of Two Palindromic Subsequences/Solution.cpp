class Solution {
public:
    int maxProduct(string s) {
        int n = s.size();
        vector<bool> p(1 << n, true);
        for (int k = 1; k < 1 << n; ++k) {
            for (int i = 0, j = n - 1; i < j; ++i, --j) {
                while (i < j && !(k >> i & 1)) {
                    ++i;
                }
                while (i < j && !(k >> j & 1)) {
                    --j;
                }
                if (i < j && s[i] != s[j]) {
                    p[k] = false;
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < 1 << n; ++i) {
            if (p[i]) {
                int a = __builtin_popcount(i);
                int mx = ((1 << n) - 1) ^ i;
                for (int j = mx; j; j = (j - 1) & mx) {
                    if (p[j]) {
                        int b = __builtin_popcount(j);
                        ans = max(ans, a * b);
                    }
                }
            }
        }
        return ans;
    }
};