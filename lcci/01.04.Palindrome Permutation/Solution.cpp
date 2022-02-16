class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_map<char, int> counter;
        for (char c : s) ++counter[c];
        int cnt = 0;
        for (auto& [k, v] : counter) cnt += v % 2;
        return cnt < 2;
    }
};