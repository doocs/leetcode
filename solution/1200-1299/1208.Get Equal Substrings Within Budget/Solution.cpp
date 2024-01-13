class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.size();
        int f[n + 1];
        f[0] = 0;
        for (int i = 0; i < n; ++i) {
            f[i + 1] = f[i] + abs(s[i] - t[i]);
        }
        auto check = [&](int x) -> bool {
            for (int i = 0; i + x - 1 < n; ++i) {
                int j = i + x - 1;
                if (f[j + 1] - f[i] <= maxCost) {
                    return true;
                }
            }
            return false;
        };
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};