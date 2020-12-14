class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.s = []
        self.helper = []


    def push(self, x: int) -> None:
        self.s.append(x)
        element = x if not self.helper or x < self.helper[-1] else self.helper[-1]
        self.helper.append(element)

    def pop(self) -> None:
        self.s.pop()
        self.helper.pop()

    def top(self) -> int:
        return self.s[-1]

    def getMin(self) -> int:
        return self.helper[-1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()