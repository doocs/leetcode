class Solution:
    def countVowelPermutation(self, n: int) -> int:
        f = [1] * 5
        mod = 10**9 + 7
        for _ in range(n - 1):
            g = [0] * 5
            g[0] = (f[1] + f[2] + f[4]) % mod
            g[1] = (f[0] + f[2]) % mod
            g[2] = (f[1] + f[3]) % mod
            g[3] = f[2]
            g[4] = (f[2] + f[3]) % mod
            f = g
        return sum(f) % mod
