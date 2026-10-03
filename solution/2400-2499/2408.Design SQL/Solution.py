class SQL:
    def __init__(self, names: List[str], columns: List[int]):
        self.cols = dict(zip(names, columns))
        self.rows = {name: {} for name in names}
        self.nxt = {name: 1 for name in names}

    def ins(self, name: str, row: List[str]) -> bool:
        if name not in self.cols or len(row) != self.cols[name]:
            return False
        i = self.nxt[name]
        self.rows[name][i] = row
        self.nxt[name] = i + 1
        return True

    def rmv(self, name: str, rowId: int) -> None:
        if name in self.rows:
            self.rows[name].pop(rowId, None)

    def sel(self, name: str, rowId: int, columnId: int) -> str:
        row = self.rows.get(name, {}).get(rowId)
        if row is None or columnId < 1 or columnId > len(row):
            return '<null>'
        return row[columnId - 1]

    def exp(self, name: str) -> List[str]:
        if name not in self.rows:
            return []
        return [
            ','.join([str(i), *self.rows[name][i]]) for i in sorted(self.rows[name])
        ]


# Your SQL object will be instantiated and called as such:
# obj = SQL(names, columns)
# param_1 = obj.ins(name,row)
# obj.rmv(name,rowId)
# param_3 = obj.sel(name,rowId,columnId)
# param_4 = obj.exp(name)
