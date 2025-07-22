class Solution:
    def findPath(self, grid: List[List[int]], k: int) -> List[List[int]]:
        def f(i: int, j: int) -> int:
            return i * n + j

        def dfs(i: int, j: int, v: int):
            nonlocal st
            path.append([i, j])
            if len(path) == m * n:
                return True
            st |= 1 << f(i, j)
            if grid[i][j] == v:
                v += 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and (st & 1 << f(x, y)) == 0
                    and grid[x][y] in (0, v)
                ):
                    if dfs(x, y, v):
                        return True
            path.pop()
            st ^= 1 << f(i, j)
            return False

        m, n = len(grid), len(grid[0])
        st = 0
        path = []
        dirs = (-1, 0, 1, 0, -1)
        for i in range(m):
            for j in range(n):
                if grid[i][j] in (0, 1):
                    if dfs(i, j, 1):
                        return path
                    path.clear()
                    st = 0
        return []
