class TripleInOne:

    def __init__(self, stackSize: int):
        self.cap = stackSize
        self.stk = [0] * (self.cap * 3 + 3)

    def push(self, stackNum: int, value: int) -> None:
        if self.stk[self.cap * 3 + stackNum] < self.cap:
            self.stk[self.cap * stackNum + self.stk[self.cap * 3 + stackNum]] = value
            self.stk[self.cap * 3 + stackNum] += 1

    def pop(self, stackNum: int) -> int:
        if self.isEmpty(stackNum):
            return -1
        self.stk[self.cap * 3 + stackNum] -= 1
        return self.stk[self.cap * stackNum + self.stk[self.cap * 3 + stackNum]]

    def peek(self, stackNum: int) -> int:
        if self.isEmpty(stackNum):
            return -1
        return self.stk[self.cap * stackNum + self.stk[self.cap * 3 + stackNum] - 1]

    def isEmpty(self, stackNum: int) -> bool:
        return self.stk[self.cap * 3 + stackNum] == 0


# Your TripleInOne object will be instantiated and called as such:
# obj = TripleInOne(stackSize)
# obj.push(stackNum,value)
# param_2 = obj.pop(stackNum)
# param_3 = obj.peek(stackNum)
# param_4 = obj.isEmpty(stackNum)
