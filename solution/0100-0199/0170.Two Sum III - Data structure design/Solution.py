class TwoSum:
    def __init__(self):
        self.cnt = Counter()

    def add(self, number: int) -> None:
        self.cnt[number] += 1

    def find(self, value: int) -> bool:
        for x, v in self.cnt.items():
            y = value - x
            if y in self.cnt:
                if x != y or v > 1:
                    return True
        return False


# Your TwoSum object will be instantiated and called as such:
# obj = TwoSum()
# obj.add(number)
# param_2 = obj.find(value)
