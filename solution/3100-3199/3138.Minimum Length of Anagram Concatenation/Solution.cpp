class Solution {
public:
    int minAnagramLength(string s) {
        int n = s.size();
        int cnt[26]{};
        for (char c : s) {
            cnt[c - 'a']++;
        }
        auto check = [&](int k) {
            for (int i = 0; i < n; i += k) {
                int cnt1[26]{};
                for (int j = i; j < i + k; ++j) {
                    cnt1[s[j] - 'a']++;
                }
                for (int j = 0; j < 26; ++j) {
                    if (cnt1[j] * (n / k) != cnt[j]) {
                        return false;
                    }
                }
            }
            return true;
        };
        for (int i = 1;; ++i) {
            if (n % i == 0 && check(i)) {
                return i;
            }
        }
    }
};