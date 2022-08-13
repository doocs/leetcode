class Solution {
public:
    bool canPermutePalindrome(string s) {
        vector<int> counter(26);
        for (auto& c : s) ++counter[c - 'a'];
        int oddCnt = 0;
        for (int& cnt : counter) oddCnt += cnt % 2;
        return oddCnt < 2;
    }
};