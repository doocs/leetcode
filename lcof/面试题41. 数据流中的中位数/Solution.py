class MedianFinder:
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.q1 = []
        self.q2 = []

    def addNum(self, num: int) -> None:
        if len(self.q1) > len(self.q2):
            heappush(self.q2, -heappushpop(self.q1, num))
        else:
            heappush(self.q1, -heappushpop(self.q2, -num))

    def findMedian(self) -> float:
        if len(self.q1) > len(self.q2):
            return self.q1[0]
        return (self.q1[0] - self.q2[0]) / 2


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
