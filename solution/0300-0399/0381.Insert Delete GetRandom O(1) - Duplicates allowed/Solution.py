class RandomizedCollection:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.m = {}
        self.l = []

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
        """
        idx_set = self.m.get(val, set())
        idx_set.add(len(self.l))
        self.m[val] = idx_set
        self.l.append(val)
        return len(idx_set) == 1

    def remove(self, val: int) -> bool:
        """
        Removes a value from the collection. Returns true if the collection contained the specified element.
        """
        if val not in self.m:
            return False
        idx_set = self.m[val]
        idx = list(idx_set)[0]
        last_idx = len(self.l) - 1
        self.l[idx] = self.l[last_idx]
        idx_set.remove(idx)

        last_idx_set = self.m[self.l[last_idx]]
        if last_idx in last_idx_set:
            last_idx_set.remove(last_idx)
        if idx < last_idx:
            last_idx_set.add(idx)
        if not idx_set:
            self.m.pop(val)
        self.l.pop()
        return True

    def getRandom(self) -> int:
        """
        Get a random element from the collection.
        """
        return -1 if len(self.l) == 0 else random.choice(self.l)


# Your RandomizedCollection object will be instantiated and called as such:
# obj = RandomizedCollection()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
