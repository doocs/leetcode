class TwoSum:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.counter = Counter()

    def add(self, number: int) -> None:
        """
        Add the number to an internal data structure..
        """
        self.counter[number] += 1

    def find(self, value: int) -> bool:
        """
        Find if there exists any pair of numbers which sum is equal to the value.
        """
        for num in self.counter.keys():
            other = value - num
            if other in self.counter:
                if other != num:
                    return True
                if other == num and self.counter[num] > 1:
                    return True
        return False


# Your TwoSum object will be instantiated and called as such:
# obj = TwoSum()
# obj.add(number)
# param_2 = obj.find(value)
