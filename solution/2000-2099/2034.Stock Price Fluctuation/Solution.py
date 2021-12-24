class StockPrice:

    def __init__(self):
        self.last_ts = 0
        self.mp = {}
        self.mi = []
        self.mx = []
        self.counter = Counter()

    def update(self, timestamp: int, price: int) -> None:
        if timestamp in self.mp:
            old_price = self.mp[timestamp]
            self.counter[old_price] -= 1

        self.mp[timestamp] = price
        self.last_ts = max(self.last_ts, timestamp)
        self.counter[price] += 1
        heapq.heappush(self.mi, price)
        heapq.heappush(self.mx, -price)

    def current(self) -> int:
        return self.mp[self.last_ts]

    def maximum(self) -> int:
        while self.counter[-self.mx[0]] == 0:
            heapq.heappop(self.mx)
        return -self.mx[0]

    def minimum(self) -> int:
        while self.counter[self.mi[0]] == 0:
            heapq.heappop(self.mi)
        return self.mi[0]


# Your StockPrice object will be instantiated and called as such:
# obj = StockPrice()
# obj.update(timestamp,price)
# param_2 = obj.current()
# param_3 = obj.maximum()
# param_4 = obj.minimum()
