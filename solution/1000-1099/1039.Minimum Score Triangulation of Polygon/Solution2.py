class Solution:
    def minScoreTriangulation(self, values: List[int]) -> int:
        n = len(values)
        f = [[0] * n for _ in range(n)]
        for i in range(n - 3, -1, -1):
            for j in range(i + 2, n):
                f[i][j] = min(
                    f[i][k] + f[k][j] + values[i] * values[k] * values[j]
                    for k in range(i + 1, j)
                )
        return f[0][-1]
