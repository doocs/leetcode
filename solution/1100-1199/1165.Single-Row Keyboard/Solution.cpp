class Solution {
public:
    int calculateTime(string keyboard, string word) {
        int pos[26];
        for (int i = 0; i < 26; ++i) {
            pos[keyboard[i] - 'a'] = i;
        }
        int ans = 0, i = 0;
        for (char& c : word) {
            int j = pos[c - 'a'];
            ans += abs(i - j);
            i = j;
        }
        return ans;
    }
};