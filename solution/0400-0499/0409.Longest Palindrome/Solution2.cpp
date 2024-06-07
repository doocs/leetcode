class Solution {
public:
    int longestPalindrome(string s) {
        int odd[128]{};
        int n = s.length();
        int cnt = 0;
        for (char& c : s) {
            odd[c] ^= 1;
            cnt += odd[c] ? 1 : -1;
        }
        return cnt ? n - cnt + 1 : n;
    }
};
