class Solution {
public:
    int maxDistinct(string s) {
        int ans = 0;
        int cnt[26]{};
        for (char c : s) {
            if (++cnt[c - 'a'] == 1) {
                ++ans;
            }
        }
        return ans;
    }
};
