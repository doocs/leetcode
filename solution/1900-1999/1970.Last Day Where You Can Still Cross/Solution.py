class Solution:
    def latestDayToCross(self, row: int, col: int, cells: List[List[int]]) -> int:
        n = row * col
        p = list(range(n + 2))
        grid = [[False] * col for _ in range(row)]
        top, bottom = n, n + 1

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def check(i, j):
            return 0 <= i < row and 0 <= j < col and grid[i][j]

        for k in range(len(cells) - 1, -1, -1):
            i, j = cells[k][0] - 1, cells[k][1] - 1
            grid[i][j] = True
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                if check(i + x, j + y):
                    p[find(i * col + j)] = find((i + x) * col + j + y)
            if i == 0:
                p[find(i * col + j)] = find(top)
            if i == row - 1:
                p[find(i * col + j)] = find(bottom)
            if find(top) == find(bottom):
                return k
        return 0
