class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def f(x: int) -> int:
            return x // 10 + x % 10

        def dfs(i, j):
            if i >= m or j >= n or f(i) + f(j) > k or (i, j) in vis:
                return 0
            vis.add((i, j))
            return 1 + dfs(i + 1, j) + dfs(i, j + 1)

        vis = set()
        return dfs(0, 0)
