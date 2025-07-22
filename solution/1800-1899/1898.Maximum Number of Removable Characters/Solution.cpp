class Solution {
public:
    int maximumRemovals(string s, string p, vector<int>& removable) {
        int m = s.size(), n = p.size();
        int l = 0, r = removable.size();
        bool rem[m];

        auto check = [&](int k) {
            memset(rem, false, sizeof(rem));
            for (int i = 0; i < k; i++) {
                rem[removable[i]] = true;
            }
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (!rem[i] && s[i] == p[j]) {
                    ++j;
                }
                ++i;
            }
            return j == n;
        };
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
