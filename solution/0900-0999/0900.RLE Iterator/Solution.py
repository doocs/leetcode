class RLEIterator:
    def __init__(self, encoding: List[int]):
        self.encoding = encoding
        self.i = 0
        self.j = 0

    def next(self, n: int) -> int:
        while self.i < len(self.encoding):
            if self.encoding[self.i] - self.j < n:
                n -= self.encoding[self.i] - self.j
                self.i += 2
                self.j = 0
            else:
                self.j += n
                return self.encoding[self.i + 1]
        return -1


# Your RLEIterator object will be instantiated and called as such:
# obj = RLEIterator(encoding)
# param_1 = obj.next(n)
