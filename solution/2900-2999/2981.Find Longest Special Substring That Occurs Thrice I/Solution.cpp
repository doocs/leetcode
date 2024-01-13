class Solution {
public:
    int maximumLength(string s) {
        int n = s.size();
        int l = 0, r = n;
        auto check = [&](int x) {
            int cnt[26]{};
            for (int i = 0; i < n;) {
                int j = i + 1;
                while (j < n && s[j] == s[i]) {
                    ++j;
                }
                int k = s[i] - 'a';
                cnt[k] += max(0, j - i - x + 1);
                if (cnt[k] >= 3) {
                    return true;
                }
                i = j;
            }
            return false;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l == 0 ? -1 : l;
    }
};