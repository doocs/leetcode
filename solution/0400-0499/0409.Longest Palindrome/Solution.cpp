class Solution {
public:
    int longestPalindrome(string s) {
        vector<int> counter(128);
        for (char c : s) ++counter[c];
        int oddCnt = 0;
        for (int e : counter) oddCnt += e % 2;
        int n = s.size();
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
};