class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self._s1 = []
        self._s2 = []

    def push(self, x: int) -> None:
        self._s1.append(x)
        self._s2.append(x if len(self._s2) ==
                        0 or self._s2[-1] >= x else self._s2[-1])

    def pop(self) -> None:
        self._s1.pop()
        self._s2.pop()

    def top(self) -> int:
        return self._s1[-1]

    def getMin(self) -> int:
        return self._s2[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
