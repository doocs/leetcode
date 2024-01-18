class Solution:
    def minSkips(self, dist: List[int], speed: int, hoursBefore: int) -> int:
        n = len(dist)
        f = [[inf] * (n + 1) for _ in range(n + 1)]
        f[0][0] = 0
        eps = 1e-8
        for i, x in enumerate(dist, 1):
            for j in range(i + 1):
                if j < i:
                    f[i][j] = min(f[i][j], ceil(f[i - 1][j] + x / speed - eps))
                if j:
                    f[i][j] = min(f[i][j], f[i - 1][j - 1] + x / speed)
        for j in range(n + 1):
            if f[n][j] <= hoursBefore + eps:
                return j
        return -1
