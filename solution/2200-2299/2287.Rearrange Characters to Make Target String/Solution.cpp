class Solution {
public:
    int rearrangeCharacters(string s, string target) {
        int cnt1[26]{};
        int cnt2[26]{};
        for (char& c : s) {
            ++cnt1[c - 'a'];
        }
        for (char& c : target) {
            ++cnt2[c - 'a'];
        }
        int ans = 100;
        for (int i = 0; i < 26; ++i) {
            if (cnt2[i]) {
                ans = min(ans, cnt1[i] / cnt2[i]);
            }
        }
        return ans;
    }
};