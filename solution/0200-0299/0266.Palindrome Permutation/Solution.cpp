class Solution {
public:
    bool canPermutePalindrome(string s) {
        vector<int> counter(26);
        for (auto& c : s) ++counter[c - 'a'];
        int oddCnt = 0;
        for (int& cnt : counter)
            if (cnt % 2 == 1)
                ++oddCnt;
        return oddCnt < 2;
    }
};