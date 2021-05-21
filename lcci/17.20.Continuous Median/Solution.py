class MedianFinder:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.min_heap = []
        self.max_heap = []

    def addNum(self, num: int) -> None:
        heapq.heappush(self.min_heap, num)
        heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))
        if len(self.max_heap) - len(self.min_heap) > 1:
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))

    def findMedian(self) -> float:
        if len(self.max_heap) > len(self.min_heap):
            return -self.max_heap[0]
        return (self.min_heap[0] - self.max_heap[0]) / 2


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()