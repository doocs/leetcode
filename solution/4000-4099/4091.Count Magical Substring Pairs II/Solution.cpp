class Solution {
    vector<array<long long, 27>> calc(const string& s) {
        int n = s.size();
        vector<array<long long, 27>> f(n);
        for (int k = 1; k <= 26; k++) {
            int cnt[26] = {};
            int l = 0, distinct = 0;
            for (int r = 0; r < n; r++) {
                if (cnt[s[r] - 'a']++ == 0) {
                    distinct++;
                }
                while (distinct > k) {
                    if (--cnt[s[l++] - 'a'] == 0) {
                        distinct--;
                    }
                }
                f[r][k] = r - l + 1LL;
            }
        }
        return f;
    }

public:
    long long countMagicalPairs(string s) {
        int n = s.size();
        auto left = calc(s);
        reverse(s.begin(), s.end());
        auto right = calc(s);
        long long ans = 0;
        for (int j = 0; j < n - 1; j++) {
            int r = n - 2 - j;
            for (int k = 1; k <= 26; k++) {
                ans += (left[j][k] - left[j][k - 1])
                    * (right[r][k] - right[r][k - 1]);
            }
        }
        return ans;
    }
};