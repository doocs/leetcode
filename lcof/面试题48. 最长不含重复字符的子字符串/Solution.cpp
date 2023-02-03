class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int ans = 0;
        unordered_set<char> vis;
        for (int i = 0, j = 0; i < s.size(); ++i) {
            while (vis.count(s[i])) {
                vis.erase(s[j++]);
            }
            vis.insert(s[i]);
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};