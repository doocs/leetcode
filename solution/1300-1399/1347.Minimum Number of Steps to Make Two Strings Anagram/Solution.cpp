class Solution {
public:
    int minSteps(string s, string t) {
        int cnt[26]{};
        for (char& c : s) ++cnt[c - 'a'];
        int ans = 0;
        for (char& c : t) {
            ans += --cnt[c - 'a'] < 0;
        }
        return ans;
    }
};