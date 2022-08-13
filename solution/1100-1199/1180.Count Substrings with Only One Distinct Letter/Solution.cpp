class Solution {
public:
    int countLetters(string s) {
        int ans = 0;
        for (int i = 0, n = s.size(); i < n;) {
            int j = i;
            while (j < n && s[j] == s[i]) ++j;
            ans += (1 + j - i) * (j - i) / 2;
            i = j;
        }
        return ans;
    }
};