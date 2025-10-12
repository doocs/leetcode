const int N = 31;
const long long MOD = 1'000'000'007;

long long f[N], g[N];

long long qpow(long long a, long long k) {
    long long res = 1;
    while (k) {
        if (k & 1) res = res * a % MOD;
        a = a * a % MOD;
        k >>= 1;
    }
    return res;
}

int init = []() {
    f[0] = g[0] = 1;
    for (int i = 1; i < N; ++i) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qpow(f[i], MOD - 2);
    }
    return 0;
}();

long long comb(int m, int n) {
    return f[m] * g[n] % MOD * g[m - n] % MOD;
}

class Solution {
    vector<vector<vector<vector<long long>>>> dp;

    long long dfs(int i, int j, int k, int st) {
        if (k < 0 || (i == nums.size() && j > 0)) {
            return 0;
        }
        if (i == nums.size()) {
            while (st > 0) {
                k -= (st & 1);
                st >>= 1;
            }
            return k == 0 ? 1 : 0;
        }

        long long& res = dp[i][j][k][st];
        if (res != -1) {
            return res;
        }

        res = 0;
        for (int t = 0; t <= j; ++t) {
            int nt = t + st;
            int nk = k - (nt & 1);
            long long p = qpow(nums[i], t);
            long long tmp = comb(j, t) * p % MOD * dfs(i + 1, j - t, nk, nt >> 1) % MOD;
            res = (res + tmp) % MOD;
        }
        return res;
    }

public:
    int magicalSum(int m, int k, vector<int>& nums) {
        int n = nums.size();
        this->nums = nums;
        dp.assign(n + 1, vector<vector<vector<long long>>>(m + 1, vector<vector<long long>>(k + 1, vector<long long>(N, -1))));
        return dfs(0, m, k, 0);
    }

private:
    vector<int> nums;
};
