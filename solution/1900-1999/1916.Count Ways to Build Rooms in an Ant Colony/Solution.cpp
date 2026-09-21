class Solution {
public:
    int waysToBuildRooms(vector<int>& prevRoom) {
        int n = prevRoom.size();
        g.assign(n, {});
        for (int i = 1; i < n; ++i) {
            g[prevRoom[i]].push_back(i);
        }
        fact.assign(n + 1, 1);
        invFact.assign(n + 1, 1);
        for (int i = 1; i <= n; ++i) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[n] = qpow(fact[n], MOD - 2);
        for (int i = n; i > 0; --i) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }
        ans = 1;
        dfs(0);
        return ans;
    }

private:
    static constexpr int MOD = 1e9 + 7;
    vector<vector<int>> g;
    vector<long long> fact, invFact;
    int ans;

    int dfs(int u) {
        int merged = 0;
        for (int v : g[u]) {
            int cn = dfs(v);
            if (merged) {
                ans = 1LL * ans * comb(merged + cn, cn) % MOD;
            }
            merged += cn;
        }
        return merged + 1;
    }

    long long comb(int n, int k) {
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

    long long qpow(long long a, long long n) {
        long long res = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
        }
        return res;
    }
};
