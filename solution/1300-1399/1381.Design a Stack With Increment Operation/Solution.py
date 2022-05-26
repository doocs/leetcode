class CustomStack:
    def __init__(self, maxSize: int):
        self.s = [0] * maxSize
        self.t = 0

    def push(self, x: int) -> None:
        if self.t < len(self.s):
            self.s[self.t] = x
            self.t += 1

    def pop(self) -> int:
        if self.t == 0:
            return -1
        self.t -= 1
        return self.s[self.t]

    def increment(self, k: int, val: int) -> None:
        for i in range(min(k, self.t)):
            self.s[i] += val


# Your CustomStack object will be instantiated and called as such:
# obj = CustomStack(maxSize)
# obj.push(x)
# param_2 = obj.pop()
# obj.increment(k,val)
