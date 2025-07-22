class OrderedStream:
    def __init__(self, n: int):
        self.ptr = 1
        self.data = [None] * (n + 1)

    def insert(self, idKey: int, value: str) -> List[str]:
        self.data[idKey] = value
        ans = []
        while self.ptr < len(self.data) and self.data[self.ptr]:
            ans.append(self.data[self.ptr])
            self.ptr += 1
        return ans


# Your OrderedStream object will be instantiated and called as such:
# obj = OrderedStream(n)
# param_1 = obj.insert(idKey,value)
