class Spreadsheet:
    def __init__(self, rows: int):
        self.d = {}

    def setCell(self, cell: str, value: int) -> None:
        self.d[cell] = value

    def resetCell(self, cell: str) -> None:
        self.d.pop(cell, None)

    def getValue(self, formula: str) -> int:
        ans = 0
        for cell in formula[1:].split("+"):
            ans += int(cell) if cell[0].isdigit() else self.d.get(cell, 0)
        return ans


# Your Spreadsheet object will be instantiated and called as such:
# obj = Spreadsheet(rows)
# obj.setCell(cell,value)
# obj.resetCell(cell)
# param_3 = obj.getValue(formula)
