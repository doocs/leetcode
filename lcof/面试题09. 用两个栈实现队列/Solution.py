class CQueue:

    def __init__(self):
        self._s1 = []
        self._s2 = []

    def appendTail(self, value: int) -> None:
        self._s1.append(value)
        if not self._s2:
            while self._s1:
                self._s2.append(self._s1.pop())

    def deleteHead(self) -> int:
        if not self._s2:
            while self._s1:
                self._s2.append(self._s1.pop())
        return -1 if not self._s2 else self._s2.pop()


# Your CQueue object will be instantiated and called as such:
# obj = CQueue()
# obj.appendTail(value)
# param_2 = obj.deleteHead()