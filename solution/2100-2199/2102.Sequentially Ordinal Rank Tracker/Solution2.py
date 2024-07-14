class Node:
    def __init__(self, s: str):
        self.s = s

    def __lt__(self, other):
        return self.s > other.s


class SORTracker:

    def __init__(self):
        self.good = []
        self.bad = []

    def add(self, name: str, score: int) -> None:
        score, node = heappushpop(self.good, (score, Node(name)))
        heappush(self.bad, (-score, node.s))

    def get(self) -> str:
        score, name = heappop(self.bad)
        heappush(self.good, (-score, Node(name)))
        return self.good[0][1].s


# Your SORTracker object will be instantiated and called as such:
# obj = SORTracker()
# obj.add(name,score)
# param_2 = obj.get()
