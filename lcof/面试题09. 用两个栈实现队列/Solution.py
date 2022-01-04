class CQueue:

    def __init__(self):
        self.s1 = []
        self.s2 = []

    def appendTail(self, value: int) -> None:
        self.s1.append(value)

    def deleteHead(self) -> int:
        if not self.s2:
            self._move()
        return -1 if not self.s2 else self.s2.pop()

    def _move(self):
        while self.s1:
            self.s2.append(self.s1.pop())



# Your CQueue object will be instantiated and called as such:
# obj = CQueue()
# obj.appendTail(value)
# param_2 = obj.deleteHead()
