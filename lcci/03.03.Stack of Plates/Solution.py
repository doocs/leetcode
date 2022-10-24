class StackOfPlates:
    def __init__(self, cap: int):
        self.cap = cap
        self.stk = []

    def push(self, val: int) -> None:
        if self.cap == 0:
            return
        if not self.stk or len(self.stk[-1]) >= self.cap:
            self.stk.append([])
        self.stk[-1].append(val)

    def pop(self) -> int:
        return self.popAt(len(self.stk) - 1)

    def popAt(self, index: int) -> int:
        ans = -1
        if 0 <= index < len(self.stk):
            ans = self.stk[index].pop()
            if not self.stk[index]:
                self.stk.pop(index)
        return ans


# Your StackOfPlates object will be instantiated and called as such:
# obj = StackOfPlates(cap)
# obj.push(val)
# param_2 = obj.pop()
# param_3 = obj.popAt(index)
