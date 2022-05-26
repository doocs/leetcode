class RandomizedSet:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.a = []
        self.m = {}

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        """
        if val in self.m:
            return False
        self.m[val] = len(self.a)
        self.a.append(val)
        return True

    def remove(self, val: int) -> bool:
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        """
        if val in self.m:
            idx = self.m[val]
            self.a[idx], self.a[-1] = self.a[-1], self.a[idx]
            self.m[self.a[idx]] = idx
            self.a.pop()
            del self.m[val]
            return True
        return False

    def getRandom(self) -> int:
        """
        Get a random element from the set.
        """
        return random.choice(self.a)


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
