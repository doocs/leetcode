class Solution {
public:
    string firstPalindrome(vector<string>& words) {
        for (auto& word : words)
            if (check(word)) return word;
        return "";
    }

    bool check(string s) {
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j)
            if (s[i] != s[j]) return false;
        return true;
    }
};