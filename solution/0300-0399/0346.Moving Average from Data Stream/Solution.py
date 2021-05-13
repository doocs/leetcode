class MovingAverage:

    def __init__(self, size: int):
        """
        Initialize your data structure here.
        """
        self.size = size
        self.data = [0] * size
        self.count = 0
        self.s = 0

    def next(self, val: int) -> float:
        idx = self.count % self.size
        old_val = self.data[idx]
        self.data[idx] = val
        self.s += (val - old_val)
        self.count += 1
        return self.s / min(self.size, self.count)


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)