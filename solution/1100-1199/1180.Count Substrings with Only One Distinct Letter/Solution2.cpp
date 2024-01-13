class Solution {
public:
    int countLetters(string s) {
        int ans = 0;
        int i = 0, n = s.size();
        while (i < n) {
            int j = i;
            int cnt = 0;
            while (j < n && s[j] == s[i]) {
                ++j;
                ans += ++cnt;
            }
            i = j;
        }
        return ans;
    }
};