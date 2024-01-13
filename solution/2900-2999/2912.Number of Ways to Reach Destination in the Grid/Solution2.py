class Solution:
    def numberOfWays(
        self, n: int, m: int, k: int, source: List[int], dest: List[int]
    ) -> int:
        mod = 10**9 + 7
        f = [1, 0, 0, 0]
        for _ in range(k):
            g = [0] * 4
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod
            f = g
        if source[0] == dest[0]:
            return f[0] if source[1] == dest[1] else f[2]
        return f[1] if source[1] == dest[1] else f[3]
