class Solution {
public:
    string repeatLimitedString(string s, int repeatLimit) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        string ans;
        for (int i = 25, j = 24; ~i; --i) {
            j = min(j, i - 1);
            while (1) {
                for (int k = min(cnt[i], repeatLimit); k; --k) {
                    ans += 'a' + i;
                    --cnt[i];
                }
                if (cnt[i] == 0) {
                    break;
                }
                while (j >= 0 && cnt[j] == 0) {
                    --j;
                }
                if (j < 0) {
                    break;
                }
                ans += 'a' + j;
                --cnt[j];
            }
        }
        return ans;
    }
};