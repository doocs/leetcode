class SortedStack:

    def __init__(self):
        self.stk = []

    def push(self, val: int) -> None:
        t = []
        while self.stk and self.stk[-1] < val:
            t.append(self.stk.pop())
        self.stk.append(val)
        while t:
            self.stk.append(t.pop())

    def pop(self) -> None:
        if not self.isEmpty():
            self.stk.pop()

    def peek(self) -> int:
        return -1 if self.isEmpty() else self.stk[-1]

    def isEmpty(self) -> bool:
        return not self.stk


# Your SortedStack object will be instantiated and called as such:
# obj = SortedStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.peek()
# param_4 = obj.isEmpty()
