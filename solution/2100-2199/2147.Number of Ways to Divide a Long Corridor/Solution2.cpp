class Solution {
public:
    int numberOfWays(string corridor) {
        const int mod = 1e9 + 7;
        long long ans = 1;
        int cnt = 0, last = 0;
        for (int i = 0; i < corridor.length(); ++i) {
            if (corridor[i] == 'S') {
                if (++cnt > 2 && cnt % 2) {
                    ans = ans * (i - last) % mod;
                }
                last = i;
            }
        }
        return cnt > 0 && cnt % 2 == 0 ? ans : 0;
    }
};
