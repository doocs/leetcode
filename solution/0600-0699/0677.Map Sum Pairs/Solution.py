class MapSum:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.data = defaultdict(int)
        self.t = defaultdict(int)

    def insert(self, key: str, val: int) -> None:
        old = self.t[key]
        self.t[key] = val
        for i in range(1, len(key) + 1):
            self.data[key[:i]] += val - old

    def sum(self, prefix: str) -> int:
        return self.data[prefix]


# Your MapSum object will be instantiated and called as such:
# obj = MapSum()
# obj.insert(key,val)
# param_2 = obj.sum(prefix)
