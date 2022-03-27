class KthLargest:
    def __init__(self, k: int, nums: List[int]):
        self.q = []
        self.size = k
        for num in nums:
            self.add(num)

    def add(self, val: int) -> int:
        heappush(self.q, val)
        if len(self.q) > self.size:
            heappop(self.q)
        return self.q[0]


# Your KthLargest object will be instantiated and called as such:
# obj = KthLargest(k, nums)
# param_1 = obj.add(val)
