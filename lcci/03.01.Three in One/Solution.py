class TripleInOne:
    def __init__(self, stackSize: int):
        self._capacity = stackSize
        self._s = [[], [], []]

    def push(self, stackNum: int, value: int) -> None:
        if len(self._s[stackNum]) < self._capacity:
            self._s[stackNum].append(value)

    def pop(self, stackNum: int) -> int:
        return -1 if self.isEmpty(stackNum) else self._s[stackNum].pop()

    def peek(self, stackNum: int) -> int:
        return -1 if self.isEmpty(stackNum) else self._s[stackNum][-1]

    def isEmpty(self, stackNum: int) -> bool:
        return len(self._s[stackNum]) == 0


# Your TripleInOne object will be instantiated and called as such:
# obj = TripleInOne(stackSize)
# obj.push(stackNum,value)
# param_2 = obj.pop(stackNum)
# param_3 = obj.peek(stackNum)
# param_4 = obj.isEmpty(stackNum)
