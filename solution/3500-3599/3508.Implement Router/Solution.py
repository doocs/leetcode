class Router:

    def __init__(self, memoryLimit: int):
        self.lim = memoryLimit
        self.vis = set()
        self.q = deque()
        self.idx = defaultdict(int)
        self.d = defaultdict(list)

    def addPacket(self, source: int, destination: int, timestamp: int) -> bool:
        x = self.f(source, destination, timestamp)
        if x in self.vis:
            return False
        self.vis.add(x)
        if len(self.q) >= self.lim:
            self.forwardPacket()
        self.q.append((source, destination, timestamp))
        self.d[destination].append(timestamp)
        return True

    def forwardPacket(self) -> List[int]:
        if not self.q:
            return []
        s, d, t = self.q.popleft()
        self.vis.remove(self.f(s, d, t))
        self.idx[d] += 1
        return [s, d, t]

    def f(self, a: int, b: int, c: int) -> int:
        return a << 46 | b << 29 | c

    def getCount(self, destination: int, startTime: int, endTime: int) -> int:
        ls = self.d[destination]
        k = self.idx[destination]
        i = bisect_left(ls, startTime, k)
        j = bisect_left(ls, endTime + 1, k)
        return j - i


# Your Router object will be instantiated and called as such:
# obj = Router(memoryLimit)
# param_1 = obj.addPacket(source,destination,timestamp)
# param_2 = obj.forwardPacket()
# param_3 = obj.getCount(destination,startTime,endTime)
