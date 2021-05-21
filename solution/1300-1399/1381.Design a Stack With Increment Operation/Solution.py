class CustomStack:

    def __init__(self, maxSize: int):
        self.s = [0] * maxSize
        self.tail = 0

    def push(self, x: int) -> None:
        if self.tail < len(self.s):
            self.s[self.tail] = x
            self.tail += 1

    def pop(self) -> int:
        if self.tail == 0:
            return -1
        self.tail -= 1
        return self.s[self.tail]

    def increment(self, k: int, val: int) -> None:
        for i in range(min(k, self.tail)):
            self.s[i] += val


# Your CustomStack object will be instantiated and called as such:
# obj = CustomStack(maxSize)
# obj.push(x)
# param_2 = obj.pop()
# obj.increment(k,val)