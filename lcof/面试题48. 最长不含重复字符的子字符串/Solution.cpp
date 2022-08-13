class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int res = 0;
        unordered_set<char> chars;
        for (int i = 0, j = 0; i < s.size(); ++i) {
            while (chars.count(s[i])) {
                chars.erase(s[j++]);
            }
            chars.insert(s[i]);
            res = max(res, i - j + 1);
        }
        return res;
    }
};