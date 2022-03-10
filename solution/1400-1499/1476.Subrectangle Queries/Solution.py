class SubrectangleQueries:
    def __init__(self, rectangle: List[List[int]]):
        self.rec = rectangle
        self.history = []

    def updateSubrectangle(
        self, row1: int, col1: int, row2: int, col2: int, newValue: int
    ) -> None:
        self.history.append((row1, col1, row2, col2, newValue))

    def getValue(self, row: int, col: int) -> int:
        for row1, col1, row2, col2, newValue in self.history[::-1]:
            if row >= row1 and row <= row2 and col >= col1 and col <= col2:
                return newValue
        return self.rec[row][col]


# Your SubrectangleQueries object will be instantiated and called as such:
# obj = SubrectangleQueries(rectangle)
# obj.updateSubrectangle(row1,col1,row2,col2,newValue)
# param_2 = obj.getValue(row,col)
