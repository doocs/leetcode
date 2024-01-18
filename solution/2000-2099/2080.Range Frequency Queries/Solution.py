class RangeFreqQuery:
    def __init__(self, arr: List[int]):
        self.mp = defaultdict(list)
        for i, x in enumerate(arr):
            self.mp[x].append(i)

    def query(self, left: int, right: int, value: int) -> int:
        if value not in self.mp:
            return 0
        arr = self.mp[value]
        l, r = bisect_right(arr, left - 1), bisect_right(arr, right)
        return r - l


# Your RangeFreqQuery object will be instantiated and called as such:
# obj = RangeFreqQuery(arr)
# param_1 = obj.query(left,right,value)
