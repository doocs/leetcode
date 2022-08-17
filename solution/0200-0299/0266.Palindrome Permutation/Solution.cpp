class Solution {
public:
    bool canPermutePalindrome(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        int n = 0;
        for (int& v : cnt) n += v & 1;
        return n < 2;
    }
};