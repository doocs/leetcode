class Solution {
public:
    string smallestNumber(long long n) {
        int cnt[10]{};
        for (int i = 9; i > 1; --i) {
            while (n % i == 0) {
                n /= i;
                ++cnt[i];
            }
        }
        if (n > 1) {
            return "-1";
        }
        string ans;
        for (int i = 2; i < 10; ++i) {
            ans += string(cnt[i], '0' + i);
        }
        return ans == "" ? "1" : ans;
    }
};