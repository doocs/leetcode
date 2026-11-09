# The read4 API is already defined for you.
# def read4(buf4: list[str]) -> int:


class Solution:
    def __init__(self):
        self.buf4 = [None] * 4
        self.i = self.size = 0

    def read(self, buf: list[str], n: int) -> int:
        j = 0
        while j < n:
            if self.i == self.size:
                self.size = read4(self.buf4)
                self.i = 0
                if self.size == 0:
                    break
            while j < n and self.i < self.size:
                buf[j] = self.buf4[self.i]
                self.i += 1
                j += 1
        return j
