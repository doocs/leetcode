class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def flood(i: int, j: int) -> int:
            ok = 1
            grid2[i][j] = 0
            stk = [(i, j)]
            while stk:
                i, j = stk.pop()
                ok &= grid1[i][j]
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid2[x][y]:
                        grid2[x][y] = 0
                        stk.append((x, y))
            return ok

        m, n = len(grid1), len(grid1[0])
        dirs = (-1, 0, 1, 0, -1)
        return sum(flood(i, j) for i in range(m) for j in range(n) if grid2[i][j])
