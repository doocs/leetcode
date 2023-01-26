class Solution {
public:
    int numSub(string s) {
        int ans = 0, cnt = 0;
        const int mod = 1e9 + 7;
        for (char& c : s) {
            cnt = c == '1' ? cnt + 1 : 0;
            ans = (ans + cnt) % mod;
        }
        return ans;
    }
};