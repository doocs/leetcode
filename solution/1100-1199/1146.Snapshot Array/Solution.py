class SnapshotArray:
    def __init__(self, length: int):
        self.idx = 0
        self.arr = defaultdict(list)

    def set(self, index: int, val: int) -> None:
        self.arr[index].append((self.idx, val))

    def snap(self) -> int:
        self.idx += 1
        return self.idx - 1

    def get(self, index: int, snap_id: int) -> int:
        vals = self.arr[index]
        i = bisect_right(vals, (snap_id, inf)) - 1
        return 0 if i < 0 else vals[i][1]


# Your SnapshotArray object will be instantiated and called as such:
# obj = SnapshotArray(length)
# obj.set(index,val)
# param_2 = obj.snap()
# param_3 = obj.get(index,snap_id)
