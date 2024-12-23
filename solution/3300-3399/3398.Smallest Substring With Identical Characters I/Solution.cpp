class Solution {
public:
    int minLength(string s, int numOps) {
        int n = s.size();
        auto check = [&](int m) {
            int cnt = 0;
            if (m == 1) {
                string t = "01";
                for (int i = 0; i < n; ++i) {
                    if (s[i] == t[i & 1]) {
                        ++cnt;
                    }
                }
                cnt = min(cnt, n - cnt);
            } else {
                int k = 0;
                for (int i = 0; i < n; ++i) {
                    ++k;
                    if (i == n - 1 || s[i] != s[i + 1]) {
                        cnt += k / (m + 1);
                        k = 0;
                    }
                }
            }
            return cnt <= numOps;
        };
        int l = 1, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
