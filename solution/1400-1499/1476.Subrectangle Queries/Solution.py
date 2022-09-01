class SubrectangleQueries:
    def __init__(self, rectangle: List[List[int]]):
        self.g = rectangle
        self.ops = []

    def updateSubrectangle(
        self, row1: int, col1: int, row2: int, col2: int, newValue: int
    ) -> None:
        self.ops.append((row1, col1, row2, col2, newValue))

    def getValue(self, row: int, col: int) -> int:
        for r1, c1, r2, c2, v in self.ops[::-1]:
            if r1 <= row <= r2 and c1 <= col <= c2:
                return v
        return self.g[row][col]


# Your SubrectangleQueries object will be instantiated and called as such:
# obj = SubrectangleQueries(rectangle)
# obj.updateSubrectangle(row1,col1,row2,col2,newValue)
# param_2 = obj.getValue(row,col)
