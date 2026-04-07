N = 100001
MOD = 10**9 + 7
f = [1] * N
g = [1] * N
for i in range(1, N):
    f[i] = f[i - 1] * i % MOD
    g[i] = pow(f[i], MOD - 2, MOD)


def comb(n, k):
    return f[n] * g[k] * g[n - k] % MOD


class Solution:
    def countVisiblePeople(self, n: int, pos: int, k: int) -> int:
        l, r = pos, n - pos - 1
        ans = 0
        for a in range(min(k, l) + 1):
            b = k - a
            if b <= r:
                ans += 2 * comb(l, a) * comb(r, b)
                ans %= MOD
        return ans
