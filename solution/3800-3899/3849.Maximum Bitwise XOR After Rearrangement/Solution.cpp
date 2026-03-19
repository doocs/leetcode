class Solution {
public:
    string maximumXor(string s, string t) {
        int cnt[2]{};
        for (char c : t) {
            cnt[c - '0']++;
        }

        string ans(s.size(), '0');
        for (int i = 0; i < s.size(); i++) {
            int x = s[i] - '0';
            if (cnt[x ^ 1] > 0) {
                cnt[x ^ 1]--;
                ans[i] = '1';
            } else {
                cnt[x]--;
            }
        }

        return ans;
    }
};
