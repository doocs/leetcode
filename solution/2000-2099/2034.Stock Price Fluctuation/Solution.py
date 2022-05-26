from sortedcontainers import SortedDict


class StockPrice:
    def __init__(self):
        self.last_ts = 0
        self.mp = {}
        self.counter = SortedDict()

    def update(self, timestamp: int, price: int) -> None:
        if timestamp in self.mp:
            old_price = self.mp[timestamp]
            self.counter[old_price] -= 1
            if self.counter[old_price] == 0:
                del self.counter[old_price]
        if price not in self.counter:
            self.counter[price] = 0
        self.counter[price] += 1
        self.mp[timestamp] = price
        self.last_ts = max(self.last_ts, timestamp)

    def current(self) -> int:
        return self.mp[self.last_ts]

    def maximum(self) -> int:
        return self.counter.keys()[-1]

    def minimum(self) -> int:
        return self.counter.keys()[0]


# Your StockPrice object will be instantiated and called as such:
# obj = StockPrice()
# obj.update(timestamp,price)
# param_2 = obj.current()
# param_3 = obj.maximum()
# param_4 = obj.minimum()
