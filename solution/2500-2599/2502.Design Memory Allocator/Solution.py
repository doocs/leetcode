from sortedcontainers import SortedList


class Allocator:
    def __init__(self, n: int):
        self.sl = SortedList([(-1, -1), (n, n)])
        self.d = defaultdict(list)

    def allocate(self, size: int, mID: int) -> int:
        for (_, s), (e, _) in pairwise(self.sl):
            s, e = s + 1, e - 1
            if e - s + 1 >= size:
                self.sl.add((s, s + size - 1))
                self.d[mID].append((s, s + size - 1))
                return s
        return -1

    def free(self, mID: int) -> int:
        ans = 0
        for block in self.d[mID]:
            self.sl.remove(block)
            ans += block[1] - block[0] + 1
        del self.d[mID]
        return ans


# Your Allocator object will be instantiated and called as such:
# obj = Allocator(n)
# param_1 = obj.allocate(size,mID)
# param_2 = obj.free(mID)
