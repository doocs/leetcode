class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_map<char, int> counter;
        for (char c : s) ++counter[c];
        int oddCnt = 0;
        for (auto& it : counter) oddCnt += it.second % 2;
        return oddCnt < 2;
    }
};