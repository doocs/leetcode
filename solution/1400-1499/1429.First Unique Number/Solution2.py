class FirstUnique:
    def __init__(self, nums: List[int]):
        self.cnt = Counter(nums)
        self.q = deque(nums)

    def showFirstUnique(self) -> int:
        while self.q and self.cnt[self.q[0]] != 1:
            self.q.popleft()
        return -1 if not self.q else self.q[0]

    def add(self, value: int) -> None:
        self.cnt[value] += 1
        self.q.append(value)


# Your FirstUnique object will be instantiated and called as such:
# obj = FirstUnique(nums)
# param_1 = obj.showFirstUnique()
# obj.add(value)
