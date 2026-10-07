class Solution:
    def maxScore(self, a: List[int], b: List[int]) -> int:
        m, n = len(a), len(b)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(m):
            f[i][n] = -inf
        for j in range(n - 1, -1, -1):
            for i in range(m - 1, -1, -1):
                f[i][j] = max(f[i][j + 1], a[i] * b[j] + f[i + 1][j + 1])
        return f[0][0]
