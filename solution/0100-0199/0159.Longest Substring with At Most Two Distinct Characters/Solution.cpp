class Solution {
public:
    int lengthOfLongestSubstringTwoDistinct(string s) {
        unordered_map<char, int> mp;
        int i = 0, j = 0, ans = 0;
        for (char& c : s) {
            ++mp[c];
            while (mp.size() > 2) {
                --mp[s[i]];
                if (mp[s[i]] == 0) mp.erase(s[i]);
                ++i;
            }
            ans = max(ans, j - i + 1);
            ++j;
        }
        return ans;
    }
};