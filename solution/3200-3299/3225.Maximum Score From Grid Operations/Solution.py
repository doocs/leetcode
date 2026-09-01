class Solution:
    def maximumScore(self, grid: List[List[int]]) -> int:
        n = len(grid)
        s = [[0] * (n + 1) for _ in range(n)]
        for j in range(n):
            for i, x in enumerate(grid):
                s[j][i + 1] = s[j][i] + x[j]
        f = [[-inf] * (n + 1) for _ in range(n + 1)]
        for h in range(n + 1):
            f[h][0] = 0
        for j in range(n - 1):
            g = [[-inf] * (n + 1) for _ in range(n + 1)]
            for h1 in range(n + 1):
                pre = [-inf] * (n + 2)
                pre[0] = f[h1][0]
                for h2 in range(1, n + 1):
                    pre[h2] = max(pre[h2 - 1], f[h1][h2])
                suf = [-inf] * (n + 2)
                for h2 in range(n, -1, -1):
                    v = -inf
                    if f[h1][h2] != -inf:
                        v = f[h1][h2] + max(0, s[j][h2] - s[j][h1])
                    suf[h2] = max(suf[h2 + 1], v)
                for hp in range(n + 1):
                    add = max(0, s[j][hp] - s[j][h1])
                    v1 = -inf if pre[hp] == -inf else pre[hp] + add
                    g[hp][h1] = max(v1, suf[hp + 1])
            f = g
        ans = 0
        for h1 in range(n + 1):
            for h2 in range(n + 1):
                if f[h1][h2] != -inf:
                    ans = max(ans, f[h1][h2] + max(0, s[-1][h2] - s[-1][h1]))
        return ans
