class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def dfs(i, j):
            if (
                i >= m
                or j >= n
                or vis[i][j]
                or (i % 10 + i // 10 + j % 10 + j // 10) > k
            ):
                return 0
            vis[i][j] = True
            return 1 + dfs(i + 1, j) + dfs(i, j + 1)

        vis = [[False] * n for _ in range(m)]
        return dfs(0, 0)
