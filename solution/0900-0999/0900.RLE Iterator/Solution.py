class RLEIterator:
    def __init__(self, encoding: List[int]):
        self.encoding = encoding
        self.i = 0
        self.curr = 0

    def next(self, n: int) -> int:
        while self.i < len(self.encoding):
            if self.curr + n > self.encoding[self.i]:
                n -= self.encoding[self.i] - self.curr
                self.curr = 0
                self.i += 2
            else:
                self.curr += n
                return self.encoding[self.i + 1]
        return -1


# Your RLEIterator object will be instantiated and called as such:
# obj = RLEIterator(encoding)
# param_1 = obj.next(n)
