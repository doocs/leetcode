class Solution:
    def minimumSum(self, grid: List[List[int]]) -> int:
        def f(i1: int, j1: int, i2: int, j2: int) -> int:
            x1 = y1 = inf
            x2 = y2 = -inf
            for i in range(i1, i2 + 1):
                for j in range(j1, j2 + 1):
                    if grid[i][j] == 1:
                        x1 = min(x1, i)
                        y1 = min(y1, j)
                        x2 = max(x2, i)
                        y2 = max(y2, j)
            return (x2 - x1 + 1) * (y2 - y1 + 1)

        m, n = len(grid), len(grid[0])
        ans = m * n
        for i1 in range(m - 1):
            for i2 in range(i1 + 1, m - 1):
                ans = min(
                    ans,
                    f(0, 0, i1, n - 1)
                    + f(i1 + 1, 0, i2, n - 1)
                    + f(i2 + 1, 0, m - 1, n - 1),
                )
        for j1 in range(n - 1):
            for j2 in range(j1 + 1, n - 1):
                ans = min(
                    ans,
                    f(0, 0, m - 1, j1)
                    + f(0, j1 + 1, m - 1, j2)
                    + f(0, j2 + 1, m - 1, n - 1),
                )
        for i in range(m - 1):
            for j in range(n - 1):
                ans = min(
                    ans,
                    f(0, 0, i, j) + f(0, j + 1, i, n - 1) + f(i + 1, 0, m - 1, n - 1),
                )
                ans = min(
                    ans,
                    f(0, 0, i, n - 1)
                    + f(i + 1, 0, m - 1, j)
                    + f(i + 1, j + 1, m - 1, n - 1),
                )

                ans = min(
                    ans,
                    f(0, 0, i, j) + f(i + 1, 0, m - 1, j) + f(0, j + 1, m - 1, n - 1),
                )
                ans = min(
                    ans,
                    f(0, 0, m - 1, j)
                    + f(0, j + 1, i, n - 1)
                    + f(i + 1, j + 1, m - 1, n - 1),
                )
        return ans
