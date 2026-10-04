class Solution:
    def isPossibleToCutPath(self, grid: List[List[int]]) -> bool:
        def dfs() -> bool:
            stk = [(0, 0)]
            while stk:
                i, j = stk.pop()
                if i >= m or j >= n or grid[i][j] == 0:
                    continue
                grid[i][j] = 0
                if i == m - 1 and j == n - 1:
                    return True
                stk.append((i, j + 1))
                stk.append((i + 1, j))
            return False

        m, n = len(grid), len(grid[0])
        a = dfs()
        grid[0][0] = grid[-1][-1] = 1
        b = dfs()
        return not (a and b)
