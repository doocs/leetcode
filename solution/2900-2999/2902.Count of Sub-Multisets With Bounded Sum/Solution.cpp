class Solution {
public:
    int countSubMultisets(const vector<int> &nums, int l, int r) {
        int cnt[20001] = {};
        int memo[20001] = {};
        const int mod = 1000000007;
        for (int n : nums) {
            ++cnt[n];
        }
        fill_n(memo, cnt[1] + 1, 1);
        for (int n = 2, total = cnt[1]; n <= r; ++n) {
            if (!cnt[n]) {
                continue;
            }
            int top = (cnt[n] + 1) * n;
            total += n * cnt[n];
            for (int i = n, ii = min(total, r); i <= ii; ++i) {
                memo[i] = (memo[i] + memo[i - n]) % mod;
            }
            for (int i = min(total, r); i >= top; --i) {
                memo[i] = (mod + memo[i] - memo[i - top]) % mod;
            }
        }
        return accumulate(memo + l, memo + r + 1, 0LL) * (cnt[0] + 1) % mod;
    }
};
