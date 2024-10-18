class Solution:
    def keyboard(self, k: int, n: int) -> int:
        f = [[0] * 27 for _ in range(n + 1)]
        f[0] = [1] * 27
        mod = 10**9 + 7
        for i in range(1, n + 1):
            for j in range(1, 27):
                for h in range(min(k, i) + 1):
                    f[i][j] += f[i - h][j - 1] * comb(i, h)
                    f[i][j] %= mod
        return f[n][26]
