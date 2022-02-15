class FirstUnique:
    def __init__(self, nums: List[int]):
        self.counter = OrderedDict()
        self.unique_nums = OrderedDict()
        for num in nums:
            self.counter[num] = self.counter.get(num, 0) + 1
        for k, v in self.counter.items():
            if v == 1:
                self.unique_nums[k] = 1

    def showFirstUnique(self) -> int:
        if len(self.unique_nums) == 0:
            return -1
        for k in self.unique_nums.keys():
            return k

    def add(self, value: int) -> None:
        if value not in self.counter:
            self.counter[value] = 1
            self.unique_nums[value] = 1
        else:
            self.counter[value] += 1
            if value in self.unique_nums:
                self.unique_nums.pop(value)


# Your FirstUnique object will be instantiated and called as such:
# obj = FirstUnique(nums)
# param_1 = obj.showFirstUnique()
# obj.add(value)
