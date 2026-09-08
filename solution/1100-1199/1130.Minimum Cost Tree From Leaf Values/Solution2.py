class Solution:
    def mctFromLeafValues(self, arr: List[int]) -> int:
        n = len(arr)
        f = [[0] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            g[i][i] = arr[i]
            for j in range(i + 1, n):
                g[i][j] = max(g[i][j - 1], arr[j])
                f[i][j] = min(
                    f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j] for k in range(i, j)
                )
        return f[0][n - 1]
