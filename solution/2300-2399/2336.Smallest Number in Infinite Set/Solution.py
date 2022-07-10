class SmallestInfiniteSet:
    def __init__(self):
        self.black = set()

    def popSmallest(self) -> int:
        i = 1
        while i in self.black:
            i += 1
        self.black.add(i)
        return i

    def addBack(self, num: int) -> None:
        self.black.discard(num)


# Your SmallestInfiniteSet object will be instantiated and called as such:
# obj = SmallestInfiniteSet()
# param_1 = obj.popSmallest()
# obj.addBack(num)
