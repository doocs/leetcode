class MyQueue:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self._s1, self._s2 = [], []

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self._s1.append(x)

    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        if len(self._s2) == 0:
            while self._s1:
                self._s2.append(self._s1.pop())
        return self._s2.pop()

    def peek(self) -> int:
        """
        Get the front element.
        """
        if len(self._s2) == 0:
            while self._s1:
                self._s2.append(self._s1.pop())
        return self._s2[-1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        return len(self._s1) + len(self._s2) == 0


# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
