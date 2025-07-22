class Solution {
public:
    long long validSubstringCount(string word1, string word2) {
        if (word1.size() < word2.size()) {
            return 0;
        }
        int cnt[26]{};
        int need = 0;
        for (char& c : word2) {
            if (++cnt[c - 'a'] == 1) {
                ++need;
            }
        }
        long long ans = 0;
        int win[26]{};
        int l = 0;
        for (char& c : word1) {
            int i = c - 'a';
            if (++win[i] == cnt[i]) {
                --need;
            }
            while (need == 0) {
                i = word1[l] - 'a';
                if (win[i] == cnt[i]) {
                    ++need;
                }
                --win[i];
                ++l;
            }
            ans += l;
        }
        return ans;
    }
};
