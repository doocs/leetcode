MX = 5 * 10**5 + 1
MOD = 10**9 + 7
f = [1] * MX
g = [1] * MX
for i in range(1, MX):
    f[i] = f[i - 1] * i % MOD
    g[i] = pow(f[i], MOD - 2, MOD)


def comb(n: int, k: int) -> int:
    return f[n] * g[k] * g[n - k] % MOD


class Solution:
    def countValidSequences(self, n: int, k: int) -> int:
        ans = comb(n - 1, k - 1)
        if (n + k) % 2 == 0:
            ans = (ans - comb((n + k) // 2 - 1, k - 1)) % MOD
        return ans
