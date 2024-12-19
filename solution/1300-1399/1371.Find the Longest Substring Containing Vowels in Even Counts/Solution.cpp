class Solution {
public:
    int findTheLongestSubstring(string s) {
        string vowels = "aeiou";
        vector<int> d(32, INT_MAX);
        d[0] = 0;
        int ans = 0, mask = 0;
        for (int i = 1; i <= s.length(); ++i) {
            char c = s[i - 1];
            for (int j = 0; j < 5; ++j) {
                if (c == vowels[j]) {
                    mask ^= 1 << j;
                    break;
                }
            }
            ans = max(ans, i - d[mask]);
            d[mask] = min(d[mask], i);
        }
        return ans;
    }
};
