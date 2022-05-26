class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int i = 0, j = 0, ans = 0;
        unordered_set<char> chars;
        for (char& c : s)
        {
            while (chars.count(c)) chars.erase(s[i++]);
            chars.insert(c);
            ans = max(ans, j - i + 1);
            ++j;
        }
        return ans;
        
    }
};