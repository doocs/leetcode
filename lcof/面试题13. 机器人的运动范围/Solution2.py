class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def f(x):
            s = 0
            while x:
                s += x % 10
                x //= 10
            return s

        def dfs(i, j):
            if not (0 <= i < m) or not (0 <= j < n) or f(i) + f(j) > k or (i, j) in vis:
                return 0
            vis.add((i, j))
            return 1 + dfs(i + 1, j) + dfs(i, j + 1)

        vis = set()
        return dfs(0, 0)
