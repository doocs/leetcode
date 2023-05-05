class Solution {
public:
    int minNumberOfFrogs(string croakOfFrogs) {
        int n = croakOfFrogs.size();
        if (n % 5 != 0) {
            return -1;
        }
        int idx[26]{};
        string s = "croak";
        for (int i = 0; i < 5; ++i) {
            idx[s[i] - 'a'] = i;
        }
        int cnt[5]{};
        int ans = 0, x = 0;
        for (char& c : croakOfFrogs) {
            int i = idx[c - 'a'];
            ++cnt[i];
            if (i == 0) {
                ans = max(ans, ++x);
            } else {
                if (--cnt[i - 1] < 0) {
                    return -1;
                }
                if (i == 4) {
                    --x;
                }
            }
        }
        return x > 0 ? -1 : ans;
    }
};