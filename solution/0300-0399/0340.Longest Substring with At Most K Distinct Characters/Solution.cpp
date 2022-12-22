class Solution {
public:
    int lengthOfLongestSubstringKDistinct(string s, int k) {
        unordered_map<char, int> cnt;
        int n = s.size();
        int ans = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            cnt[s[i]]++;
            while (cnt.size() > k) {
                if (--cnt[s[j]] == 0) {
                    cnt.erase(s[j]);
                }
                ++j;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};