class Solution {
public:
    int longestDecomposition(string text) {
        using ull = unsigned long long;
        int n = text.size();
        int base = 131;
        ull p[n + 10];
        ull h[n + 10];
        p[0] = 1;
        h[0] = 0;
        for (int i = 0; i < n; ++i) {
            int t = text[i] - 'a' + 1;
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + t;
        }

        int ans = 0;
        auto get = [&](int l, int r) {
            return h[r] - h[l - 1] * p[r - l + 1];
        };
        for (int i = 0, j = n - 1; i <= j;) {
            bool ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (get(i + 1, i + k) == get(j - k + 2, j + 1)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ++ans;
                break;
            }
        }
        return ans;
    }
};