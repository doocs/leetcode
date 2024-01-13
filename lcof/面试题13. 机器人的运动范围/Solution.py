class Solution:
    def movingCount(self, m: int, n: int, k: int) -> int:
        def f(x):
            s = 0
            while x:
                s += x % 10
                x //= 10
            return s

        def dfs(i, j):
            vis.add((i, j))
            nonlocal ans
            ans += 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and f(x) + f(y) <= k and (x, y) not in vis:
                    dfs(x, y)

        vis = set()
        ans = 0
        dirs = (0, 1, 0)
        dfs(0, 0)
        return ans
