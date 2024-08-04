class neighborSum:

    def __init__(self, grid: List[List[int]]):
        self.grid = grid
        self.d = {}
        self.dirs = ((-1, 0, 1, 0, -1), (-1, 1, 1, -1, -1))
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                self.d[x] = (i, j)

    def adjacentSum(self, value: int) -> int:
        return self.cal(value, 0)

    def cal(self, value: int, k: int):
        i, j = self.d[value]
        s = 0
        for a, b in pairwise(self.dirs[k]):
            x, y = i + a, j + b
            if 0 <= x < len(self.grid) and 0 <= y < len(self.grid[0]):
                s += self.grid[x][y]
        return s

    def diagonalSum(self, value: int) -> int:
        return self.cal(value, 1)


# Your neighborSum object will be instantiated and called as such:
# obj = neighborSum(grid)
# param_1 = obj.adjacentSum(value)
# param_2 = obj.diagonalSum(value)
