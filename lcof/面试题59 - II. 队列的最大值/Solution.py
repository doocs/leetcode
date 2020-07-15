class MaxQueue:

    def __init__(self):
        self.p = []
        self.q = []

    def max_value(self) -> int:
        return -1 if not self.q else self.q[0]

    def push_back(self, value: int) -> None:
        while self.q and self.q[-1] < value:
            self.q.pop(-1)
        self.q.append(value)
        self.p.append(value)
        

    def pop_front(self) -> int:
        if not self.p: return -1
        res = self.p.pop(0)
        if res == self.q[0]:
            self.q.pop(0)
        return res
