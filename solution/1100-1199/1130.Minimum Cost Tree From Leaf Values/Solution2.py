class Solution:
    def mctFromLeafValues(self, arr: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i == j:
                return 0
            return min(
                dfs(i, k) + dfs(k + 1, j) + g[i][k] * g[k + 1][j] for k in range(i, j)
            )

        n = len(arr)
        g = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            g[i][i] = arr[i]
            for j in range(i + 1, n):
                g[i][j] = max(g[i][j - 1], arr[j])
        return dfs(0, n - 1)
