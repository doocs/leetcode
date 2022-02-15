from sortedcontainers import SortedDict


class SummaryRanges:
    def __init__(self):
        self.mp = SortedDict()

    def addNum(self, val: int) -> None:
        n = len(self.mp)
        ridx = self.mp.bisect_right(val)
        lidx = n if ridx == 0 else ridx - 1
        keys = self.mp.keys()
        values = self.mp.values()
        if (
            lidx != n
            and ridx != n
            and values[lidx][1] + 1 == val
            and values[ridx][0] - 1 == val
        ):
            self.mp[keys[lidx]][1] = self.mp[keys[ridx]][1]
            self.mp.pop(keys[ridx])
        elif lidx != n and val <= values[lidx][1] + 1:
            self.mp[keys[lidx]][1] = max(val, self.mp[keys[lidx]][1])
        elif ridx != n and val >= values[ridx][0] - 1:
            self.mp[keys[ridx]][0] = min(val, self.mp[keys[ridx]][0])
        else:
            self.mp[val] = [val, val]

    def getIntervals(self) -> List[List[int]]:
        return list(self.mp.values())


# # Your SummaryRanges object will be instantiated and called as such:
# # obj = SummaryRanges()
# # obj.addNum(val)
# # param_2 = obj.getIntervals()
