class MaxQueue:
    def __init__(self):
        self.q1 = deque()
        self.q2 = deque()

    def max_value(self) -> int:
        return -1 if not self.q2 else self.q2[0]

    def push_back(self, value: int) -> None:
        while self.q2 and self.q2[-1] < value:
            self.q2.pop()
        self.q1.append(value)
        self.q2.append(value)

    def pop_front(self) -> int:
        if not self.q1:
            return -1
        ans = self.q1.popleft()
        if self.q2[0] == ans:
            self.q2.popleft()
        return ans


# Your MaxQueue object will be instantiated and called as such:
# obj = MaxQueue()
# param_1 = obj.max_value()
# obj.push_back(value)
# param_3 = obj.pop_front()
