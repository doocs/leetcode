class MaxQueue:
    def __init__(self):
        self.p = deque()
        self.q = deque()

    def max_value(self) -> int:
        return -1 if not self.q else self.q[0]

    def push_back(self, value: int) -> None:
        while self.q and self.q[-1] < value:
            self.q.pop()
        self.p.append(value)
        self.q.append(value)

    def pop_front(self) -> int:
        if not self.p:
            return -1
        res = self.p.popleft()
        if self.q[0] == res:
            self.q.popleft()
        return res


# Your MaxQueue object will be instantiated and called as such:
# obj = MaxQueue()
# param_1 = obj.max_value()
# obj.push_back(value)
# param_3 = obj.pop_front()
