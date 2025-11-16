class Solution {
public:
    int numSub(string s) {
        const int mod = 1e9 + 7;
        int ans = 0, cur = 0;
        for (char c : s) {
            if (c == '0') {
                cur = 0;
            } else {
                cur++;
                ans = (ans + cur) % mod;
            }
        }
        return ans;
    }
};
