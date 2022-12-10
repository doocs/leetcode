class Solution {
public:
    int countPalindromicSubsequence(string s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.find_first_of(c), r = s.find_last_of(c);
            unordered_set<char> cs;
            for (int i = l + 1; i < r; ++i) cs.insert(s[i]);
            ans += cs.size();
        }
        return ans;
    }
};