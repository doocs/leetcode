class Solution:
    def minDistance(self, houses: List[int], k: int) -> int:
        houses.sort()
        n = len(houses)
        g = [[0] * n for _ in range(n)]
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = g[i + 1][j - 1] + houses[j] - houses[i]
        f = [[inf] * (k + 1) for _ in range(n)]
        for i in range(n):
            f[i][1] = g[0][i]
            for j in range(2, min(k + 1, i + 2)):
                for p in range(i):
                    f[i][j] = min(f[i][j], f[p][j - 1] + g[p + 1][i])
        return f[-1][k]
