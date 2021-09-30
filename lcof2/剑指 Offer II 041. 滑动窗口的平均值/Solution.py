class MovingAverage:

    def __init__(self, size: int):
        """
        Initialize your data structure here.
        """
        self.size = size
        self.data = [0] * size
        self.sum = 0
        self.count = 0

    def next(self, val: int) -> float:
        idx = self.count % self.size
        old_val = self.data[idx]
        self.data[idx] = val
        self.sum += val - old_val
        self.count += 1
        return self.sum / min(self.count, self.size)

# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
