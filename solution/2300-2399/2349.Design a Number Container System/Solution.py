class NumberContainers:
    def __init__(self):
        self.d = {}
        self.g = defaultdict(SortedSet)

    def change(self, index: int, number: int) -> None:
        if index in self.d:
            old_number = self.d[index]
            self.g[old_number].remove(index)
        self.d[index] = number
        self.g[number].add(index)

    def find(self, number: int) -> int:
        ids = self.g[number]
        return ids[0] if ids else -1


# Your NumberContainers object will be instantiated and called as such:
# obj = NumberContainers()
# obj.change(index,number)
# param_2 = obj.find(number)
