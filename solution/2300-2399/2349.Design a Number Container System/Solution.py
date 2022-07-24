from sortedcontainers import SortedSet


class NumberContainers:
    def __init__(self):
        self.mp = {}
        self.t = defaultdict(SortedSet)

    def change(self, index: int, number: int) -> None:
        if index in self.mp:
            v = self.mp[index]
            self.t[v].remove(index)
        self.mp[index] = number
        self.t[number].add(index)

    def find(self, number: int) -> int:
        s = self.t[number]
        return s[0] if s else -1


# Your NumberContainers object will be instantiated and called as such:
# obj = NumberContainers()
# obj.change(index,number)
# param_2 = obj.find(number)
