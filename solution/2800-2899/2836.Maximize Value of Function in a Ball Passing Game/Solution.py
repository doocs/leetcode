class Solution:
    def getMaxFunctionValue(self, receiver: List[int], k: int) -> int:
        n, m = len(receiver), k.bit_length()
        f = [[0] * m for _ in range(n)]
        g = [[0] * m for _ in range(n)]
        for i, x in enumerate(receiver):
            f[i][0] = x
            g[i][0] = i
        for j in range(1, m):
            for i in range(n):
                f[i][j] = f[f[i][j - 1]][j - 1]
                g[i][j] = g[i][j - 1] + g[f[i][j - 1]][j - 1]
        ans = 0
        for i in range(n):
            p, t = i, 0
            for j in range(m):
                if k >> j & 1:
                    t += g[p][j]
                    p = f[p][j]
            ans = max(ans, t + p)
        return ans
