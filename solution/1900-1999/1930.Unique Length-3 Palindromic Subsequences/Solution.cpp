class Solution {
public:
    int countPalindromicSubsequence(string s) {
        int res = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.find_first_of(c), r = s.find_last_of(c);
            unordered_set<char> chars;
            for (int i = l + 1; i < r; ++i) {
                chars.insert(s[i]);
            }
            res += chars.size();
        }
        return res;
    }
};