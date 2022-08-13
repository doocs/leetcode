class Solution {
public:
    long long numberOfWays(string s) {
        int n = s.size();
        int cnt0 = 0;
        for (char& c : s) cnt0 += c == '0';
        int cnt1 = n - cnt0;
        int c0 = 0, c1 = 0;
        long long ans = 0;
        for (char& c : s) {
            if (c == '0') {
                ans += c1 * (cnt1 - c1);
                ++c0;
            } else {
                ans += c0 * (cnt0 - c0);
                ++c1;
            }
        }
        return ans;
    }
};