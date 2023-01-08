class DataStream:
    def __init__(self, value: int, k: int):
        self.val, self.k = value, k
        self.cnt = 0

    def consec(self, num: int) -> bool:
        self.cnt = 0 if num != self.val else self.cnt + 1
        return self.cnt >= self.k


# Your DataStream object will be instantiated and called as such:
# obj = DataStream(value, k)
# param_1 = obj.consec(num)
